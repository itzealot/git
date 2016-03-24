package spring.beans;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.beans.collection.Car;

public class TestHelloWord {
	private ApplicationContext applicationContext = null;

	@Test
	public void testHelloWord() {
		//1.创建Spring的IOC容器对象
		//ClassPathXmlApplicationContext:是ApplicationContext接口的实现类，该实现类从路径下 来加载配置文件。
		applicationContext  = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.从IOC容器中获取Bean实例
		//利用id类定位到IOC容器中的bean
		HelloWord helloWord = (HelloWord) applicationContext .getBean("helloWord");
		//3.调用hello方法
		helloWord.hello();
	}
	
	@Test
	public void testCar() {
		applicationContext  = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.从IOC容器中获取Bean实例
		//利用id类定位到IOC容器中的bean
		Car car = (Car) applicationContext .getBean("car");
		//3.调用toString方法
		System.out.println(car.toString());
	}
}
