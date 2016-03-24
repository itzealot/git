package iface2;

import iface2.Human;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSetter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Human human = null;
		/**
		 * To get object by bean's id where id = "saychinese"
		 */
		human = (Human) ctx.getBean("saychinese");
		human.speak();
	}

}
