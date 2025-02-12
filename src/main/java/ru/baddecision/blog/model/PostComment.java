package ru.baddecision.blog.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostComment {
    private Long id;
    private LocalDateTime createdAt;
    private String text;
    private Long postId;
}