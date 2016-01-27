package com.zt.bean;

/**
 * SEI 的实现类
 * 
 * Title: OrderProcessImpl.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年9月7日
 */
public class OrderProcessImpl implements OrderProcess {

	public OrderProcessImpl() {
		System.out.println("OrderProcessImpl()");
	}

	@Override
	public String processOrder(Order order) {
		// TODO Auto-generated method stub
		String orderID = validate(order);
		return orderID;
	}

	/**
	 * Validates the order and returns the order ID
	 */
	private String validate(Order order) {
		String custID = order.getCustomerID();
		String itemID = order.getItemID();
		int qty = order.getQty();
		double price = order.getPrice();
		if (custID != null && itemID != null && !custID.equals("")
				&& !itemID.equals("") && qty > 0 && price > 0.0) {
			return "ORD1234";
		}
		return null;
	}
}
