package ru.baddecision.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.baddecision.model.PostComment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class JdbcPostCommentMapper implements RowMapper<PostComment> {
    @Override
    public PostComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PostComment.builder()
                .id(rs.getLong("id"))
                .text(rs.getString("text_"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .postId(rs.getLong("post_id"))
                .build();
    }
}
