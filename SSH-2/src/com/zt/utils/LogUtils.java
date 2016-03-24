package com.zt.utils;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * ��־������
 * 
 * @author zengtao
 *
 */
public class LogUtils {

	/**
	 * ������־������:logs_year_month<br />
	 * offset��ƫ����
	 * 
	 * @param offset
	 * @return
	 */
	public static String generateLogTableName(int offset) {
		// ����Calendar
		Calendar c = Calendar.getInstance();
		// 2015
		int year = c.get(Calendar.YEAR);

		// Calendar.MONTH : 0-11 + 1 = 1 - 12
		int month = c.get(Calendar.MONTH) + 1;

		// ��ƫ����
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
		// ʹ�ø�ʽ����λ��������λ��0
		format.applyPattern("00");
		return "logs_" + year + "_" + format.format(month);
	}
}
