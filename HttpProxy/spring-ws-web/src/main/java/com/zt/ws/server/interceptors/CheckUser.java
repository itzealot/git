package com.zt.ws.server.interceptors;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;

/**
 * 检查用户的拦截器
 *  
 * @author zengtao
 *
 */
public class CheckUser extends AbstractPhaseInterceptor<SoapMessage> {

	public CheckUser() {
		super(Phase.PRE_PROTOCOL);
		// TODO Auto-generated constructor stub
	}
	
	/*
 	<Envelope>
 		//The header
 		<head>
 			<atguigu>
 				<name>Zhangsan</name>
 				<password>123456</password>
 			</atguigu>
 			<atguigu2>
 				<name>Zhangsan</name>
 				<password>123456</password>
 			</atguigu2>
 		</head>
 		//The body
 		<body>
 			<sayHello>
 				<arg0>Bob</arg0>
 			</sayHello>
 		</body>
	</Envelope
	*/
	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		// TODO Auto-generated method stub
		Header header = msg.getHeader(new QName("atguigu"));
		if(header != null) {
			Element element = (Element) header.getObject();
			String username = element.getElementsByTagName("name").item(0).getTextContent();
			String password = element.getElementsByTagName("password").item(0).getTextContent();
			if ("zhangsan".equals(username) && "123456".equals(password)) {
				System.out.println("Server pass the interceptor....");
				return ;
			}
			// not pass the interceptor
			System.out.println("Server not pass the interceptor...");
			throw new Fault(new RuntimeException("请求需要一个正确的用户信息"));
		}
	}

}
