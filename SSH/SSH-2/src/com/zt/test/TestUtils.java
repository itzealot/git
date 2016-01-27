package com.zt.test;

import org.junit.Test;

import com.zt.utils.LogUtils;
import com.zt.utils.MD5Utils;
import com.zt.utils.StringUtils;
import com.zt.utils.ValidateUtils;

/**
 * ���Թ�����
 * 
 * @author zengtao
 *
 */
public class TestUtils {

	/**
	 * ����MD5�������е�getMD5String����
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMD5Utils_GetMD5String() throws Exception {
		String source = "admin";
		System.out.println(MD5Utils.getMD5String(source));
	}

	/**
	 * ����StringUtils�������е�str2StrArray������displayStrArray����
	 */
	@Test
	public void testStringUtils_Str2StrArray() {
		String str = "a;b;vv;d;a";
		String tag = ";";
		String[] results = StringUtils.str2StrArray(str, tag);
		System.out.println("lengths = " + results.length);
		StringUtils.displayStrArray(results);
	}

	/**
	 * ����StringUtils�е�displayCharArray����
	 */
	@Test
	public void testStringUtils_DisplayCharArray() {
		char[] chs = { 'a', 'b', 'c', 'd' };
		System.out.println("lengths = " + chs.length);
		StringUtils.displayCharArray(chs);
	}

	/**
	 * ����ValidateUtils�������е�isExistSpecialChar����
	 */
	@Test
	public void testValidateUtils_IsExistSpecialChar() {
		String str = "aaasca";
		System.out.println(ValidateUtils.isExistSpecialChar(str));
	}

	/**
	 * ����ValidateUtils�������е�isValidEmail����
	 */
	@Test
	public void testValidateUtils_IsValidEmail() {
		String email = "454s34@qq.com";
		System.out.println(ValidateUtils.isValidEmail(email));
	}

	/**
	 * ����ValidateUtils�������е�comparePass����
	 */
	@Test
	public void testValidateUtils_ComparePass() {
		String pass1 = "aa";
		String pass2 = "aa";
		System.out.println(ValidateUtils.comparePass(pass1, pass2));
	}

	/**
	 * ����ValidateUtils�������е�isValidate����
	 */
	@Test
	public void test_IsValidateLength() {
		String str = "aaaaaaaaaaaaaaaaaa";
		System.out.println(ValidateUtils.isValidate(str, 6, 16));
	}

	/**
	 * ����ValidateUtils�������е�countStringTypeWords����
	 */
	@Test
	public void testValidateUtils_CountStringTypeWords() {
		String str = "000000aa#aaaaaaaaaa";
		System.out.println(ValidateUtils.countStringTypeWords(str));
	}

	/**
	 * ����ValidateUtils�������е�passwordHigh����
	 */
	@Test
	public void testValidateUtils_PasswordHigh() {
		String str = "0000aaaaaa11#aa";
		System.out.println(ValidateUtils.passwordHigh(str));
	}

	/**
	 * ����LogUtils�������е�generateLogTableName����
	 */
	@Test
	public void testLogUtils_GenerateLogTableName() {
		System.out.println(LogUtils.generateLogTableName(1));
		System.out.println(LogUtils.generateLogTableName(2));
		System.out.println(LogUtils.generateLogTableName(3));
		System.out.println(LogUtils.generateLogTableName(-1));
		System.out.println(LogUtils.generateLogTableName(-2));
	}
	
}
