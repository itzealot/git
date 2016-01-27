package com.zt.actions;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * ����Action��ר�����ڼ̳�
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
	// ����Ϊpublic���������ֱ�Ӽ̳в�ʹ��model
	public T model;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseAction() {
		try {
			// ��÷��Ͳ�����ʵ��
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class clazz = (Class) type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Ԥ������
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	// To change to the abstract method
	public T getModel() {
		return model;
	}

}
