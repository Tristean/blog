package com.blog.test.sqlTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.entity.Role;
import com.blog.service.imp.BaseRoleContext;
import com.blog.services.RoleContext;

public class RoleSqlTest {
	public ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
	RoleContext rolecon=app.getBean(BaseRoleContext.class);
	
	@Test
	public void test(){
		Role role=new Role();
		role.setId(1);
		role.setName("baseUser");
		rolecon.insert(role);
	}
	
	@Test
	public void test1(){
		List<Integer>list=new ArrayList<Integer>();
		list.add(1);
		rolecon.addUserRoleList(3, list);
	}
}
