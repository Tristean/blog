package com.blog.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static int toInt(String s){
		if(s!=null&&!"".equals(s.trim())){
			try{
				return Integer.parseInt(s);
			}
			catch(Exception e){
				return 0;
			}
		}
		return 0;
	}
	
	public static long toLong(String s){
		try{
			if(s!=null&&!"".equals(s.trim()))
				return Long.parseLong(s);
		}catch(Exception e){
			
		}
		return 0L;
	}
	
	public static boolean isEmpty(String s){
		if(s!=null&&!s.equals(""))
			return false;
		return true;
	}
	
	public static List<String> splitToList(String split,String src){
		String sp=",";
		if(split!=null&&split.length()==1)
			sp=split;
		List<String>r=new ArrayList<String>();
		int lastIndex=-1;
		int index=src.indexOf(sp);
		if(index==-1&&src!=null){
			r.add(src);
			return r;
		}
		
		while(index>=0){
			if(index>lastIndex)
				r.add(src.substring(lastIndex+1, index));
			else
				r.add("");
			
			lastIndex=index;
			index=src.indexOf(sp,index+1);
			if(index==-1)
				r.add(src.substring(lastIndex+1, src.length()));
		}
		return r;
	}
}
