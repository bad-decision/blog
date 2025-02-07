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

    public Post createPost(Post post) {
        return postRepository.create(post);
    }

    public void updatePost(Post post, boolean needUpdateImage) {
        Post existPost = getPostBy(post.getId());
        existPost.setTags(post.getTags());
        existPost.setName(post.getName());
        existPost.setText(post.getText());
        if (needUpdateImage) {
            existPost.setImageName(post.getImageName());
        }
        postRepository.update(existPost);
    }

    public void likePost(Long id) {
        postRepository.likePost(id);
    }

    public void deletePost(Long id) {
        postRepository.delete(id);
    }
}