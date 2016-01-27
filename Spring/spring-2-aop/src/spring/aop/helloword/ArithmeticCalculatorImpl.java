package spring.aop.helloword;

public class ArithmeticCalculatorImpl implements ArithmeticCalculator {

	@Override
	public int add(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("before add...");
		int result = i + j;
		System.out.println("after add...");
		return result;
	}

	@Override
	public int sub(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("before sub...");
		int result = i - j;
		System.out.println("after sub...");
		return result;
	}

	@Override
	public int mul(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("before mul...");
		int result = i * j;
		System.out.println("after mul...");
		return result;
	}

	@Override
	public int div(int i, int j) {
		// TODO Auto-generated method stub
		System.out.println("before div...");
		int result = i / j;
		System.out.println("after div...");
		return result;
	}

}
