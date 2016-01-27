package spring.transaction.service.impl;

import java.util.List;

import spring.transaction.service.BookService;
import spring.transaction.service.CashierService;

public class CashierServiceImpl implements CashierService {
	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	@Override
	public void checkout(String username, List<String> isbns) {
		// TODO Auto-generated method stub
		for(String isbn : isbns) {
			bookService.purchase(username, isbn);
		}
	}

}
