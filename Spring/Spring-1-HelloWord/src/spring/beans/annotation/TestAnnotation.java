package spring.beans.annotation;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.beans.annotation.controller.UserController;
import spring.beans.annotation.repository.UserRepository;
import spring.beans.annotation.service.UserService;

public class TestAnnotation {
	private ApplicationContext applicationContext = null;
	@Test
	public void test() {
		applicationContext = new ClassPathXmlApplicationContext("beans-annotation.xml");
		
		TestObject to = (TestObject) applicationContext.getBean("testObject");
		System.out.println(to);
		
		
		UserController controller = (UserController) applicationContext.getBean("userController");
		System.out.println(controller);
		
		UserService service = (UserService) applicationContext.getBean("userService");
		System.out.println(service);
		
		
		UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
		System.out.println(userRepository);
	}
	/**
	 * ≈‰÷√¡Àresource-pattern ±
	 */
	@Test
	public void test2() {
		applicationContext = new ClassPathXmlApplicationContext("beans-annotation.xml");
		UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
		System.out.println(userRepository);
	}
}
