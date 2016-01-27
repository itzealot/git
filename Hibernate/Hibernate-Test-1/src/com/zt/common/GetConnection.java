package com.zt.common;

import java.sql.Connection;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class GetConnection {
	public static DataSource dataSource = null;
	//数据库连接池应只被初始化一次
	static {
		dataSource = new ComboPooledDataSource("helloc3p0");
	}
	
	public static Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
	
	public static void releaseDB(Connection connection) {
		if(connection != null) {
			try {
				//数据库连接池的Connection 对象进行close时，并不是真的
				//进行关闭，而是把数据库链接会归还到数据库连接池。
				connection.close();
			} catch (Exception e) {
				
			}
		}
	}
}
