package com.zt.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {

	/**
	 * To get List by index from start to end.
	 * 
	 * @param lists
	 * @param from
	 * @param end
	 * @return
	 */
	public static <T> List<T> getListByIndex(List<T> lists, int start, int end) {
		List<T> newList = new ArrayList<T>();
		// validate the list
		if (!ValidateUtils.isValid(lists)) {
			return newList;
		}

		// compare start and end
		if (start > end) {
			return newList;
		}
		int size = lists.size();

		// init start index
		if (start < 0) {
			start = 0;
		}

		// init end index
		if (end >= size) {
			end = size - 1;
		}

		// add element
		for (int i = start; i <= end; i++) {
			newList.add(lists.get(i));
		}

		return newList;
	}

	/**
	 * To get the List by Page util like pageSize and pageNo.
	 * 
	 * @param lists
	 * @param pageSize
	 *            the page size
	 * @param pageNo
	 *            the page number
	 * @return
	 */
	public static <T> List<T> getListByPage(List<T> lists, int pageSize,
			int pageNo) {
		// validate the list
		if (!ValidateUtils.isValid(lists)) {
			return new ArrayList<T>();
		}

		if (pageSize <= 0 || pageNo <= 0) {
			return new ArrayList<T>();
		}

		// get List by index
		int start = pageSize * (pageNo - 1);
		int end = pageSize * pageNo - 1;
		return getListByIndex(lists, start, end);
	}
}
