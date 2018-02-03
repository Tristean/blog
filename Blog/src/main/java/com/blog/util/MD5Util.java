package com.blog.util;

import java.security.NoSuchAlgorithmException;

public class MD5Util {

	public static String getMD5(byte[] bytes) {
		// TODO Auto-generated method stub
		String s=null;
		char hexDigits[]={'0','1','2','3','4','5','6','7','8','9',
				'a','b','c','d','e','f'};//���ֽ�ת��Ϊ16�����ַ�
		try{
			java.security.MessageDigest md=java.security.MessageDigest.getInstance("MD5");
			md.update(bytes);
			byte tmp[]=md.digest();//md5�ļ�����128λ��һ��16�ֽ�
			char[]str=new char[16*2];//128λ16���ֽ�ÿ���ֽ������ַ�
			int k=0;
			for(int i=0;i<16;i++){//ÿ���ֽ�ת����16�����ַ�
				byte byte0=tmp[i];
				str[k++]=hexDigits[byte0>>>4&0xf];//ȡ�ֽ��и�4λ������ת��
				str[k++]=hexDigits[byte0&0xf];//ȡ����λ
			}
			s=new String(str);
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return s;
	}

}
