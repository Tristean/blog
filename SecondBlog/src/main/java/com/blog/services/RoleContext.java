package com.blog.services;

import java.util.List;

import com.blog.entity.Role;
import com.blog.service.base.BaseService;

public interface RoleContext extends BaseService<Role> {
	List<Integer>getRoleIdListByUserId(Integer userId);
	
	void addUserRoleList(Integer userId,List<Integer>roleList);
	
	void deleteUserRoleList(Integer userId,List<Integer>roleList);
	
	void deleteRoleByUserIdList(List<Integer>userIdList);
	
	List<Role>getRoleListByUserId(Integer id);
	
	List<Role>getRoleList(Integer id);
	
	Integer getNameCount(String roleName);
}
