package com.zt.ssi.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.ssi.model.User;
import com.zt.ssi.service.UserService;

public class UserServiceTest {
	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	}

	@Test
	public void testQueryUserByName() {
		UserService userService = (UserService) applicationContext
				.getBean("userService");
		User user = userService.queryUserByName("ÕÅÈý");
		System.out.println(user.toString());
	}

	@Test
	public void testAddUser() {
		User user = new User();
		user.setName("ddd");
		user.setAge(18);
		UserService userService = (UserService) applicationContext
				.getBean("userService");
		userService.addUser(user);
	}
}
