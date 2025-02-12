package ru.baddecision.blog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.baddecision.blog.model.Post;
import ru.baddecision.blog.model.PostFilter;
import ru.baddecision.blog.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FileService fileService;

    public List<Post> getPostBy(PostFilter filter) {
        return postRepository.getBy(filter);
    }

    public Post getPostBy(Long id) {
        return postRepository.getBy(id);
    }

    public Post createPost(Post post, MultipartFile file) {
        if (file != null) {
            post.setImageName(fileService.saveFile(file));
        }
        return postRepository.create(post);
    }

    public void updatePost(Post post, MultipartFile file) {
        Post existPost = getPostBy(post.getId());
        existPost.setTags(post.getTags());
        existPost.setName(post.getName());
        existPost.setText(post.getText());
        if (file != null) {
            existPost.setImageName(fileService.saveFile(file));
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