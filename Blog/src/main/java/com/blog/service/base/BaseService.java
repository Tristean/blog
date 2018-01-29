package com.blog.service.base;

public interface BaseService<T> {
	int deleteByPrimaryKey(Integer id);
	
	int insert(T Record);
	
	int insertSelective(T record);
	
	T selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(T record);
	
	int updateByPrimaryKey(T record);
}
