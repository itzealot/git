package com.zt.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * �Զ�������Դ·���������ڷֲ�ʽ���ݿ�<br />
 * �÷���������
 * 
 * @author zengtao
 *
 */
public class DataSourceRouter extends AbstractRoutingDataSource {

	/**
	 * ����key������ҪѰ�ҵ�����Դkey����spring�����ļ�����odd,even <br />
	 * ������null����Ĭ�ϵ�dataSource <br />
	 * ��⵱ǰkey <br />
	 */
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		Token token = Token.getCurrentToken();
		if (token != null) {
			Integer id = token.getObject().getId();

			// ������Ƶİ�
			Token.unbindToken();
			return (id % 2) == 0 ? "even" : "odd";
		}
		return null;
	}

}
