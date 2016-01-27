package hibernate.n21;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class TestMany2One {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
	}
	@After
	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void testMany2OneDelete() {
		//�ڲ��趨������ϵ����£���1��һ�˵Ķ�����n�Ķ��������ã�����ֱ��ɾ��1��һ�˵Ķ���
		Customer customer = (Customer) session.get(Customer.class, 1);
		
		session.delete(customer);
	}
	
	@Test
	public void testMany2OneUpdate() {
		Order order = (Order) session.get(Order.class, 1);
		order.getCustomer().setCustomerName("AAA");
	}
	
	@Test
	public void testMany2OneGet() {
		
		//1).����ѯ���һ�˵�һ��������Ĭ������£�ֻ��ѯ�˶��һ�˵Ķ��󣬶�û�в�ѯ������1��һ�˵Ķ��󣡡�
		Order order = (Order) session.get(Order.class, 1);
		System.out.println(order.getOrderName());
		System.out.println(order.getCustomer().getClass().getName());
		
		//2).����Ҫʹ�õ������Ķ���ʱ���ŷ��Ͷ�Ӧ��sql��䡣
		
		Customer customer = order.getCustomer();
		System.out.println(customer.getCustomerName());
		
		
		//3).�ڲ�ѯCustomer����ʱ���ɶ��һ�˵���1��һ��ʱ��
		//����ʱsession�رգ���Ĭ������»ᷢ��LazyIntializationException���������쳣��
		
		//4).��ȡOrder ����ʱ��Ĭ������£��������Customer������һ���������
	}
	
	@Test
	public void testMany2OneSave() {
		Customer customer = new Customer();
		customer.setCustomerName("BB");
		
		Order order1 = new Order();
		order1.setOrderName("order-3");
		
		Order order2 = new Order();
		order2.setOrderName("order-4");
		
		//�趨������ϵ
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//ִ��save����:�Ȳ���Customer���ٲ���Order��3��INSERT��
		//�Ȳ���1��һ�ˣ��ڲ���n��һ�ˣ�ֻ��INSERT��䡣
		session.save(customer);
		
		session.save(order1);
		session.save(order2);
		
		//�Ȳ���Order���ٲ���Customer��3��INSERT��2��UPDATE���
		
		//�Ȳ���n��һ�ߣ��ٲ���һ��һ�ߣ�����UPDATE��䡣
		//��Ϊ�ڲ�����һ��ʱ���޷�ȷ��1��һ�˵����ֵ������ֻ�ܵ�һ��һ�˲�����ٶ��ⷢ��UPDATE��䡣
		
		//�Ƽ��Ȳ���1��һ�ߣ��ٲ�����һ�ߡ�
//		session.save(order1);
//		session.save(order2);
//		
//		session.save(customer);
	}

}
