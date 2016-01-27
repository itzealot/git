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
		//1.获取书的单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		
		//2.更新书的库存
		bookShopDao.updateBookStock(isbn);
		
		//3.更新用户余额
		bookShopDao.updateUserAccount(username, price);
	}
}
