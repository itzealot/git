package com.zt.test.service;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.service.BaseService;

public abstract class BaseServiceTest<T> {
	// applicationContext
	public ApplicationContext applicationContext = null;

	// factory
	public SessionFactory factory;

	public BaseServiceTest() {
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		factory = (SessionFactory) applicationContext.getBean("sessionFactory");
	}

	// service
	public BaseService<T> service;

	@Before
	public abstract void before();

	@After
	public void after() {
		service = null;
		factory = null;
		applicationContext = null;
	}
}
