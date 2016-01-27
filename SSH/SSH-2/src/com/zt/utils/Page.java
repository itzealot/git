package com.zt.utils;

import java.util.List;

public class Page<T> {

	// 当前第几页
	private int pageNo = 1;

	// 当前页的 List
	private List<T> list;

	// 每页显示多少条记录
	private int pageSize = 5;

	// 共有多少条记录
	private int totalItemNumber;

	// 构造器中需要对 pageNo 进行初始化
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}

	// 获得页数
	public int getPageNo() {
		// 页数小于0，初始化为1
		if (pageNo <= 1) {
			pageNo = 1;
		}
		// 页数大于总的页数，初始化为最大页数
		if (pageNo >= getTotalPageNumber()) {
			pageNo = getTotalPageNumber();
		}
		return pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	// 获取总页数
	public int getTotalPageNumber() {
		// 总数/页大小
		int totalPageNumber = (int) totalItemNumber / pageSize;
		if (totalItemNumber % pageSize != 0) {
			totalPageNumber++;
		}
		return totalPageNumber;
	}

	// 获取总记录数
	public int getTotalItemNumber() {
		return totalItemNumber;
	}

	public void setTotalItemNumber(int totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}

	// 是否有下一页
	public boolean isHasNext() {
		if (getPageNo() < getTotalPageNumber()) {
			return true;
		}
		return false;
	}

	// 是否含有上一页
	public boolean isHasPrev() {
		if (getPageNo() > 1) {
			return true;
		}
		return false;
	}

	// 有上一页，跳转到上一页
	public int getPrevPage() {
		if (isHasPrev()) {
			return getPageNo() - 1;
		}
		return getPageNo();
	}

	// 获取下一页
	public int getNextPage() {
		if (isHasNext()) {
			return getPageNo() + 1;
		}
		return getPageNo();
	}

	public boolean isFirst() {
		if (isHasPrev()) {
			return false;
		}
		return true;
	}

	public boolean isLast() {
		if (isHasNext()) {
			return false;
		}
		return true;
	}
}
