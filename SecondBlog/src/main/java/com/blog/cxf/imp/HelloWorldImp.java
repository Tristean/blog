package com.blog.cxf.imp;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.blog.cxf.IHelloWorld;
@Component("helloworld")
@WebService
public class HelloWorldImp implements IHelloWorld {

	@Override
	public String say(String str) {
		// TODO Auto-generated method stub
		return "Hello"+str;
	}
	
}
