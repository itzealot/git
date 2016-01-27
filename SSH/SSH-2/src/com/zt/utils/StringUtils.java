package com.zt.utils;

import java.util.ArrayList;
import java.util.List;

import com.zt.entities.BaseEntity;
import com.zt.entities.Department;

/**
 * �ַ���������
 * 
 * @author zengtao
 *
 */
public class StringUtils {

	/**
	 * ��ָ�����ַ���(str)���ո����Ĳ�ַ���(tag)���в��
	 * 
	 * @param str
	 * @param tag
	 * @return ���ز�ֺ���ַ�������
	 */
	public static String[] str2StrArray(String str, String tag) {
		// str��Ч
		if (ValidateUtils.isValid(str)) {
			return str.split(tag);
		}
		return null;
	}

	/**
	 * ����ַ�������
	 * 
	 * @param strs
	 */
	public static void displayStrArray(String[] strs) {
		for (String str : strs) {
			System.out.println(str);
		}
	}

	/**
	 * ����ַ�����
	 * 
	 * @param chs
	 */
	public static void displayCharArray(char[] chs) {
		for (char ch : chs) {
			System.out.println(ch);
		}
	}

	/**
	 * �����������ַ����������ö���","����
	 * 
	 * @param ids
	 * @return
	 */
	public static String arr2Str(Object[] arr) {
		// TODO Auto-generated method stub
		String temp = "";
		if (ValidateUtils.isValid(arr)) {
			for (Object id : arr) {
				temp += id.toString() + ",";
			}
			return getDescString(temp.substring(0, temp.length() - 2));
		}
		return null;
	}

	/**
	 * ��BaseEntity�����ַ����������ö���","����
	 * 
	 * @param ids
	 * @return
	 */
	public static String arr2Str(BaseEntity[] arr) {
		// TODO Auto-generated method stub
		String temp = "";
		if (ValidateUtils.isValid(arr)) {
			for (BaseEntity base : arr) {
				temp += base.toString() + ",";
			}
			return temp.substring(0, temp.length() - 2);
		}
		return null;
	}

	/**
	 * ����ַ�����������Ϣ<br />
	 * ����־����Ϣ�г�ȡ������Ϣ<br />
	 * ��jspҳ�����ͨ��@���η�����<br />
	 * 
	 * @param str
	 * @return
	 */
	public static String getDescString(String str) {
		if (str != null && str.trim().length() > 30) {
			return str.substring(0, 30);
		}
		return str;
	}

	/**
	 * ��Class<T>�л�ȡclass name
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> String getClassName(Class<? extends T> clazz) {
		int index = clazz.toString().lastIndexOf(".");
		return clazz.toString().substring(index + 1);
	}

	/**
	 * ��Class<T>�л�ȡHql���
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> String getHqlFromClazz(Class<? extends T> clazz) {
		String className = getClassName(clazz);
		return "FROM " + className + " as t";
	}

	/**
	 * �Ӽ������ҳ�name�ļ���
	 * 
	 * @param entities
	 * @return
	 */
	public static List<String> getNameStringFromList(List<Department> depts,
			Department dept) {
		if (!ValidateUtils.isValid(depts)) {
			return null;
		}
		List<String> sLists = new ArrayList<String>();
		// ���ǵ�һ�������뵽��һ��
		if (dept != null
				&& !depts.get(0).getDeptName().equals(dept.getDeptName())) {
			depts.remove(dept);
			depts.add(0, dept);
		}
		// ����
		for (Department d : depts) {
			sLists.add(d.getDeptName());
		}
		return sLists;
	}
}
