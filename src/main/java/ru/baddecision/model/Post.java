package ru.baddecision.model;

import lombok.*;
import org.springframework.util.CollectionUtils;

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
    private Long likeCount;
    private Long commentCount;
    private List<String> tags = new ArrayList<>();

    public String getTagsString() {
        return CollectionUtils.isEmpty(tags) ? null : String.join(";", tags);
    }
}