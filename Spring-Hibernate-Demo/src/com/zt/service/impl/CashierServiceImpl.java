package com.zt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zt.service.BookService;
import com.zt.service.CashierService;

@Service
public class CashierServiceImpl implements CashierService {
	@Autowired
	private BookService bookService;

	@Override
	public void checkout(String username, List<String> isbns) {
		// TODO Auto-generated method stub
		for(String isbn : isbns) {
			bookService.purchase(username, isbn);
		}
	}

}
