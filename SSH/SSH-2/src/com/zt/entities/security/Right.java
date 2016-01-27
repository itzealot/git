package com.zt.entities.security;

import com.zt.entities.BaseEntity;

/**
 * Ȩ��ʵ�� Ȩ�޷��飬ÿ��6��Ȩ�ޣ�������long�ʹ洢��ͨ����λ����ʵ�֣�������Ӧ��Ȩ���룬����ж��ٸ�Ȩ����
 * 
 * @author zengtao
 *
 */
public class Right extends BaseEntity {

	private static final long serialVersionUID = 5005407349271990549L;
	private Integer id;
	// Ȩ������
	private String rightName = "undefined";
	// Ȩ��Url��ַ
	private String rightUrl;
	// Ȩ������
	private String rightDesc;
	// Ȩ����1<<n��1������nλ
	private long rightCode;
	// Ȩ��λ���൱��Ȩ�޷��飬��0��ʼ
	private int rightPos;

	// ����Ƿ�Ϊ������Դ
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
