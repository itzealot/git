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
	 * ����ע��logDao ��д�÷�������Ϊ�˸��ǳ����и÷�����ע�⣬ָ��ע���Dao�����𣬷���spring <br />
	 * �޷�ȷ��ע���ĸ�Dao
	 */
	@Resource(name = "logDao")
	public void setDao(BaseDao<Log> dao) {
		// TODO Auto-generated method stub
		super.setDao(dao);
	}

	/**
	 * ͨ������������־��
	 */
	@Override
	public void createLogTable(String tableName) {
		// TODO Auto-generated method stub
		String sql = "create table if not exists " + tableName + " like logs";
		this.executeSQL(sql);
	}

	/**
	 * ��дsaveEntity��������ǰ�·ݲ�����־<br />
	 * ����̬������־��¼
	 */
	@Override
	public void saveEntity(Log t) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO "
				+ LogUtils.generateLogTableName(0)
				+ "(ID,OPERATOR,OPER_NAME,OPER_PARAMS,OPER_RESULT,RESULT_MSG,OPER_TIME) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";

		// Hibernate�������ɲ��ԣ�����id
		String id = UUIDGenerator
				.buildSessionFactoryUniqueIdentifierGenerator()
				.generate(null, null).toString();
		System.out.println("id = " + id);
		this.executeSQL(sql, id, t.getOperator(), t.getOperName(),
				t.getOperParams(), t.getOperResult(), t.getResultMsg(),
				t.getOperTime());
	}

	/**
	 * ��ѯ���ָ���·�������־��Ĭ��ֵ��2<br />
	 * ���迴�Ƿ���ڱ�
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Log> findNearestLogs(int i) {
		// TODO Auto-generated method stub
		// ��õ�ǰ�·ݵ���־������
		String tableName = LogUtils.generateLogTableName(0);

		// ��ѯ�������־������
		String sql = "SELECT table_name FROM information_schema.tables "
				+ "WHERE table_schema='DeChengCleaningComWebApp' "
				+ "AND table_name like 'logs_%' " + "AND table_name <='"
				+ tableName + "' " + "ORDER BY table_name DESC LIMIT 0, " + i;
		// String sql = "SELECT * FROM " + tableName;
		List<?> lists = this.executeSQLQuery(null, sql);

		// ��ѯ��������µ���־
		String logSql = "";
		String logName = "";
		int length = lists.size();
		for (int j = 0; j < length; j++) {
			logName = (String) lists.get(j);
			// �������һ��
			logSql += "SELECT * FROM " + logName;
			if (j != length - 1) {
				logSql += " UNION ";
			}
		}
		// ָ��Logʵ����
		return this.executeSQLQuery(Log.class, logSql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Log> findLogsPage(int i, int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		// ��õ�ǰ��־������
		String tableName = LogUtils.generateLogTableName(0);
		
		// ��ѯ�������־������
		String sql = "SELECT table_name FROM information_schema.tables "
				+ "WHERE table_schema='DeChengCleaningComWebApp' "
				+ "AND table_name like 'logs_%' " + "AND table_name <='"
				+ tableName + "' " + "ORDER BY table_name DESC LIMIT 0, " + i;
		List<?> lists = this.executeSQLQuery(null, sql);

		// ��ѯ��������µ���־
		String logSql = "";
		String logName = "";
		int length = lists.size();
		for (int j = 0; j < length; j++) {
			logName = (String) lists.get(j);
			// �������һ��
			logSql += "SELECT * FROM " + logName;
			if (j != length - 1) {
				logSql += " UNION ";
			}
		}
		// ָ��Logʵ����
		return this.executeSQLQuery(Log.class, logSql);
	}

}
