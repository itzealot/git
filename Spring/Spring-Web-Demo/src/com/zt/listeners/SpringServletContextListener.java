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
    	//1.�@ȡSpring �����ļ�������
    	ServletContext servletContext = arg0.getServletContext();
    	String config = servletContext.getInitParameter("configLocation");
    	
    	//1.����IOC����
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
    	
    	//2.��IOC��������ServletContext ��һ�������С�
    	servletContext.setAttribute("applicationContext", applicationContext);
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
