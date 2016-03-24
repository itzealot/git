package com.zt.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestSetUtil {

	@Test
	public void clearSet() {
		Set<Object> sets = new HashSet<Object>();
		sets.add("1");
		sets.add("2");
		sets.add("3");

		System.out.println(sets);

		// 即为引用类型
		Set<Object> s = sets;
		s.clear();
		System.out.println(s);
		System.out.println(sets);
	}
}
