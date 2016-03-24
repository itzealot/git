package com.zt.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 日期格式化转换
 * 
 * @author zengtao
 *
 */
public class DateConverter extends StrutsTypeConverter {

	private DateFormat dateFormat;

	{
		// 格式化为年-月-日
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
		// TODO Auto-generated method stub
		if (arg2 == Date.class) {
			try {
				return dateFormat.parse(arg1[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		if (arg1 instanceof Date) {
			return dateFormat.format((Date) arg1);
		}
		return null;
	}

}
