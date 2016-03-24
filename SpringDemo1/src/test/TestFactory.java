package test;
import iface.Human;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestFactory {
	public static void main(String[] args) {
		/*Factory factory = new Factory();
		Human chinese = factory.createChinese();
		chinese.eat();
		chinese.walk();*/
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Human human = null;
		/**
		 * get bean by bean's name that id="chinese"
		 */
		human = (Human) ctx.getBean("chinese");
		human.eat();
		human.walk();
		/**
		 * get bean by bean's name that id="american"
		 */
		human = (Human) ctx.getBean("american");
		human.eat();
		human.walk();
	}
}
