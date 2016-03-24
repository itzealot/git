package spring.beans.factorybean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.beans.factorybean.Car;

public class TestCarFactoryBean {
	private ApplicationContext applicationContext = null;
	@Test
	public void testStaticCarFactory() {
		applicationContext  = new ClassPathXmlApplicationContext("beans-factorybean.xml");
		Car car = (Car) applicationContext.getBean("car");
		System.out.println(car);
	}
	
}
