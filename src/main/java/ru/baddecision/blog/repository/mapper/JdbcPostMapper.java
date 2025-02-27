package ru.baddecision.blog.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.baddecision.blog.model.Post;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Post.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .text(rs.getString("text_"))
                .imageName(rs.getString("image_name"))
                .likeCount(rs.getLong("like_count"))
                .tags(rs.getArray("tags") != null ?
                        List.of((String[])(rs.getArray("tags").getArray())) :
                        new ArrayList<>())
                .build();
    }
}
