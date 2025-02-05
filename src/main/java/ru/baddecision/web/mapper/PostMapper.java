package ru.baddecision.web.mapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.baddecision.model.Post;
import ru.baddecision.web.dto.PostCreateUpdateDto;

import java.util.Arrays;
import java.util.List;

@Component
public class PostMapper {

    public Post toPost(PostCreateUpdateDto dto) {
        List<String> tags = StringUtils.isNotEmpty(dto.getTags()) ?
                Arrays.stream(dto.getTags().split(";")).map(String::trim).toList() :
                null;
        return Post.builder()
                .id(dto.getId())
                .name(dto.getName())
                .text(dto.getText())
                .tags(tags)
                .build();
    }
}
