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
	 * ɾ����ɫ
	 */
	@Override
	public void deleteEntity(Role t) {
		// TODO Auto-generated method stub
		super.deleteEntity(t);
	}

	/**
	 * ��ѯԱ��δӵ�еĽ�ɫ
	 */
	@Override
	public List<Role> findStaffNoRoles(Set<Role> roles) {
		// TODO Auto-generated method stub
		String hql = "";
		// ����δӵ�н�ɫ����ѯ�����н�ɫ
		if (!ValidateUtils.isValid(roles)) {
			hql = "FROM Role r";
		} else {// ��������δӵ�еĽ�ɫ
			hql = "FROM Role r WHERE r.id NOT IN ("
					+ DataUtils.extractEntityIds(roles) + ")";
		}
		return this.findEntityByHQL(hql);
	}

	/**
	 * ��ѯԱ��ӵ�еĽ�ɫ
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
	 * �Ƴ�Ȩ��
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
	 * ����Ȩ��
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
	 * �������н�ɫ��keyword�ֶ���Ϣ
	 */
	@Override
	public void updateRolesKeyword() {
		// TODO Auto-generated method stub
		List<Role> roles = findAllEntities();
		updateRolesKeyword(roles);
	}

	/**
	 * ���½�ɫ�б��keyword�ֶ���Ϣ
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
	 * ���ݽ�ɫ��Ϣ���½�ɫ��keyword�ֶ���Ϣ
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
