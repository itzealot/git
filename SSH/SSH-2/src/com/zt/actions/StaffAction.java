package com.zt.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.zt.entities.Department;
import com.zt.entities.Staff;
import com.zt.entities.security.Role;
import com.zt.service.BaseService;
import com.zt.service.DepartmentService;
import com.zt.service.RoleService;
import com.zt.service.StaffService;
import com.zt.utils.DataUtils;
import com.zt.utils.ListUtil;
import com.zt.utils.MD5Utils;
import com.zt.utils.Page;
import com.zt.utils.ValidateUtils;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {

	private static final long serialVersionUID = -1398512038765156033L;

	public StaffAction() {
		this.currentPage = 1;
	}

	@Resource(name = "staffService")
	public void setBaseService(BaseService<Staff> baseService) {
		// TODO Auto-generated method stub
		super.setBaseService(baseService);
	}

	private List<Staff> staffs;

	public List<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}

	// 部门实体
	private Department dept;

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	private List<String> deptNames;

	public List<String> getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(List<String> deptNames) {
		this.deptNames = deptNames;
	}

	@Resource
	private StaffService staffService;

	@Resource
	private DepartmentService departmentService;

	/**
	 * 查询所有的员工信息
	 * 
	 * @return
	 */
	public String findAllStaffs() {
		this.staffs = staffService.findAllEntities();
		int length = staffs.size();
		for (int i = 0; i < length; i++) {
			Integer deptId = staffs.get(i).getDept().getId();
			if (deptId != null) {
				dept = departmentService.getEntity(deptId);
				staffs.get(i).setDept(dept);
			}
		}
		return "listStaffPage";
	}

	/**
	 * 保存员工信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveStaff() throws Exception {
		deptNames = departmentService.findAllDeptNames(null);

		// To validate the name
		if (!ValidateUtils.isValid(model.getName())) {
			addActionError("名字不能为空");
			return "addStaffPage";
		}

		// To validate the username
		if (!ValidateUtils.isValid(model.getUsername())) {
			addActionError("用户名不能为空");
			return "addStaffPage";
		}

		// To validate password
		if (!ValidateUtils.isValidPassword(model.getPassword())) {
			addActionError("密码不能为空");
			return "addStaffPage";
		}

		// To validate the password and confirmPass
		if (!ValidateUtils.comparePass(model.getPassword(), confirmPass)) {
			addActionError("密码不一致");
			return "addStaffPage";
		}

		if (!ValidateUtils.isValid(deptName)) {
			addActionError("部门信息不能为空");
			return "addStaffPage";
		}
		// To validate the dept_id
		dept = departmentService.getDeptByName(deptName);
		// 设置部门信息
		model.setDept(dept);

		// To validate the username is existing
		Staff s = staffService.findByUsername(model.getUsername());
		if (s != null) {
			addActionError("用户名已存在，请重新输入");
			return "addStaffPage";
		} else {
			// 密码加密
			model.setPassword(MD5Utils.getMD5String(model.getPassword()));
		}

		// 存储
		staffService.saveEntity(model);

		// update the staff keyword
		staffService.updateStaffsKeyword(model);
		return "findAllStaffsAction";
	}

	/**
	 * 更新员工信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateStaff() throws Exception {
		deptNames = departmentService.findAllDeptNames(dept);
		// To validate the name
		if (!ValidateUtils.isValid(model.getName())) {
			addActionError("名字不能为空");
			return "editStaffPage";
		}

		// To validate the dept
		Department dept = model.getDept();
		dept = departmentService.getDeptByName(dept.getDeptName());

		// 设置部门信息
		model.setDept(dept);

		staffService.saveOrUpdateEntity(model);
		// update the staff keyword
		staffService.updateStaffsKeyword(model);
		return "findAllStaffsAction";
	}

	/**
	 * The method is execute before updateStaff method
	 */
	public void prepareUpdateStaff() {
		if (id != null) {
			model = staffService.getEntity(id);
		}
	}

	/**
	 * 删除员工信息
	 * 
	 * @return
	 */
	public String deleteStaff() {
		staffService.deleteEntity(model);
		return "findAllStaffsAction";
	}

	/**
	 * 跳转到编辑员工信息页面
	 * 
	 * @return
	 */
	public String toEditStaffPage() {
		return "editStaffPage";
	}

	/**
	 * 此方法在toEditStaffPage之前执行
	 */
	public void prepareToEditStaffPage() {
		if (id != null) {
			model = staffService.getEntity(id);
			// 获取部门对象并设置
			dept = departmentService.getEntity(model.getDept().getId());
			model.setDept(dept);
		}
		// 查询所有部门，并将其放在第一个
		deptNames = departmentService.findAllDeptNames(dept);
		request.put("deptNames", deptNames);
	}

	/**
	 * 跳转到添加员工的页面
	 * 
	 * @return
	 */
	public String toAddStaffPage() {
		deptNames = departmentService.findAllDeptNames(null);
		return "addStaffPage";
	}

	// 拥有角色的集合
	private List<Role> ownRoles;

	public List<Role> getOwnRoles() {
		return ownRoles;
	}

	public void setOwnRoles(List<Role> ownRoles) {
		this.ownRoles = ownRoles;
	}

	// 未拥有角色的集合
	private List<Role> noRoles;

	@Resource
	private RoleService roleService;

	public List<Role> getNoRoles() {
		return noRoles;
	}

	public void setNoRoles(List<Role> noRoles) {
		this.noRoles = noRoles;
	}

	// 保存角色id数组
	private Integer[] roleIds;

	public Integer[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * 添加角色页面
	 * 
	 * @return
	 */
	public String toStaffAddRolesPage() {
		return "findStaffAllRoles";
	}

	@SuppressWarnings("unchecked")
	public void prepareToStaffAddRolesPage() {
		if (id != null) {
			// 获得当前员工的所有角色
			Set<Role> roles = staffService.getEntity(id).getRoles();
			// 将角色进行转换为List集合
			ownRoles = (List<Role>) DataUtils.getListFromSet(roles);
			if (ownRoles == null) {
				ownRoles = new ArrayList<Role>();
			}
			// 查询员工没有的角色
			noRoles = roleService.findStaffNoRoles(roles);

			// model = staffService.getEntity(id);
		}
	}

	/**
	 * 添加角色
	 * 
	 * @return
	 */
	public String staffAddRoles() {
		staffService.grantAndCancelRole(id, leftRoles, rightRoles);
		return "findAllStaffsAction";
	}

	/**
	 * To my info page.<br />
	 * 
	 * @return
	 */
	public String toMyInfoPage() {
		Staff staff = (Staff) ActionContext.getContext().getSession()
				.get("staff");
		dept = departmentService.getEntity(staff.getDept().getId());
		request.put("dept", dept);
		return "myInfoPage";
	}

	/**
	 * To change password page.<br />
	 * 
	 * @return
	 */
	public String toChangePassPage() {
		return "changePassPage";
	}

	/**
	 * To change the password.<br />
	 * 
	 * @return
	 */
	public String changePass() {
		Staff staff = (Staff) ActionContext.getContext().getSession()
				.get("staff");
		// password is must
		if ("".equals(model.getPassword())) {
			addActionError("Password is must!");
			return "changePassPage";
		}

		// confirm password
		if ("".equals(confirmPass)) {
			addActionError("Confirm Password is must!");
			return "changePassPage";
		}

		// check is equal
		if (!model.getPassword().equals(confirmPass)) {
			addActionError("两次输入的密码不一致!");
			return "changePassPage";
		}

		// get MD5 and change password
		try {
			staff.setPassword(MD5Utils.getMD5String(confirmPass));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "changePassPage";
		}

		// update the password
		staffService.updatePassword(staff);

		// must login again
		ActionContext.getContext().getSession().clear();
		return "login";
	}

	/**
	 * To find staff by keyword
	 * 
	 * @return
	 */
	public String findStaffsByKeyword() {
		// the list that find by keyword
		List<Staff> lists = staffService.findStaffsByKeyword(keyword);

		page = new Page<Staff>(pageNo);

		// To set the size
		Integer totalItemNumber = lists.size();
		page.setTotalItemNumber(totalItemNumber);

		page.setList(ListUtil.getListByPage(lists, pageSize, pageNo));

		// System.out.println("page = " + page.getList());
		// save into request
		request.put("page", page);
		return "listPage";
	}

	// 授予角色Id的数组
	private String[] leftRoles;

	public String[] getLeftRoles() {
		return leftRoles;
	}

	public void setLeftRoles(String[] leftRoles) {
		this.leftRoles = leftRoles;
	}

	// 没有授予角色Id的数组
	private String[] rightRoles;

	public String[] getRightRoles() {
		return rightRoles;
	}

	public void setRightRoles(String[] rightRoles) {
		this.rightRoles = rightRoles;
	}

	// 保存部门名称
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	// 保存确认密码
	private String confirmPass;

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	// 保存id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
