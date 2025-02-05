package ru.baddecision.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;
import ru.baddecision.model.Post;
import ru.baddecision.model.PostFilter;
import ru.baddecision.repository.PostRepository;
import ru.baddecision.repository.mapper.JdbcPostMapper;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JdbcTemplate jdbcTemplate;
    private final JdbcPostMapper jdbcPostMapper = new JdbcPostMapper();

    @Override
    public List<Post> getBy(PostFilter filter) {
        long offset = (filter.getPage() - 1) * filter.getLimit();
        String findAllQuery = "SELECT id, name, text_, image_path, tags, like_count, comment_count FROM posts LIMIT ? OFFSET ?";
        return jdbcTemplate.query(findAllQuery, jdbcPostMapper, filter.getLimit(), offset);
    }

    @Override
    public Post getBy(Long id) {
        String findByIdQuery = "SELECT id, name, text_, image_path, tags, like_count, comment_count FROM posts WHERE id=?";
        return jdbcTemplate.query(findByIdQuery, jdbcPostMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() ->  new IllegalArgumentException("Post not found"));
    }

    @Override
    public void create(Post post) {
        String insertPostQuery = "INSERT INTO posts (name, text_, image_path, tags, like_count, comment_count) VALUES (?,?,?,?,?,?)";
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(insertPostQuery,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(post.getName(), post.getText(),
                post.getImagePath(), post.getTagsString(), post.getLikeCount(), post.getCommentCount()));
        jdbcTemplate.update(psc);
    }

    @Override
    public void update(Post post) {
        String updatePostQuery = "UPDATE posts SET name=?, text_=?, image_path=?, tags=?, like_count=?, comment_count=? WHERE id=?";
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(updatePostQuery,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.BIGINT, Types.BIGINT, Types.BIGINT);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(post.getName(), post.getText(),
                post.getImagePath(), post.getTagsString(), post.getLikeCount(), post.getCommentCount(), post.getId()));
        jdbcTemplate.update(psc);
    }

    @Override
    public void delete(Long id) {
        String deleteByIdQuery = "DELETE FROM posts WHERE id=?";
        jdbcTemplate.update(deleteByIdQuery, id);
    }
}