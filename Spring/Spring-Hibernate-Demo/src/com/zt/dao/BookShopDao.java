package com.zt.dao;

public interface BookShopDao {
	/**
	 * ������Ż�ȡ��ĵ���
	 * @param isbn
	 * @return 
	 */
	public int findBookPriceByIsbn(String isbn);
	
	/**
	 * ������Ŀ�棬ʹ��Ŷ�Ӧ�Ŀ��-1
	 * @param isbn
	 */
	public void updateBookStock(String isbn);
	
	/**
	 * �����û����˻���ʹusername��balance - price
	 * @param username
	 * @param price
	 */
	public void updateUserAccount(String username, int price);
}
