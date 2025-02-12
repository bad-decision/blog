package ru.baddecision.blog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.baddecision.blog.model.Post;
import ru.baddecision.blog.model.PostFilter;
import ru.baddecision.blog.repository.PostRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostService postService;
    private static final Long POST_ID = 1L;
    private Post testPost;

    @BeforeEach
    void prepare() {
        testPost = Post.builder()
                .id(POST_ID)
                .name("Test name")
                .text("Test text")
                .tags(List.of("tag"))
                .build();
    }

    @Test
    void getPostById_success() {
        doReturn(testPost).when(postRepository).getBy(testPost.getId());
        Post result = postService.getPostBy(POST_ID);
        assertEquals(testPost, result);
    }

    @Test
    void getPostByFilter_success() {
        PostFilter postFilter = PostFilter.builder().limit(1L).page(1L).build();
        doReturn(List.of(testPost)).when(postRepository).getBy(postFilter);
        List<Post> result = postService.getPostBy(postFilter);
        assertEquals(1, result.size());
        assertEquals(testPost, result.get(0));
    }

    @Test
    void createPost() {
        doReturn(testPost).when(postRepository).create(testPost);
        Post result = postService.createPost(testPost, null);
        assertEquals(testPost, result);
        verify(postRepository, times(1)).create(testPost);
    }

    @Test
    void updatePost() {
        doNothing().when(postRepository).update(testPost);
        doReturn(testPost).when(postRepository).getBy(testPost.getId());
        postService.updatePost(testPost, null);
        verify(postRepository, times(1)).update(testPost);
    }

    @Test
    void likePost() {
        doNothing().when(postRepository).likePost(POST_ID);
        postService.likePost(POST_ID);
        verify(postRepository, times(1)).likePost(POST_ID);
    }

    @Test
    void deletePost() {
        doNothing().when(postRepository).delete(POST_ID);
        postService.deletePost(POST_ID);
        verify(postRepository, times(1)).delete(POST_ID);
    }
}