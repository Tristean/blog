package com.blog.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.blog.exception.MyException;

public class MapUtil {
	 public static String getKey(HashMap<String,String>message){
		 	if(message==null)
		 		throw new MyException("·¢Éú´íÎó");
	    	List<String>list=new ArrayList<String>();
	    	Set<String>set=message.keySet();
	    	Iterator iter=set.iterator();
	    	while(iter.hasNext()){
	    		String key=(String) iter.next();
	    		list.add(key);
	    	}
	    	return list.get(0);
	    }
	    
	    public static String getValue(HashMap<String,String>message,String key){
	    	String value;
	    	value=message.get(key);
	    	return value;
	    }
}
