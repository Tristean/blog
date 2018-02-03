package com.blog.util;

import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String getMD5(byte[] bytes) {
		// TODO Auto-generated method stub
		String s=null;
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f'};//将字节转换为16进制字符
		try{
			java.security.MessageDigest md=java.security.MessageDigest.getInstance("MD5");
			md.update(bytes);
			byte tmp[]=md.digest();//md5的计算结果128位，一共16字节
			char[]str=new char[16*2];//128位16个字节每个字节两个字符
			int k=0;
			for(int i=0;i<16;i++){//每个字节转换成16进制字符
				byte byte0=tmp[i];
				str[k++]=hexDigits[byte0>>>4&0xf];//取字节中高4位的数字转换
				str[k++]=hexDigits[byte0&0xf];//取低四位
			}
			s=new String(str);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return s;
	}

}
