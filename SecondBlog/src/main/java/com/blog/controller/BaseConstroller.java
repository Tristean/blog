package com.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.blog.exception.MyException;
import com.blog.util.StringUtil;

public class BaseConstroller {
	private static Logger logger=LoggerFactory.getLogger(BaseConstroller.class);
	
	protected Integer getUserId(HttpServletRequest request){
		String userId=request.getHeader("userId");
		
		if(!StringUtil.isEmpty(userId)){
			try{
				Integer Id=StringUtil.toInt(userId);
				return Id;
			}catch(NumberFormatException e){
				logger.warn("格式错误：",userId);
			}
		}
		
		return null;
	}
	
	protected Integer getUserIdInJudge(HttpServletRequest request){
		Integer userId=getUserId(request);
		if(userId==null)
			throw new MyException("用户id不能为空");
		return userId;
	}
}
