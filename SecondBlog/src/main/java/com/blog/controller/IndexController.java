package com.blog.controller;
/*
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.ajax.AjaxResult;
import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.util.LuceneIndexForBlog;
import com.blog.util.LuceneIndexForUser;
import com.blog.util.Page;

@Controller("IndexController")
public class IndexController {
	private LuceneIndexForUser userIndex=new LuceneIndexForUser();
	private LuceneIndexForBlog blogIndex=new LuceneIndexForBlog();
	
	@RequestMapping(value="/QueryUser",method=RequestMethod.POST)
	public @ResponseBody AjaxResult searchUser(String q,int pageSize,int index){
		List<User>users=userIndex.search(q);
		int start=(index-1)*pageSize;
		int end=index*pageSize;
		List<User>queryUser;
		
		if(end>users.size()){
			end=users.size()-1;
		}
		
		queryUser=users.subList(start, end);
		Page<User>pageUser=new Page<User>();
		pageUser.setPageData(queryUser);
		
		return AjaxResult.getOK(pageUser);
	}
	
	@RequestMapping(value="/QueryBlog",method=RequestMethod.POST)
	public @ResponseBody AjaxResult searchBlog(String q,int pageSize,int index){
		List<Blog>blogs=blogIndex.search(q);
		int start=(index-1)*pageSize;
		int end=index*pageSize;
		List<Blog>queryBlog;
		
		if(end>blogs.size()){
			end=blogs.size()-1;
		}
		
		queryBlog=blogs.subList(start, end);
		Page<Blog>pageBlog=new Page<Blog>();
		pageBlog.setPageData(queryBlog);
		
		return AjaxResult.getOK(pageBlog);
	}
}*/
