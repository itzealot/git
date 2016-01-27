package com.zt.interceptors;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zt.actions.BaseAction;
import com.zt.actions.LoginAction;
import com.zt.entities.Staff;
import com.zt.struts2.StaffAware;

/**
 * 实现对为登入或注销用户的拦截器
 * 
 * @author zengtao
 *
 */
public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = 3211985100339832551L;

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
		// To get HttpServletRequest request and HttpSession session
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 登入Action
		if (action instanceof LoginAction) {
			// 放行，去登入
			return arg0.invoke();
		}

		Staff staff = (Staff) session.getAttribute("staff");
		// 没有登入
		if (staff == null) {
			// 去登入界面
			return "login";
		} else { // 已经登入
			// StaffAware处理,注入staff
			if (action != null && action instanceof StaffAware) {
				((StaffAware) action).setStaff(staff);
			}
		}
		// 放行
		return arg0.invoke();
	}
}
