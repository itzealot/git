package com.zt.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.service.RightService;

/**
 * 提取所有权限的工具类，通过一次性获得所有权限，这样的捕获url是规范定义的才能知道
 * 
 * @author zengtao
 *
 */
public class ExtractAllRightsUtil {

	private static ApplicationContext applicationContext = null;

	public static void main(String[] args) throws Exception {
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		RightService rightService = (RightService) applicationContext
				.getBean("rightService");

		// 获得类加载器
		ClassLoader loader = ExtractAllRightsUtil.class.getClassLoader();
		URL url = loader.getResource("com/zt/actions");
		// System.out.println(url);
		// 得到所有文件
		File dir = new File(url.toURI());
		File[] files = dir.listFiles();
		String fname = "";
		for (File file : files) {
			fname = file.getName();
			// 对文件进行校验，以.class文件结尾且不为BaseAction.class
			if (fname.endsWith(".class") && !fname.equals("BaseAction.class")) {
				// System.out.println(fname);
				processAction(fname, rightService);
			}
		}
	}

	/**
	 * 处理Action类，捕获所有url地址，形成权限
	 */
	private static void processAction(String fname, RightService rightService)
			throws Exception {
		// TODO Auto-generated method stub

		// 包名The package Name
		String packageName = "com.zt.actions";
		// 过滤掉.class
		String simpleClassName = fname.substring(0, fname.indexOf(".class"));
		// System.out.println(simpleClassName);

		// 得到全类名
		String className = packageName + "." + simpleClassName;
		// 得到具体类
		Class<?> clazz = Class.forName(className);
		Method[] methods = clazz.getDeclaredMethods();

		Class<?> retType = null;
		String mname = null;
		@SuppressWarnings("rawtypes")
		Class[] paramType = null;
		String url = null;

		for (Method method : methods) {
			// 返回值类型
			retType = method.getReturnType();
			// 方法名称
			mname = method.getName();
			// 参数类型
			paramType = method.getParameterTypes();

			// 判断数组是否有效
			if (retType == String.class && ValidateUtils.isValid(paramType)
					&& Modifier.isPublic(method.getModifiers())) {
				url = "/" + simpleClassName;
				// 不是execute()方法
				if (!mname.equals("execute")) {
					url += "_" + mname;
				}
				// 输出权限url
				System.out.println(url);

				rightService.appendRightByURL(url);
			}
		}
	}
}
