package com.blog.test.sqlTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.dto.output.UserDetails;
import com.blog.entity.User;
import com.blog.service.base.BaseService;
import com.blog.service.base.BaseServiceImp;
import com.blog.service.imp.UserBaseServiceImp;
import com.blog.services.UserBaseService;
import com.blog.util.Page;

public class UserSqlTest {
	
	ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
	UserBaseService ubs=app.getBean(UserBaseServiceImp.class);
	@Test
	public void test(){
		ubs.createUser("tcc","123456", "modi", "32233", "earth");
	}
	@Test
	public void test1(){
		UserDetails user=ubs.login("tcc", "123456");
		System.out.println(user.getDes());
	}
	@Test
	public void test2(){
		ubs.addRole("baseUser");
	}
	
	@Test
	public void test3(){
		Page<User>page=ubs.selectByPage(2, 2);
		System.out.println(page.getPageData().size());
	}
}
