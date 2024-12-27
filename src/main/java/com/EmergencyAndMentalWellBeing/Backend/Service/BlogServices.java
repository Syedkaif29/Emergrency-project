package com.EmergencyAndMentalWellBeing.Backend.Service;

import com.EmergencyAndMentalWellBeing.Backend.Model.Blog;
import com.EmergencyAndMentalWellBeing.Backend.Repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogServices {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Long id, Blog blogDetails) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        blog.setTitle(blogDetails.getTitle());
        blog.setContent(blogDetails.getContent());
        blog.setDate(blogDetails.getDate());
        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
