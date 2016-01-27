package com.zt.ssi.dao;

import com.zt.ssi.model.User;

public interface UserDao {
	
	public void insertUser(User user);
	
	public User getUser(String name);
	
//	public User queryById(Integer userId);
	
//	public User query(String username, String password);
}
