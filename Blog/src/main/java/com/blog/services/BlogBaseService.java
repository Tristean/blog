package com.blog.services;

import java.util.List;

import com.blog.dto.output.BlogDetails;
import com.blog.entity.Blog;
import com.blog.service.base.BaseService;

public interface BlogBaseService extends BaseService<Blog> {
	public void createBlog(Blog blog,Integer userId);
	
	public void updateBlog(Blog blog,Integer userId);
	
	public void deleteBlog(Integer blogId,Integer userId);
	
	public List<BlogDetails>getUserBlogList(Integer userId);
	
	public String getContext(Integer id);
}
