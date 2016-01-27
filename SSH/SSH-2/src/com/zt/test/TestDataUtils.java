package com.zt.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.zt.entities.BaseEntity;
import com.zt.entities.Department;
import com.zt.entities.Staff;
import com.zt.entities.security.Right;
import com.zt.entities.security.Role;
import com.zt.utils.DataUtils;

public class TestDataUtils {

	@Test
	public void testExtractEntityIds() {
		Set<BaseEntity> entities = new HashSet<BaseEntity>();

		Right right = new Right();
		right.setId(1);
		entities.add(right);

		Staff staff = new Staff();
		staff.setId(2);
		entities.add(staff);

		Department department = new Department();
		department.setId(4);
		entities.add(department);

		System.out.println(DataUtils.extractEntityIds(entities));
	}

	@Test
	public void testGetLogPageList() {

	}

	@Test
	public void testGetListFromSet() {
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role();
		role.setId(1);
		roles.add(role);

		Role role2 = new Role();
		role2.setId(2);
		roles.add(role2);

		System.out.println(DataUtils.getListFromSet(roles));
	}
}
