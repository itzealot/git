package com.zt.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zt.entities.security.Right;
import com.zt.entities.security.Role;
import com.zt.service.BaseService;
import com.zt.service.RightService;
import com.zt.service.RoleService;
import com.zt.utils.DataUtils;
import com.zt.utils.ValidateUtils;

/**
 * RoleAction��ɫAction
 * 
 * @author zengtao
 *
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = -7256791516739173604L;
	@Resource
	private RoleService roleService;

	@Resource(name = "roleService")
	public void setBaseService(BaseService<Role> baseService) {
		// TODO Auto-generated method stub
		super.setBaseService(baseService);
	}

	public RoleAction() {
		this.currentPage = 6;
	}

	@Resource
	private RightService rightService;

	/**
	 * ��ѯ���еĽ�ɫ
	 */
	public String findAllRoles() {
		allRoles = roleService.findAllEntities();
		return "findAllRoles";
	}

	/**
	 * �������½�ɫ
	 */
	public String saveOrUpdateRole() {
		if (!ValidateUtils.isValid(model.getRoleName())) {
			addActionError("��ɫ��Ϊ��");
			if (model.getId() == null) {// �������
				return "addRolePage";
			} else {
				return "editRolePage";
			}

		}

		Role r = roleService.findRoleByName(model.getRoleName());
		if (r != null) {// ��ɫ������
			if (model.getId() == null) {// �������
				addActionError("��ɫ������");
				return "addRolePage";
			}
		}
		roleService.saveOrUpdateEntity(model);

		// update the role's keyword
		roleService.updateRolesKeyword(model);
		return "findAllRolesAction";
	}

	/**
	 * ɾ����ɫ
	 */
	public String deleteRole() {
		roleService.deleteEntity(model);
		return "findAllRolesAction";
	}

	/**
	 * ��ת����ɫ�༭ҳ��
	 */
	public String toEditRolePage() {
		return "editRolePage";
	}

	/**
	 * �η�����toEditRolePage֮ǰִ��
	 */
	public void prepareToEditRolePage() {
		if (id != null) {
			// ��Ҫ�ر������أ����������������쳣����Role.hbm.xmlӳ���ļ��йر�
			this.model = roleService.getEntity(id);
		}
	}

	/**
	 * ��ת����ӽ�ɫҳ��
	 */
	public String toAddRolePage() {
		return "addRolePage";
	}

	// �������н�ɫ
	private List<Role> allRoles;

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	// ��ɫid
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * ����ɫ����Ȩ��
	 */
	public String roleAddRights() {
		roleService.grantAndRemoveRights(id, leftRights, rightRights);
		return "findAllRolesAction";
	}

	/**
	 * �����ɫ����Ȩ��ҳ��
	 */
	public String toRoleAddRightsPage() {
		return "findRoleAllRights";
	}

	/**
	 * �˷�����toRoleAddRightsPage֮ǰִ��
	 */
	@SuppressWarnings("unchecked")
	public void prepareToRoleAddRightsPage() {
		if (id != null) {
			model = roleService.getEntity(id);
			// ��ȡ����Ȩ��
			Set<Right> rights = model.getRights();

			ownRights = (List<Right>) DataUtils.getListFromSet(rights);
			if (ownRights == null) {
				ownRights = new ArrayList<Right>();
			}
			// ��ѯȨ��
			noRights = rightService.findRightsNotInRange(rights);
		}
	}

	// ӵ�е�Ȩ�޼���
	private List<Right> ownRights;
	// δӵ�е�Ȩ�޼���
	private List<Right> noRights;

	public List<Right> getOwnRights() {
		return ownRights;
	}

	public void setOwnRights(List<Right> ownRights) {
		this.ownRights = ownRights;
	}

	public List<Right> getNoRights() {
		return noRights;
	}

	public void setNoRights(List<Right> noRights) {
		this.noRights = noRights;
	}

	// ����Ȩ��Id������
	private String[] leftRights;
	// û������Ȩ��Id������
	private String[] rightRights;

	public String[] getLeftRights() {
		return leftRights;
	}

	public void setLeftRights(String[] leftRights) {
		this.leftRights = leftRights;
	}

	public String[] getRightRights() {
		return rightRights;
	}

	public void setRightRights(String[] rightRights) {
		this.rightRights = rightRights;
	}

}
