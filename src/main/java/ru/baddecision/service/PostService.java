package ru.baddecision.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.baddecision.model.Post;
import ru.baddecision.model.PostFilter;
import ru.baddecision.repository.PostRepository;

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
            String imageName = fileService.saveFile(file);
            post.setImageName(imageName);
        }
        return postRepository.create(post);
    }

    public void updatePost(Post post, MultipartFile file) {
        Post existPost = getPostBy(post.getId());
        existPost.setTags(post.getTags());
        existPost.setName(post.getName());
        existPost.setText(post.getText());
        if (file != null) {
            String imageName = fileService.saveFile(file);
            existPost.setImageName(imageName);
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