package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.ajax.AjaxResult;
import com.blog.dto.output.BlogDetails;
import com.blog.entity.Blog;
import com.blog.exception.MyException;
import com.blog.services.BlogBaseService;
import com.blog.util.StringUtil;

@Controller("blogController")
public class BlogBaseController extends BaseConstroller {
	@Autowired
	private BlogBaseService blogBaseService;
	
	//д����
	
	@RequestMapping(value="/BCreate",method=RequestMethod.POST)
	public @ResponseBody AjaxResult createBlog(@RequestBody Blog blog,HttpServletRequest request){
		Integer userId=getUserIdInJudge(request);
		if(StringUtil.isEmpty(blog.getTitle())||StringUtil.isEmpty(blog.getContext())){
			throw new MyException("������Ϣ��ȫ");
		}
		blogBaseService.createBlog(blog, userId);
		return AjaxResult.getOk();
	}
	
	//ɾ������
	
	@RequestMapping(value="/BDelete",method=RequestMethod.POST)
	public @ResponseBody AjaxResult delete(Integer blogId,Integer userId){
		if(userId!=null||blogId==null)
			throw new MyException("�޷�ɾ��");
		blogBaseService.deleteBlog(blogId, userId);
		return AjaxResult.getOk();
	}
	
	//���²���
	
	@RequestMapping(value="/BUpdate",method=RequestMethod.POST)
	public @ResponseBody AjaxResult update(@RequestBody Blog blog,Integer userId){
		if(userId==null||blog==null)
			throw new MyException("���²���Ϊ��");
		blogBaseService.updateBlog(blog, userId);
		return AjaxResult.getOk();
	}
	
	//��ѯ��д����
	@RequestMapping(value="/BSelect",method=RequestMethod.POST)
	public @ResponseBody AjaxResult select(Integer userId){
		if(userId==null)
			throw new MyException("�û�����Ϊ��");
		List<BlogDetails>blogList=blogBaseService.getUserBlogList(userId);
		return AjaxResult.getOK(blogList);
	}
}
