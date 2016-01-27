package com.zt.ssi.test;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DataSourceTest {

	private ApplicationContext applicationContext = null;

	{
		applicationContext = new ClassPathXmlApplicationContext("beans.xml");
	}

	@Test
	public void testDataSource() throws Exception {
		DataSource dataSource = (DataSource) applicationContext
				.getBean("dataSource");
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}
}
