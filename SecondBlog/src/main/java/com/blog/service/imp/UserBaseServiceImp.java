package com.blog.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.blog.dto.input.UserEditDetails;
import com.blog.dto.output.UserDetails;
import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.exception.MyException;
import com.blog.mapper.RoleMapper;
import com.blog.mapper.UserMapper;
import com.blog.mapper.UserRoleMapper;
import com.blog.mapper.base.IBaseMapper;
import com.blog.service.base.BaseServiceImp;
import com.blog.services.UserBaseService;
import com.blog.util.MD5Util;
import com.blog.util.StringUtil;

@Service
public class UserBaseServiceImp extends BaseServiceImp<User> implements UserBaseService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	
	@Override
	public List<Integer> getUserRoleList(Integer userId) {
		// TODO Auto-generated method stub
		return userRoleMapper.getRoleIdListByUserId(userId);
	}

	@Override
	public UserDetails login(String username, String password) {
		// TODO Auto-generated method stub
		User user=userMapper.getUser(username);
		if(user.getPassword().equals(MD5Util.getMD5(password.getBytes())))
			return getUserDetails(user.getId());
		System.out.println("登录错误");
		return null;
	}

	@Override
	public void createUser(String username, String password, String des, String tel,String address) {
		// TODO Auto-generated method stub
		if(userMapper.getUserNameCount(username)>0)
			throw new MyException("该用户已存在");
		User user=new User();
		user.setName(username);
		user.setPassword(MD5Util.getMD5(password.getBytes()));
		user.setTel(tel);
		user.setDes(des);
		user.setLevel(1);
		user.setAddress(address);
		userMapper.insertSelective(user);
	}

	@Override
	public UserDetails getUserDetails(Integer userId) {
		// TODO Auto-generated method stub
		UserDetails userDetails=userMapper.getUserDetailsById(userId);
		if(userDetails==null)
			throw new MyException("该成员信息不存在");
		List<Role>roles=roleMapper.getRoleList(userId);
		userDetails.setRoles(roles);
		return userDetails;
	}

	@Override
	public List<User> getUserList(Integer level, String username, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		List<User>userList=userMapper.getUserListByLevel(level, username);
		return userList;
	}

	@Override
	public IBaseMapper<User> getBaseMapper() {
		// TODO Auto-generated method stub
		return userMapper;
	}

	@Override
	public void updateUserDetails(Integer userId,UserEditDetails userEditDetails) {
		List<Integer> roleIdList = userRoleMapper.getRoleIdListByUserId(userId);
		User userTempUser=userEditDetails.getUser();
		User user = userMapper.selectByPrimaryKey(userTempUser.getId());
		if (user == null) {
			throw new MyException("该用户不存在");
		}
		if (roleIdList.contains(1)) {
			// 管理员（修改角色信息）
			if (!StringUtil.isEmpty(userEditDetails.getRoleIds())) {
				// 1获取该用户原有角色
				List<Integer> roleIdListPre = userRoleMapper
						.getRoleIdListByUserId(userTempUser.getId());
				// 2获取前台传给的角色列表
				List<String> roldeIdStrings = StringUtil.splitToList(null,
						userEditDetails.getRoleIds());
				List<Integer> roleIdIntegers = new ArrayList<Integer>();
				// 需要增加的角色
				List<Integer> roleIdAdds = new ArrayList<Integer>();
				// 需要删除的角色
				List<Integer> roleIdDeletes = new ArrayList<Integer>();
				for (String idString : roldeIdStrings) {
					roleIdIntegers.add(StringUtil.toInt(idString));
				}
				for (Integer roleId : roleIdIntegers) {
					if (!roleIdListPre.contains(roleId)) {
						roleIdAdds.add(roleId);
					}
				}
				for (Integer roleId : roleIdListPre) {
					if (!roleIdIntegers.contains(roleId)) {
						roleIdDeletes.add(roleId);
					}
				}
             //批量增加
				if(!roleIdAdds.isEmpty()){
					userRoleMapper.addUserRoleList(userTempUser.getId(), roleIdAdds);
				}
				if(!roleIdDeletes.isEmpty()){
					userRoleMapper.deleteUserRoleList(userTempUser.getId(), roleIdDeletes);
				}
			}
			//管理员可以修改的用户基础信息
			if(userTempUser.getLevel()!=null){
	        	user.setLevel(userTempUser.getLevel());
	        }
			if(userTempUser.getName()!=null&&!userTempUser.getName().equals(user.getName())){
				//确保唯一性
				if(userMapper.getUserNameCount(userTempUser.getName())==0){
					user.setName(userTempUser.getName());
				}
			}
		} 
       //管理员与管理员都可以修改的部分：
        if(!StringUtil.isEmpty(userTempUser.getDes())){
        	user.setDes(userTempUser.getDes());
        }
        if(!StringUtil.isEmpty(userTempUser.getAddress())){
        	user.setAddress(userTempUser.getAddress());
        }
        if(!StringUtil.isEmpty(userTempUser.getTel())){
        	user.setTel(userTempUser.getTel());
        }
        if(!StringUtil.isEmpty(userTempUser.getPassword())){
        	if(!user.getPassword().equals(MD5Util.getMD5(userTempUser.getPassword().getBytes()))){
        		user.setPassword(MD5Util.getMD5(userTempUser.getPassword().getBytes()));
        	}
        	
        }
        userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void deleteUser(String ids) {
		// TODO Auto-generated method stub
		List<String>idListStr=StringUtil.splitToList(null, ids);
		List<Integer>idList=new ArrayList<Integer>();
		for(String id:idListStr){
			idList.add(StringUtil.toInt(id));
		}
		if(!idList.isEmpty()){
			userMapper.deleteByIdList(idList);
			userRoleMapper.deleteByUserIdList(idList);
		}
			
	}

	@Override
	public List<Role> getRoles(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleList(id);
	}

	@Override
	public void addRole(String roleName) {
		// TODO Auto-generated method stub
		if(roleMapper.getNameCount(roleName)>0)
			throw new MyException("权限已存在");
		Role role=new Role();
		role.setName(roleName);
		roleMapper.insertSelective(role);
	}

	@Override
	public Integer getUserID(String name) {
		// TODO Auto-generated method stub
		return userMapper.getUserID(name);
	}
}
