package com.zt.entities;

import java.util.HashSet;
import java.util.Set;

import com.zt.entities.security.Right;
import com.zt.entities.security.Role;

/**
 * 员工实体
 * 
 * @author zengtao
 *
 */
public class Staff extends BaseEntity {

	private static final long serialVersionUID = -6085785064525262508L;
	// 拥有初始化权限总和数组的初始值
	public static int INIT_POS_VALUE = 2;

	private Integer id;
	private String name;
	private String username;
	private String password;
	private Department dept;
	private String remark;
	private String keyword;

	// 与权限多对多关联
	private Set<Role> roles = new HashSet<Role>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	// 权限总和
	private long[] rightSum;

	public long[] getRightSum() {
		return rightSum;
	}

	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}

	// 是否为超级管理员
	private boolean superAdmin = false;

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public Staff() {
		super();
		this.superAdmin = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 通过或运算计算权限总和的同时，判断是否为超级管理员
	 */
	public void calculateRightSum() {
		// 初始化权限码和权限位
		int pos = 0;
		long code = 0;

		// 初始化权限总和数组
		rightSum = new long[INIT_POS_VALUE];

		// 是超级管理员无需计算权限和
		if (this.superAdmin) {
			return;
		}
		// 没有任何角色
		if (roles == null) {
			return;
		}

		// 遍历员工拥有的所有角色
		for (Role role : roles) {
			// 判断是否为超级管理员，超级管理员拥有的角色值为-1
			if ("-1".equals(role.getRoleValue())) {
				// 设置超级管理员属性
				this.superAdmin = true;
				// 引用置空，垃圾收集
				this.roles = null;
				return;
			}

			// 遍历所对应角色所有的权限
			for (Right right : role.getRights()) {
				// 获得权限码
				pos = right.getRightPos();
				// 获得权限位
				code = right.getRightCode();
				// 对相同权限码的权限位做或运算，求对应的权限和
				this.rightSum[pos] = this.rightSum[pos] | code;
			}
		}
		// 设置权限和
		this.setRightSum(rightSum);

		// 释放资源，占用大量的Session资源，指向null，供垃圾回收器回收
		this.roles = null;
	}

	/**
	 * 判断用户是否具有指定权限，通过与运算
	 * 
	 * @param right
	 * @return
	 */
	public boolean hasRight(Right right) {
		// TODO Auto-generated method stub
		int pos = right.getRightPos();
		long code = right.getRightCode();
		// 取值，并保存在其他变量，不然操作之后，值回改变
		long value = this.getRightSum()[pos];
		// 等于0，没有权限
		return (value & code) != 0;
	}

}
