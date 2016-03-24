package com.zt.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zt.dao.BaseDao;
import com.zt.entities.Staff;
import com.zt.entities.security.Role;
import com.zt.service.StaffService;
import com.zt.utils.DataUtils;
import com.zt.utils.ValidateUtils;

/**
 * StaffServiceImpl
 * 
 * @author zengtao
 *
 */
@Service("staffService")
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements
		StaffService {

	@Resource(name = "staffDao")
	public void setDao(BaseDao<Staff> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	/**
	 * 删除员工
	 */
	@Override
	public void deleteEntity(Staff t) {
		// TODO Auto-generated method stub
		if (t.getRoles() != null) {
			// 清除角色信息
			t.getRoles().clear();

			// 保存到当前员工信息中
			t.setRoles(t.getRoles());
		}
		// 1.先删除角色信息，即更新员工授予角色的信息（修改中间表）
		this.updateEntity(t);

		// 2.删除员工
		super.deleteEntity(t);
	}

	/**
	 * 根据username查询员工信息
	 */
	@Override
	public Staff findByUsername(String username) {
		// TODO Auto-generated method stub
		if (username == null) {
			return null;
		}
		String hql = "FROM Staff s WHERE s.username = ?";
		return (Staff) uniqueResult(hql, username);
	}

	@Override
	public void grantAndCancelRole(Integer id, String[] grantRoleIds,
			String[] cancelRoleIds) {
		// TODO Auto-generated method stub
		if (id == null) {
			return;
		}
		grantRole(id, grantRoleIds);
		cancelRole(id, cancelRoleIds);
	}

	/**
	 * 取消授予角色
	 * 
	 * @param id
	 * @param cancelRoleIds
	 */
	private void cancelRole(Integer id, String[] cancelRoleIds) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(cancelRoleIds)) {
			return;
		}
		Staff staff = this.getEntity(id);
		if (staff == null) {
			return;
		}
		Integer[] ids = DataUtils.getIntIdsFromStringIds(cancelRoleIds);
		if (!ValidateUtils.isValid(ids)) {
			return;
		}
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			Role r = new Role();
			r.setId(ids[i]);
			// 移除
			staff.getRoles().remove(r);
		}
		this.updateEntity(staff);
	}

	/**
	 * 授予角色
	 * 
	 * @param id
	 * @param grantRoleIds
	 */
	private void grantRole(Integer id, String[] grantRoleIds) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(grantRoleIds)) {
			return;
		}
		Staff staff = this.getEntity(id);
		if (staff == null) {
			return;
		}
		Integer[] ids = DataUtils.getIntIdsFromStringIds(grantRoleIds);
		if (!ValidateUtils.isValid(ids)) {
			return;
		}
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			Role r = new Role();
			r.setId(ids[i]);
			staff.getRoles().add(r);
		}
		this.updateEntity(staff);
	}

	/**
	 * To find Staff By department's id.<br />
	 * 根据部门Id查询员工信息.<br />
	 */
	@Override
	public List<Staff> findByDeptId(Integer deptId) {
		// TODO Auto-generated method stub
		if (deptId == null) {
			return new ArrayList<Staff>();
		}
		String hql = "SELECT s FROM Staff s left join s.dept d WHERE d.id = ?";
		return this.findEntityByHQL(hql, deptId);
	}

	/**
	 * To update the password by Staff object.
	 */
	@Override
	public void updatePassword(Staff staff) {
		// TODO Auto-generated method stub
		if (staff.getId() == null
				|| !ValidateUtils.isValid(staff.getPassword())) {
			return;
		}
		String hql = "UPDATE Staff s SET s.password = ? WHERE s.id = ?";
		this.executeUpdateByHql(hql, staff.getPassword(), staff.getId());
	}

	/**
	 * To find staffs by keyword.
	 */
	@Override
	public List<Staff> findStaffsByKeyword(String keyword) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(keyword)) {
			return this.findAllEntities();
		}
		String hql = "SELECT s FROM Staff s WHERE s.name like ?";
		return this.findEntityByHQL(hql, "%" + keyword + "%");
	}

	/**
	 * 更新所有员工的keyword字段信息
	 */
	@Override
	public void updateStaffsKeyword() {
		// TODO Auto-generated method stub
		List<Staff> staffs = findAllEntities();
		updateStaffsKeyword(staffs);
	}

	/**
	 * 更新员工列表的keyword字段信息
	 */
	@Override
	public void updateStaffsKeyword(List<Staff> staffs) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(staffs)) {
			return;
		}

		// execute update by hql
		String hql = "UPDATE Staff s SET s.keyword = ? WHERE s.id = ?";
		for (Staff staff : staffs) {
			String keyword = "" + staff.getId() + "," + staff.getName() + ","
					+ staff.getUsername();
			this.executeUpdateByHql(hql, keyword, staff.getId());
		}
	}

	/**
	 * 根据员工信息更新员工的keyword字段信息
	 */
	@Override
	public void updateStaffsKeyword(Staff staff) {
		// TODO Auto-generated method stub

		// execute update by hql
		String hql = "UPDATE Staff s SET s.keyword = ? WHERE s.id = ?";
		String keyword = "" + staff.getId() + "," + staff.getName() + ","
				+ staff.getUsername();
		this.executeUpdateByHql(hql, keyword, staff.getId());
	}
}
