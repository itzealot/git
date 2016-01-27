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
	 * 根据表名创日志表
	 */
	public void createLogTable(String tableName);

	/**
	 * 查询最近指定月份数的日志，默认值是2
	 * 
	 * @param i
	 * @return
	 */
	public List<Log> findNearestLogs(int i);

	/**
	 * 实现分页查询<br />
	 * i : 查询最近i个月份的日志<br />
	 * firstResult : 从第多少条记录开始查询<br />
	 * maxResult : 查询多少条记录<br />
	 * 
	 * @param i
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	public List<Log> findLogsPage(int i, int firstResult, int maxResult);

}
