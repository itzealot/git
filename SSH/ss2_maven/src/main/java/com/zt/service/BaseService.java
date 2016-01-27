package com.zt.service;

import java.util.List;

/**
 * 
 * @author zengtao
 *
 * The interface of BaseService
 * @param <T>
 */
public interface BaseService<T> {

	//Write Op
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchEntityByHQL(String hql, Object...objects);
	
	//Read Op
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql, Object...objects);
}
