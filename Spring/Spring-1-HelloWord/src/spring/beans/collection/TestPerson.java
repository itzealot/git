package spring.beans.collection;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPerson {
	private ApplicationContext applicationContext = null;
	@Test
	public void testPerson() {
		applicationContext  = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Person person = (Person) applicationContext .getBean("person");
		//3.调用toString方法
		System.out.println(person.toString());
	}
}
