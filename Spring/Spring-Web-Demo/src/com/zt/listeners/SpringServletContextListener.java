package com.zt.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebListener
public class SpringServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	//1.獲取Spring 配置文件的名称
    	ServletContext servletContext = arg0.getServletContext();
    	String config = servletContext.getInitParameter("configLocation");
    	
    	//1.创建IOC容器
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
    	
    	//2.把IOC容器放在ServletContext 的一个属性中。
    	servletContext.setAttribute("applicationContext", applicationContext);
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
