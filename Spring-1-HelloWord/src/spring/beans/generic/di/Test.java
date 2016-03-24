package spring.beans.generic.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	private static ApplicationContext applicationContext = null;

	public static void main(String[] args) {
		applicationContext  = new ClassPathXmlApplicationContext("beans-generic.xml");
		UserService userService = (UserService) applicationContext .getBean("userService");
		userService.add();
	}
}
