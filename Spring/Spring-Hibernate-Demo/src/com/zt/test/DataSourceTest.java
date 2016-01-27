package com.zt.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.service.BookService;

public class DataSourceTest {
	private ApplicationContext applicationContext = null;
	private BookService bookService = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@Test
	public void testBookService() {
		bookService = applicationContext.getBean(BookService.class);
		bookService.purchase("AA", "1001");
	}
	
	@Test
	public void testDataSourceTest() throws SQLException {
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		connection.close();
	}
}
