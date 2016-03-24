package com.zt.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;

import com.zt.beans.Person;

@WebServlet("/testServlet")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.从application 域对象中得到IOC容器的引用
		ServletContext servletContext = getServletContext();
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		
		//2.从IOC容器在得到需要的bean
		Person person = applicationContext.getBean(Person.class);
		person.sayHello();
	}
}
