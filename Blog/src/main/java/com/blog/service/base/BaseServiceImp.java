package com.blog.service.base;

import org.springframework.stereotype.Service;

import com.blog.mapper.base.IBaseMapper;

@Service
public abstract class BaseServiceImp<T> implements BaseService<T> {
	public abstract IBaseMapper<T> getBaseMapper();
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().deleteByPrimaryKey(id);
	}

	@Override
	public int insert(T Record) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().insert(Record);
	}

	@Override
	public int insertSelective(T record) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(T record) {
		// TODO Auto-generated method stub
		return this.getBaseMapper().updateByPrimaryKeySelective(record);
	}

}
