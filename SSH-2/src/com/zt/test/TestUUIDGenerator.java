package com.zt.test;

import org.hibernate.id.UUIDGenerator;
import org.junit.Test;

/**
 * ����Hibernate���������ɵķ�����ͨ��UUIDGenerator
 * 
 * @author zengtao
 *
 */
public class TestUUIDGenerator {

	@Test
	public void test() {
		String id = UUIDGenerator
				.buildSessionFactoryUniqueIdentifierGenerator()
				.generate(null, null).toString();
		System.out.println("id = " + id);
	}
}
