package hibernate.n21;

/**
 * Customer : Order = 1 : n.
 * һ���ͻ������ж������������һ������ֻ����һ���ͻ��������ڶ��һ�������ٵ�һ����һ��ʵ����
 * @author zengtao
 *
 */
public class Order {
	private Integer orderId;
	private String orderName;
	private Customer customer;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
