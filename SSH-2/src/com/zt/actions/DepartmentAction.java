package com.zt.actions;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zt.entities.Department;
import com.zt.service.BaseService;
import com.zt.service.DepartmentService;
import com.zt.utils.ValidateUtils;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {

	private static final long serialVersionUID = 923275096309441157L;

	public DepartmentAction() {
		// change the currentPage value
		this.currentPage = 4;
	}

	@Resource
	private DepartmentService departmentService;

	@Resource(name = "departmentService")
	public void setBaseService(BaseService<Department> baseService) {
		// TODO Auto-generated method stub
		super.setBaseService(baseService);
	}

	private List<Department> departments;

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	/**
	 * ��ѯ���еĲ���
	 * 
	 * @return
	 */
	public String findAllDepts() {
		this.departments = departmentService.findAllEntities();
		return "listDeptPage";
	}

	/**
	 * ��ת����Ӳ���ҳ��
	 * 
	 * @return
	 */
	public String toAddDeptPage() {
		return "addDeptPage";
	}

	/**
	 * ��ת���༭����ҳ��
	 * 
	 * @return
	 */
	public String toEditDeptPage() {
		return "editDeptPage";
	}

	/**
	 * �˷�����toEditDeptPage֮ǰ����
	 */
	public void prepareToEditDeptPage() {
		if (id != null) {
			model = departmentService.getEntity(id);
		}
	}

	/**
	 * ��������ʵ��
	 * 
	 * @return
	 */
	public String saveOrUpdateDept() {
		if (!ValidateUtils.isValid(model.getDeptName())) {
			addActionError("�����벿����Ϣ");
			if (model.getId() == null) {// �������
				return "addDeptPage";
			} else {// ���²���
				return "editDeptPage";
			}
		}
		Department dept = departmentService.getDeptByName(model.getDeptName());
		if (dept != null) {// ���Ŵ���
			if (model.getId() == null) {// �������
				addActionError("������Ϣ����");
				return "addDeptPage";
			}
		}
		departmentService.saveOrUpdateEntity(model);

		// update the keyword
		departmentService.updateDeptsKeyword(model);

		return "findAllDeptsAction";
	}

	/**
	 * ɾ��ʵ��
	 */
	public String deleteDept() {
		boolean existStaffs = departmentService.existStaffs(id);
		if (existStaffs) {// ���Ŵ���Ա��
			addActionError("ɾ��ʧ�ܣ��ò��Ŵ���Ա��");

			this.pageModels();
			return "listPage";
		}

		// ִ��ɾ��
		departmentService.deleteEntity(model);
		return "findAllDeptsAction";
	}

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
