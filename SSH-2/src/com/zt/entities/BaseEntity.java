package com.zt.entities;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * ����ʵ�嶼�й���id���������Ӧ��BaseEntity��Ϊ�����ʵ�峬�࣬ר�����ڼ̳�;<br />
 * ͬʱ������ƹ������ʵ��
 * 
 * @author zengtao
 *
 */
public abstract class BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4487345473187686900L;

	/**
	 * �����get��set����������������Ӧ���ֶΣ���Ϊ���Բ��ܸ��ǣ�����ˣ�Ϊ����<br />
	 */
	public abstract Integer getId();

	public abstract void setId(Integer id);

	public String toString() {
		try {
			StringBuffer buffer = new StringBuffer();
			Class<?> clazz = this.getClass();
			String simpleName = clazz.getSimpleName();
			buffer.append(simpleName);
			buffer.append("{");
			//
			Field[] fs = clazz.getDeclaredFields();
			Class<?> ftype = null;
			String fname = null;
			Object fvalue = null;

			for (Field f : fs) {
				ftype = f.getType();
				fname = f.getName();

				// ����˽������ֵ���Է���
				f.setAccessible(true);
				fvalue = f.get(this);

				// �Ƿ��ǻ����������ͣ����Ǿ�̬��
				if (ftype.isPrimitive() || ftype == Integer.class
						|| ftype == Long.class || ftype == String.class
						|| ftype == Short.class || ftype == Boolean.class
						|| ftype == Character.class || ftype == Double.class
						|| ftype == Float.class
						&& !Modifier.isStatic(f.getModifiers())) {
					buffer.append(fname);
					buffer.append(":");
					buffer.append(fvalue);
					buffer.append(",");
				}
			}

			buffer.append("}");
			return buffer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
