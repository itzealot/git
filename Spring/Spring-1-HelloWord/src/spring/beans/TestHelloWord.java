package spring.beans;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.beans.collection.Car;

public class TestHelloWord {
	private ApplicationContext applicationContext = null;

	@Test
	public void testHelloWord() {
		//1.����Spring��IOC��������
		//ClassPathXmlApplicationContext:��ApplicationContext�ӿڵ�ʵ���࣬��ʵ�����·���� �����������ļ���
		applicationContext  = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.��IOC�����л�ȡBeanʵ��
		//����id�ඨλ��IOC�����е�bean
		HelloWord helloWord = (HelloWord) applicationContext .getBean("helloWord");
		//3.����hello����
		helloWord.hello();
	}
	
	@Test
	public void testCar() {
		applicationContext  = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.��IOC�����л�ȡBeanʵ��
		//����id�ඨλ��IOC�����е�bean
		Car car = (Car) applicationContext .getBean("car");
		//3.����toString����
		System.out.println(car.toString());
	}
}
