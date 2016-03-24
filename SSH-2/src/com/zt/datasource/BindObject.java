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
	 * ��õ�ǰ��Object
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
	 * �󶨵���ǰ�߳�
	 */
	public void bindToCurrentThread() {
		// token����ǰ�߳�
		Token token = new Token();
		token.setObject(getCurrentBindObject());
		// ������
		Token.bindToken(token);
	}
}
