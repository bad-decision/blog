package ru.baddecision.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.baddecision.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcPostMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Post.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .text(rs.getString("text_"))
                .imagePath(rs.getString("image_path"))
                .likeCount(rs.getLong("like_count"))
                .tags(List.of((String[])(rs.getArray("tags").getArray())))
                .build();
    }
}
