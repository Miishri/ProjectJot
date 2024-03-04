package org.personal.projectjot.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.personal.projectjot.entities.Blog;
import org.personal.projectjot.repository.BlogRepository;
import org.personal.projectjot.service.BlogService;
import org.personal.projectjot.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogService blogService;

    @Autowired
    BlogServiceImpl blogServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    private final String key = "&h3shDjqAo7FyG7BMW@QyUF9F8JyW@QZVQmjLd";

    @BeforeEach
    @Transactional
    void setUp() {
        blogRepository.save(
                Blog.builder()
                        .title("Understanding Spring in-Depth")
                        .paragraphs("To really understand what Spring does under the hood is quite complex...")
                        .selfReferenceLink("https")
                        .thumbnailLink("https")
                        .build()
        );
    }

    @AfterEach
    void tearDown() {
        blogRepository.deleteAll();
        blogRepository.flush();
    }

    @Test
    void testFindBlogByTitle() throws Exception {
        String testTitle = "Understanding Spring in-Depth";

        mockMvc.perform(
                get(BlogController.BLOG_PATH).param("blogTitle", testTitle)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("KEY", key))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(testTitle)));
    }


    @Test
    void testFindBlogsById() throws Exception {
        Long testBlogId = blogRepository.findAll().get(0).getId();

        mockMvc.perform(
                get(BlogController.BLOG_PATH + "/{id}", testBlogId)
                        .accept(MediaType.APPLICATION_JSON)
                        .header("KEY", key))
                .andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(testBlogId.intValue())));
    }

    @Test
    void testFindAllBlogs() throws Exception {

        mockMvc.perform(
                get(BlogController.BLOG_PATH + "s")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("KEY", key))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()", is(1)));
    }


    @Test
    @Transactional
    void testCreateNewBlog() throws Exception{
        Blog testBlog = Blog.builder()
                .title("This is a test blog")
                .paragraphs("I am currently testing the blog controller save endpoint")
                .selfReferenceLink("https")
                .thumbnailLink("https")
                .build();

        mockMvc.perform(
                post(BlogController.BLOG_PATH + "/new")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBlog))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("KEY", key))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    void testUpdateBlogById() throws Exception {
        Blog blog = blogRepository.findAll().get(0);
        Blog updatedBlog = Blog.builder()
                .title("THIS IS A TEST FOR UPDATING BLOG TITLE")
                .paragraphs(blog.getParagraphs())
                .thumbnailLink(blog.getThumbnailLink())
                .selfReferenceLink(blog.getSelfReferenceLink())
                .build();

        mockMvc.perform(
                post(BlogController.BLOG_PATH + "/update/{id}", blog.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBlog))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("KEY", key))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void testDeleteBlogById() throws Exception {
        Blog blog = blogRepository.findAll().get(0);

        mockMvc.perform(
                delete(BlogController.BLOG_PATH + "/delete/{id}", blog.getId())
                        .header("KEY", key))
                .andExpect(status().isAccepted());
    }
}
































