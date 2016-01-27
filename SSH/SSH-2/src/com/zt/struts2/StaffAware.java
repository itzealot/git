package com.zt.struts2;

import com.zt.entities.Staff;

/**
 * StaffAware接口，注入Staff
 * 
 * @author zengtao
 *
 */
public interface StaffAware {

	/**
	 * 注入Staff
	 * 
	 * @param staff
	 */
	public void setStaff(Staff staff);
}
