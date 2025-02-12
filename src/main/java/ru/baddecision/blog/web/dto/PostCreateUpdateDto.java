package ru.baddecision.blog.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateUpdateDto {
    private Long id;
    private String name;
    private String text;
    private MultipartFile file;
    private String tags;
}
