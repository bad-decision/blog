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

    public List<Post> getPostBy(PostFilter filter) {
        return postRepository.getBy(filter);
    }

    public Post getPostBy(Long id) {
        return postRepository.getBy(id);
    }

    public void createPost(Post post) {
        postRepository.create(post);
    }

    public void updatePost(Post post) {
        postRepository.update(post);
    }

    public void likePost(Long id) {
        postRepository.likePost(id);
    }

    public void deletePost(Long id) {
        postRepository.delete(id);
    }
}