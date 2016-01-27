package com.zt.interceptors;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zt.actions.BaseAction;
import com.zt.utils.ValidateUtils;

/**
 * 权限过滤拦截器
 * 
 * @author zengtao
 *
 */
public class RightFilterInterceptor implements Interceptor {

	private static final long serialVersionUID = 2285670854280912194L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		BaseAction action = (BaseAction) arg0.getAction();
		ActionProxy proxy = arg0.getProxy();
		String ns = proxy.getNamespace();
		String actionName = proxy.getActionName();

		// 有权限，则放行
		if (ValidateUtils.hasRight(ns, actionName,
				ServletActionContext.getRequest(), action)) {
			return arg0.invoke();
		}
		// 没有权限，返回的登入的首页
		return "login-success";
	}

}
