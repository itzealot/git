package com.zt.utils;

import java.util.List;

public class Page<T> {

	// ��ǰ�ڼ�ҳ
	private int pageNo = 1;

	// ��ǰҳ�� List
	private List<T> list;

	// ÿҳ��ʾ��������¼
	private int pageSize = 5;

	// ���ж�������¼
	private int totalItemNumber;

	// ����������Ҫ�� pageNo ���г�ʼ��
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}

	// ���ҳ��
	public int getPageNo() {
		// ҳ��С��0����ʼ��Ϊ1
		if (pageNo <= 1) {
			pageNo = 1;
		}
		// ҳ�������ܵ�ҳ������ʼ��Ϊ���ҳ��
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

	// ��ȡ��ҳ��
	public int getTotalPageNumber() {
		// ����/ҳ��С
		int totalPageNumber = (int) totalItemNumber / pageSize;
		if (totalItemNumber % pageSize != 0) {
			totalPageNumber++;
		}
		return totalPageNumber;
	}

	// ��ȡ�ܼ�¼��
	public int getTotalItemNumber() {
		return totalItemNumber;
	}

	public void setTotalItemNumber(int totalItemNumber) {
		this.totalItemNumber = totalItemNumber;
	}

	// �Ƿ�����һҳ
	public boolean isHasNext() {
		if (getPageNo() < getTotalPageNumber()) {
			return true;
		}
		return false;
	}

	// �Ƿ�����һҳ
	public boolean isHasPrev() {
		if (getPageNo() > 1) {
			return true;
		}
		return false;
	}

	// ����һҳ����ת����һҳ
	public int getPrevPage() {
		if (isHasPrev()) {
			return getPageNo() - 1;
		}
		return getPageNo();
	}

	// ��ȡ��һҳ
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
