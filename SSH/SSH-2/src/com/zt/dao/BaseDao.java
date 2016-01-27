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
	 * ����Hql��ѯʵ�壬����List����
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	public List<T> findEntityByHQL(String hql, Object... objects);

	/**
	 * ����Hql��ѯ������Ψһ���
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	public Object uniqueResult(String hql, Object... objects);

	/**
	 * ִ��ԭ����SQL���
	 * 
	 * @param sql
	 * @param objects
	 */
	public void executeSQL(String sql, Object... objects);

	/**
	 * ִ��ԭ����sql��ѯ,clazzָ���Ƿ��װ��ʵ�壬Ϊnull���װ��ʵ��
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz, String sql, Object... objects);

	/**
	 * ʵ�ַ�ҳ��ѯ firstResult : �ӵڶ�������¼��ʼ��ѯ maxResult : ��ѯ��������¼
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findEntityByPageUtil(int firstResult, int maxResult);

	/**
	 * ��ȡ�������ݼ�¼����
	 * 
	 * @return
	 */
	public Integer getEntityCounts();

	/**
	 * ����Hql�����и���
	 * 
	 * @param hql
	 * @param objects
	 */
	public void executeUpdateByHql(String hql, Object... objects);

	/**
	 * ���ݹؼ��ַ�ҳ��ѯ�����б�
	 * 
	 * @param keyword
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findModelsByKeyword(String keyword, int firstResult,
			int maxResult);

	/**
	 * ���ݹؼ��ֲ�ѯ�����б������
	 * 
	 * @param keyword
	 * @return
	 */
	public Integer getModelsCountsByKeyword(String keyword);
}
