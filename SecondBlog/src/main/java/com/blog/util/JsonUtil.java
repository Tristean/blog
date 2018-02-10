package com.blog.util;

import java.util.HashMap;

import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	public static HashMap<String,String> parse(String message){
		JSONObject json=(JSONObject) JSONObject.parse(message);
		HashMap<String,String>map=new HashMap<String,String>();
		map.put(json.getString("name"), json.getString("msg"));
		return map;
	}
}
