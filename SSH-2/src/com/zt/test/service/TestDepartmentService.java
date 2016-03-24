package com.zt.test.service;

import org.junit.Test;

import com.zt.entities.Department;
import com.zt.service.DepartmentService;

public class TestDepartmentService extends BaseServiceTest<Department> {

	private DepartmentService departmentService;

	@Override
	public void before() {
		// TODO Auto-generated method stub
		service = applicationContext.getBean(DepartmentService.class);

		departmentService = applicationContext.getBean(DepartmentService.class);
	}

	@Test
	public void testGetEntity() {
		System.out.println(service.getEntity(1));
	}

	@Test
	public void testExistStaffs() {
		System.out.println("are exist staffs: "
				+ departmentService.existStaffs(1));
		System.out.println("are exist staffs: "
				+ departmentService.existStaffs(3));
		System.out.println("are exist staffs: "
				+ departmentService.existStaffs(8));
	}

	@Test
	public void testUpdateDeptsKeyword() {
		departmentService.updateDeptsKeyword();
	}
}
