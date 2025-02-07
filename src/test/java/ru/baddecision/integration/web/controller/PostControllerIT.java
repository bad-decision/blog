package ru.baddecision.integration.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import ru.baddecision.integration.IntegrationTestBase;
import ru.baddecision.web.controller.PostController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PostControllerIT extends IntegrationTestBase {

    @Autowired
    private PostController postController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(postController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    void getPostList_shouldReturnHtmlWithPosts() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("posts"))
                .andExpect(view().name("posts"));
    }

    @Test
    void getPostById_shouldReturnHtmlWithPosts() throws Exception {
        mockMvc.perform(get("/posts/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("post"))
                .andExpect(view().name("post"));
    }

    @Test
    void createPost_successRedirect() throws Exception {
        mockMvc.perform(post("/posts")
                        .param("text", "Text")
                        .param("name", "Name")
                        .param("tags", "tag1;tag2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));
    }

    @Test
    void updatePost_successRedirect() throws Exception {
        mockMvc.perform(post("/posts/1")
                        .param("_method", "patch")
                        .param("text", "Text")
                        .param("name", "Name")
                        .param("tags", "tag1;tag2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));
    }

    @Test
    void deletePost_successRedirect() throws Exception {
        mockMvc.perform(post("/posts/1")
                        .param("_method", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts"));
    }

    @Test
    void likePost() throws Exception {
        mockMvc.perform(post("/posts/1/like"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));
    }

    @Test
    void createPostComment_successRedirect() throws Exception {
        mockMvc.perform(post("/posts/1/comment")
                        .param("text", "Text"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));
    }

    @Test
    void updatePostComment() throws Exception {
        mockMvc.perform(post("/posts/1/comment/1")
                        .param("_method", "patch")
                        .param("text", "Text"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));
    }

    @Test
    void deletePostComment() throws Exception {
        mockMvc.perform(post("/posts/1/comment/1")
                        .param("_method", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/posts/1"));
    }
}