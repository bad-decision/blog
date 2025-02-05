package ru.baddecision.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.baddecision.model.Post;
import ru.baddecision.model.PostFilter;
import ru.baddecision.service.PostService;
import ru.baddecision.web.dto.PostCreateUpdateDto;
import ru.baddecision.web.mapper.PostMapper;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
@Slf4j
@Validated
@RequiredArgsConstructor
public class PostController {

    private final PostMapper postMapper;
    private final PostService service;
    @Value("${blog.defaultPageSize}")
    private Long defaultPageSize;

    @GetMapping
    public String getList(@RequestParam Optional<Long> page,
                          @RequestParam Optional<Long> limit,
                          @RequestParam Optional<String> tag,
                          Model model) {
        PostFilter filter = PostFilter.builder()
                .page(page.orElse(1L))
                .limit(limit.orElse(defaultPageSize))
                .tag(tag.orElse(null))
                .build();
        List<Post> posts = service.getBy(filter);
        model.addAttribute("posts", posts);
        model.addAttribute("filter", filter);
        return "posts";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id,
                          Model model) {
        Post post = service.getBy(id);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping
    public String create(@ModelAttribute PostCreateUpdateDto dto) {
        service.create(postMapper.toPost(dto));
        return "redirect:/posts";
    }

    @PostMapping(value = "/{id}", params = "_method=patch")
    public String update(@ModelAttribute PostCreateUpdateDto dto) {
        service.update(postMapper.toPost(dto));
        return "redirect:/posts/" + dto.getId();
    }

    @PostMapping(value = "/{id}", params = "_method=delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/posts";
    }

    @PostMapping(value = "/{id}/like")
    public String likePost(@PathVariable(name = "id") Long id) {
        service.likePost(id);
        return "redirect:/posts/" + id;
    }
}