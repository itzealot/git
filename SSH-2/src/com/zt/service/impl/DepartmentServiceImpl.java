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
	 * ɾ�����ţ�����Ӧ�ò�ѯ�����Ƿ����Ա��
	 */
	@Override
	public void deleteEntity(Department t) {
		// TODO Auto-generated method stub
		super.deleteEntity(t);
	}

	/**
	 * ���ݲ������Ʋ�ѯ������Ϣ
	 */
	@Override
	public Department getDeptByName(String name) {
		// TODO Auto-generated method stub
		String hql = "FROM Department d WHERE d.deptName = ?";
		return (Department) this.uniqueResult(hql, name);
	}

	/**
	 * ��ѯ���в������ƣ������ò��ŵ�����һ��
	 */
	@Override
	public List<String> findAllDeptNames(Department dept) {
		// TODO Auto-generated method stub
		return StringUtils.getNameStringFromList(this.findAllEntities(), dept);
	}

	/**
	 * �жϲ������Ƿ����Ա��<br />
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
	 * �������в��ŵ�keyword�ֶ���Ϣ
	 */
	@Override
	public void updateDeptsKeyword() {
		// TODO Auto-generated method stub
		List<Department> depts = this.findAllEntities();
		updateDeptsKeyword(depts);
	}

	/**
	 * ���²����б��keyword�ֶ���Ϣ
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
	 * ���ݲ�����Ϣ����Ա����keyword�ֶ���Ϣ
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
