package ru.baddecision.blog.repository;

import ru.baddecision.blog.model.Post;
import ru.baddecision.blog.model.PostFilter;

import java.util.List;

public interface PostRepository {
    List<Post> getBy(PostFilter filter);

    Post getBy(Long id);

    Post create(Post post);

    void update(Post post);

    void delete(Long id);

    void likePost(Long id);
}