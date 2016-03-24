package com.zt.test.action;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.service.RightService;

public class TestRightAction {
	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}

	private RightService rightService;
	
	{
		rightService = (RightService) applicationContext.getBean("rightService");
	}

	@Test
	public void testFindAllRights() {
		System.out.println(rightService.findAllEntities());
	}
}
