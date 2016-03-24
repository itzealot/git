package com.zt.actions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;


@Controller
@Scope("prototype")
public class EntryAction extends ActionSupport {

	private static final long serialVersionUID = -3098056151346217370L;

	public String home() {
		return "home";
	}
	
	public String toErrorPage() {
		return "error";
	}
}
