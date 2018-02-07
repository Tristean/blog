package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.blog.dto.output.UserDetails;
import com.blog.entity.User;
import com.blog.mapper.base.IBaseMapper;

public interface UserMapper extends IBaseMapper<User>{
	public UserDetails getUserDetailsById(Integer id);
	
	public List<User> getUserListByLevel(@Param("level")Integer level,@Param("username")String username);
	
	public void deleteByIdList(@Param("idList")List<Integer>idLIst);
	
	public Integer getUserNameCount(String userName);
	
	public User getUser(String userName);
	
	public Integer getUserID(String userName);
}
