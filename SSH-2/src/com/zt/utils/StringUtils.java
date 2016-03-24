package com.zt.utils;

import java.util.ArrayList;
import java.util.List;

import com.zt.entities.BaseEntity;
import com.zt.entities.Department;

/**
 * 字符串工具类
 * 
 * @author zengtao
 *
 */
public class StringUtils {

	/**
	 * 对指定的字符串(str)按照给定的拆分方法(tag)进行拆分
	 * 
	 * @param str
	 * @param tag
	 * @return 返回拆分后的字符串数组
	 */
	public static String[] str2StrArray(String str, String tag) {
		// str有效
		if (ValidateUtils.isValid(str)) {
			return str.split(tag);
		}
		return null;
	}

	/**
	 * 输出字符串数组
	 * 
	 * @param strs
	 */
	public static void displayStrArray(String[] strs) {
		for (String str : strs) {
			System.out.println(str);
		}
	}

	/**
	 * 输出字符数组
	 * 
	 * @param chs
	 */
	public static void displayCharArray(char[] chs) {
		for (char ch : chs) {
			System.out.println(ch);
		}
	}

	/**
	 * 将对象数组字符串化，并用逗号","隔开
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
	 * 将BaseEntity数组字符串化，并用逗号","隔开
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
	 * 获得字符串的描述信息<br />
	 * 从日志的信息中抽取描述信息<br />
	 * 在jsp页面可以通过@修饰符访问<br />
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
	 * 从Class<T>中获取class name
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
	 * 从Class<T>中获取Hql语句
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> String getHqlFromClazz(Class<? extends T> clazz) {
		String className = getClassName(clazz);
		return "FROM " + className + " as t";
	}

	/**
	 * 从集合中找出name的集合
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
		// 不是第一个，放入到第一个
		if (dept != null
				&& !depts.get(0).getDeptName().equals(dept.getDeptName())) {
			depts.remove(dept);
			depts.add(0, dept);
		}
		// 遍历
		for (Department d : depts) {
			sLists.add(d.getDeptName());
		}
		return sLists;
	}
}
