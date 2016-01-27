package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * To realize the dynamic proxy must realize implements InvocationHandler
 * @author zengtao 2014-7-31
 *
 */
public class LogHandler implements InvocationHandler {

	private Object sub;
	public LogHandler() {
		//has nothing
	}
	public LogHandler(Object sub) {
		this.sub = sub;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before you do thing!");
		method.invoke(sub, args);
		System.out.println("after you do thing!");
		return null;
	}
	
}
