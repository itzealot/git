package com.zt.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zt.service.LogService;
import com.zt.utils.LogUtils;

/**
 * ������־���ʯӢ����
 * 
 * @author zengtao
 *
 */
public class CreateLogTablesTask extends QuartzJobBean {

	private LogService logService;

	// ע��logService
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		// ��������3���µ���־��
		// ��һ����
		String tableName = LogUtils.generateLogTableName(1);
		logService.createLogTable(tableName);

		// ��������
		tableName = LogUtils.generateLogTableName(2);
		logService.createLogTable(tableName);

		// ��������
		tableName = LogUtils.generateLogTableName(3);
		logService.createLogTable(tableName);
	}
}
