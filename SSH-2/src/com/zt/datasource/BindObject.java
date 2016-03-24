package com.zt.datasource;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class BindObject implements SessionAware {

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获得当前的Object
	 * 
	 * @return
	 */
	public BindObject getCurrentBindObject() {
		return (BindObject) session.get("bindObject");
	}

	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	/**
	 * 绑定到当前线程
	 */
	public void bindToCurrentThread() {
		// token到当前线程
		Token token = new Token();
		token.setObject(getCurrentBindObject());
		// 绑定令牌
		Token.bindToken(token);
	}
}
