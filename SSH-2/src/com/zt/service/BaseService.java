package com.zt.service;

import java.util.List;

/**
 * 
 * @author zengtao
 *
 *         The interface of BaseService
 * @param <T>
 */
public interface BaseService<T> {

	// Write Op
	public void saveEntity(T t);

	public void saveOrUpdateEntity(T t);

	public void updateEntity(T t);

	public void deleteEntity(T t);

	public void batchEntityByHQL(String hql, Object... objects);

	// Read Op
	public T loadEntity(Integer id);

	public T getEntity(Integer id);

	public List<T> findEntityByHQL(String hql, Object... objects);

	// 单值检索，确保查询结果有且只有一条记录
	public Object uniqueResult(String hql, Object... objects);

	// 查询所有实体
	public List<T> findAllEntities();

	/**
	 * 执行原生的SQL语句
	 * 
	 * @param sql
	 * @param objects
	 */
	public void executeSQL(String sql, Object... objects);

	/**
	 * 执行原生的sql查询
	 */
	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz, String sql, Object... objects);

	/**
	 * 实现分页查询 <br />
	 * firstResult : 从第多少条记录开始查询 <br />
	 * maxResult : 查询多少条记录<br />
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findEntityByPageUtil(int firstResult, int maxResult);

	/**
	 * 查询实体记录总数
	 * 
	 * @return
	 */
	public Integer getEntityCounts();

	/**
	 * 根据Hql语句进行更新
	 * 
	 * @param hql
	 * @param objects
	 */
	public void executeUpdateByHql(String hql, Object... objects);

	/**
	 * 根据关键字分页查询对象列表
	 * 
	 * @param keyword
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findModelsByKeyword(String keyword, int firstResult,
			int maxResult);

	/**
	 * 根据关键字查询对象列表的数量
	 * 
	 * @param keyword
	 * @return
	 */
	public Integer getModelsCountsByKeyword(String keyword);

}
