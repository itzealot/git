package com.zt.actions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zt.entities.User;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 1395193853996083982L;

	public String toErrorPage() {
		return "error";
	}
}
