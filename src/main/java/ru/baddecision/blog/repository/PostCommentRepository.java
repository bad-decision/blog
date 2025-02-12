package ru.baddecision.blog.repository;

import ru.baddecision.blog.model.PostComment;

import java.util.List;

public interface PostCommentRepository {
    PostComment getById(Long id);

    List<PostComment> getByPostId(Long postId);

    List<PostComment> getByPostId(List<Long> postIds);

    PostComment create(PostComment postComment);

    void update(PostComment postComment);

    void delete(Long id);
}