package com.zt.utils;

import java.security.MessageDigest;

/**
 * 使用MD5算法进行加密
 * 
 * @author zengtao
 *
 */
public class MD5Utils {

	/**
	 * 对所给的源串进行MD5加密处理，返回加密后的串
	 * @param source
	 * @return 返回对源串加密后的串
	 * @throws Exception
	 */
	public static String getMD5String(String source) throws Exception {
		StringBuffer buffer = new StringBuffer();
		char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		byte[] bytes = source.getBytes();
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] targ = digest.digest(bytes);
		for (byte b : targ) {
			// 获得高四位 To get the 4 high bits
			buffer.append(chars[b >> 4 & 0x0F]);
			// 获得低四位 To get the 4 low bits
			buffer.append(chars[b & 0x0F]);
		}
		return buffer.toString();
	}
}
