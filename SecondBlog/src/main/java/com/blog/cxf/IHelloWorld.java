package com.blog.cxf;

import javax.jws.WebService;

@WebService
public interface IHelloWorld {
	public String say(String str);
}
