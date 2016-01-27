package dynamicproxy;

import java.lang.reflect.Proxy;

public class TestProxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelloSpeaker helloSpeaker = new HelloSpeaker();
		LogHandler logHandler = new LogHandler(helloSpeaker);
		@SuppressWarnings("rawtypes")
		Class cls =  helloSpeaker.getClass();
		IHello iHello = (IHello) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), logHandler);
		iHello.hello("ZhangSan");
	}

}
