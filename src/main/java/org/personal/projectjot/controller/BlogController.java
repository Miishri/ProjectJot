package org.personal.projectjot.controller;

import lombok.RequiredArgsConstructor;
import org.personal.projectjot.entities.Blog;
import org.personal.projectjot.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogController {
    public static final String BLOG_PATH = "/blog";
    private final BlogService blogService;

    @GetMapping(BLOG_PATH)
    public ResponseEntity<Blog> findBlogByTitle(@RequestParam String blogTitle) {

        return new ResponseEntity<>(
                blogService.getBlogByTitle(blogTitle),
                HttpStatus.FOUND
        );
    }

    @GetMapping(BLOG_PATH + "/{id}")
    public ResponseEntity<Blog> findBlogById(@PathVariable Long id) {

        return new ResponseEntity<>(
                blogService.getBlogById(id),
                HttpStatus.FOUND
        );
    }

    @GetMapping(BLOG_PATH  + "s")
    public ResponseEntity<List<Blog>> findAllBlogs() {

        return new ResponseEntity<>(
                blogService.getBlogs(),
                HttpStatus.OK
        );
    }

    @PostMapping(BLOG_PATH + "/new")
    public ResponseEntity<Blog> createNewBlog(@RequestBody Blog blog) {

        return new ResponseEntity<>(
                blogService.createBlog(blog),
                HttpStatus.CREATED
        );
    }

    @PutMapping(BLOG_PATH + "/update/{id}")
    public ResponseEntity<Blog> updateBlogById(@PathVariable Long id, @RequestBody Blog blog) {

        return new ResponseEntity<>(
                blogService.updateBlog(id, blog),
                HttpStatus.OK
        );
    }

    @DeleteMapping(BLOG_PATH + "/delete/{id}")
    public ResponseEntity<Void> deleteBlogById(@PathVariable Long id) {

        if (blogService.deleteBlog(id)) {
            return new ResponseEntity<>(
                    HttpStatus.ACCEPTED
            );
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
