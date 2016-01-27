package staticproxy;

import java.util.logging.Level;

import com.sun.istack.internal.logging.Logger;

public class HelloProxy implements IHello {

	private IHello hello;
	private Logger logger = Logger.getLogger(this.getClass());
	public HelloProxy(IHello hello) {
		this.hello = hello;
	}
	@Override
	public void hello(String name) {
		// TODO Auto-generated method stub
		//��־����
		log("hello method starts...");
		//ִ��ҵ���߼�
		hello.hello(name);
		//��־����
		log("hello method ends...");
	}
	private void log(String string) {
		// TODO Auto-generated method stub
		logger.log(Level.INFO, string);
	}

}
