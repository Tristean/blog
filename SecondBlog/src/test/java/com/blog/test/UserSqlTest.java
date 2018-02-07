package com.blog.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.service.imp.UserBaseServiceImp;
import com.blog.services.UserBaseService;

public class UserSqlTest {
	ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
	UserBaseService ubs=app.getBean(UserBaseServiceImp.class);
	
	@Test
	public void test(){
		System.out.println(ubs.getUserID("test"));
	}
}
