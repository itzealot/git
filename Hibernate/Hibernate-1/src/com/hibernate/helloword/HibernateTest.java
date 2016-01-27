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
		//1. ����һ��SessionFactory����
		SessionFactory sessionFactory = null;
		
		
		//1).����Configuration ���󣬶�Ӧhibernate�Ļ���������Ϣ�Ͷ���ӳ����Ϣ
		Configuration configuration = new Configuration().configure();
		
		//4.0֮ǰ��������
		//configuration = (Configuration) configuration.buildSessionFactory();
		
		//2).����һ��ServiceRegistry ����hibernate 4.x ����Ӷ���
		//hibernate ���κ����úͷ���Ҫ�ڸö�����ע�������Ч
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		//3).
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		

		
		//2. ����һ��Session����
		Session session = sessionFactory.openSession();
		
		//3. ��������
		Transaction transaction = session.beginTransaction();
		
		//4. ִ�б������
		News news = new News("java", "Autho", new Date(new Date().getTime()));
		session.save(news);
		
		/**
		 * ͨ��Session������Դ����ݿ��ȡ��Ϣ��
		 * ����Ϊ(*.class, int rowIndex) in the database.
		 * ͨ�������ã�Ҫ���޲���������ȥʵ������
		 */
		/*
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2.toString());
		
		*/
		//5. �ύ����
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
		
		//6. �ر�Session
		session.close();
		//7. �ر�SessionFactory����
		sessionFactory.close();
	}
	
}
