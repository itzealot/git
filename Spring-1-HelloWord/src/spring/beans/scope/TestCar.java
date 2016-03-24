package spring.beans.scope;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.beans.collection.Car;

public class TestCar {
	private ApplicationContext applicationContext = null;
	@Test
	public void testBean() {
		applicationContext  = new ClassPathXmlApplicationContext("beans-scope.xml");
		/**
		 * Beanµ¥Àý
		 */
		Car car = (Car) applicationContext.getBean("car");
		Car car2 = (Car) applicationContext.getBean("car");
		System.out.println(car == car2);
	}
	@Test
	public void testBean2() {
		applicationContext  = new ClassPathXmlApplicationContext("beans-scope.xml");
		/**
		 * scope: prototype, ·Çµ¥Àý
		 */
		Car car = (Car) applicationContext.getBean("car2");
		Car car2 = (Car) applicationContext.getBean("car2");
		System.out.println(car == car2);
	}
}
