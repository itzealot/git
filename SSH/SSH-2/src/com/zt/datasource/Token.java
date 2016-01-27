package com.zt.datasource;

/**
 * Token ����
 * 
 * @author zengtao
 *
 */
public class Token {

	// �̱߳��ػ�����
	private static ThreadLocal<Token> t = new ThreadLocal<Token>();

	// Ҫ�����߳��еĶ���
	private BindObject object;

	public BindObject getObject() {
		return object;
	}

	public void setObject(BindObject object) {
		this.object = object;
	}

	/**
	 * ���ƶ������ƶ���󶨵���ǰ�߳�
	 * 
	 * @param token
	 */
	public static void bindToken(Token token) {
		t.set(token);
	}

	/**
	 * �����ǰ�̰߳󶨵�����
	 */
	public static void unbindToken() {
		t.remove();
	}

	/**
	 * �ӵ�ǰ�߳��л�ð󶨵�����
	 * 
	 * @return
	 */
	public static Token getCurrentToken() {
		return t.get();
	}
}
