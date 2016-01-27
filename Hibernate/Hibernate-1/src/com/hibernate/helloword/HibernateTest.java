package com.hibernate.helloword;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class HibernateTest {

	@Test
	public void test() {
		//1. 创建一个SessionFactory对象
		SessionFactory sessionFactory = null;
		
		
		//1).创建Configuration 对象，对应hibernate的基本配置信息和对象映射信息
		Configuration configuration = new Configuration().configure();
		
		//4.0之前这样创建
		//configuration = (Configuration) configuration.buildSessionFactory();
		
		//2).创建一个ServiceRegistry 对象，hibernate 4.x 新添加对象
		//hibernate 的任何配置和服务都要在该对象中注册才能有效
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		//3).
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		

		
		//2. 创建一个Session对象
		Session session = sessionFactory.openSession();
		
		//3. 开启事务
		Transaction transaction = session.beginTransaction();
		
		//4. 执行保存操作
		News news = new News("java", "Autho", new Date(new Date().getTime()));
		session.save(news);
		
		/**
		 * 通过Session对象可以从数据库获取信息，
		 * 参数为(*.class, int rowIndex) in the database.
		 * 通过反射获得，要有无参数构造器去实例化。
		 */
		/*
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2.toString());
		
		*/
		//5. 提交事务
		try {
			transaction.commit();
		} catch (Exception e) {
			//TODO 
			try {
				transaction.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
		
		//6. 关闭Session
		session.close();
		//7. 关闭SessionFactory对象
		sessionFactory.close();
	}
	
}
