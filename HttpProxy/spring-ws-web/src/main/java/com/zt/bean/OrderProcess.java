package com.zt.bean;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * 注解 @WebService 修饰 SEI接口
 * 
 * 注解 @WebMethod 修饰发布服务的方法
 * 
 * Title: OrderProcess.<br />
 * Description: .<br />
 * Company: 伯乐园.<br />
 * 
 * @author zengtao
 * @date 2015年9月7日
 */
@WebService
public interface OrderProcess {

	@WebMethod
	String processOrder(Order order);
}