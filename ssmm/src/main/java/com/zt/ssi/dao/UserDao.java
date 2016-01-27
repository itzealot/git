package com.zt.ssi.dao;

import com.zt.ssi.model.User;

public interface UserDao {

	/**
	 * To add User
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * To get User By name
	 * 
	 * @param name
	 * @return
	 */
	public User queryUserByName(String name);
}
