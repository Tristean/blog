package com.blog.dto.input;

import com.blog.entity.User;

public class UserEditDetails {
	private User user;
	
	private String roleIds;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	
	
}
