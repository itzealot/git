package com.zt.dao.impl;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zt.dao.BookShopDao;
import com.zt.exception.BookStockException;
import com.zt.exception.UserAccountException;

@Repository("bookShopDao")
public class BookShopDaoImpl implements BookShopDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	//���Ƽ�ʹ��HibernateTemplate �� HibernateDaoSupport
	//�����ᵼ�� Dao �� Spring �� API������ϣ�����ֲ�Ա��
//	private HibernateTemplate hibernateTemplate;
	//��ȡ�͵�ǰ�̰߳󶨵� Session
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int findBookPriceByIsbn(String isbn) {
		// TODO Auto-generated method stub
		String hql = "SELECT b.price FROM Book b WHERE b.isbn = ?";
		Query query = getSession().createQuery(hql).setString(0, isbn);
		return (Integer) query.uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) {
		// TODO Auto-generated method stub
		//��֤��Ŀ���Ƿ����
		String hql2 = "SELECT b.stock FROM Book b WHERE b.isbn = ?";
		int stock = (int) getSession().createQuery(hql2).setString(0, isbn).uniqueResult();
		if(stock == 0) {
			throw new BookStockException("��治��");
		}
		String hql = "UPDATE Book b SET b.stock = b.stock - 1 WHERE b.isbn = ?";
		getSession().createQuery(hql).setString(0, isbn).executeUpdate();
	}

	@Override
	public void updateUserAccount(String username, int price) {
		// TODO Auto-generated method stub
		//��֤����Ƿ��㹻
		String hql2 = "SELECT a.balance FROM Account a WHERE a.username = ?";
		int balance = (int) getSession().createQuery(hql2).setString(0, username).uniqueResult();
		if(balance < price) {
			throw new UserAccountException("���㣡");
		}
		String hql = "UPDATE Account a SET a.balance = a.balance - ? WHERE a.username = ?";
		getSession().createQuery(hql).setInteger(0, price).setString(1, username).executeUpdate();
	}
}
