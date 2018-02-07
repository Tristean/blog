package com.blog.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.blog.dto.input.UserEditDetails;
import com.blog.dto.output.UserDetails;
import com.blog.entity.Role;
import com.blog.entity.User;

public interface UserBaseService {
	public List<Integer> getUserRoleList(Integer userId);
	
	public UserDetails login(String username,String password);
	
	public void createUser(String username,String password,String des,String tel,String address);
	
	public UserDetails getUserDetails(Integer userId);
	
	public List<User>getUserList(Integer level,String username,RowBounds rowBounds);
	
	public void updateUserDetails(Integer userId,UserEditDetails userEditDetails);
	
	public void deleteUser(String ids);
	
	public List<Role>getRoles(Integer id);
	
	public void addRole(String roleName);
}
