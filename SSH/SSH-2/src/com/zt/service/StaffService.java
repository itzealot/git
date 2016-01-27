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
	 * ����username��ѯԱ����Ϣ
	 * 
	 * @param username
	 * @return
	 */
	public Staff findByUsername(String username);

	/**
	 * ����Ա��id�ͽ�ɫid���������ɫ��ȡ������
	 * 
	 * @param id
	 * @param grantRoleIds
	 * @param cancelRoleIds
	 */
	public void grantAndCancelRole(Integer id, String[] grantRoleIds,
			String[] cancelRoleIds);

	/**
	 * To find Staff By department's id.<br />
	 * ���ݲ���Id��ѯԱ����Ϣ.<br />
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
	 * ��������Ա����keyword�ֶ���Ϣ
	 */
	public void updateStaffsKeyword();

	/**
	 * ����Ա���б��keyword�ֶ���Ϣ
	 */
	public void updateStaffsKeyword(List<Staff> staffs);

	/**
	 * ����Ա����Ϣ����Ա����keyword�ֶ���Ϣ
	 */
	public void updateStaffsKeyword(Staff staff);
}
