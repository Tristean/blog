package com.blog.service.base;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.mapper.base.IBaseMapper;
import com.blog.util.Page;

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
	
	@Override
	public Page<T> selectByPage(int start,int size){
		Page<T> page=new Page<T>();
		List<T>list=this.getBaseMapper().selectByPage(start, size);
		page.setPageData(list);
		return page;
	}
}
