package spring.beans.properties;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestPool {
	private ApplicationContext applicationContext = null;
	
	
	@Test
	public void testConnection() {
		applicationContext  = new ClassPathXmlApplicationContext("beans-properties.xml");
		DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(connection);
	}
}
