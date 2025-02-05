package ru.baddecision.web.mapper;

import org.springframework.stereotype.Component;
import ru.baddecision.model.PostComment;
import ru.baddecision.web.dto.PostCommentCreateUpdateDto;

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
