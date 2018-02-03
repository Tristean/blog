package com.blog.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Role;
import com.blog.exception.MyException;
import com.blog.mapper.RoleMapper;
import com.blog.mapper.UserMapper;
import com.blog.mapper.UserRoleMapper;
import com.blog.mapper.base.IBaseMapper;
import com.blog.service.base.BaseServiceImp;
import com.blog.services.RoleContext;
@Service
public class BaseRoleContext extends BaseServiceImp<Role> implements RoleContext{
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	UserRoleMapper userRoleMapper;
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<Integer> getRoleIdListByUserId(Integer userId) {
		// TODO Auto-generated method stub
		List<Integer> list=userRoleMapper.getRoleIdListByUserId(userId);
		if(list==null)
			throw new MyException("请检查用户是否正确");
		return list;
	}

	@Override
	public void addUserRoleList(Integer userId, List<Integer> roleList) {
		// TODO Auto-generated method stub
		if(userMapper.getUserDetailsById(userId)==null)
			throw new MyException("用户不存在");
		userRoleMapper.addUserRoleList(userId, roleList);
	}

	@Override
	public void deleteUserRoleList(Integer userId, List<Integer> roleList) {
		// TODO Auto-generated method stub
		if(userMapper.getUserDetailsById(userId)==null)
			throw new MyException("用户不存在");
		userRoleMapper.deleteUserRoleList(userId, roleList);
	}

	@Override
	public void deleteRoleByUserIdList(List<Integer> userIdList) {
		// TODO Auto-generated method stub
		for(int i=0;i<userIdList.size();i++){
			if(userMapper.getUserDetailsById(userIdList.get(i))==null){
				throw new MyException("用户不存在");
			}
		}
		userRoleMapper.deleteByUserIdList(userIdList);
		
	}

	@Override
	public List<Role> getRoleListByUserId(Integer id) {
		// TODO Auto-generated method stub
		if(userMapper.getUserDetailsById(id)==null)
			throw new MyException("用户不存在");
		return roleMapper.getRoleListByUserId(id);
	}

	@Override
	public List<Role> getRoleList(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getNameCount(String roleName) {
		// TODO Auto-generated method stub
		return roleMapper.getNameCount(roleName);
	}

	@Override
	public IBaseMapper<Role> getBaseMapper() {
		// TODO Auto-generated method stub
		return roleMapper;
	}

}
