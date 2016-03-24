package com.zt.actions;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 抽象Action，专门用于继承
 * 
 * @author zengtao
 *
 * @param <T>
 */

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1576640149213706903L;
	// 设置为public，子类可以直接继承并使用model
	public T model;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseAction() {
		try {
			// 获得泛型并创建实例
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class clazz = (Class) type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 预备方法
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	// To change to the abstract method
	public T getModel() {
		return model;
	}

}
