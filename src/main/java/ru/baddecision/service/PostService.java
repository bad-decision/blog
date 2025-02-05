package ru.baddecision.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.baddecision.model.Post;
import ru.baddecision.model.PostFilter;
import ru.baddecision.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getBy(PostFilter filter) {
        return postRepository.getBy(filter);
    }

    public Post getBy(Long id) {
        return postRepository.getBy(id);
    }

    public void create(Post post) {
        postRepository.create(post);
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public void likePost(Long id) {
        Post post = getBy(id);
        post.setLikeCount(post.getLikeCount() + 1);
        update(post);
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}