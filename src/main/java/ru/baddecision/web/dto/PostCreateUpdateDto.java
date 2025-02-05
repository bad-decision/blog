package ru.baddecision.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateUpdateDto {
    private Long id;
    private String name;
    private String text;
    private String tags;
}
