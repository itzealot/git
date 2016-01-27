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
	 * get 和 load
	 * 1. 执行get方法， 会立即加载对象，而执行load方法，若不使用该对象，则不会立即执行查询操作，而
	 * 	返回一个代理对象。
	 * 
	 * 	get是立即检索，load是延迟检索。
	 * 
	 * 2.若数据表中没有对应的记录，get返回null，load抛出异常。
	 * 
	 * 返回代理对象，关闭session时，无法加载。
	 * 3.load可能会抛出异常LazyIntializationException。
	 * 代理之前已经关闭了session。
	 * 
	 * 4.若数据表中没有对应的记录，且Session 也没有关闭，同时需要使用对象时，get返回null
	 * load 若不使用该对象的任何属性，没有问题；若需要初始化了，则抛出异常。
	 */
	@Test
	public void testLoad() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news.getClass().getName());
		//使用
		System.out.println(news);
	}

	@Test
	public void testGet() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
	}
	
	
	/**
	 * persist(): 也会执行insert操作
	 * 	和save 方法的区别：
	 * 在persist 方法之前，若对象已经有Id啦，则不会执行insert，相反抛出异常。
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
		 * 会抛出异常
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
				 * Sql 中原生态的Connection 
				 */
				System.out.println(connection);
				
				//调用存储过程.进行批量操作可以
				
			}
		});
	}
	
	
	/**
	 * evict: 从session缓存中把指定的持久化对象移除。
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
	 * delete: 执行删除操作，只要OID 和数据表中一条记录对应，就会准备执行delete操作
	 * 若OID在数据表中没有对应的记录，则抛出异常。
	 * 
	 * 可以通过设置Hibernate配置文件hibernate.use_identifier_rollback为true，
	 * 使删除对象后，把其OID（object id）置为null。
	 */
	@Test
	public void testDelete() {
		/**
		 * 删除游离态的对象
		 */
		News news = new News();
		news.setId(1);
		session.delete(news);
		
		/**
		 * 删除持久化对象，并不是马上删除
		 */
		News news2 = (News) session.get(News.class, 2);
		session.delete(news2);
		System.out.println(news2);
		
	}
	
	/**
	 * 1.若OID不为null，但数据表中还没有和其对应的记录，会抛出一个异常。
	 * 2.了解： OID（id）值等于id的unsaved-value 属性值的对象，也被认为是一个游离对象。
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
	 * 1.若更新一个持久化对象，不需要显示的调用update方法，因为在调用Transaction的commit
	 * 方法时，会执行session的flush方法。
	 * 2.更新一个游离对象，需要显示地调用session.update()方法，可以把一个游离对象变为持久化
	 * 对象。
	 * 需要注意的几点：
	 * 1.无论要更新的游离对象和数据表的记录是否一致，都要发送update语句。
	 * 		如何能让update方法不再盲目的发出update语句？
	 * 			在*.hbm.xml中加入select-before-update = true（默认为false，通常不需要设置该属性）.
	 * 
	 * 2.若数据表中没有对应的记录，但调用了update方法，会抛出异常。
	 * 3.当update 方法关联一个游离对象时，如果在Session的缓存中已经存在相同的OID的持久化对象，会抛出异常。
	 * 因为在Session中不能有两个OID相同的对象。
	 */
	@Test
	public void testUpdate() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		news.setAutho("Oracle");
		
		//session.update(news);//可以不写，自己会调用
	}
	
	
	/**
	 * 1. save() method.
	 * 1). 使一个临时变量成为一个持久化对象。
	 * 2). 为对象分配id。
	 * 3). 在flush 缓存时会发送一条insert语句。
	 * 4). 在save方法之前的修改id是无效的。
	 * 5). 持久化对象的id是不能修改的。
	 */
	@Test
	public void testSave() {
		News news = new News("aa", "aa", new Date());
		
		/**
		 * id is null
		 */
		System.out.println(news);
		
		/**
		 * 没有用，并不会按该id插入数据库.
		 */
		news.setId(300);
		
		session.save(news);
		/**
		 * id has value
		 */
		System.out.println(news);
	}
	
	
	/**
	 * clear(): 清理缓存,
	 * 会发送两条select sql语句去查询对象的状态。
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
	 * refresh(): 会强制发送select 语句，以使Session缓存中的状态和数据表中对应的记录保持一致！
	 * 数据仍然是和第一次的一样，因为mysql事务隔离级别是可重复读，应该改为
	 * 读以提交，即connection.isolation属性值设置为2。
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
	 * 会发送更新语句, flush the session by flush() method.
	 * flush: 使数据表中的记录和Session 缓存中的对象的状态保存一致，为了保持一致，则可能会发送SQL语句。
	 * 1. 在Transaction 的commit() 方法中，先调用session 的flush 方法，再提交事务
	 * 2. flush() 方法可能会发送SQL语句，但不会提交事务。
	 * 3. 注意：在未提及事务或显示的调用session.flush() 方法之前， 也有可能会进行flush() 操作。
	 * 1).  执行HQL 或QBC 查询 ，会先进行flush() 操作，以得到数据表的最新记录。
	 * 2).  若数据记录的ID 是由底层数据库使用的自增方式生成的，则调用save() 方法时，就会立即发送insert语句。
	 * 因为save 方法后，必须保证对象的ID是存在的！
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
		 * 不会输出sql语句，session会缓存
		 */
		News news2 = (News) session.get(News.class, 1);
		System.out.println(news2);
	}

}
