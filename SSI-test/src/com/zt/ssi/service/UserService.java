package com.zt.ssi.service;

import com.zt.ssi.dao.BaseDao;
import com.zt.ssi.dao.UserDao;
import com.zt.ssi.model.User;

public class UserService extends BaseDao {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public boolean addUser(User user) {
		userDao.insertUser(user);
		return true;
	}
	
	public User queryUser(String name) {
		return userDao.getUser(name);
	}
}
