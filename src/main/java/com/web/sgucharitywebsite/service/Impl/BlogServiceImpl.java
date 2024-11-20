package com.web.sgucharitywebsite.service.Impl;

import com.web.sgucharitywebsite.dto.BlogDto;
import com.web.sgucharitywebsite.dto.ProjectDto;
import com.web.sgucharitywebsite.entity.Blog;
import com.web.sgucharitywebsite.entity.Category;
import com.web.sgucharitywebsite.entity.Project;
import com.web.sgucharitywebsite.repository.BlogRepository;
import com.web.sgucharitywebsite.repository.CategoryRepository;
import com.web.sgucharitywebsite.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    private CategoryRepository categoryRepository;
    private BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(CategoryRepository categoryRepository, BlogRepository blogRepository) {
        this.categoryRepository = categoryRepository;
        this.blogRepository = blogRepository;
    }
    @Override
    public List<BlogDto> findAllBlogs() {
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map((blog) -> mapToBlogDto(blog)).collect(Collectors.toList());
    }
    @Override
    public void createBlog(BlogDto blogDto) {
        Category category = categoryRepository.findById(blogDto.getCategoryId()).get();
        Blog blog = mapToBlog(blogDto);
        blog.setCategory(category);
        blogRepository.save(blog);
    }
    @Override
    public void updateBlog(BlogDto blogDto) {
        Category category = categoryRepository.findById(blogDto.getCategoryId()).get();
        Blog blog = mapToBlog(blogDto);
        blog.setCategory(category);
        blogRepository.save(blog);
    }

    @Override
    public BlogDto findBlogById(long blogId) {
        Blog blog = blogRepository.findById(blogId).get();
        return mapToBlogDto(blog);
    }

    @Override
    public void deleteBlogById(long blogId) {
        blogRepository.deleteById(blogId);
    }

    private Blog mapToBlog(BlogDto blogDto) {
        return Blog.builder()
                .id(blogDto.getId())
                .name(blogDto.getName())
                .content(blogDto.getContent())
                .status(blogDto.getStatus())
                .createOn(blogDto.getCreateOn())
                .updateOn(blogDto.getUpdateOn())
                .build();
    }

    private BlogDto mapToBlogDto(Blog blog) {
        return BlogDto.builder()
                .id(blog.getId())
                .name(blog.getName())
                .content(blog.getContent())
                .status(blog.getStatus())
                .createOn(blog.getCreateOn())
                .updateOn(blog.getUpdateOn())
                .categoryId(blog.getCategory().getId())
                .build();
    }
}
