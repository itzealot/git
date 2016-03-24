package com.zt.actions;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.zt.service.BaseService;
import com.zt.utils.Page;

/**
 * 抽象Action，专门用于继承
 * 
 * @author zengtao
 *
 * @param <T>
 */
public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T>, Preparable, RequestAware {

	private BaseService<T> baseService;

	// 注入baseService，和Service层中写的方式不同，写在set方法上
	@Resource
	public void setBaseService(BaseService<T> baseService) {
		this.baseService = baseService;
	}

	private Class<?> clazz;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1576640149213706903L;
	// 设置为public，子类可以直接继承并使用model
	public T model;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseAction() {
		try {
			// 获得泛型并创建实例
			ParameterizedType type = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			clazz = (Class) type.getActualTypeArguments()[0];
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 预备方法
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	// To change to the abstract method
	public T getModel() {
		return model;
	}

	// 注入request对象
	public Map<String, Object> request;

	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	// save all models
	public Page<T> page;

	public int pageNo = 1;
	public int pageSize = 5;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 分页查询权限
	 */
	public String pageModels() {
		page = new Page<T>(pageNo);

		// To get all models and counts
		Integer totalItemNumber = baseService.getEntityCounts();
		page.setTotalItemNumber(totalItemNumber);

		// 初始化firstResult和maxResult
		int firstResult = pageSize * (page.getPageNo() - 1);
		int maxResult = pageSize;

		// save into page
		List<T> list = baseService.findEntityByPageUtil(firstResult, maxResult);
		page.setList(list);

		// System.out.println("page = " + page.getList());
		// save into request
		request.put("page", page);
		return "listPage";
	}

	/**
	 * 记录当前为什么页面.<br />
	 * default value is 1.<br />
	 * 1: staff-page.<br />
	 * 2: webapp-page.<br />
	 * 3: user-page.<br />
	 * 4: dept-page.<br />
	 * 5: right-page.<br />
	 * 6: role-page.<br />
	 * 7: log-page.<br />
	 */
	public int currentPage = 1;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	// The keyword input to search
	public String keyword = "";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 根据关键字查询对象列表
	 * 
	 * @return
	 */
	public String pageModelsByKeyword() {
		page = new Page<T>(pageNo);

		// To get all models and counts
		Integer totalItemNumber = baseService.getModelsCountsByKeyword(keyword);
		page.setTotalItemNumber(totalItemNumber);

		// 初始化firstResult和maxResult
		int firstResult = pageSize * (page.getPageNo() - 1);
		int maxResult = pageSize;

		// save into page
		List<T> list = baseService.findModelsByKeyword(keyword, firstResult,
				maxResult);
		page.setList(list);

		// System.out.println("page = " + page.getList());
		// save into request
		request.put("page", page);
		return "listPage";
	}
}
