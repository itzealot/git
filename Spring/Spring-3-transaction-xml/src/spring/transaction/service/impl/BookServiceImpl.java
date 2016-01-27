package spring.transaction.service.impl;

import spring.transaction.dao.BookShopDao;
import spring.transaction.service.BookService;

public class BookServiceImpl implements BookService {
	private BookShopDao bookShopDao;
	
	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}
	@Override
	public void purchase(String username, String isbn) {
		// TODO Auto-generated method stub
		//1.��ȡ��ĵ���
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		
		//2.������Ŀ��
		bookShopDao.updateBookStock(isbn);
		
		//3.�����û����
		bookShopDao.updateUserAccount(username, price);
	}
}
