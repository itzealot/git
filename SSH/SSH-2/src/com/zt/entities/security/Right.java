package com.zt.entities.security;

import com.zt.entities.BaseEntity;

/**
 * 权限实体 权限分组，每组6个权限，可以用long型存储，通过移位操作实现，设置相应的权限码，标记有多少个权限组
 * 
 * @author zengtao
 *
 */
public class Right extends BaseEntity {

	private static final long serialVersionUID = 5005407349271990549L;
	private Integer id;
	// 权限名称
	private String rightName = "undefined";
	// 权限Url地址
	private String rightUrl;
	// 权限描述
	private String rightDesc;
	// 权限码1<<n，1向左移n位
	private long rightCode;
	// 权限位，相当于权限分组，从0开始
	private int rightPos;

	// 标记是否为公共资源
	private boolean common;
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Right() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	public String getRightDesc() {
		return rightDesc;
	}

	public void setRightDesc(String rightDesc) {
		this.rightDesc = rightDesc;
	}

	public long getRightCode() {
		return rightCode;
	}

	public void setRightCode(long rightCode) {
		this.rightCode = rightCode;
	}

	public int getRightPos() {
		return rightPos;
	}

	public void setRightPos(int rightPos) {
		this.rightPos = rightPos;
	}

	public boolean isCommon() {
		return common;
	}

	public void setCommon(boolean common) {
		this.common = common;
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
		Right other = (Right) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Right [id=" + id + ", rightName=" + rightName + ", rightUrl="
				+ rightUrl + ", rightDesc=" + rightDesc + ", rightCode="
				+ rightCode + ", rightPos=" + rightPos + ", common=" + common
				+ "]";
	}

}
