package com.zt.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zt.service.LogService;
import com.zt.utils.LogUtils;

/**
 * 创建日志表的石英任务
 * 
 * @author zengtao
 *
 */
public class CreateLogTablesTask extends QuartzJobBean {

	private LogService logService;

	// 注入logService
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		// 连续生成3个月的日志表
		// 下一个月
		String tableName = LogUtils.generateLogTableName(1);
		logService.createLogTable(tableName);

		// 下两个月
		tableName = LogUtils.generateLogTableName(2);
		logService.createLogTable(tableName);

		// 下三个月
		tableName = LogUtils.generateLogTableName(3);
		logService.createLogTable(tableName);
	}
}
