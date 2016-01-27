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
 * 通过该拦截器，捕获url，进行拦截和添加到进行权限管理
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
		// 名字空间
		String ns = proxy.getNamespace();

		// actionName
		String actionName = proxy.getActionName();

		// 处理命名空间，若为无效或者为/则处理为ns
		if (!ValidateUtils.isValid(ns) || ns.equals("/")) {
			ns = "";
		}
		// 根据命名空间和actionName得到相应的url
		String url = ns + "/" + actionName;

		// 取得在application在spring容器，比较繁琐，下面使用第二种方法
		// ApplicationContext context = (ApplicationContext) arg0
		// .getInvocationContext()
		// .getApplication()
		// .get(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);

		// 通过以下方法取得权限Serivice来将url保存到数据库中
		ServletContext sc = ServletActionContext.getServletContext();
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(sc);
		RightService rightService = (RightService) context
				.getBean("rightService");

		// 通过rightService添加到数据库中
		rightService.appendRightByURL(url);
		// 放行
		return arg0.invoke();
	}

}
