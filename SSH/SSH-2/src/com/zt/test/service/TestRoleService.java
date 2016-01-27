package com.zt.test.service;

import org.junit.Test;

import com.zt.entities.security.Role;
import com.zt.service.RoleService;

public class TestRoleService extends BaseServiceTest<Role> {
	private RoleService roleService;

	@Override
	public void before() {
		// TODO Auto-generated method stub
		roleService = applicationContext.getBean(RoleService.class);
	}

	@Test
	public void testUpdateRolesKeyword() {
		roleService.updateRolesKeyword();
	}
}
