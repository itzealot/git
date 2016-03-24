package com.zt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zt.dao.BaseDao;
import com.zt.entities.Department;
import com.zt.service.DepartmentService;
import com.zt.utils.StringUtils;
import com.zt.utils.ValidateUtils;

@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl<Department>
		implements DepartmentService {

	@Resource(name = "departmentDao")
	public void setDao(BaseDao<Department> dao) {
		super.setDao(dao);
	}

	/**
	 * 删除部门，首先应该查询部门是否存在员工
	 */
	@Override
	public void deleteEntity(Department t) {
		// TODO Auto-generated method stub
		super.deleteEntity(t);
	}

	/**
	 * 根据部门名称查询部门信息
	 */
	@Override
	public Department getDeptByName(String name) {
		// TODO Auto-generated method stub
		String hql = "FROM Department d WHERE d.deptName = ?";
		return (Department) this.uniqueResult(hql, name);
	}

	/**
	 * 查询所有部门名称，并将该部门调到第一个
	 */
	@Override
	public List<String> findAllDeptNames(Department dept) {
		// TODO Auto-generated method stub
		return StringUtils.getNameStringFromList(this.findAllEntities(), dept);
	}

	/**
	 * 判断部门中是否存在员工<br />
	 */
	@Override
	public boolean existStaffs(Integer id) {
		// TODO Auto-generated method stub
		String hql = "SELECT d FROM Staff s left join s.dept d WHERE d.id = ?";
		List<Department> depts = this.findEntityByHQL(hql, id);
		if (depts == null || depts.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 更新所有部门的keyword字段信息
	 */
	@Override
	public void updateDeptsKeyword() {
		// TODO Auto-generated method stub
		List<Department> depts = this.findAllEntities();
		updateDeptsKeyword(depts);
	}

	/**
	 * 更新部门列表的keyword字段信息
	 */
	@Override
	public void updateDeptsKeyword(List<Department> depts) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(depts)) {
			return;
		}

		// execute update by hql
		String hql = "UPDATE Department d SET d.keyword = ? WHERE d.id = ?";
		for (Department dept : depts) {
			String keyword = "" + dept.getId() + "," + dept.getDeptName();
			this.executeUpdateByHql(hql, keyword, dept.getId());
		}
	}

	/**
	 * 根据部门信息更新员工的keyword字段信息
	 */
	@Override
	public void updateDeptsKeyword(Department dept) {
		// TODO Auto-generated method stub
		// execute update by hql
		String hql = "UPDATE Department d SET d.keyword = ? WHERE d.id = ?";
		String keyword = "" + dept.getId() + "," + dept.getDeptName();
		this.executeUpdateByHql(hql, keyword, dept.getId());
	}
}
