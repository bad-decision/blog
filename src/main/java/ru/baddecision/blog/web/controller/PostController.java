package ru.baddecision.blog.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.baddecision.blog.model.Post;
import ru.baddecision.blog.model.PostFilter;
import ru.baddecision.blog.service.PostCommentService;
import ru.baddecision.blog.service.PostService;
import ru.baddecision.blog.web.dto.PostCommentCreateUpdateDto;
import ru.baddecision.blog.web.dto.PostCreateUpdateDto;
import ru.baddecision.blog.web.mapper.PostCommentMapper;
import ru.baddecision.blog.web.mapper.PostMapper;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
@Slf4j
@Validated
@RequiredArgsConstructor
public class PostController {
    @Value("${blog.defaultPageSize}")
    private Long defaultPageSize;
    private final PostMapper postMapper;
    private final PostCommentMapper postCommentMapper;
    private final PostService postService;
    private final PostCommentService postCommentService;

    @GetMapping
    public String getPostList(@RequestParam Optional<Long> page,
                              @RequestParam Optional<Long> limit,
                              @RequestParam Optional<String> tag,
                              Model model) {
        PostFilter filter = PostFilter.builder()
                .page(page.orElse(1L))
                .limit(limit.orElse(defaultPageSize))
                .tag(tag.orElse(""))
                .build();
        List<Post> posts = postService.getPostBy(filter);
        model.addAttribute("posts", posts);
        model.addAttribute("filter", filter);
        return "posts";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable Long id,
                              Model model) {
        Post post = postService.getPostBy(id);
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping
    public String createPost(PostCreateUpdateDto dto) {
        Post post = postMapper.toPost(dto);
        postService.createPost(post, dto.getFile());
        return "redirect:/posts";
    }

    @PostMapping(value = "/{id}", params = "_method=patch")
    public String updatePost(PostCreateUpdateDto dto) {
        Post post = postMapper.toPost(dto);
        postService.updatePost(post, dto.getFile());
        return "redirect:/posts/" + dto.getId();
    }

    @PostMapping(value = "/{id}", params = "_method=delete")
    public String deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    @PostMapping(value = "/{id}/like")
    public String likePost(@PathVariable(name = "id") Long id) {
        postService.likePost(id);
        return "redirect:/posts/" + id;
    }

    @PostMapping(value = "/{postId}/comment")
    public String createPostComment(PostCommentCreateUpdateDto dto) {
        postCommentService.createPostComment(postCommentMapper.toPostComment(dto));
        return "redirect:/posts/" + dto.getPostId();
    }

    @PostMapping(value = "/{postId}/comment/{id}", params = "_method=patch")
    public String updatePostComment(PostCommentCreateUpdateDto dto) {
        postCommentService.updatePostComment(postCommentMapper.toPostComment(dto));
        return "redirect:/posts/" + dto.getPostId();
    }

    @PostMapping(value = "/{postId}/comment/{commentId}", params = "_method=delete")
    public String deletePostComment(@PathVariable(name = "commentId") Long commentId,
                                    @PathVariable(name = "postId") Long postId) {
        postCommentService.deletePostComment(commentId);
        return "redirect:/posts/" + postId;
    }
}