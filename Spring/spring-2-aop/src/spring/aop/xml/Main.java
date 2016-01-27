package spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	
	private static ApplicationContext applicationContext = null;

	public static void main(String[] args) {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext-xml.xml");
		
		//2.��IOC �����л�ȡbean��ʵ��
		ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) applicationContext.getBean("arithmeticCalculator");
		
		//3.ʹ��bean
		int result = arithmeticCalculator.add(2, 3);
		System.out.println("result = " + result);
		
		//�׳��쳣
		result = arithmeticCalculator.div(6, 0);
		System.out.println("result = " + result);
	}
}