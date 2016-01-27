package spring.aop.test;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * �����������Ϊһ�����棺 ��Ҫ�Ѹ�������IOC�����У�������Ϊһ������
 * @author zengtao
 *
 */
//������������ȼ���ֵԽС�����ȼ�Խ�ߣ��Ӷ�ȷ�������ִ��˳��
@Order(2)
@Aspect
@Component
public class LogAspect {
	
	/**
	 * ����һ�����������������е���ʽ��һ��أ��÷����в�����Ҫ��������Ĵ��롣
	 * ����������Ҫ����ʱ��������ͬһ�����У�����Ҫ�Ӱ���.������
	 */
	@Pointcut("execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(..))")
	public void declareJointExpression() { }
	
	//�����÷�����һ��ǰ��֪ͨ����Ŀ�귽����ʼ֮ǰִ�У��Ե���������������
//	@Before("execution(int spring.aop.impl.ArithmeticCalculatorImpl.add(int, int))")
	//���������JoinPoint
	//�����з�������ͨ��
	//..��������������.����һ������
//	@Before("execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(..))")
	@Before("declareJointExpression()")
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
//	@After("execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(int, int))")
	@After("execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(..))")
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinPoint.getArgs());
//		System.out.println("before method...");
		
		System.out.println("The method " + methodName + " ends with " + args);
	}
	/**
	 * �ڷ�����������ִ�еĴ��롣
	 * ����֪ͨ�ǿ��Է��ʵ������ķ���ֵ�ģ�
	 * @param joinPoint
	 * @param result
	 */
//	@AfterReturning(value="execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(int, int))", returning="result")
	@AfterReturning(value="declareJointExpression()", returning="result")
	public void afterRunning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with " + result);
	}
	
	/**
	 * ��Ŀ�귽�������쳣ʱ��ִ�еĴ��롣
	 * ���Է��ʵ��쳣�����ҿ���ָ���ڳ����ض��쳣ʱ��ִ��֪ͨ���롣
	 * @param joinPoint
	 * @param ex
	 */
//	@AfterThrowing(value="execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(int, int))", throwing="ex")
	@AfterThrowing(value="declareJointExpression()", throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception: " + ex);
	}
	
	/**
	 * ����֪ͨ��Ҫд��ProceedingJoinPoint ���͵Ĳ�����
	 * ����֪ͨ�����ڶ�̬�����ȫ���̣� ProceedingJoinPoint ���͵Ĳ������Ծ����Ƿ�ִ��Ŀ�귽����
	 * �һ���֪ͨ�����з���ֵ������ֵ��ΪĿ�귽���ķ���ֵ��
	 */
//	@Around("execution(public int spring.aop.test.ArithmeticCalculatorImpl.*(int, int))")
	@Around("declareJointExpression()")
	public Object around(ProceedingJoinPoint joinPoint) {
		System.out.println("arround method.");
		Object result = null;
		String methodName = joinPoint.getSignature().getName();
		
		//ִ��Ŀ�귽��
		try {
			//ǰ��֪ͨ
			List<Object> args = Arrays.asList(joinPoint.getArgs());
			
			System.out.println("The method " + methodName + " ends with " + args);
			result = joinPoint.proceed();
			//����֪ͨ
			System.out.println("The method " + methodName + " ends with " + result);
		} catch (Throwable e) {
			// TODO: handle exception
			//�쳣֪ͨ
			System.out.println("The method " + methodName + " occurs exception: " + e);
			throw new RuntimeException(e);
		}
		return result;
	}
}
