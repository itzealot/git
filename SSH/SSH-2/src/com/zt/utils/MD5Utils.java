package com.zt.utils;

import java.security.MessageDigest;

/**
 * ʹ��MD5�㷨���м���
 * 
 * @author zengtao
 *
 */
public class MD5Utils {

	/**
	 * ��������Դ������MD5���ܴ������ؼ��ܺ�Ĵ�
	 * @param source
	 * @return ���ض�Դ�����ܺ�Ĵ�
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
			// ��ø���λ To get the 4 high bits
			buffer.append(chars[b >> 4 & 0x0F]);
			// ��õ���λ To get the 4 low bits
			buffer.append(chars[b & 0x0F]);
		}
		return buffer.toString();
	}
}
