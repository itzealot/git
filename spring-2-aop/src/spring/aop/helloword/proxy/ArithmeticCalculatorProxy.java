package spring.aop.helloword.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ArithmeticCalculatorProxy {
	//��Ҫ����Ķ���
	private ArithmeticCalculator target;
	
	public ArithmeticCalculatorProxy(ArithmeticCalculator target) {
		this.target = target;
	}
	
	//��̬����
	public ArithmeticCalculator getProxy() {
		ArithmeticCalculator proxy = null;
		//�����������һ����������������
		ClassLoader loader = target.getClass().getClassLoader();
		
		//�����������ͣ�����������Щ����
		@SuppressWarnings("rawtypes")
		Class[] interfaces = new Class[]{ArithmeticCalculator.class};
		
		//�����ô���������еķ���ʱ����ִ�еĴ��롣
		InvocationHandler h = new InvocationHandler() {
			/**
			 * proxy: ���ڷ��ص��Ǹ��������һ������£���invoke �����ж���ʹ�øö���
			 * method: ���ڱ����õķ�����
			 * args: ���÷���ʱ������Ĳ�����
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				String methodName = method.getName();
				//��־
				System.out.println("The method " + methodName + " begin with " + Arrays.asList(args));
				
				Object result = null;
				try {
					//ִ�з��������ܻ���쳣
					//ǰ��֪ͨ
					result = method.invoke(target, args);
					//����֪ͨ�����Է��ʵ������ķ���ֵ��
				} catch (Exception e) {
					// TODO: handle exception
					//�쳣֪ͨ�����Է��ʵ��������ֵ��쳣��
				}
				//����֪ͨ
				
				//��־
				System.out.println("The method " + methodName + " ends with " + result);
				return result;
			}
		};
		proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}
}
