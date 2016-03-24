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
	 * ���ݲ������Ʋ�ѯ������Ϣ
	 * 
	 * @param name
	 * @return
	 */
	public Department getDeptByName(String name);

	/**
	 * ��ѯ���в������ƣ������ò��ŵ�����һ��
	 * 
	 * @return
	 */
	public List<String> findAllDeptNames(Department dept);

	/**
	 * �жϲ������Ƿ����Ա��<br />
	 * 
	 * @param id
	 * @return
	 */
	public boolean existStaffs(Integer id);

	/**
	 * �������в��ŵ�keyword�ֶ���Ϣ
	 */
	public void updateDeptsKeyword();

	/**
	 * ���²����б��keyword�ֶ���Ϣ
	 */
	public void updateDeptsKeyword(List<Department> depts);

	/**
	 * ���ݲ�����Ϣ����Ա����keyword�ֶ���Ϣ
	 */
	public void updateDeptsKeyword(Department dept);
}
