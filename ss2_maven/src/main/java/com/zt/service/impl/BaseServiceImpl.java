package com.zt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zt.dao.BaseDao;
import com.zt.service.BaseService;

/**
 * 
 * @author zengtao abstract BaseServiceImpl<T>，专门用于继承
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;

	// 注入dao，和Dao层中写的方式不同，写在set方法上
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	public void saveEntity(T t) {
		// TODO Auto-generated method stub
		dao.saveEntity(t);
	}

	public void saveOrUpdateEntity(T t) {
		// TODO Auto-generated method stub
		dao.saveOrUpdateEntity(t);
	}

	public void updateEntity(T t) {
		// TODO Auto-generated method stub
		dao.updateEntity(t);
	}

	public void deleteEntity(T t) {
		// TODO Auto-generated method stub
		dao.deleteEntity(t);
	}

	public void batchEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		dao.batchEntityByHQL(hql, objects);
	}

	public T loadEntity(Integer id) {
		// TODO Auto-generated method stub
		return dao.loadEntity(id);
	}

	public T getEntity(Integer id) {
		// TODO Auto-generated method stub
		return dao.getEntity(id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		return dao.findEntityByHQL(hql, objects);
	}

}
