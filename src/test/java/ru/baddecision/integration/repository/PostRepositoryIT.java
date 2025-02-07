package ru.baddecision.integration.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.baddecision.integration.IntegrationTestBase;
import ru.baddecision.model.Post;
import ru.baddecision.model.PostFilter;
import ru.baddecision.repository.PostRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryIT extends IntegrationTestBase {

    @Autowired
    private PostRepository postRepository;
    private static final Long postId = 1L;

    @Test
    void getPostById_mustReturnPost() {
        Post post = postRepository.getBy(postId);
        assertEquals(postId, post.getId());
        assertEquals("Text 1", post.getText());
        assertEquals("Title 1", post.getName());
        assertEquals(2, post.getTags().size());
    }

    @Test
    void getPostByFilter_mustReturnPosts() {
        PostFilter filter = PostFilter.builder()
                .page(1L)
                .limit(10L)
                .tag("tag1")
                .build();
        List<Post> posts = postRepository.getBy(filter);
        assertEquals(5, posts.size());

        filter.setLimit(1L);
        posts = postRepository.getBy(filter);
        assertEquals(1, posts.size());

        filter.setPage(100L);
        posts = postRepository.getBy(filter);
        assertEquals(0, posts.size());
    }

    @Test
    void createPost_mustReturnNewPost() {
        Post newPost = Post.builder()
                .text("New text")
                .name("New name")
                .tags(List.of("newTag1, newTag2"))
                .build();
        Post createdPost = postRepository.create(newPost);

        assertNotNull(createdPost);
        assertEquals(newPost.getName(), createdPost.getName());
        assertEquals(newPost.getText(), createdPost.getText());
        assertEquals(newPost.getLikeCount(), createdPost.getLikeCount());
        assertEquals(newPost.getTags(), createdPost.getTags());
    }

    @Test
    void updatePost_mustCorrectlyUpdatePost() {
        Post post = postRepository.getBy(postId);
        post.setName("Updated name");
        post.setText("Updated text");
        post.setTags(List.of("newTag1, newTag2"));
        postRepository.update(post);
        Post updatedPost = postRepository.getBy(postId);

        assertNotNull(updatedPost);
        assertEquals(post.getId(), updatedPost.getId());
        assertEquals(post.getName(), updatedPost.getName());
        assertEquals(post.getText(), updatedPost.getText());
        assertEquals(post.getLikeCount(), updatedPost.getLikeCount());
        assertEquals(post.getTags(), updatedPost.getTags());
    }

    @Test
    void likePost_mustReturnCorrectLikeCount() {
        Post post = postRepository.getBy(postId);
        long beforeLike = post.getLikeCount();
        postRepository.likePost(postId);
        post = postRepository.getBy(postId);
        assertEquals(beforeLike + 1, post.getLikeCount());
    }

    @Test
    void deletePost_mustReturnNull() {
        Post post = postRepository.getBy(postId);
        assertNotNull(post);
        postRepository.delete(postId);
        post = postRepository.getBy(postId);
        assertNull(post);
    }
}