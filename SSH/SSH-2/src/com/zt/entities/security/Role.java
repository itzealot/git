package com.zt.entities.security;

import java.util.HashSet;
import java.util.Set;

import com.zt.entities.BaseEntity;

/**
 * 角色实体
 * 
 * @author zengtao
 *
 */
public class Role extends BaseEntity {

	private static final long serialVersionUID = -2548409655736415695L;
	private Integer id;
	private String roleName;
	private String roleValue;
	private String roleDesc;
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	// 简历从Role到Right之间多对多关系，权限集合
	private Set<Right> rights = new HashSet<Right>();

	public Role() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleValue() {
		return roleValue;
	}

	public void setRoleValue(String roleValue) {
		this.roleValue = roleValue;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleValue="
				+ roleValue + ", roleDesc=" + roleDesc + ", rights=" + rights
				+ "]";
	}

}
