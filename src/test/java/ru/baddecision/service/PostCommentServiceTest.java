package ru.baddecision.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.baddecision.model.Post;
import ru.baddecision.model.PostComment;
import ru.baddecision.repository.PostCommentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostCommentServiceTest {

    @Mock
    private PostCommentRepository postCommentRepository;
    @InjectMocks
    private PostCommentService postCommentService;
    private PostComment testPostComment;

    @BeforeEach
    void prepare() {
        Post testPost = Post.builder()
                .id(1L)
                .name("Test name")
                .text("Test text")
                .tags(List.of("tag"))
                .build();
        testPostComment = PostComment.builder()
                .id(1L)
                .text("Test comment")
                .postId(testPost.getId())
                .build();
    }

    @Test
    void getById_success() {
        doReturn(testPostComment).when(postCommentRepository).getById(testPostComment.getId());
        PostComment result = postCommentService.getById(testPostComment.getId());
        assertEquals(testPostComment, result);
    }

    @Test
    void getById_returnNull() {
        doReturn(null).when(postCommentRepository).getById(testPostComment.getId());
        PostComment result = postCommentService.getById(testPostComment.getId());
        assertNull(result);
    }

    @Test
    void createPostComment_success() {
        doReturn(testPostComment).when(postCommentRepository).create(testPostComment);
        PostComment result = postCommentService.createPostComment(testPostComment);
        assertEquals(testPostComment, result);
        assertNotNull(result.getCreatedAt());
        verify(postCommentRepository, times(1)).create(testPostComment);
    }

    @Test
    void updatePostComment_success() {
        doNothing().when(postCommentRepository).update(testPostComment);
        postCommentService.updatePostComment(testPostComment);
        verify(postCommentRepository, times(1)).update(testPostComment);
    }

    @Test
    void deletePostComment_success() {
        doNothing().when(postCommentRepository).delete(testPostComment.getId());
        postCommentService.deletePostComment(testPostComment.getId());
        verify(postCommentRepository, times(1)).delete(testPostComment.getId());
    }
}