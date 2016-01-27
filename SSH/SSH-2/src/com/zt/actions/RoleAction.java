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
 * RoleAction角色Action
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
	 * 查询所有的角色
	 */
	public String findAllRoles() {
		allRoles = roleService.findAllEntities();
		return "findAllRoles";
	}

	/**
	 * 保存或更新角色
	 */
	public String saveOrUpdateRole() {
		if (!ValidateUtils.isValid(model.getRoleName())) {
			addActionError("角色名为空");
			if (model.getId() == null) {// 插入操作
				return "addRolePage";
			} else {
				return "editRolePage";
			}

		}

		Role r = roleService.findRoleByName(model.getRoleName());
		if (r != null) {// 角色名存在
			if (model.getId() == null) {// 插入操作
				addActionError("角色名存在");
				return "addRolePage";
			}
		}
		roleService.saveOrUpdateEntity(model);

		// update the role's keyword
		roleService.updateRolesKeyword(model);
		return "findAllRolesAction";
	}

	/**
	 * 删除角色
	 */
	public String deleteRole() {
		roleService.deleteEntity(model);
		return "findAllRolesAction";
	}

	/**
	 * 跳转到角色编辑页面
	 */
	public String toEditRolePage() {
		return "editRolePage";
	}

	/**
	 * 次方法在toEditRolePage之前执行
	 */
	public void prepareToEditRolePage() {
		if (id != null) {
			// 需要关闭懒加载，否则后出现懒加载异常，在Role.hbm.xml映射文件中关闭
			this.model = roleService.getEntity(id);
		}
	}

	/**
	 * 跳转到添加角色页面
	 */
	public String toAddRolePage() {
		return "addRolePage";
	}

	// 保存所有角色
	private List<Role> allRoles;

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	// 角色id
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 给角色授予权限
	 */
	public String roleAddRights() {
		roleService.grantAndRemoveRights(id, leftRights, rightRights);
		return "findAllRolesAction";
	}

	/**
	 * 到达角色授予权限页面
	 */
	public String toRoleAddRightsPage() {
		return "findRoleAllRights";
	}

	/**
	 * 此方法在toRoleAddRightsPage之前执行
	 */
	@SuppressWarnings("unchecked")
	public void prepareToRoleAddRightsPage() {
		if (id != null) {
			model = roleService.getEntity(id);
			// 获取所有权限
			Set<Right> rights = model.getRights();

			ownRights = (List<Right>) DataUtils.getListFromSet(rights);
			if (ownRights == null) {
				ownRights = new ArrayList<Right>();
			}
			// 查询权限
			noRights = rightService.findRightsNotInRange(rights);
		}
	}

	// 拥有的权限集合
	private List<Right> ownRights;
	// 未拥有的权限集合
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

	// 授予权限Id的数组
	private String[] leftRights;
	// 没有授予权限Id的数组
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
