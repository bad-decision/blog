package ru.baddecision.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;
    private String name;
    private String text;
    private String imagePath;
    private long likeCount;
    private List<String> tags = new ArrayList<>();
    private List<PostComment> comments = new ArrayList<>();
}