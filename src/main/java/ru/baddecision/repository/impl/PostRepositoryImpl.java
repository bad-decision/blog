package ru.baddecision.repository.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;
import ru.baddecision.model.Post;
import ru.baddecision.model.PostComment;
import ru.baddecision.model.PostFilter;
import ru.baddecision.repository.PostCommentRepository;
import ru.baddecision.repository.PostRepository;
import ru.baddecision.repository.mapper.JdbcPostMapper;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JdbcTemplate jdbcTemplate;
    private final JdbcPostMapper jdbcPostMapper = new JdbcPostMapper();
    private final PostCommentRepository postCommentRepository;

    @Override
    public List<Post> getBy(PostFilter filter) {
        long offset = (filter.getPage() - 1) * filter.getLimit();
        boolean filterByTag = StringUtils.isNotBlank(filter.getTag());
        String getQuery =  filterByTag ?
                "SELECT id, name, text_, image_name, tags, like_count FROM posts WHERE ? = ANY(tags) ORDER BY id desc LIMIT ? OFFSET ?" :
                "SELECT id, name, text_, image_name, tags, like_count FROM posts ORDER BY id desc LIMIT ? OFFSET ?";
        List<Object> args = filterByTag ?
                List.of(filter.getTag(), filter.getLimit(), offset) :
                List.of(filter.getLimit(), offset);
        List<Post> posts = jdbcTemplate.query(getQuery, jdbcPostMapper, args.toArray());
        List<Long> postIds = posts.stream().map(Post::getId).toList();
        List<PostComment> postComments = postCommentRepository.getByPostId(postIds);
        posts.forEach(post -> post.setComments(postComments.stream()
                .filter(postComment -> postComment.getPostId().equals(post.getId()))
                .toList()));
        return posts;
    }

    @Override
    public Post getBy(Long id) {
        String findByIdQuery = "SELECT id, name, p.text_ as text_, image_name, tags, like_count FROM posts p WHERE id=?";
        Post post = jdbcTemplate.query(findByIdQuery, jdbcPostMapper, id).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        post.setComments(postCommentRepository.getByPostId(id));
        return post;
    }

    @Override
    public void create(Post post) {
        String insertPostQuery = "INSERT INTO posts (name, text_, image_name, tags, like_count) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(connection -> {
            Array array = connection.createArrayOf("VARCHAR", post.getTags().toArray());
            PreparedStatement ps = connection.prepareStatement(insertPostQuery);
            ps.setString(1, post.getName());
            ps.setString(2, post.getText());
            ps.setString(3, post.getImageName());
            ps.setArray(4, mapToSqlArray(connection, post.getTags()));
            ps.setLong(5, post.getLikeCount());
            return ps;
        });
    }

    @Override
    public void update(Post post) {
        String updatePostQuery = "UPDATE posts SET name=?, text_=?, image_name=?, tags=?, like_count=? WHERE id=?";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(updatePostQuery);
            ps.setString(1, post.getName());
            ps.setString(2, post.getText());
            ps.setString(3, post.getImageName());
            ps.setArray(4, mapToSqlArray(connection, post.getTags()));
            ps.setLong(5, post.getLikeCount());
            ps.setLong(6, post.getId());
            return ps;
        });
    }

    @Override
    public void delete(Long id) {
        String deleteByIdQuery = "DELETE FROM posts WHERE id=?";
        jdbcTemplate.update(deleteByIdQuery, id);
    }

    @Override
    public void likePost(Long id) {
        String updatePostQuery = "UPDATE posts SET like_count = 1 + (SELECT like_count FROM posts WHERE id=?) WHERE id=?";
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(updatePostQuery, Types.BIGINT, Types.BIGINT);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(id, id));
        jdbcTemplate.update(psc);
    }

    private Array mapToSqlArray(Connection connection,  List<String> items) throws SQLException {
        return connection.createArrayOf("VARCHAR", items.toArray());
    }
}