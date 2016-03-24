package staticproxy;

public class TestProxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IHello proxy = new HelloProxy(new HelloSpeaker());
		proxy.hello("ZhangSan");
	}

}
