package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.blog.dto.output.BlogDetails;
import com.blog.entity.Blog;
import com.blog.mapper.base.IBaseMapper;

public interface BlogMapper extends IBaseMapper<Blog> {
	public void deleteByIdAndUserId(@Param("userid")Integer userid,@Param("id")Integer id);
	
	public List<BlogDetails>getUserDetails(Integer userId);
}
