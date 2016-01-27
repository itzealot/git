package com.zt.ssi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zt.ssi.dao.UserDao;
import com.zt.ssi.model.User;
import com.zt.ssi.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * To add User
	 */
	// 事务管理
	@Transactional(rollbackForClassName = "Exception")
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
		try {
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * To query User by name
	 */
	@Override
	public User queryUserByName(String name) {
		return userDao.queryUserByName(name);
	}
}
