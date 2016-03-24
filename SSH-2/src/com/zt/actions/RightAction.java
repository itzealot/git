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

	// ��������Ȩ��
	private List<Right> allRights;

	public List<Right> getAllRights() {
		return allRights;
	}

	public void setAllRights(List<Right> allRights) {
		this.allRights = allRights;
	}

	/**
	 * �Զ�ע��
	 */
	@Resource
	private RightService rightService;

	/**
	 * ��ѯ����Ȩ��
	 */
	public String findAllRights() {
		this.allRights = rightService.findAllEntities();
		return "rightListPage";
	}

	/**
	 * ����/����Ȩ��
	 * 
	 * @return
	 */
	public String saveOrUpdateRight() {
		if (model.getId() != null) {// ����
			if (!ValidateUtils.isValid(model.getRightName())) {
				addActionError("������Ȩ������");
				return "editRightPage";
			}
		}

		rightService.saveOrUpdateRight(model);
		// update model keyword
		rightService.updateStaffsKeyword(model);
		return "findAllRightAction";
	}

	/**
	 * ��������Ȩ��
	 */
	public String batchUpdateRights() {
		rightService.batchUpdateRights(allRights);
		return "findAllRightAction";
	}

	// Ȩ��id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * �༭Ȩ��
	 */
	public String editRight() {
		return "editRightPage";
	}

	/**
	 * �÷�����editRight֮ǰִ��
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
