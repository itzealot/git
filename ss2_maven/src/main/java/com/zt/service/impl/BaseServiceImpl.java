package com.zt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zt.dao.BaseDao;
import com.zt.service.BaseService;

/**
 * 
 * @author zengtao abstract BaseServiceImpl<T>��ר�����ڼ̳�
 * @param <T>
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> dao;

	// ע��dao����Dao����д�ķ�ʽ��ͬ��д��set������
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
