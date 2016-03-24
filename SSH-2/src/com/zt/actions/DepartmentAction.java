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
	 * 查询所有的部门
	 * 
	 * @return
	 */
	public String findAllDepts() {
		this.departments = departmentService.findAllEntities();
		return "listDeptPage";
	}

	/**
	 * 跳转到添加部门页面
	 * 
	 * @return
	 */
	public String toAddDeptPage() {
		return "addDeptPage";
	}

	/**
	 * 跳转到编辑部门页面
	 * 
	 * @return
	 */
	public String toEditDeptPage() {
		return "editDeptPage";
	}

	/**
	 * 此方法在toEditDeptPage之前调用
	 */
	public void prepareToEditDeptPage() {
		if (id != null) {
			model = departmentService.getEntity(id);
		}
	}

	/**
	 * 保存或更新实体
	 * 
	 * @return
	 */
	public String saveOrUpdateDept() {
		if (!ValidateUtils.isValid(model.getDeptName())) {
			addActionError("请输入部门信息");
			if (model.getId() == null) {// 插入操作
				return "addDeptPage";
			} else {// 更新操作
				return "editDeptPage";
			}
		}
		Department dept = departmentService.getDeptByName(model.getDeptName());
		if (dept != null) {// 部门存在
			if (model.getId() == null) {// 插入操作
				addActionError("部门信息存在");
				return "addDeptPage";
			}
		}
		departmentService.saveOrUpdateEntity(model);

		// update the keyword
		departmentService.updateDeptsKeyword(model);

		return "findAllDeptsAction";
	}

	/**
	 * 删除实体
	 */
	public String deleteDept() {
		boolean existStaffs = departmentService.existStaffs(id);
		if (existStaffs) {// 部门存在员工
			addActionError("删除失败，该部门存在员工");

			this.pageModels();
			return "listPage";
		}

		// 执行删除
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
