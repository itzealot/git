package com.zt.interceptors;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zt.service.RightService;
import com.zt.utils.ValidateUtils;

/**
 * ͨ����������������url���������غ���ӵ�����Ȩ�޹���
 * 
 * @author zengtao
 *
 */
public class CatchUrlInterceptor implements Interceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2012828406029439322L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		ActionProxy proxy = arg0.getProxy();
		// ���ֿռ�
		String ns = proxy.getNamespace();

		// actionName
		String actionName = proxy.getActionName();

		// ���������ռ䣬��Ϊ��Ч����Ϊ/����Ϊns
		if (!ValidateUtils.isValid(ns) || ns.equals("/")) {
			ns = "";
		}
		// ���������ռ��actionName�õ���Ӧ��url
		String url = ns + "/" + actionName;

		// ȡ����application��spring�������ȽϷ���������ʹ�õڶ��ַ���
		// ApplicationContext context = (ApplicationContext) arg0
		// .getInvocationContext()
		// .getApplication()
		// .get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		// ͨ�����·���ȡ��Ȩ��Serivice����url���浽���ݿ���
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		RightService rightService = (RightService) context
				.getBean("rightService");

		// ͨ��rightService��ӵ����ݿ���
		rightService.appendRightByURL(url);
		// ����
		return arg0.invoke();
	}

}
