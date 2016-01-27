package iface2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConstructor {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Human human = null;
		/**
		 * To get object by bean's id where id = "Chinese"
		 */
		human = (Human) ctx.getBean("Chinese");
		human.speak();
	}
}
