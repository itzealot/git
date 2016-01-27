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
		//日志服务
		log("hello method starts...");
		//执行业务逻辑
		hello.hello(name);
		//日志服务
		log("hello method ends...");
	}
	private void log(String string) {
		// TODO Auto-generated method stub
		logger.log(Level.INFO, string);
	}

}
