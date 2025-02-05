package ru.baddecision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baddecision.model.PostComment;
import ru.baddecision.repository.PostCommentRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostCommentRepository postCommentRepository;

    public void createPostComment(PostComment postComment) {
        postComment.setCreatedAt(LocalDateTime.now());
        postCommentRepository.create(postComment);
    }

    public void updatePostComment(PostComment postComment) {
        postCommentRepository.update(postComment);
    }

    public void deletePostComment(Long id) {
        postCommentRepository.delete(id);
    }
}