package com.zt.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.zt.entities.BaseEntity;
import com.zt.entities.Log;

/**
 * 数据工具类
 * 
 * @author zengtao
 *
 */
public class DataUtils {

	/**
	 * 抽取所有实体的id，形成字符串，使用逗号","隔开
	 */
	public static String extractEntityIds(Set<? extends BaseEntity> entities) {
		String temp = "";
		if (ValidateUtils.isValid(entities)) {
			for (BaseEntity entity : entities) {
				temp += entity.getId() + ",";
			}
			// 去掉最后一个,号
			return temp.substring(0, temp.length() - 1);
		}
		return temp;
	}

	/**
	 * 抽取日志表中的数据进行分页查询根据pageNo, pageSize<br />
	 * pageNo : 当前的页号 <br />
	 * pageSize: 页面大小<br />
	 * 
	 * @param firstResult
	 * @param maxResult
	 * @param lists
	 * @param pageSize
	 * @return
	 */
	public static List<Log> getLogPageList(int pageNo, List<Log> lists,
			int pageSize) {
		int fromIndex = (pageNo - 1) * pageSize;
		int toIndex = fromIndex + 5;
		if (toIndex >= lists.size()) {
			toIndex = lists.size();
		}
		return lists.subList(fromIndex, toIndex);
	}

	/**
	 * 将set集合中的内容转换为List集合
	 * 
	 * @param <T>
	 * 
	 * @param t
	 * @return
	 */
	public static <T> List<? extends T> getListFromSet(Set<? extends T> t) {
		if (ValidateUtils.isValid(t)) {
			List<T> lists = new ArrayList<T>();
			@SuppressWarnings("unchecked")
			Iterator<T> it = (Iterator<T>) t.iterator();

			while (it.hasNext()) {
				lists.add(it.next());
			}
			return lists;
		}
		return null;
	}

	/**
	 * 将List集合中的内容转换为set集合
	 * 
	 * @param t
	 * @return
	 */
	public static <T> Set<? extends T> getSetFromList(List<? extends T> t) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(t)) {
			return null;
		}
		Set<T> set = new HashSet<T>();

		for (T tt : t) {
			set.add(tt);
		}
		System.out.println("set in DataUtils : " + set);
		return set;
	}

	/**
	 * 将字符串数组转为整数数组
	 * 
	 * @param roelIds
	 * @return
	 */
	public static Integer[] getIntIdsFromStringIds(String[] roleIds) {
		// TODO Auto-generated method stub
		if (!ValidateUtils.isValid(roleIds)) {
			return null;
		}
		int length = roleIds.length;
		Integer[] ids = new Integer[length];
		for (int i = 0; i < length; i++) {
			ids[i] = Integer.parseInt(roleIds[i]);
		}
		return ids;
	}

	/**
	 * 输出对象数组的值
	 * 
	 * @param objects
	 */
	public static void displayObjectArray(Object[] objects) {
		if (!ValidateUtils.isValid(objects)) {
			return;
		}
		for (Object object : objects) {
			System.out.println(object);
		}
	}

	/**
	 * 输出数组的值
	 * 
	 * @param ts
	 */
	public static void displayArray(long[] ts) {
		for (long t : ts) {
			System.out.println(t);
		}
	}
}
