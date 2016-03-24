package com.zt.test.action;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.service.LogService;

public class TestLogAction {
	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}

	private LogService logService;

	{
		logService = (LogService) applicationContext.getBean("logService");
	}

	@Test
	public void testGetAllLogs() {
		System.out.println(logService.findAllEntities());
	}

	@Test
	public void testGetNearLogs() {
		System.out.println(logService.findNearestLogs(2));
	}
}
