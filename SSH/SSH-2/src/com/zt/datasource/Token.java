package com.zt.datasource;

/**
 * Token 令牌
 * 
 * @author zengtao
 *
 */
public class Token {

	// 线程本地化对象
	private static ThreadLocal<Token> t = new ThreadLocal<Token>();

	// 要绑定在线程中的对象
	private BindObject object;

	public BindObject getObject() {
		return object;
	}

	public void setObject(BindObject object) {
		this.object = object;
	}

	/**
	 * 将制定的令牌对象绑定到当前线程
	 * 
	 * @param token
	 */
	public static void bindToken(Token token) {
		t.set(token);
	}

	/**
	 * 解除当前线程绑定的令牌
	 */
	public static void unbindToken() {
		t.remove();
	}

	/**
	 * 从当前线程中获得绑定的令牌
	 * 
	 * @return
	 */
	public static Token getCurrentToken() {
		return t.get();
	}
}
