package com.zt.service;

import java.util.List;

import com.zt.entities.Department;

/**
 * DepartmentService
 * 
 * @author zengtao
 *
 */
public interface DepartmentService extends BaseService<Department> {

	/**
	 * 根据部门名称查询部门信息
	 * 
	 * @param name
	 * @return
	 */
	public Department getDeptByName(String name);

	/**
	 * 查询所有部门名称，并将该部门调到第一个
	 * 
	 * @return
	 */
	public List<String> findAllDeptNames(Department dept);

	/**
	 * 判断部门中是否存在员工<br />
	 * 
	 * @param id
	 * @return
	 */
	public boolean existStaffs(Integer id);

	/**
	 * 更新所有部门的keyword字段信息
	 */
	public void updateDeptsKeyword();

	/**
	 * 更新部门列表的keyword字段信息
	 */
	public void updateDeptsKeyword(List<Department> depts);

	/**
	 * 根据部门信息更新员工的keyword字段信息
	 */
	public void updateDeptsKeyword(Department dept);
}
