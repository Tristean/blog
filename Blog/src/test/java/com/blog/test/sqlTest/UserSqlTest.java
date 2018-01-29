package com.blog.test.sqlTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.blog.entity.User;
import com.blog.service.UserBaseService;
import com.blog.service.base.BaseService;
import com.blog.service.base.BaseServiceImp;
import com.blog.service.imp.UserBaseServiceImp;

public class UserSqlTest {
	
	ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
}
