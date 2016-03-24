package spring.transaction.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.transaction.dao.CashierDao;
import spring.transaction.service.BookService;

@Service("cashierDao")
public class CashierDaoImpl implements CashierDao {

	@Autowired
	private BookService bookService;
	
	//声明事务，添加事务注解
	
	@Transactional
	@Override
	public void checkout(String username, List<String> isbns) {
		// TODO Auto-generated method stub
		for(String isbn : isbns) {
			bookService.purchase(username, isbn);
		}
	}

}
