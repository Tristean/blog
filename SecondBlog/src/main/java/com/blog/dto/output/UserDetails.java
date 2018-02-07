package com.blog.dto.output;

import java.util.List;

import com.blog.entity.Role;
import com.blog.entity.User;

public class UserDetails extends User {
	private List<Role>roles;

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
