package com.zt.service;

import java.util.List;

import com.zt.entities.Log;

/**
 * LogService <br />
 * extends BaseService<Log> <br />
 * 
 * @author zengtao
 *
 */
public interface LogService extends BaseService<Log> {

	/**
	 * ���ݱ�������־��
	 */
	public void createLogTable(String tableName);

	/**
	 * ��ѯ���ָ���·�������־��Ĭ��ֵ��2
	 * 
	 * @param i
	 * @return
	 */
	public List<Log> findNearestLogs(int i);

	/**
	 * ʵ�ַ�ҳ��ѯ<br />
	 * i : ��ѯ���i���·ݵ���־<br />
	 * firstResult : �ӵڶ�������¼��ʼ��ѯ<br />
	 * maxResult : ��ѯ��������¼<br />
	 * 
	 * @param i
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<Log> findLogsPage(int i, int firstResult, int maxResult);

}
