package hibernate.one2one.foreign;

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
public class Test121 {
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
	public void testSave() {
		Department department = new Department();
		department.setDepartName("DEPT-AA");
		
		Manager manager = new Manager();
		manager.setManagerName("MGR-AA");
		
		//�趨������ϵ
		department.setManager(manager);
		
		manager.setDepartment(department);
		
		//���������2��INSERT���
		//�����ȱ���û������е��Ǹ��������������UPDATE��䡣
		session.save(manager);
		session.save(department);
		
		//��������������������
//		session.save(department);
//		session.save(manager);
	}
	
	@Test
	public void testDelete() {
		
	}
	@Test
	public void testUpdate() {
		
	}
	
	@Test
	public void testGet2() {
		//�ڲ�ѯû�������ʵ�����ʱ��ʹ�õ��������Ӳ�ѯ��һ����ѯ��������Ķ���
		//���Ѿ����г�ʼ����
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getManagerName());
		System.out.println(manager.getDepartment().getDepartName());
	}
	@Test
	public void testGet() {
		//1). Ĭ������¶Թ�������ʹ�������ء�
		//2). ���Կ��ܻ�����������쳣����session�ر�ʱ������ʹ����Ӧ�����ԣ���Ա���ԣ���
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getDepartName());
		
		//3). ��ѯManger �������������Ӧ����department.manager_id = manager_id��
		//����Ӧ����dept_depart_id = manager.manager_id
		Manager manager = (Manager) session.get(Manager.class, 1);
		System.out.println(manager.getManagerName());
	}
}
