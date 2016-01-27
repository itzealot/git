package com.zt.entities;

import java.util.HashSet;
import java.util.Set;

import com.zt.entities.security.Right;
import com.zt.entities.security.Role;

/**
 * Ա��ʵ��
 * 
 * @author zengtao
 *
 */
public class Staff extends BaseEntity {

	private static final long serialVersionUID = -6085785064525262508L;
	// ӵ�г�ʼ��Ȩ���ܺ�����ĳ�ʼֵ
	public static int INIT_POS_VALUE = 2;

	private Integer id;
	private String name;
	private String username;
	private String password;
	private Department dept;
	private String remark;
	private String keyword;

	// ��Ȩ�޶�Զ����
	private Set<Role> roles = new HashSet<Role>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	// Ȩ���ܺ�
	private long[] rightSum;

	public long[] getRightSum() {
		return rightSum;
	}

	public void setRightSum(long[] rightSum) {
		this.rightSum = rightSum;
	}

	// �Ƿ�Ϊ��������Ա
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
	 * ͨ�����������Ȩ���ܺ͵�ͬʱ���ж��Ƿ�Ϊ��������Ա
	 */
	public void calculateRightSum() {
		// ��ʼ��Ȩ�����Ȩ��λ
		int pos = 0;
		long code = 0;

		// ��ʼ��Ȩ���ܺ�����
		rightSum = new long[INIT_POS_VALUE];

		// �ǳ�������Ա�������Ȩ�޺�
		if (this.superAdmin) {
			return;
		}
		// û���κν�ɫ
		if (roles == null) {
			return;
		}

		// ����Ա��ӵ�е����н�ɫ
		for (Role role : roles) {
			// �ж��Ƿ�Ϊ��������Ա����������Աӵ�еĽ�ɫֵΪ-1
			if ("-1".equals(role.getRoleValue())) {
				// ���ó�������Ա����
				this.superAdmin = true;
				// �����ÿգ������ռ�
				this.roles = null;
				return;
			}

			// ��������Ӧ��ɫ���е�Ȩ��
			for (Right right : role.getRights()) {
				// ���Ȩ����
				pos = right.getRightPos();
				// ���Ȩ��λ
				code = right.getRightCode();
				// ����ͬȨ�����Ȩ��λ�������㣬���Ӧ��Ȩ�޺�
				this.rightSum[pos] = this.rightSum[pos] | code;
			}
		}
		// ����Ȩ�޺�
		this.setRightSum(rightSum);

		// �ͷ���Դ��ռ�ô�����Session��Դ��ָ��null������������������
		this.roles = null;
	}

	/**
	 * �ж��û��Ƿ����ָ��Ȩ�ޣ�ͨ��������
	 * 
	 * @param right
	 * @return
	 */
	public boolean hasRight(Right right) {
		// TODO Auto-generated method stub
		int pos = right.getRightPos();
		long code = right.getRightCode();
		// ȡֵ����������������������Ȼ����֮��ֵ�ظı�
		long value = this.getRightSum()[pos];
		// ����0��û��Ȩ��
		return (value & code) != 0;
	}

}
