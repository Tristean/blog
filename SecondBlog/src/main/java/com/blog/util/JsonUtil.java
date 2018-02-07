package com.blog.util;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static String parse(String message){
		JSONObject json=(JSONObject) JSONObject.parse(message);
		String str=json.getString("name")+" : "+json.getString("msg");
		return str;
	}
}
