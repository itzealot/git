package com.zt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.id.UUIDGenerator;
import org.springframework.stereotype.Service;

import com.zt.dao.BaseDao;
import com.zt.entities.Log;
import com.zt.service.LogService;
import com.zt.utils.LogUtils;

@Service("logService")
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

	@Resource
	private LogService logService;

	/**
	 * 泛型注入logDao 重写该方法，是为了覆盖超类中该方法的注解，指明注入的Dao对象吗，否则spring <br />
	 * 无法确定注入哪个Dao
	 */
	@Resource(name = "logDao")
	public void setDao(BaseDao<Log> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	/**
	 * 通过表名创建日志表
	 */
	@Override
	public void createLogTable(String tableName) {
		// TODO Auto-generated method stub
		String sql = "create table if not exists " + tableName + " like logs";
		this.executeSQL(sql);
	}

	/**
	 * 重写saveEntity方法，向当前月份插入日志<br />
	 * 代动态插入日志记录
	 */
	@Override
	public void saveEntity(Log t) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "
				+ LogUtils.generateLogTableName(0)
				+ "(ID,OPERATOR,OPER_NAME,OPER_PARAMS,OPER_RESULT,RESULT_MSG,OPER_TIME) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

		// Hibernate主键生成策略，生成id
		String id = UUIDGenerator
				.buildSessionFactoryUniqueIdentifierGenerator()
				.generate(null, null).toString();
		System.out.println("id = " + id);
		this.executeSQL(sql, id, t.getOperator(), t.getOperName(),
				t.getOperParams(), t.getOperResult(), t.getResultMsg(),
				t.getOperTime());
	}

	/**
	 * 查询最近指定月份数的日志，默认值是2<br />
	 * 还需看是否存在表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> findNearestLogs(int i) {
		// TODO Auto-generated method stub
		// 获得当前月份的日志表名称
		String tableName = LogUtils.generateLogTableName(0);

		// 查询最近的日志表名称
		String sql = "SELECT table_name FROM information_schema.tables "
				+ "WHERE table_schema='DeChengCleaningComWebApp' "
				+ "AND table_name like 'logs_%' " + "AND table_name <='"
				+ tableName + "' " + "ORDER BY table_name DESC LIMIT 0, " + i;
		// String sql = "SELECT * FROM " + tableName;
		List<?> lists = this.executeSQLQuery(null, sql);

		// 查询最近若干月的日志
		String logSql = "";
		String logName = "";
		int length = lists.size();
		for (int j = 0; j < length; j++) {
			logName = (String) lists.get(j);
			// 不是最后一个
			logSql += "SELECT * FROM " + logName;
			if (j != length - 1) {
				logSql += " UNION ";
			}
		}
		// 指定Log实体类
		return this.executeSQLQuery(Log.class, logSql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> findLogsPage(int i, int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		// 获得当前日志表名称
		String tableName = LogUtils.generateLogTableName(0);
		
		// 查询最近的日志表名称
		String sql = "SELECT table_name FROM information_schema.tables "
				+ "WHERE table_schema='DeChengCleaningComWebApp' "
				+ "AND table_name like 'logs_%' " + "AND table_name <='"
				+ tableName + "' " + "ORDER BY table_name DESC LIMIT 0, " + i;
		List<?> lists = this.executeSQLQuery(null, sql);

		// 查询最近若干月的日志
		String logSql = "";
		String logName = "";
		int length = lists.size();
		for (int j = 0; j < length; j++) {
			logName = (String) lists.get(j);
			// 不是最后一个
			logSql += "SELECT * FROM " + logName;
			if (j != length - 1) {
				logSql += " UNION ";
			}
		}
		// 指定Log实体类
		return this.executeSQLQuery(Log.class, logSql);
	}

}
