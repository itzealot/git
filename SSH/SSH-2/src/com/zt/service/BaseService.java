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

	// ��ֵ������ȷ����ѯ�������ֻ��һ����¼
	public Object uniqueResult(String hql, Object... objects);

	// ��ѯ����ʵ��
	public List<T> findAllEntities();

	/**
	 * ִ��ԭ����SQL���
	 * 
	 * @param sql
	 * @param objects
	 */
	public void executeSQL(String sql, Object... objects);

	/**
	 * ִ��ԭ����sql��ѯ
	 */
	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz, String sql, Object... objects);

	/**
	 * ʵ�ַ�ҳ��ѯ <br />
	 * firstResult : �ӵڶ�������¼��ʼ��ѯ <br />
	 * maxResult : ��ѯ��������¼<br />
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findEntityByPageUtil(int firstResult, int maxResult);

	/**
	 * ��ѯʵ���¼����
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
