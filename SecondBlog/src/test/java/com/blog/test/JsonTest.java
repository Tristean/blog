package com.blog.test;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonTest {
	public static void main(String[] args){
		TestPerson json = new TestPerson(19,"����");
        List<TestPerson> list = new ArrayList<TestPerson>();
        list.add(json);
        list.add(new TestPerson(12,"����"));
        //�����ϻ��߶�����������JSON
        System.out.println(JSON.toJSON(json));
        System.out.println( JSON.toJSON(list) );
        //Json�������л��ɶ���
        TestPerson person = JSON.parseObject("{\"name\":\"����\",\"age\":19}", TestPerson.class);
        System.out.printf("name:%s,age:%d\n",person.getName(),person.getAge());
         
        String str = "[{\"name\":\"����\",\"age\":19},{\"name\":\"����\",\"age\":12}]";
        //����������л��ɼ���
        List<TestPerson> listPerson = JSON.parseArray(str,TestPerson.class);
         
        for(TestPerson item : listPerson){
            System.out.println( item.getName() );
            System.out.println( item.getAge());
        }
         
        //û�ж���ֱ�ӽ���JSON����
        JSONObject jobj = JSON.parseObject("{\"name\":\"����\",\"age\":19}");
        System.out.printf("name:%s,age:%d\n",jobj.getString("name"),jobj.getBigInteger("age"));
         
        //û�ж���ֱ�ӽ���JSON����
        JSONArray jarr = JSON.parseArray("[{\"name\":\"����\",\"age\":19},{\"name\":\"����\",\"age\":12}]");
         
        for(int i=0,len=jarr.size();i<len;i++){
            JSONObject temp=  jarr.getJSONObject(i);
            System.out.printf("name:%s,age:%d\n",temp.getString("name"),temp.getBigInteger("age"));
        }
         
        for(Object obj:jarr){
            System.out.println(obj.toString());
        }
	}
}
