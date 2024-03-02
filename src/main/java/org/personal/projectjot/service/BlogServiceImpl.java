package org.personal.projectjot.service;

import org.personal.projectjot.entities.Blog;
import org.personal.projectjot.exception.BlogNotFoundException;
import org.personal.projectjot.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    public BlogServiceImpl(BlogRepository blogRepository) { this.blogRepository = blogRepository; }

    @Override
    public Blog getBlogByTitle(String blogTitle) {
        return blogRepository
                .findByTitle(blogTitle)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with name " + blogTitle));
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository
                .findById(id)
                .orElseThrow(BlogNotFoundException::new);
    }

    @Override
    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {

        Blog updatedBlog = blogRepository.findById(id)
                .orElseThrow(BlogNotFoundException::new);

        updatedBlog.setTitle(blog.getTitle());
        updatedBlog.setParagraphs(blog.getParagraphs());
        updatedBlog.setThumbnailLink(blog.getThumbnailLink());
        updatedBlog.setSelfReferenceLink(blog.getSelfReferenceLink());

        return blogRepository.save(updatedBlog);
    }

    @Override
    public Boolean deleteBlog(Long id) {
        if (!blogRepository.existsById(id)) return false;

        blogRepository.deleteById(id);
        return true;
    }
}
