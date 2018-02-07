package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.blog.entity.Role;
import com.blog.mapper.base.IBaseMapper;

public interface RoleMapper extends IBaseMapper<Role> {
	public List<Role> getRoleListByUserId(Integer userId);
	
	public List<Role>getRoleList(@Param("id")Integer id);
	
	public Integer getNameCount(String roleName);
}
