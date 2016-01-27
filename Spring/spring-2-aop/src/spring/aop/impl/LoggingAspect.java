package spring.aop.impl;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * �����������Ϊһ�����棺 ��Ҫ�Ѹ�������IOC�����У�������Ϊһ������
 * @author zengtao
 *
 */
@Aspect
@Component
public class LoggingAspect {
	
	
	//�����÷�����һ��ǰ��֪ͨ����Ŀ�귽����ʼ֮ǰִ�У��Ե���������������
//	@Before("execution(int spring.aop.impl.ArithmeticCalculatorImpl.add(int, int))")
	//���������JoinPoint
	//�����з�������ͨ��
	@Before("execution(public int spring.aop.impl.ArithmeticCalculatorImpl.*(int, int))")
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
//		System.out.println("before method...");
		
		System.out.println("The method " + methodName + " begins with " + args);
	}
	
	//�Ե�����������
//	@After("execution(int spring.aop.impl.ArithmeticCalculatorImpl.add(int, int))")
	//�����з�������ͨ��
	//�ں���֪ͨ�в��ܷ���Ŀ�귽��ִ�еĽ��
	@After("execution(public int spring.aop.impl.ArithmeticCalculatorImpl.*(int, int))")
	public void afterMethod() {
		System.out.println("after method...");
	}
}
