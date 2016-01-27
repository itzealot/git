package com.zt.service;

import java.util.List;
import java.util.Set;

import com.zt.entities.security.Role;

/**
 * RoleService
 * 
 * @author zengtao
 *
 */
public interface RoleService extends BaseService<Role> {

	/**
	 * ���ݽ�ɫ����ѯ��ɫ
	 * 
	 * @param roleName
	 * @return
	 */
	Role findRoleByName(String roleName);

	/**
	 * ��ѯԱ��ӵ�еĽ�ɫ
	 * 
	 * @param id
	 * @return
	 */
	public List<Role> findStaffOwnRoles(Integer[] ids);

	/**
	 * ��ѯԱ��δӵ�еĽ�ɫ
	 * 
	 * @param id
	 * @return
	 */
	public List<Role> findStaffNoRoles(Set<Role> roles);

	/**
	 * ���ݽ�ɫId�����ɫȨ��
	 * 
	 * @param id
	 * @param ownRightIds
	 * @param noRightIds
	 */
	public void grantAndRemoveRights(Integer id, String[] ownRightIds,
			String[] noRightIds);

	/**
	 * �������н�ɫ��keyword�ֶ���Ϣ
	 */
	public void updateRolesKeyword();

	/**
	 * ���½�ɫ�б��keyword�ֶ���Ϣ
	 */
	public void updateRolesKeyword(List<Role> roles);

	/**
	 * ���ݽ�ɫ��Ϣ���½�ɫ��keyword�ֶ���Ϣ
	 */
	public void updateRolesKeyword(Role role);
}
