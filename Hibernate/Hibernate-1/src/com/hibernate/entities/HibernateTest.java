package com.hibernate.entities;

//import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hibernate.helloword.News;

@SuppressWarnings("deprecation")
public class HibernateTest {
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
	
	/**
	 * get �� load
	 * 1. ִ��get������ ���������ض��󣬶�ִ��load����������ʹ�øö����򲻻�����ִ�в�ѯ��������
	 * 	����һ���������
	 * 
	 * 	get������������load���ӳټ�����
	 * 
	 * 2.�����ݱ���û�ж�Ӧ�ļ�¼��get����null��load�׳��쳣��
	 * 
	 * ���ش�����󣬹ر�sessionʱ���޷����ء�
	 * 3.load���ܻ��׳��쳣LazyIntializationException��
	 * ����֮ǰ�Ѿ��ر���session��
	 * 
	 * 4.�����ݱ���û�ж�Ӧ�ļ�¼����Session Ҳû�йرգ�ͬʱ��Ҫʹ�ö���ʱ��get����null
	 * load ����ʹ�øö�����κ����ԣ�û�����⣻����Ҫ��ʼ���ˣ����׳��쳣��
	 */
	@Test
	public void testLoad() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news.getClass().getName());
		//ʹ��
		System.out.println(news);
	}

	@Test
	public void testGet() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
	}
	
	
	/**
	 * persist(): Ҳ��ִ��insert����
	 * 	��save ����������
	 * ��persist ����֮ǰ���������Ѿ���Id�����򲻻�ִ��insert���෴�׳��쳣��
	 * 
	 */
	@Test
	public void testPersist() {
		News news = new News("dd", "dd", new Date());
		/**
		 * id is null
		 */
		
		System.out.println(news);
		
		/**
		 * ���׳��쳣
		 */
		news.setId(301);
		
		
		session.save(news);
		/**
		 * id has value
		 */
		System.out.println(news);
	}
	
	@Test
	public void testDoWork() {
		
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				/**
				 * Sql ��ԭ��̬��Connection 
				 */
				System.out.println(connection);
				
				//���ô洢����.����������������
				
			}
		});
	}
	
	
	/**
	 * evict: ��session�����а�ָ���ĳ־û������Ƴ���
	 */
	
	@Test
	public void testEvict() {
		News news1 = (News) session.get(News.class, 1);
		News news2 = (News) session.get(News.class, 2);
		
		news1.setTitle("aa");
		news2.setTitle("bb");
		
		session.evict(news1);
	}
	
	
	/**
	 * delete: ִ��ɾ��������ֻҪOID �����ݱ���һ����¼��Ӧ���ͻ�׼��ִ��delete����
	 * ��OID�����ݱ���û�ж�Ӧ�ļ�¼�����׳��쳣��
	 * 
	 * ����ͨ������Hibernate�����ļ�hibernate.use_identifier_rollbackΪtrue��
	 * ʹɾ������󣬰���OID��object id����Ϊnull��
	 */
	@Test
	public void testDelete() {
		/**
		 * ɾ������̬�Ķ���
		 */
		News news = new News();
		news.setId(1);
		session.delete(news);
		
		/**
		 * ɾ���־û����󣬲���������ɾ��
		 */
		News news2 = (News) session.get(News.class, 2);
		session.delete(news2);
		System.out.println(news2);
		
	}
	
	/**
	 * 1.��OID��Ϊnull�������ݱ��л�û�к����Ӧ�ļ�¼�����׳�һ���쳣��
	 * 2.�˽⣺ OID��id��ֵ����id��unsaved-value ����ֵ�Ķ���Ҳ����Ϊ��һ���������
	 */
	@Test
	public void saveOrUpdate() {
		/**
		 * will insert
		 */
		News news = new News("FF", "FF", new Date());
		session.saveOrUpdate(news);
		
		/**
		 * wiil update
		 */
		news.setId(1);
		session.saveOrUpdate(news);
	}
	
	/**
	 * update: 
	 * 1.������һ���־û����󣬲���Ҫ��ʾ�ĵ���update��������Ϊ�ڵ���Transaction��commit
	 * ����ʱ����ִ��session��flush������
	 * 2.����һ�����������Ҫ��ʾ�ص���session.update()���������԰�һ����������Ϊ�־û�
	 * ����
	 * ��Ҫע��ļ��㣺
	 * 1.����Ҫ���µ������������ݱ�ļ�¼�Ƿ�һ�£���Ҫ����update��䡣
	 * 		�������update��������äĿ�ķ���update��䣿
	 * 			��*.hbm.xml�м���select-before-update = true��Ĭ��Ϊfalse��ͨ������Ҫ���ø����ԣ�.
	 * 
	 * 2.�����ݱ���û�ж�Ӧ�ļ�¼����������update���������׳��쳣��
	 * 3.��update ��������һ���������ʱ�������Session�Ļ������Ѿ�������ͬ��OID�ĳ־û����󣬻��׳��쳣��
	 * ��Ϊ��Session�в���������OID��ͬ�Ķ���
	 */
	@Test
	public void testUpdate() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		news.setAutho("Oracle");
		
		//session.update(news);//���Բ�д���Լ������
	}
	
	
	/**
	 * 1. save() method.
	 * 1). ʹһ����ʱ������Ϊһ���־û�����
	 * 2). Ϊ�������id��
	 * 3). ��flush ����ʱ�ᷢ��һ��insert��䡣
	 * 4). ��save����֮ǰ���޸�id����Ч�ġ�
	 * 5). �־û������id�ǲ����޸ĵġ�
	 */
	@Test
	public void testSave() {
		News news = new News("aa", "aa", new Date());
		
		/**
		 * id is null
		 */
		System.out.println(news);
		
		/**
		 * û���ã������ᰴ��id�������ݿ�.
		 */
		news.setId(300);
		
		session.save(news);
		/**
		 * id has value
		 */
		System.out.println(news);
	}
	
	
	/**
	 * clear(): ������,
	 * �ᷢ������select sql���ȥ��ѯ�����״̬��
	 */
	@Test
	public void testClear() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		
		session.clear();
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2);
	}
	
	/**
	 * refresh(): ��ǿ�Ʒ���select ��䣬��ʹSession�����е�״̬�����ݱ��ж�Ӧ�ļ�¼����һ�£�
	 * ������Ȼ�Ǻ͵�һ�ε�һ������Ϊmysql������뼶���ǿ��ظ�����Ӧ�ø�Ϊ
	 * �����ύ����connection.isolation����ֵ����Ϊ2��
	 * <property name="connection.isolation">2</property>
	 */
	@Test
	public void testRefresh() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		
		//interrupt and change the database
		/**
		 * refresh the session
		 */
		session.refresh(news);
		System.out.println(news);
	}
	
	
	/**
	 * �ᷢ�͸������, flush the session by flush() method.
	 * flush: ʹ���ݱ��еļ�¼��Session �����еĶ����״̬����һ�£�Ϊ�˱���һ�£�����ܻᷢ��SQL��䡣
	 * 1. ��Transaction ��commit() �����У��ȵ���session ��flush ���������ύ����
	 * 2. flush() �������ܻᷢ��SQL��䣬�������ύ����
	 * 3. ע�⣺��δ�ἰ�������ʾ�ĵ���session.flush() ����֮ǰ�� Ҳ�п��ܻ����flush() ������
	 * 1).  ִ��HQL ��QBC ��ѯ �����Ƚ���flush() �������Եõ����ݱ�����¼�¼��
	 * 2).  �����ݼ�¼��ID ���ɵײ����ݿ�ʹ�õ�������ʽ���ɵģ������save() ����ʱ���ͻ���������insert��䡣
	 * ��Ϊsave �����󣬱��뱣֤�����ID�Ǵ��ڵģ�
	 */
	@Test
	public void testSessionFlush() {
		News news = (News) session.get(News.class, 1);
		news.setAutho("Oracle");
	}
	
	@Test
	public void testSessionCache() {
//		fail("Not yet implemented");
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		
		/**
		 * �������sql��䣬session�Ỻ��
		 */
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2);
	}

}
