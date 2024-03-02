package org.personal.projectjot.service;

import org.personal.projectjot.entities.Blog;

import java.util.List;

public interface BlogService {
    Blog getBlogByTitle(String blogTitle);

    Blog getBlogById(Long id);
    List<Blog> getBlogs();
    Blog createBlog(Blog blog);
    Blog updateBlog(Long id, Blog blog);
    Boolean deleteBlog(Long id);
}
