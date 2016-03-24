package com.zt.utils;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.zt.actions.BaseAction;
import com.zt.entities.Staff;
import com.zt.entities.security.Right;

/**
 * 校验工具类
 * 
 * @author zengtao
 *
 */
public class ValidateUtils {

	public final static int PASSWORD_MAX_LEN = 16;
	public final static int PASSWORD_MIN_LEN = 6;
	public final static int PASSWORD_MID_LEN = 11;

	/**
	 * 判断字符串有效性，若有效返回true；否则返回false
	 * 
	 * @param source
	 * @return true or false
	 */
	public static boolean isValid(String source) {
		return !(source == null || "".equals(source.trim()));
	}

	/**
	 * 校验是否符合密码格式
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isValidPassword(String source) {
		return !(source == null || "".equals(source));
	}

	/**
	 * 判断集合的有效性
	 * 
	 * @param col
	 * @return 若集合有效，则返回true；否则返回false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Collection col) {
		if (col == null || col.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断给定的字符串是否存在特殊字符，若存在，则返回true；否则返回false
	 * 
	 * @param source
	 * @return true or false
	 */
	public static boolean isExistSpecialChar(String source) {
		if (!isValid(source)) {
			return false;
		}
		char[] specials = { '*', ' ', ';', ',', '\\', '/', '-' };
		for (char ch : specials) {
			// the special char is existing
			if (source.indexOf(ch) != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断Email是否有效，若有效，返回true；否则返回false
	 * 
	 * @param email
	 * @return true or false
	 */
	public static boolean isValidEmail(String email) {
		if (!isValid(email)) {
			return false;
		}
		if (isExistSpecialChar(email)) {
			return false;
		}
		int index1 = email.lastIndexOf("@");
		int index2 = email.lastIndexOf(".com");
		String str = email.substring(0, index1);
		if (index1 < index2 && isValid(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 校验两次输入的密码是否相同，若有一者为空，应该返回false；若相同返回true；否则返回false
	 * 
	 * @param pass1
	 * @param pass2
	 * @return
	 */
	public static boolean comparePass(String pass1, String pass2) {
		if (!isValid(pass1)) {
			return false;
		}
		if (pass2 == null) {
			return false;
		}
		return pass1.equals(pass2);
	}

	/**
	 * 判断密码的长度是否符合允许长度范围内；若符合，返回true；否则返回false
	 * 
	 * @param str
	 * @param minLength
	 * @param maxLenth
	 * @return
	 */
	public static boolean isValidate(String str, int minLength, int maxLenth) {
		if (str.length() >= minLength && str.length() <= maxLenth) {
			return true;
		}
		return false;
	}

	/**
	 * 计算字符串包含字符类型的数量，返回0表示为空串；返回1，表示只包含一种字符；<br />
	 * 返回2，表示包含两种字符； 返回3表示包含三种字符. <br />
	 * 
	 * @param source
	 * @return
	 */
	public static int countStringTypeWords(String source) {
		if (!isValid(source)) {
			return 0;
		}
		// 转换为小写字母
		String str = source.toLowerCase();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		char[] words = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z' };
		char[] specials = { ',', ' ', ';', '/', '\\', '!', '?', '$', '%', '^',
				'@', '#', '&', '*', '(', ')', '{', '}', '[', ']', '|', '-',
				'_', '+', '=' };
		int counts = 0;
		for (char ch : digits) {
			if (str.indexOf(ch) != -1) {
				// 若找到一个，先累加，再结束循环
				counts++;
				break;
			}
		}
		for (char ch : words) {
			if (str.indexOf(ch) != -1) {
				// 若找到一个，先累加，再结束循环
				counts++;
				break;
			}
		}
		for (char ch : specials) {
			if (str.indexOf(ch) != -1) {
				// 若找到一个，先累加，再结束循环
				counts++;
				break;
			}
		}
		return counts;
	}

	/**
	 * 判断密码的强度范围，若返回0，表示密码无效；若返回1，密码强度表示弱；<br />
	 * 若返回2，表示密码强度中等； 若返回3，表示密码强度强 <br />
	 * 
	 * @param password
	 * @return
	 */
	public static int passwordHigh(String password) {
		if (!isValid(password)) {
			return 0;
		}
		// 密码长度不在6-16之间
		if (!isValidate(password, PASSWORD_MIN_LEN, PASSWORD_MAX_LEN)) {
			return 0;
		}
		// 含三种字符且长度在PASSWORD_MID_LEN与PASSWORD_MAX_LEN之间，密码强度为高，返回3
		if (countStringTypeWords(password) == 3
				&& isValidate(password, PASSWORD_MID_LEN, PASSWORD_MAX_LEN)) {
			return 3;
		}
		// 否则，若只含一种字符或者含两种字符但密码长度在PASSWORD_MIN_LEN与PASSWORD_MID_LEN之间，
		// 密码强度为若，返回1
		if (countStringTypeWords(password) == 1
				|| (countStringTypeWords(password) == 2 && isValidate(password,
						PASSWORD_MIN_LEN, PASSWORD_MID_LEN))) {
			return 1;
		}
		// 否则密码强度中等，返回2
		return 2;
	}

	/**
	 * 判断数组是否有效
	 * 
	 * @param objects
	 * @return
	 */
	public static boolean isValid(Object[] objects) {
		if (objects == null || objects.length == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否拥有权限
	 */
	@SuppressWarnings("rawtypes")
	public static boolean hasRight(String namespace, String actionName,
			HttpServletRequest request, BaseAction action) {
		if (isValid(namespace) || "/".equals(namespace)) {
			namespace = "";
		}
		// System.out.println("actionName = " + actionName);

		// 将超链接中的'?', ',', '.'号及其他参数滤掉
		if (actionName.contains("?")) {
			actionName = actionName.substring(0, actionName.indexOf("?"));
		} else if (actionName.contains(";")) {
			actionName = actionName.substring(0, actionName.indexOf(";"));
		} else if (actionName.contains(".")) {
			actionName = actionName.substring(0, actionName.indexOf("."));
		}

		String url = namespace + "/" + actionName;
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();

		// 从权限map中取出权限
		@SuppressWarnings("unchecked")
		Map<String, Right> map = (Map<String, Right>) context
				.getAttribute("all rights map");

		// get right from map by String key using url
		Right r = map.get(url);
		// 公共资源?
		if (r == null || r.isCommon()) {
			return true;
		}

		// 获取登入用户的信息
		Staff staff = (Staff) session.getAttribute("staff");

		if (staff == null) {
			return false;
		}

		if (!isValid(staff.getRightSum())) {// 未计算权限和
			// 计算权限和
			staff.calculateRightSum();
		}

		// 超级管理员?
		if (staff.isSuperAdmin()) {
			return true;
		} else {
			// 有权限?，员工是否拥有该权限
			if (staff.hasRight(r)) {
				return true;// 有返回true；
			} else {
				return false;// 返回false
			}
		}
	}

	private static boolean isValid(long[] rightSum) {
		// TODO Auto-generated method stub
		if (rightSum == null || rightSum.length == 0) {
			return false;
		}
		return true;
	}
}
