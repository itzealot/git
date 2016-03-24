package com.zt.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.zt.dao.BaseDao;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	//ע��SessionFactory
	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	
	public BaseDaoImpl() {
		//To get the superclass T���õ����ͻ�����
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		//�����飬����ȡ��һ�������±�Ϊ0
		clazz = (Class<T>) type.getActualTypeArguments()[0];
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
		for(int i = 0; i < objects.length; i++) {
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
		for(int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}

}
