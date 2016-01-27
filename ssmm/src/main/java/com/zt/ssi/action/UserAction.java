package com.zt.ssi.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zt.ssi.model.User;
import com.zt.ssi.service.UserService;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private static final long serialVersionUID = 1636400281118764131L;

	@Resource
	private UserService userService;

	/**
	 * To add User
	 */
	public String addUser() {
		userService.addUser(user);
		return "add-success";
	}

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * To query User
	 */
	public String queryUser() {
		if (name == null) {
			return "query-fail";
		}
		userService.queryUserByName(name);
		return "query-success";
	}

	// To get the model User object
	private User user;

	public User getModel() {
		// TODO Auto-generated method stub
		if (user == null) {
			user = new User();
		}
		return user;
	}
}
