package hibernate.n21.multiple;

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
	public void testCascade() {
		/**
		 * ����cascade�����е�delete-orphanֵ��
		 */
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.getOrder().clear();
	}
	
	@Test
	public void testMany2OneDelete() {
		
		//cascade�����е�deleteֵ��get(Class.class, OID);
		Customer customer = (Customer) session.get(Customer.class, 1);
		
		session.delete(customer);
	}
	
	@Test
	public void testMany2OneUpdate() {
		Customer customer = (Customer) session.get(Customer.class, 1);
		customer.getOrder().iterator().next().setOrderName("GGG");
	}
	
	@Test
	public void testOne2ManyGet() {
		//1).��n��һ�˵ļ���ʹ���ӳټ���
		Customer customer = (Customer) session.get(Customer.class, 1);
		
		//2).���ض��һ�˵ļ�����Hibernate���õļ������͡�
		//�����;����ӳټ��غʹ�Ŵ������Ĺ��ܡ�
		System.out.println(customer.getCustomerName());
		System.out.println(customer.getOrder().getClass());
		
		//3).���ܻ��׳�LazyInitializationException�쳣����session.close()ʱ����ʹ�ô���Ķ���
		
		//4).����Ҫʹ�ü�����Ԫ�ص�ʱ����г�ʼ����
	}
	
	@Test
	public void testMany2OneSave() {
		Customer customer = new Customer();
		customer.setCustomerName("CC");
		
		Order order1 = new Order();
		order1.setOrderName("order-5");
		
		Order order2 = new Order();
		order2.setOrderName("order-6");
		
		//�趨������ϵ
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//add into set
		customer.getOrder().add(order1);
		customer.getOrder().add(order2);
		//������1��һ�˵�set�ڵ�ָ��inverse="true"�� ��ʹ1��һ�˷���ά��������ϵ��
		//�����趨set��inverse="true"�������Ȳ���1��һ�ˣ��������һ�ˣ��ó���
		//������Update���
		session.save(customer);
		
		session.save(order1);
		session.save(order2);
		//ִ��save����:�Ȳ���Customer���ٲ���Order��3��INSERT,2��Update��
		//�Ȳ���1��һ�ˣ��ڲ���n��һ�ˣ�ֻ��INSERT��䡣
		//��Ϊ1��һ�˺�n��һ�˶�ά��������ϵ�����Ի���UPDATE
//		session.save(customer);
//		
//		session.save(order1);
//		session.save(order2);		
			
		//�Ȳ���Order���ٲ���Customer��3��INSERT��4��UPDATE���
		//�Ȳ���n��һ�ߣ��ٲ���һ��һ�ߣ�����UPDATE��䡣
		//��Ϊ�ڲ�����һ��ʱ���޷�ȷ��1��һ�˵����ֵ������ֻ�ܵ�һ��һ�˲�����ٶ��ⷢ��UPDATE��䡣
		//�Ƽ��Ȳ���1��һ�ߣ��ٲ�����һ�ߡ�
//		session.save(order1);
//		session.save(order2);
//		
//		session.save(customer);
	}

}
