package ru.baddecision.blog.integration.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.baddecision.blog.integration.IntegrationTestBase;
import ru.baddecision.blog.model.PostComment;
import ru.baddecision.blog.repository.PostCommentRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PostCommentRepositoryIT extends IntegrationTestBase {

    @Autowired
    private PostCommentRepository postCommentRepository;
    private static final Long POST_ID = 1L;
    private static final Long POST_COMMENT_ID = 2L;

    @Test
    void getPostCommentById_mustReturnPostComment() {
        PostComment postComment = postCommentRepository.getById(POST_COMMENT_ID);
        assertEquals(POST_COMMENT_ID, postComment.getId());
        assertEquals("Small comment 2", postComment.getText());
    }

    @Test
    void createPostComment_mustReturnNewComment() {
        PostComment newPostComment = PostComment.builder()
                .text("New text")
                .postId(POST_ID)
                .createdAt(LocalDateTime.now())
                .build();
        PostComment createdPostComment = postCommentRepository.create(newPostComment);

        assertNotNull(createdPostComment);
        assertNotNull(createdPostComment.getCreatedAt());
        assertEquals(newPostComment.getText(), createdPostComment.getText());
        assertEquals(newPostComment.getPostId(), createdPostComment.getPostId());
    }

    @Test
    void updatePostComment_mustCorrectlyUpdateComment() {
        PostComment postComment = postCommentRepository.getById(POST_COMMENT_ID);
        postComment.setText("Updated text");
        postCommentRepository.update(postComment);
        PostComment updatedPostComment = postCommentRepository.getById(POST_COMMENT_ID);

        assertNotNull(updatedPostComment);
        assertEquals(postComment.getId(), updatedPostComment.getId());
        assertEquals(postComment.getText(), updatedPostComment.getText());
        assertEquals(postComment.getPostId(), updatedPostComment.getPostId());
    }

    @Test
    void deletePost() {
        PostComment postComment = postCommentRepository.getById(POST_COMMENT_ID);
        assertNotNull(postComment);
        postCommentRepository.delete(postComment.getId());
        postComment = postCommentRepository.getById(POST_COMMENT_ID);
        assertNull(postComment);
    }
}