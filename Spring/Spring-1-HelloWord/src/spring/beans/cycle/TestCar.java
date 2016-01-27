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
		
		//关闭IOC容器，调用销毁Bean的方法
		ctx.close();
	}
}
