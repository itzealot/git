package com.zt.ssi.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zt.ssi.model.User;
import com.zt.ssi.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public String add() {
		boolean flag = userService.addUser(user);
		if(!flag) {
			return "add-fail";
		}
		return "add-success";
	}

	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String query() {
		if(name == null) {
			return "query-fail";
		}
		userService.queryUser(name);
		return "query-success";
	}
	
	private User user;
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		if(user == null) {
			user = new User();
		}
		return user;
	}
}
