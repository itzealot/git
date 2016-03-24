package com.zt.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.zt.entities.BaseEntity;
import com.zt.entities.Log;

/**
 * ���ݹ�����
 * 
 * @author zengtao
 *
 */
public class DataUtils {

	/**
	 * ��ȡ����ʵ���id���γ��ַ�����ʹ�ö���","����
	 */
	public static String extractEntityIds(Set<? extends BaseEntity> entities) {
		String temp = "";
		if (ValidateUtils.isValid(entities)) {
			for (BaseEntity entity : entities) {
				temp += entity.getId() + ",";
			}
			// ȥ�����һ��,��
			return temp.substring(0, temp.length() - 1);
		}
		return temp;
	}

	/**
	 * ��ȡ��־���е����ݽ��з�ҳ��ѯ����pageNo, pageSize<br />
	 * pageNo : ��ǰ��ҳ�� <br />
	 * pageSize: ҳ���С<br />
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
	 * ��set�����е�����ת��ΪList����
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
	 * ��List�����е�����ת��Ϊset����
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
	 * ���ַ�������תΪ��������
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
	 * ������������ֵ
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
	 * ��������ֵ
	 * 
	 * @param ts
	 */
	public static void displayArray(long[] ts) {
		for (long t : ts) {
			System.out.println(t);
		}
	}
}
