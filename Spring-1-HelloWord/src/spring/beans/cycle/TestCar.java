package spring.beans.cycle;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCar {
private ClassPathXmlApplicationContext ctx = null;
	@Test
	public void testConnection() {
		ctx  = new ClassPathXmlApplicationContext("beans-cycle.xml");
		Car car = (Car) ctx.getBean("car");
		System.out.println(car);
		
		//�ر�IOC��������������Bean�ķ���
		ctx.close();
	}
}
