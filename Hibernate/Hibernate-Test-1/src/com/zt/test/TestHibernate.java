package com.zt.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.zt.model.Book;

@SuppressWarnings("deprecation")
public class TestHibernate {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testSave() {
		Book book = new Book("1003", "CCC", 22);
		session.save(book);
	}
	
	@Test
	public void testGet() {
		Book book = (Book) session.get(Book.class, "1003");
		System.out.println(book);
	}
}
