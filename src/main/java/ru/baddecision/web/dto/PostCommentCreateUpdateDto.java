package ru.baddecision.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentCreateUpdateDto {
    private Long id;
    private Long postId;
    private String text;
}
