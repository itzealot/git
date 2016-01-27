package com.zt.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zt.dao.BaseDao;
import com.zt.utils.StringUtils;
import com.zt.utils.ValidateUtils;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	// ע��SessionFactory
	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> clazz;

	public BaseDaoImpl() {
		// To get the superclass T���õ����ͻ�����
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		// �����飬����ȡ��һ�������±�Ϊ0
		clazz = (Class<T>) type.getActualTypeArguments()[0];
		// System.out.println("clazz = " + clazz);
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void saveEntity(T t) {
		// TODO Auto-generated method stub
		getCurrentSession().save(t);
	}

	public void saveOrUpdateEntity(T t) {
		// TODO Auto-generated method stub
		getCurrentSession().saveOrUpdate(t);
	}

	public void updateEntity(T t) {
		// TODO Auto-generated method stub
		getCurrentSession().update(t);
	}

	public void deleteEntity(T t) {
		// TODO Auto-generated method stub
		getCurrentSession().delete(t);
	}

	/**
	 * ����HQL��������������
	 */
	public void batchEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

	public T loadEntity(Integer id) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().load(clazz, id);
	}

	public T getEntity(Integer id) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().get(clazz, id);
	}

	public List<T> findEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}

	@Override
	public Object uniqueResult(String hql, Object... objects) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.uniqueResult();
	}

	/**
	 * ִ��ԭ����SQL���
	 */
	public void executeSQL(String sql, Object... objects) {
		// TODO Auto-generated method stub
		SQLQuery query = getCurrentSession().createSQLQuery(sql);
		// �󶨲���
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		// ִ�и������
		query.executeUpdate();
	}

	/**
	 * ִ��ԭ����sql��ѯ
	 */
	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz, String sql, Object... objects) {
		// TODO Auto-generated method stub
		SQLQuery query = getCurrentSession().createSQLQuery(sql);

		// ���ʵ����
		if (clazz != null) {
			query.addEntity(clazz);
		}
		// �󶨲���
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		// ִ�в�ѯ���
		return query.list();
	}

	@Override
	public List<T> findEntityByPageUtil(int firstResult, int maxResult) {
		String hql = StringUtils.getHqlFromClazz(clazz);
		// System.out.println("hql = " + hql);
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		return query.list();
	}

	/**
	 * ��ȡ�������ݼ�¼����
	 */
	@Override
	public Integer getEntityCounts() {
		// TODO Auto-generated method stub
		String className = StringUtils.getClassName(clazz);
		String hql = "SELECT COUNT(*) FROM " + className;
		Query query = getCurrentSession().createQuery(hql);
		return Integer.parseInt(query.uniqueResult().toString());
	}

	/**
	 * ����Hql�����и���
	 * 
	 * @param hql
	 * @param objects
	 */
	@Override
	public void executeUpdateByHql(String hql, Object... objects) {
		Query query = getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

	/**
	 * ���ݹؼ��ַ�ҳ��ѯ�����б�
	 */
	@Override
	public List<T> findModelsByKeyword(String keyword, int firstResult,
			int maxResult) {
		// TODO Auto-generated method stub

		// not validate then return all entities
		if (!ValidateUtils.isValid(keyword)) {
			return this.findEntityByPageUtil(firstResult, maxResult);
		}
		String className = StringUtils.getClassName(clazz);
		String hql = "SELECT t FROM " + className + " t WHERE t.keyword like ?";
		Query query = getCurrentSession().createQuery(hql);

		// set firstResult and maxResult
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);

		// set parameter
		query.setParameter(0, "%" + keyword + "%");
		return query.list();
	}

	/**
	 * ���ݹؼ��ֲ�ѯ�����б������
	 */
	@Override
	public Integer getModelsCountsByKeyword(String keyword) {
		// TODO Auto-generated method stub

		// not validate then return all counts
		if (!ValidateUtils.isValid(keyword)) {
			return this.getEntityCounts();
		}
		String className = StringUtils.getClassName(clazz);
		String hql = "SELECT COUNT(t.id) FROM " + className
				+ " t WHERE t.keyword like ?";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter(0, "%" + keyword + "%");

		return Integer.parseInt(query.uniqueResult().toString());
	}
}
