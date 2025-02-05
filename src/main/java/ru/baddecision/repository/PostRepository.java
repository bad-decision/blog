package ru.baddecision.repository;

import ru.baddecision.model.Post;
import ru.baddecision.model.PostFilter;

import java.util.List;

public interface PostRepository {
    List<Post> getBy(PostFilter filter);

    Post getBy(Long id);

    void create(Post post);

    void update(Post post);

    void delete(Long id);

    void likePost(Long id);
}