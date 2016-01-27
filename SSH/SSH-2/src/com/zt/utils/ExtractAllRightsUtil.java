package com.zt.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zt.service.RightService;

/**
 * ��ȡ����Ȩ�޵Ĺ����࣬ͨ��һ���Ի������Ȩ�ޣ������Ĳ���url�ǹ淶����Ĳ���֪��
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

		// ����������
		ClassLoader loader = ExtractAllRightsUtil.class.getClassLoader();
		URL url = loader.getResource("com/zt/actions");
		// System.out.println(url);
		// �õ������ļ�
		File dir = new File(url.toURI());
		File[] files = dir.listFiles();
		String fname = "";
		for (File file : files) {
			fname = file.getName();
			// ���ļ�����У�飬��.class�ļ���β�Ҳ�ΪBaseAction.class
			if (fname.endsWith(".class") && !fname.equals("BaseAction.class")) {
				// System.out.println(fname);
				processAction(fname, rightService);
			}
		}
	}

	/**
	 * ����Action�࣬��������url��ַ���γ�Ȩ��
	 */
	private static void processAction(String fname, RightService rightService)
			throws Exception {
		// TODO Auto-generated method stub

		// ����The package Name
		String packageName = "com.zt.actions";
		// ���˵�.class
		String simpleClassName = fname.substring(0, fname.indexOf(".class"));
		// System.out.println(simpleClassName);

		// �õ�ȫ����
		String className = packageName + "." + simpleClassName;
		// �õ�������
		Class<?> clazz = Class.forName(className);
		Method[] methods = clazz.getDeclaredMethods();

		Class<?> retType = null;
		String mname = null;
		@SuppressWarnings("rawtypes")
		Class[] paramType = null;
		String url = null;

		for (Method method : methods) {
			// ����ֵ����
			retType = method.getReturnType();
			// ��������
			mname = method.getName();
			// ��������
			paramType = method.getParameterTypes();

			// �ж������Ƿ���Ч
			if (retType == String.class && ValidateUtils.isValid(paramType)
					&& Modifier.isPublic(method.getModifiers())) {
				url = "/" + simpleClassName;
				// ����execute()����
				if (!mname.equals("execute")) {
					url += "_" + mname;
				}
				// ���Ȩ��url
				System.out.println(url);

				rightService.appendRightByURL(url);
			}
		}
	}
}
