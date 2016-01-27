package com.zt.ssi.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.ssi.dao.UserDao;
import com.zt.ssi.model.User;

public class UserDaoTest {
	
	private ApplicationContext applicationContext = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test  
	public void testGetUser() {  
		UserDao userDao = applicationContext.getBean(UserDao.class);
		User user = userDao.getUser("����");  
		System.out.println(user.toString());  
	}  

	@Test
	public void testAddUser(){
		UserDao userDao = applicationContext.getBean(UserDao.class);
		User user = new User();
	    user.setName("����");
	    user.setAge(18);
	    userDao.insertUser(user);
	    System.out.println("��ӳɹ�");
	}
	
	@Test
	public void testDataSource() throws Exception {
		DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}
}
