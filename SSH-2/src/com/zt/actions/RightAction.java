package com.zt.actions;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zt.entities.security.Right;
import com.zt.service.BaseService;
import com.zt.service.RightService;
import com.zt.utils.ValidateUtils;

/**
 * RightAction
 * 
 * @author zengtao
 *
 */
@Controller
@Scope("prototype")
public class RightAction extends BaseAction<Right> {

	private static final long serialVersionUID = -2732781771046945337L;

	@Resource(name = "rightService")
	public void setBaseService(BaseService<Right> baseService) {
		// TODO Auto-generated method stub
		super.setBaseService(baseService);
	}

	public RightAction() {
		this.currentPage = 5;
	}

	// 保存所有权限
	private List<Right> allRights;

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	/**
	 * 自动注入
	 */
	@Resource
	private RightService rightService;

	/**
	 * 查询所有权限
	 */
	public String findAllRights() {
		this.allRights = rightService.findAllEntities();
		return "rightListPage";
	}

	/**
	 * 保存/更新权限
	 * 
	 * @return
	 */
	public String saveOrUpdateRight() {
		if (model.getId() != null) {// 更新
			if (!ValidateUtils.isValid(model.getRightName())) {
				addActionError("请输入权限名称");
				return "editRightPage";
			}
		}

		rightService.saveOrUpdateRight(model);
		// update model keyword
		rightService.updateStaffsKeyword(model);
		return "findAllRightAction";
	}

	/**
	 * 批量更新权限
	 */
	public String batchUpdateRights() {
		rightService.batchUpdateRights(allRights);
		return "findAllRightAction";
	}

	// 权限id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 编辑权限
	 */
	public String editRight() {
		return "editRightPage";
	}

	/**
	 * 该方法在editRight之前执行
	 */
	public void prepareEditRight() {
		if (id != null) {
			model = rightService.getEntity(id);
		}
	}

	/**
	 * To Delete Right Page.<br />
	 * 
	 * @return
	 */
	public String toDeleteRightPage() {
		// To use the pageModels method
		this.pageModels();
		return "deleteRightPage";
	}

}
