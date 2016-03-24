package com.zt.test.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.entities.Staff;
import com.zt.service.StaffService;
import com.zt.utils.ListUtil;

public class TestStaffService {
	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}

	private StaffService staffService;

	private SessionFactory factory;
	{
		staffService = (StaffService) applicationContext
				.getBean("staffService");
		factory = (SessionFactory) applicationContext.getBean("sessionFactory");
	}

	@Test
	public void testGetStaff() {
		System.out.println(staffService.getEntity(1).getRoles());
	}

	@Test
	public void testDeleteStaff() {
		String hql = "DELETE FROM Staff s WHERE s.id = 45";
		Session session = factory.openSession();
		Query query = session.createQuery(hql);
		query.executeUpdate();
		session.close();
	}

	@Test
	public void testGrantAndCancelRole() {
		Integer id = 1;
		String[] grantRoleIds = { "1", "2" };
		String[] cancelRoleIds = { "3", "4" };
		staffService.grantAndCancelRole(id, grantRoleIds, cancelRoleIds);
	}

	@Test
	public void testFindByDeptId() {
		Integer deptId = 1;
		List<Staff> staffs = staffService.findByDeptId(deptId);
		System.out.println(staffs.size());
		System.out.println(staffs);
	}

	@Test
	public void testFindStaffByKeyword() {
		List<Staff> staffs = staffService.findStaffsByKeyword("a");
		System.out.println(staffs.size());
		System.out.println(staffs);

		System.out.println(ListUtil.getListByPage(staffs, 5, 1));
	}

	@Test
	public void testUpdateStaffsKeyword() {
		staffService.updateStaffsKeyword();
	}

	@Test
	public void testFindModelsByKeyword() {
		int pageSize = 5;
		int pageNo = 1;

		int firstResult = pageSize * (pageNo - 1);
		int maxResult = pageSize;

		List<Staff> staffs = staffService.findModelsByKeyword("a", firstResult,
				maxResult);
		System.out.println(staffs.size());
		System.out.println(staffs);
		
		staffs = staffService.findModelsByKeyword("ad", firstResult,
				maxResult);
		System.out.println(staffs.size());
		System.out.println(staffs);
	}
}