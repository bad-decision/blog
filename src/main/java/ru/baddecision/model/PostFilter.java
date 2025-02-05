package ru.baddecision.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostFilter {
    private Long page;
    private Long limit;
    private String tag;
}
