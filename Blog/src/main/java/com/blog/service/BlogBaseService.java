package com.blog.service;

import java.util.List;

import com.blog.dto.output.BlogDetails;
import com.blog.entity.Blog;

public interface BlogBaseService {
	public void createBlog(Blog blog,Integer userId);
	
	public void updateBlog(Blog blog,Integer userId);
	
	public void deleteBlog(Integer blogId,Integer userId);
	
	public List<BlogDetails>getUserBlogList(Integer userId);
}
