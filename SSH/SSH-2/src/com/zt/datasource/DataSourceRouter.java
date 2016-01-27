package com.zt.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 自定义数据源路由器，用于分布式数据库<br />
 * 该方法有问题
 * 
 * @author zengtao
 *
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

	/**
	 * 返回key，即需要寻找的数据源key，在spring配置文件中有odd,even <br />
	 * 若返回null，找默认的dataSource <br />
	 * 检测当前key <br />
	 */
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		Token token = Token.getCurrentToken();
		if (token != null) {
			Integer id = token.getObject().getId();

			// 解除令牌的绑定
			Token.unbindToken();
			return (id % 2) == 0 ? "even" : "odd";
		}
		return null;
	}

}
