package com.zt.entities;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 所有实体都有共性id，抽象出相应的BaseEntity作为抽象的实体超类，专门用于继承;<br />
 * 同时方便设计工具类的实现
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
	 * 抽象的get和set方法，不能设置相应的字段，因为属性不能覆盖，添加了，为多余<br />
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

				// 设置私有属性值可以访问
				f.setAccessible(true);
				fvalue = f.get(this);

				// 是否是基本数据类型，不是静态的
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
