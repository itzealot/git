package com.zt.ssi.dao;

import org.apache.ibatis.session.SqlSessionFactory;

public class BaseDao {
	private SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
}
