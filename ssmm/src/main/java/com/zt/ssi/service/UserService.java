package com.zt.ssi.service;

import com.zt.ssi.model.User;

public interface UserService {
	/**
	 * To add User
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * To query User by name
	 * 
	 * @param name
	 * @return
	 */
	public User queryUserByName(String name);
}
