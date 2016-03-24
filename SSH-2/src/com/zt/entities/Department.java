package com.zt.entities;

/**
 * 部门实体 The department entity
 * 
 * @author zengtao
 *
 */
public class Department extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5914227954316588718L;
	private Integer id;
	// 部门名称
	private String deptName;
	private String remark;

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Department() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", deptName=" + deptName + ", remark="
				+ remark + "]";
	}

}
