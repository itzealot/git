package com.zt.common;

import java.sql.Connection;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class GetConnection {
	public static DataSource dataSource = null;
	//���ݿ����ӳ�Ӧֻ����ʼ��һ��
	static {
		dataSource = new ComboPooledDataSource("helloc3p0");
	}
	
	public static Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
	
	public static void releaseDB(Connection connection) {
		if(connection != null) {
			try {
				//���ݿ����ӳص�Connection �������closeʱ�����������
				//���йرգ����ǰ����ݿ����ӻ�黹�����ݿ����ӳء�
				connection.close();
			} catch (Exception e) {
				
			}
		}
	}
}
