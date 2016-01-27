package com.zt.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.service.RightService;

public class TestRightPageUtil {

	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}

	private RightService rightService;

	@Test
	public void testPageUtil() {
		rightService = (RightService) applicationContext
				.getBean("rightService");
		int pageSize = 5;
		int pageNo = 2;
		int firstResult = (pageNo - 1) * pageSize;
		int maxResult = 5;
		System.out.println(rightService.findEntityByPageUtil(firstResult,
				maxResult));
		System.out.println(rightService.getEntityCounts());
	}
}
