package com.blog.test;

import com.blog.service.imp.UserBaseServiceImp;
import com.blog.services.UserBaseService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest {
    ApplicationContext app=new ClassPathXmlApplicationContext("spring.xml");
    @Autowired
    UserBaseService ubs=app.getBean(UserBaseServiceImp.class);
    @Test
    public void test(){
        int id=ubs.getUserID("test");
        System.out.println(id);
    }
}
