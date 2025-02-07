package ru.baddecision.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.baddecision.model.PostComment;
import ru.baddecision.repository.PostCommentRepository;
import ru.baddecision.repository.mapper.JdbcPostCommentMapper;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostCommentRepositoryImpl implements PostCommentRepository {

    private final JdbcTemplate jdbcTemplate;
    private final JdbcPostCommentMapper jdbcPostCommentMapper = new JdbcPostCommentMapper();

    @Override
    public PostComment getById(Long id) {
        String findByIdQuery = "SELECT id, text_, created_at, post_id FROM post_comments WHERE id=?";
        return jdbcTemplate.query(findByIdQuery, jdbcPostCommentMapper, id).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<PostComment> getByPostId(Long postId) {
        String findByIdQuery = "SELECT id, text_, created_at, post_id FROM post_comments WHERE post_id=?";
        return jdbcTemplate.query(findByIdQuery, jdbcPostCommentMapper, postId);
    }

    @Override
    public List<PostComment> getByPostId(List<Long> postIds) {
        if (CollectionUtils.isEmpty(postIds)) {
            return new ArrayList<>();
        }
        String inSql = String.join(",", Collections.nCopies(postIds.size(), "?"));
        String findByIdQuery = String.format("SELECT id, text_, created_at, post_id FROM post_comments WHERE post_id in (%s)", inSql);
        return jdbcTemplate.query(findByIdQuery, jdbcPostCommentMapper, postIds.toArray());
    }

    @Override
    public PostComment create(PostComment postComment) {
        String insertPostCommentQuery = "INSERT INTO post_comments (text_, created_at, post_id) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(insertPostCommentQuery,
                Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(postComment.getText(), postComment.getCreatedAt(),
                postComment.getPostId()));
        pscf.setReturnGeneratedKeys(true);
        jdbcTemplate.update(psc, keyHolder);
        Long id = (Long) (keyHolder.getKeys().get("id"));
        return getById(id);
    }

    @Override
    public void update(PostComment postComment) {
        String updatePostCommentQuery = "UPDATE post_comments SET text_=? WHERE id=?";
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(updatePostCommentQuery, Types.VARCHAR, Types.BIGINT);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(postComment.getText(), postComment.getId()));
        jdbcTemplate.update(psc);
    }

    @Override
    public void delete(Long id) {
        String deleteByIdQuery = "DELETE FROM post_comments WHERE id=?";
        jdbcTemplate.update(deleteByIdQuery, id);
    }
}