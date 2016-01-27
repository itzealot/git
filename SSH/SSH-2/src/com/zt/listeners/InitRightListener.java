package com.zt.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.zt.entities.security.Right;
import com.zt.service.RightService;

/**
 * 初始化权限监听器，在spring初始化完成后立即调用
 * 
 * @author zengtao
 *
 */
// 交给spring容器管理
@Component
@SuppressWarnings("rawtypes")
public class InitRightListener implements ApplicationListener,
		ServletContextAware {

	@Resource
	private RightService rightService;

	// 接受ServletContext对象
	private ServletContext servletContext;

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		// 上下文刷新事件
		if (arg0 instanceof ContextRefreshedEvent) {
			// 查出所有权限
			List<Right> rights = rightService.findAllEntities();
			Map<String, Right> map = new HashMap<String, Right>();

			for (Right r : rights) {
				map.put(r.getRightUrl(), r);
			}

			if (servletContext != null) {
				servletContext.setAttribute("all rights map", map);
				System.out.println("Init Rights in Application Object...");
			}
		}
	}

	// 注入ServletContext对象
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.servletContext = arg0;
	}

}
