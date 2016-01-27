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
 * У�鹤����
 * 
 * @author zengtao
 *
 */
public class ValidateUtils {

	public final static int PASSWORD_MAX_LEN = 16;
	public final static int PASSWORD_MIN_LEN = 6;
	public final static int PASSWORD_MID_LEN = 11;

	/**
	 * �ж��ַ�����Ч�ԣ�����Ч����true�����򷵻�false
	 * 
	 * @param source
	 * @return true or false
	 */
	public static boolean isValid(String source) {
		return !(source == null || "".equals(source.trim()));
	}

	/**
	 * У���Ƿ���������ʽ
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isValidPassword(String source) {
		return !(source == null || "".equals(source));
	}

	/**
	 * �жϼ��ϵ���Ч��
	 * 
	 * @param col
	 * @return ��������Ч���򷵻�true�����򷵻�false
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isValid(Collection col) {
		if (col == null || col.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * �жϸ������ַ����Ƿ���������ַ��������ڣ��򷵻�true�����򷵻�false
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
	 * �ж�Email�Ƿ���Ч������Ч������true�����򷵻�false
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
	 * У����������������Ƿ���ͬ������һ��Ϊ�գ�Ӧ�÷���false������ͬ����true�����򷵻�false
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
	 * �ж�����ĳ����Ƿ���������ȷ�Χ�ڣ������ϣ�����true�����򷵻�false
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
	 * �����ַ��������ַ����͵�����������0��ʾΪ�մ�������1����ʾֻ����һ���ַ���<br />
	 * ����2����ʾ���������ַ��� ����3��ʾ���������ַ�. <br />
	 * 
	 * @param source
	 * @return
	 */
	public static int countStringTypeWords(String source) {
		if (!isValid(source)) {
			return 0;
		}
		// ת��ΪСд��ĸ
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
				// ���ҵ�һ�������ۼӣ��ٽ���ѭ��
				counts++;
				break;
			}
		}
		for (char ch : words) {
			if (str.indexOf(ch) != -1) {
				// ���ҵ�һ�������ۼӣ��ٽ���ѭ��
				counts++;
				break;
			}
		}
		for (char ch : specials) {
			if (str.indexOf(ch) != -1) {
				// ���ҵ�һ�������ۼӣ��ٽ���ѭ��
				counts++;
				break;
			}
		}
		return counts;
	}

	/**
	 * �ж������ǿ�ȷ�Χ��������0����ʾ������Ч��������1������ǿ�ȱ�ʾ����<br />
	 * ������2����ʾ����ǿ���еȣ� ������3����ʾ����ǿ��ǿ <br />
	 * 
	 * @param password
	 * @return
	 */
	public static int passwordHigh(String password) {
		if (!isValid(password)) {
			return 0;
		}
		// ���볤�Ȳ���6-16֮��
		if (!isValidate(password, PASSWORD_MIN_LEN, PASSWORD_MAX_LEN)) {
			return 0;
		}
		// �������ַ��ҳ�����PASSWORD_MID_LEN��PASSWORD_MAX_LEN֮�䣬����ǿ��Ϊ�ߣ�����3
		if (countStringTypeWords(password) == 3
				&& isValidate(password, PASSWORD_MID_LEN, PASSWORD_MAX_LEN)) {
			return 3;
		}
		// ������ֻ��һ���ַ����ߺ������ַ������볤����PASSWORD_MIN_LEN��PASSWORD_MID_LEN֮�䣬
		// ����ǿ��Ϊ��������1
		if (countStringTypeWords(password) == 1
				|| (countStringTypeWords(password) == 2 && isValidate(password,
						PASSWORD_MIN_LEN, PASSWORD_MID_LEN))) {
			return 1;
		}
		// ��������ǿ���еȣ�����2
		return 2;
	}

	/**
	 * �ж������Ƿ���Ч
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
	 * �ж��Ƿ�ӵ��Ȩ��
	 */
	@SuppressWarnings("rawtypes")
	public static boolean hasRight(String namespace, String actionName,
			HttpServletRequest request, BaseAction action) {
		if (isValid(namespace) || "/".equals(namespace)) {
			namespace = "";
		}
		// System.out.println("actionName = " + actionName);

		// ���������е�'?', ',', '.'�ż����������˵�
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

		// ��Ȩ��map��ȡ��Ȩ��
		@SuppressWarnings("unchecked")
		Map<String, Right> map = (Map<String, Right>) context
				.getAttribute("all rights map");

		// get right from map by String key using url
		Right r = map.get(url);
		// ������Դ?
		if (r == null || r.isCommon()) {
			return true;
		}

		// ��ȡ�����û�����Ϣ
		Staff staff = (Staff) session.getAttribute("staff");

		if (staff == null) {
			return false;
		}

		if (!isValid(staff.getRightSum())) {// δ����Ȩ�޺�
			// ����Ȩ�޺�
			staff.calculateRightSum();
		}

		// ��������Ա?
		if (staff.isSuperAdmin()) {
			return true;
		} else {
			// ��Ȩ��?��Ա���Ƿ�ӵ�и�Ȩ��
			if (staff.hasRight(r)) {
				return true;// �з���true��
			} else {
				return false;// ����false
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
