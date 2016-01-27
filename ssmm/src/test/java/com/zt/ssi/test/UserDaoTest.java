package com.zt.ssi.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.ssi.dao.UserDao;
import com.zt.ssi.model.User;

public class UserDaoTest {

	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	}

	@Test
	public void testQueryUserByName() {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		User user = userDao.queryUserByName("张三");
		System.out.println(user.toString());
	}

	@Test
	public void testAddUser() {
		UserDao userDao = applicationContext.getBean(UserDao.class);
		User user = new User();
		user.setName("aaaa");
		user.setAge(18);
		userDao.addUser(user);
		System.out.println("添加成功");
	}

}
