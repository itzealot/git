package com.zt.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 数据源单元测试类
 * @author zengtao
 *
 */
public class TestDataSource {
	private ApplicationContext applicationContext = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testDataSourceTest() throws SQLException {
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}
}
