package ru.baddecision.repository;

import ru.baddecision.model.PostComment;

import java.util.List;

public interface PostCommentRepository {
    List<PostComment> getByPostId(Long postId);

    List<PostComment> getByPostId(List<Long> postIds);

    void create(PostComment postComment);

    void update(PostComment postComment);

    void delete(Long id);
}