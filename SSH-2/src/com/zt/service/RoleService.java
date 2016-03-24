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
	 * 根据角色名查询角色
	 * 
	 * @param roleName
	 * @return
	 */
	Role findRoleByName(String roleName);

	/**
	 * 查询员工拥有的角色
	 * 
	 * @param id
	 * @return
	 */
	public List<Role> findStaffOwnRoles(Integer[] ids);

	/**
	 * 查询员工未拥有的角色
	 * 
	 * @param id
	 * @return
	 */
	public List<Role> findStaffNoRoles(Set<Role> roles);

	/**
	 * 根据角色Id授予角色权限
	 * 
	 * @param id
	 * @param ownRightIds
	 * @param noRightIds
	 */
	public void grantAndRemoveRights(Integer id, String[] ownRightIds,
			String[] noRightIds);

	/**
	 * 更新所有角色的keyword字段信息
	 */
	public void updateRolesKeyword();

	/**
	 * 更新角色列表的keyword字段信息
	 */
	public void updateRolesKeyword(List<Role> roles);

	/**
	 * 根据角色信息更新角色的keyword字段信息
	 */
	public void updateRolesKeyword(Role role);
}
