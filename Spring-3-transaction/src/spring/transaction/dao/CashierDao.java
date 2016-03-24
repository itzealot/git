package spring.transaction.dao;

import java.util.List;

public interface CashierDao {
	
	public void checkout(String username, List<String> isbns);
}
