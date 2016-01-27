package com.zt.dao;

import java.util.List;

/**
 * 
 * @author zengtao
 *
 *         The interface of BaseDao
 * @param <T>
 */
public interface BaseDao<T> {

	// Write Op
	public void saveEntity(T t);

	public void saveOrUpdateEntity(T t);

	public void updateEntity(T t);

	public void deleteEntity(T t);

	public void batchEntityByHQL(String hql, Object... objects);

	// Read Op
	public T loadEntity(Integer id);

	public T getEntity(Integer id);

	/**
	 * 根据Hql查询实体，返回List集合
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	public List<T> findEntityByHQL(String hql, Object... objects);

	/**
	 * 根据Hql查询，返回唯一结果
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	public Object uniqueResult(String hql, Object... objects);

	/**
	 * 执行原生的SQL语句
	 * 
	 * @param sql
	 * @param objects
	 */
	public void executeSQL(String sql, Object... objects);

	/**
	 * 执行原生的sql查询,clazz指定是否封装成实体，为null则封装成实体
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz, String sql, Object... objects);

	/**
	 * 实现分页查询 firstResult : 从第多少条记录开始查询 maxResult : 查询多少条记录
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findEntityByPageUtil(int firstResult, int maxResult);

	/**
	 * 获取表中数据记录条数
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
