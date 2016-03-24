package com.zt.service.impl;

import java.lang.reflect.ParameterizedType;
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

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		super();
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	// 注入dao，和Dao层中写的方式不同，写在set方法上
	@Resource
	public void setDao(BaseDao<T> dao) {
		this.dao = dao;
	}

	public BaseDao<T> getDao() {
		return dao;
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

	public Object uniqueResult(String hql, Object... objects) {
		// TODO Auto-generated method stub
		return dao.uniqueResult(hql, objects);
	}

	public List<T> findAllEntities() {
		// TODO Auto-generated method stub
		String hql = "FROM " + clazz.getSimpleName();
		return this.findEntityByHQL(hql);
	}

	/**
	 * 执行原生的SQL语句
	 */
	public void executeSQL(String sql, Object... objects) {
		// TODO Auto-generated method stub
		dao.executeSQL(sql, objects);
	}

	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz, String sql, Object... objects) {
		// TODO Auto-generated method stub
		return dao.executeSQLQuery(clazz, sql, objects);
	}

	/**
	 * 实现分页查询 <br />
	 * firstResult : 从第多少条记录开始查询 <br />
	 * maxResult : 查询多少条记录<br />
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findEntityByPageUtil(int firstResult, int maxResult) {
		return dao.findEntityByPageUtil(firstResult, maxResult);
	}

	/**
	 * 获取表中的记录数
	 */
	public Integer getEntityCounts() {
		return dao.getEntityCounts();
	}

	/**
	 * 根据Hql语句进行更新
	 */
	public void executeUpdateByHql(String hql, Object... objects) {
		dao.executeUpdateByHql(hql, objects);
	}

	/**
	 * 根据关键字分页查询对象列表
	 */
	@Override
	public List<T> findModelsByKeyword(String keyword, int firstResult,
			int maxResult) {
		// TODO Auto-generated method stub
		return dao.findModelsByKeyword(keyword, firstResult, maxResult);
	}

	/**
	 * 根据关键字查询对象列表的数量
	 * 
	 * @param keyword
	 * @return
	 */
	@Override
	public Integer getModelsCountsByKeyword(String keyword) {
		return dao.getModelsCountsByKeyword(keyword);
	}
}
