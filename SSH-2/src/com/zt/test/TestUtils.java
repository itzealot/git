package com.zt.test;

import org.junit.Test;

import com.zt.utils.LogUtils;
import com.zt.utils.MD5Utils;
import com.zt.utils.StringUtils;
import com.zt.utils.ValidateUtils;

/**
 * 测试工具类
 * 
 * @author zengtao
 *
 */
public class TestUtils {

	/**
	 * 测试MD5工具类中的getMD5String方法
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMD5Utils_GetMD5String() throws Exception {
		String source = "admin";
		System.out.println(MD5Utils.getMD5String(source));
	}

	/**
	 * 测试StringUtils工具类中的str2StrArray方法和displayStrArray方法
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
	 * 测试StringUtils中的displayCharArray方法
	 */
	@Test
	public void testStringUtils_DisplayCharArray() {
		char[] chs = { 'a', 'b', 'c', 'd' };
		System.out.println("lengths = " + chs.length);
		StringUtils.displayCharArray(chs);
	}

	/**
	 * 测试ValidateUtils工具类中的isExistSpecialChar方法
	 */
	@Test
	public void testValidateUtils_IsExistSpecialChar() {
		String str = "aaasca";
		System.out.println(ValidateUtils.isExistSpecialChar(str));
	}

	/**
	 * 测试ValidateUtils工具类中的isValidEmail方法
	 */
	@Test
	public void testValidateUtils_IsValidEmail() {
		String email = "454s34@qq.com";
		System.out.println(ValidateUtils.isValidEmail(email));
	}

	/**
	 * 测试ValidateUtils工具类中的comparePass方法
	 */
	@Test
	public void testValidateUtils_ComparePass() {
		String pass1 = "aa";
		String pass2 = "aa";
		System.out.println(ValidateUtils.comparePass(pass1, pass2));
	}

	/**
	 * 测试ValidateUtils工具类中等isValidate方法
	 */
	@Test
	public void test_IsValidateLength() {
		String str = "aaaaaaaaaaaaaaaaaa";
		System.out.println(ValidateUtils.isValidate(str, 6, 16));
	}

	/**
	 * 测试ValidateUtils工具类中的countStringTypeWords方法
	 */
	@Test
	public void testValidateUtils_CountStringTypeWords() {
		String str = "000000aa#aaaaaaaaaa";
		System.out.println(ValidateUtils.countStringTypeWords(str));
	}

	/**
	 * 测试ValidateUtils工具类中的passwordHigh方法
	 */
	@Test
	public void testValidateUtils_PasswordHigh() {
		String str = "0000aaaaaa11#aa";
		System.out.println(ValidateUtils.passwordHigh(str));
	}

	/**
	 * 测试LogUtils工具类中的generateLogTableName方法
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
