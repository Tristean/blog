package com.blog.mapper.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IBaseMapper<T> {
	int deleteByPrimaryKey(Integer id);
	
	int insert(T record);
	
	int insertSelective(T record);
	
	T selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(T record);
	
	int updateByPrimaryKey(T record);
	
	List<T>getAllByPage();
	
	List<T>selectByPage(@Param("start")int start,@Param("size")int size);
}
