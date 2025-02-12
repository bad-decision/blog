package ru.baddecision.blog.web.mapper;

import org.springframework.stereotype.Component;
import ru.baddecision.blog.model.PostComment;
import ru.baddecision.blog.web.dto.PostCommentCreateUpdateDto;

@Component
public class PostCommentMapper {
    public PostComment toPostComment(PostCommentCreateUpdateDto dto) {
        return PostComment.builder()
                .id(dto.getId())
                .text(dto.getText())
                .postId(dto.getPostId())
                .build();
    }
}
