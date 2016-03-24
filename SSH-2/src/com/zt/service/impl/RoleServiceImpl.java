package com.zt.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zt.dao.BaseDao;
import com.zt.entities.security.Right;
import com.zt.entities.security.Role;
import com.zt.service.RoleService;
import com.zt.utils.DataUtils;
import com.zt.utils.StringUtils;
import com.zt.utils.ValidateUtils;

/**
 * RoleServiceImpl, name: roleService
 * 
 * @author zengtao
 *
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		RoleService {

	@Resource(name = "roleDao")
	public void setDao(BaseDao<Role> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	@Override
	public Role findRoleByName(String roleName) {
		// TODO Auto-generated method stub
		String hql = "FROM Role r WHERE r.roleName = ?";
		return (Role) this.uniqueResult(hql, roleName);
	}

	/**
	 * 删除角色
	 */
	@Override
	public void deleteEntity(Role t) {
		// TODO Auto-generated method stub
		super.deleteEntity(t);
	}

	/**
	 * 查询员工未拥有的角色
	 */
	@Override
	public List<Role> findStaffNoRoles(Set<Role> roles) {
		// TODO Auto-generated method stub
		String hql = "";
		// 若尚未拥有角色，查询出所有角色
		if (!ValidateUtils.isValid(roles)) {
			hql = "FROM Role r";
		} else {// 否则查出尚未拥有的角色
			hql = "FROM Role r WHERE r.id NOT IN ("
					+ DataUtils.extractEntityIds(roles) + ")";
		}
		return this.findEntityByHQL(hql);
	}

	/**
	 * 查询员工拥有的角色
	 */
	@Override
	public List<Role> findStaffOwnRoles(Integer[] ids) {
		// TODO Auto-generated method stub
		if (ValidateUtils.isValid(ids)) {
			String hql = "FROM Role r WHERE r.id IN ("
					+ StringUtils.arr2Str(ids) + ")";
			return this.findEntityByHQL(hql);
		}
		return null;
	}

	@Override
	public void grantAndRemoveRights(Integer id, String[] ownRightIds,
			String[] noRightIds) {
		// TODO Auto-generated method stub
		if (id == null) {
			return;
		}
		grantRights(id, ownRightIds);
		removeRights(id, noRightIds);
	}

	/**
	 * 移除权限
	 * 
	 * @param id
	 * @param noRightIds
	 */
	private void removeRights(Integer id, String[] noRightIds) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(noRightIds)) {
			return;
		}
		Role role = this.getEntity(id);

		if (role == null) {
			return;
		}
		Integer[] ids = DataUtils.getIntIdsFromStringIds(noRightIds);
		if (!ValidateUtils.isValid(ids)) {
			return;
		}
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			Right right = new Right();
			right.setId(ids[i]);
			role.getRights().remove(right);
		}
		this.updateEntity(role);
	}

	/**
	 * 授予权限
	 * 
	 * @param id
	 * @param ownRightIds
	 */
	private void grantRights(Integer id, String[] ownRightIds) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(ownRightIds)) {
			return;
		}
		Role role = this.getEntity(id);

		if (role == null) {
			return;
		}
		Integer[] ids = DataUtils.getIntIdsFromStringIds(ownRightIds);
		if (!ValidateUtils.isValid(ids)) {
			return;
		}
		int length = ids.length;
		for (int i = 0; i < length; i++) {
			Right right = new Right();
			right.setId(ids[i]);
			role.getRights().add(right);
		}
		this.updateEntity(role);
	}

	/**
	 * 更新所有角色的keyword字段信息
	 */
	@Override
	public void updateRolesKeyword() {
		// TODO Auto-generated method stub
		List<Role> roles = findAllEntities();
		updateRolesKeyword(roles);
	}

	/**
	 * 更新角色列表的keyword字段信息
	 */
	@Override
	public void updateRolesKeyword(List<Role> roles) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(roles)) {
			return;
		}

		// execute update by hql
		String hql = "UPDATE Role r SET r.keyword = ? WHERE r.id = ?";
		for (Role role : roles) {
			String keyword = "" + role.getId() + "," + role.getRoleName() + ","
					+ role.getRoleValue();
			this.executeUpdateByHql(hql, keyword, role.getId());
		}
	}

	/**
	 * 根据角色信息更新角色的keyword字段信息
	 */
	@Override
	public void updateRolesKeyword(Role role) {
		// TODO Auto-generated method stub

		// execute update by hql
		String hql = "UPDATE Role r SET r.keyword = ? WHERE r.id = ?";
		String keyword = "" + role.getId() + "," + role.getRoleName() + ","
				+ role.getRoleValue();
		this.executeUpdateByHql(hql, keyword, role.getId());
	}
}
