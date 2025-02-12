package ru.baddecision.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baddecision.blog.model.PostComment;
import ru.baddecision.blog.repository.PostCommentRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;

    public PostComment getById(Long id) {
        return postCommentRepository.getById(id);
    }

    public PostComment createPostComment(PostComment postComment) {
        postComment.setCreatedAt(LocalDateTime.now());
        return postCommentRepository.create(postComment);
    }

    public void updatePostComment(PostComment postComment) {
        postCommentRepository.update(postComment);
    }

    public void deletePostComment(Long id) {
        postCommentRepository.delete(id);
    }
}