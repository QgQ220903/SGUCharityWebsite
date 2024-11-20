package com.web.sgucharitywebsite.service;

import com.web.sgucharitywebsite.dto.BlogDto;
import com.web.sgucharitywebsite.dto.ProjectDto;

import java.util.List;

public interface BlogService {
    List<BlogDto> findAllBlogs();
    void createBlog(BlogDto blogDto);
    void updateBlog(BlogDto blogDto);
    BlogDto findBlogById(long blogId);
    void deleteBlogById(long blogId);
}
