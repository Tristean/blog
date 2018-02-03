package com.blog.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.blog.dto.output.BlogDetails;
import com.blog.entity.Blog;
import com.blog.exception.MyException;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.UserRoleMapper;
import com.blog.mapper.base.IBaseMapper;
import com.blog.service.base.BaseServiceImp;
import com.blog.services.BlogBaseService;
@Service
public class BlogBaseServiceImp extends BaseServiceImp<Blog> implements BlogBaseService{
	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public void createBlog(Blog blog, Integer userId) {
		// TODO Auto-generated method stub
		blog.setId(null);
		blog.setUserId(userId);
		blogMapper.insertSelective(blog);
	}

	@Override
	public void updateBlog(Blog blog, Integer userId) {
		// TODO Auto-generated method stub
		if(userId.equals(blog.getUserId()))
			blogMapper.updateByPrimaryKey(blog);
		else
			throw new MyException("只能修改自己的博客");
	}

	@Override
	public void deleteBlog(Integer blogId, Integer userId) {
		// TODO Auto-generated method stub
		List<Integer>roleIdList=userRoleMapper.getRoleIdListByUserId(userId);
		if(roleIdList.contains(1))
			blogMapper.deleteByPrimaryKey(blogId);
		else
			blogMapper.deleteByIdAndUserId(userId, blogId);
	}

	@Override
	public List<BlogDetails> getUserBlogList(Integer userId) {
		// TODO Auto-generated method stub
		return blogMapper.getUserBlogDetails(userId);
	}

	@Override
	public IBaseMapper<Blog> getBaseMapper() {
		// TODO Auto-generated method stub
		return blogMapper;
	}
	
	public String getContext(Integer id){
		return blogMapper.getContext(id);
	}

}
