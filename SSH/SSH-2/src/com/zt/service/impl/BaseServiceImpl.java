package com.zt.service.impl;

import java.lang.reflect.ParameterizedType;
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

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		super();
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	// ע��dao����Dao����д�ķ�ʽ��ͬ��д��set������
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
	 * ִ��ԭ����SQL���
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
	 * ʵ�ַ�ҳ��ѯ <br />
	 * firstResult : �ӵڶ�������¼��ʼ��ѯ <br />
	 * maxResult : ��ѯ��������¼<br />
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<T> findEntityByPageUtil(int firstResult, int maxResult) {
		return dao.findEntityByPageUtil(firstResult, maxResult);
	}

	/**
	 * ��ȡ���еļ�¼��
	 */
	public Integer getEntityCounts() {
		return dao.getEntityCounts();
	}

	/**
	 * ����Hql�����и���
	 */
	public void executeUpdateByHql(String hql, Object... objects) {
		dao.executeUpdateByHql(hql, objects);
	}

	/**
	 * ���ݹؼ��ַ�ҳ��ѯ�����б�
	 */
	@Override
	public List<T> findModelsByKeyword(String keyword, int firstResult,
			int maxResult) {
		// TODO Auto-generated method stub
		return dao.findModelsByKeyword(keyword, firstResult, maxResult);
	}

	/**
	 * ���ݹؼ��ֲ�ѯ�����б������
	 * 
	 * @param keyword
	 * @return
	 */
	@Override
	public Integer getModelsCountsByKeyword(String keyword) {
		return dao.getModelsCountsByKeyword(keyword);
	}
}
