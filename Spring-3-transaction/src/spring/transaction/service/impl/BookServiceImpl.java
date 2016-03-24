package spring.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.transaction.dao.BookShopDao;
import spring.transaction.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookShopDao bookShopDao;

	//�������ע��
	//1.ʹ��propagation ָ������Ĵ�����Ϊ������ǰ�����񷽷�����һ�����񷽷�����ʱ��
	//���ʹ������Ĭ��ֵΪPropagation.REQUIRED����ʹ�õ��÷��������񡣣���������һ��ִ�У�
	//@Transactional(propagation=Propagation.REQUIRED)
	//(propagation=Propagation.REQUIRES_NEW): ʹ���Լ������񣬵������񷽷������񱻹���
	
	//2.ʹ��isolation ָ������ĸ��뼶����õ�ȡֵΪREAD_COMMITTED
	
	//3.Ĭ�������Spring������ʱ��������е�����ʱ�쳣���лع���Ҳ����ͨ����Ӧ�����Խ������á�
	//noRollbackFor={UserAccountException.class}//����Щ�쳣�����лع�
	//ͨ�������ȡĬ��ֵ���ɡ�
	
	//4.ʹ��readOnly ָ������ֻ�����ԡ���ֻ��ȡ���������ݣ���Ϊtrue������Ϊfalse��
	//�������԰������ݿ������Ż������������һ��ֻ��ȡ�ķ�����Ӧ������Ϊtrue
	
	//5.ʹ��timeout ָ��ǿ�ƻع�֮ǰ�������ռ�õ�ʱ�䣬��λ�롣
	@Transactional(propagation=Propagation.REQUIRES_NEW,
					isolation=Isolation.READ_COMMITTED
					)
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
