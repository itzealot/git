package com.zt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zt.dao.BookShopDao;
import com.zt.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	private BookShopDao bookShopDao;

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
