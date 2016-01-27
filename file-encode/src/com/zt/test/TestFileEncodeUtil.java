package com.zt.test;

import org.junit.Test;

/**
 * Test FileEncodeUtil.<br />
 * 
 * Title: TestFileEncodeUtil.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年8月31日
 */
public class TestFileEncodeUtil {
	@Test
	public void testGetFileCharset() {
		System.out.println(FileEncodeUtil.getFileCharset("E:/test.txt"));
	}
}
