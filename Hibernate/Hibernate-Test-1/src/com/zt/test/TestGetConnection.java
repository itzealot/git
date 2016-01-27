package com.zt.test;

import java.sql.Connection;

import org.junit.Test;

import com.zt.common.GetConnection;

public class TestGetConnection {
	
	@Test
	public void testGetConnection() throws Exception {
		Connection connection = GetConnection.getConnection();
		System.out.println(connection);
		GetConnection.releaseDB(connection);
	}
}
