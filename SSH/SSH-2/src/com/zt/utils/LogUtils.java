package com.zt.utils;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * 日志工具类
 * 
 * @author zengtao
 *
 */
public class LogUtils {

	/**
	 * 生成日志表名称:logs_year_month<br />
	 * offset是偏移量
	 * 
	 * @param offset
	 * @return
	 */
	public static String generateLogTableName(int offset) {
		// 单例Calendar
		Calendar c = Calendar.getInstance();
		// 2015
		int year = c.get(Calendar.YEAR);

		// Calendar.MONTH : 0-11 + 1 = 1 - 12
		int month = c.get(Calendar.MONTH) + 1;

		// 加偏移量
		month += offset;
		if (month > 12) {
			year++;
			// or month -= 1
			month = month - 12;
		}
		if (month < 1) {
			year--;
			month = month + 12;
		}
		DecimalFormat format = new DecimalFormat();
		// 使用格式即两位，不够高位补0
		format.applyPattern("00");
		return "logs_" + year + "_" + format.format(month);
	}
}
