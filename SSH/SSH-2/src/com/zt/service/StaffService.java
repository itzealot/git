package com.zt.service;

import java.util.List;

import com.zt.entities.Staff;

/**
 * StaffService
 * 
 * @author zengtao
 *
 */
public interface StaffService extends BaseService<Staff> {

	/**
	 * 根据username查询员工信息
	 * 
	 * @param username
	 * @return
	 */
	public Staff findByUsername(String username);

	/**
	 * 根据员工id和角色id数组授予角色和取消授予
	 * 
	 * @param id
	 * @param grantRoleIds
	 * @param cancelRoleIds
	 */
	public void grantAndCancelRole(Integer id, String[] grantRoleIds,
			String[] cancelRoleIds);

	/**
	 * To find Staff By department's id.<br />
	 * 根据部门Id查询员工信息.<br />
	 * 
	 * @param deptId
	 * @return
	 */
	public List<Staff> findByDeptId(Integer deptId);

	/**
	 * To update the password by Staff object.
	 * 
	 * @param staff
	 */
	public void updatePassword(Staff staff);

	/**
	 * To find staffs by keyword.
	 */
	public List<Staff> findStaffsByKeyword(String keyword);

	/**
	 * 更新所有员工的keyword字段信息
	 */
	public void updateStaffsKeyword();

	/**
	 * 更新员工列表的keyword字段信息
	 */
	public void updateStaffsKeyword(List<Staff> staffs);

	/**
	 * 根据员工信息更新员工的keyword字段信息
	 */
	public void updateStaffsKeyword(Staff staff);
}
