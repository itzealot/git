package spring.aop.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private static ApplicationContext applicationContext = null;

	public static void main(String[] args) {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.从IOC 容器中获取bean的实例
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) applicationContext.getBean("arithmeticCalculator");
		
		//3.使用bean
		int result = arithmeticCalculator.add(2, 3);
		System.out.println("result = " + result);
		
		result = arithmeticCalculator.div(6, 3);
		System.out.println("result = " + result);
	}
}
