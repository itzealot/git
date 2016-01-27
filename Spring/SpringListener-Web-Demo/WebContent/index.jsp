<%@page import="com.zt.beans.Person"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//1.从application 域对象在得到 IOC容器的实例
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(application);
		
		//2.从IOC容器中得到bean
		Person person = applicationContext.getBean(Person.class);
		
		//3.使用bean
		person.sayHello();
	%>
</body>
</html>