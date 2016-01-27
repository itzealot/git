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
 * ʵ�ֶ�Ϊ�����ע���û���������
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
		// ����Action
		if (action instanceof LoginAction) {
			// ���У�ȥ����
			return arg0.invoke();
		}

		Staff staff = (Staff) session.getAttribute("staff");
		// û�е���
		if (staff == null) {
			// ȥ�������
			return "login";
		} else { // �Ѿ�����
			// StaffAware����,ע��staff
			if (action != null && action instanceof StaffAware) {
				((StaffAware) action).setStaff(staff);
			}
		}
		// ����
		return arg0.invoke();
	}
}
