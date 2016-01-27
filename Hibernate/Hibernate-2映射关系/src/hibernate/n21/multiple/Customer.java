package hibernate.n21.multiple;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	
	private Integer customerId;
	private String customerName;
	
	/**
	 * ����ע�⣺
	 * 1.������������ʱ����ʹ�ýӿ����ͣ���ΪHibernate�ڻ�ȡ��������ʱ��
	 * ���ص���Hibernate���õļ������ͣ�������JavaSEһ����׼�ļ���ʵ�֡�
	 * 2.��Ҫ�Ѽ��Ͻ��г��ۣ����Է�ֹ������ָ���쳣
	 */
	private Set<Order> order = new HashSet<Order>();
	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Set<Order> getOrder() {
		return order;
	}
	public void setOrder(Set<Order> order) {
		this.order = order;
	}
}
