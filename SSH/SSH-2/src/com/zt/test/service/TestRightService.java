package com.zt.test.service;

import org.junit.Test;

import com.zt.entities.security.Right;
import com.zt.service.RightService;

public class TestRightService extends BaseServiceTest<Right> {

	private RightService rightService;

	@Override
	public void before() {
		// TODO Auto-generated method stub
		rightService = applicationContext.getBean(RightService.class);
	}

	@Test
	public void testUpdateRightsKeyword() {
		rightService.updateRightsKeyword();
	}
}
