package com.zt.listeners;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.zt.service.LogService;
import com.zt.utils.LogUtils;

/**
 * 初始化日志表监听器，在spring初始化完成后立即调用
 * 
 * @author zengtao
 *
 */
// 交给spring容器管理
@Component
@SuppressWarnings("rawtypes")
public class InitLogTablesListener implements ApplicationListener {

	@Resource
	private LogService logService;

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		// 上下文刷新事件
		if (arg0 instanceof ContextRefreshedEvent) {
			String tableName = LogUtils.generateLogTableName(0);
			logService.createLogTable(tableName);

			tableName = LogUtils.generateLogTableName(1);
			logService.createLogTable(tableName);

			tableName = LogUtils.generateLogTableName(2);
			logService.createLogTable(tableName);

			System.out.println("Finish the log tables...");
		}
	}

}
