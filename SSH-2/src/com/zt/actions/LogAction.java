package com.zt.actions;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zt.entities.Log;
import com.zt.service.BaseService;
import com.zt.service.LogService;
import com.zt.utils.DataUtils;
import com.zt.utils.Page;

/**
 * LogAction
 * 
 * @author zengtao
 *
 */
@Controller
@Scope("prototype")
public class LogAction extends BaseAction<Log> {

	private static final long serialVersionUID = 1719309153984147190L;

	public LogAction() {
		this.currentPage = 7;
	}

	@Resource
	private LogService logService;

	@Resource(name = "logService")
	public void setBaseService(BaseService<Log> baseService) {
		// TODO Auto-generated method stub
		super.setBaseService(baseService);
	}

	// ����������־
	private List<Log> allLogs;

	public List<Log> getAllLogs() {
		return allLogs;
	}

	public void setAllLogs(List<Log> allLogs) {
		this.allLogs = allLogs;
	}

	private int monthId = 2;

	public int getMonthId() {
		return monthId;
	}

	public void setMonthId(int monthId) {
		this.monthId = monthId;
	}

	/**
	 * ��ѯ������־��Ϣ
	 * 
	 * @return
	 */
	public String findAllLogs() {
		allLogs = this.logService.findAllEntities();
		return "logListPage";
	}

	/**
	 * ɾ����־
	 * 
	 * @return
	 */
	public String deleteLog() {
		logService.deleteEntity(model);
		return "findLogListsAction";
	}

	// ��ȡid
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ��ѯ�������־�������£����Է�ҳ����ʽ��ʾ
	 * 
	 * @return
	 */
	public String findNearestLogs() {
		this.allLogs = logService.findNearestLogs(monthId);
		int totalItemNumber = this.allLogs.size();

		// System.out.println("totalItemNumber = " + totalItemNumber);

		Page<Log> page = new Page<Log>(pageNo);
		page.setTotalItemNumber(totalItemNumber);

		this.allLogs = DataUtils.getLogPageList(page.getPageNo(), allLogs,
				pageSize);
		// System.out.println(this.allLogs);
		page.setList(allLogs);

		// ��Ȩ�޴���request������
		request.put("page", page);

		return "logListPage";
	}
}
