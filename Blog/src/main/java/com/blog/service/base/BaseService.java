package com.blog.service.base;

import java.util.List;

import com.blog.util.Page;

public interface BaseService<T> {
	int deleteByPrimaryKey(Integer id);
	
	int insert(T Record);
	
	int insertSelective(T record);
	
	T selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(T record);
	
	int updateByPrimaryKey(T record);
	
	Page<T> selectByPage(int start,int size);
}
