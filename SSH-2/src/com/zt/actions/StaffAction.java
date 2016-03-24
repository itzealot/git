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

	// ����ʵ��
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
	 * ��ѯ���е�Ա����Ϣ
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
	 * ����Ա����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveStaff() throws Exception {
		deptNames = departmentService.findAllDeptNames(null);

		// To validate the name
		if (!ValidateUtils.isValid(model.getName())) {
			addActionError("���ֲ���Ϊ��");
			return "addStaffPage";
		}

		// To validate the username
		if (!ValidateUtils.isValid(model.getUsername())) {
			addActionError("�û�������Ϊ��");
			return "addStaffPage";
		}

		// To validate password
		if (!ValidateUtils.isValidPassword(model.getPassword())) {
			addActionError("���벻��Ϊ��");
			return "addStaffPage";
		}

		// To validate the password and confirmPass
		if (!ValidateUtils.comparePass(model.getPassword(), confirmPass)) {
			addActionError("���벻һ��");
			return "addStaffPage";
		}

		if (!ValidateUtils.isValid(deptName)) {
			addActionError("������Ϣ����Ϊ��");
			return "addStaffPage";
		}
		// To validate the dept_id
		dept = departmentService.getDeptByName(deptName);
		// ���ò�����Ϣ
		model.setDept(dept);

		// To validate the username is existing
		Staff s = staffService.findByUsername(model.getUsername());
		if (s != null) {
			addActionError("�û����Ѵ��ڣ�����������");
			return "addStaffPage";
		} else {
			// �������
			model.setPassword(MD5Utils.getMD5String(model.getPassword()));
		}

		// �洢
		staffService.saveEntity(model);

		// update the staff keyword
		staffService.updateStaffsKeyword(model);
		return "findAllStaffsAction";
	}

	/**
	 * ����Ա����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateStaff() throws Exception {
		deptNames = departmentService.findAllDeptNames(dept);
		// To validate the name
		if (!ValidateUtils.isValid(model.getName())) {
			addActionError("���ֲ���Ϊ��");
			return "editStaffPage";
		}

		// To validate the dept
		Department dept = model.getDept();
		dept = departmentService.getDeptByName(dept.getDeptName());

		// ���ò�����Ϣ
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
	 * ɾ��Ա����Ϣ
	 * 
	 * @return
	 */
	public String deleteStaff() {
		staffService.deleteEntity(model);
		return "findAllStaffsAction";
	}

	/**
	 * ��ת���༭Ա����Ϣҳ��
	 * 
	 * @return
	 */
	public String toEditStaffPage() {
		return "editStaffPage";
	}

	/**
	 * �˷�����toEditStaffPage֮ǰִ��
	 */
	public void prepareToEditStaffPage() {
		if (id != null) {
			model = staffService.getEntity(id);
			// ��ȡ���Ŷ�������
			dept = departmentService.getEntity(model.getDept().getId());
			model.setDept(dept);
		}
		// ��ѯ���в��ţ���������ڵ�һ��
		deptNames = departmentService.findAllDeptNames(dept);
		request.put("deptNames", deptNames);
	}

	/**
	 * ��ת�����Ա����ҳ��
	 * 
	 * @return
	 */
	public String toAddStaffPage() {
		deptNames = departmentService.findAllDeptNames(null);
		return "addStaffPage";
	}

	// ӵ�н�ɫ�ļ���
	private List<Role> ownRoles;

	public List<Role> getOwnRoles() {
		return ownRoles;
	}

	public void setOwnRoles(List<Role> ownRoles) {
		this.ownRoles = ownRoles;
	}

	// δӵ�н�ɫ�ļ���
	private List<Role> noRoles;

	@Resource
	private RoleService roleService;

	public List<Role> getNoRoles() {
		return noRoles;
	}

	public void setNoRoles(List<Role> noRoles) {
		this.noRoles = noRoles;
	}

	// �����ɫid����
	private Integer[] roleIds;

	public Integer[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * ��ӽ�ɫҳ��
	 * 
	 * @return
	 */
	public String toStaffAddRolesPage() {
		return "findStaffAllRoles";
	}

	@SuppressWarnings("unchecked")
	public void prepareToStaffAddRolesPage() {
		if (id != null) {
			// ��õ�ǰԱ�������н�ɫ
			Set<Role> roles = staffService.getEntity(id).getRoles();
			// ����ɫ����ת��ΪList����
			ownRoles = (List<Role>) DataUtils.getListFromSet(roles);
			if (ownRoles == null) {
				ownRoles = new ArrayList<Role>();
			}
			// ��ѯԱ��û�еĽ�ɫ
			noRoles = roleService.findStaffNoRoles(roles);

			// model = staffService.getEntity(id);
		}
	}

	/**
	 * ��ӽ�ɫ
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
			addActionError("������������벻һ��!");
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

	// �����ɫId������
	private String[] leftRoles;

	public String[] getLeftRoles() {
		return leftRoles;
	}

	public void setLeftRoles(String[] leftRoles) {
		this.leftRoles = leftRoles;
	}

	// û�������ɫId������
	private String[] rightRoles;

	public String[] getRightRoles() {
		return rightRoles;
	}

	public void setRightRoles(String[] rightRoles) {
		this.rightRoles = rightRoles;
	}

	// ���沿������
	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	// ����ȷ������
	private String confirmPass;

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	// ����id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
