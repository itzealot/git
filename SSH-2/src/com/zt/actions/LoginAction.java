package com.zt.actions;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zt.entities.Staff;
import com.zt.service.BaseService;
import com.zt.service.StaffService;
import com.zt.utils.MD5Utils;
import com.zt.utils.ValidateUtils;

/**
 * 登入Action
 * 
 * @author zengtao
 *
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<Staff> implements SessionAware {

	private static final long serialVersionUID = 5852976681081483410L;

	@Resource
	private StaffService staffService;

	@Resource(name = "staffService")
	public void setBaseService(BaseService<Staff> baseService) {
		// TODO Auto-generated method stub
		super.setBaseService(baseService);
	}

	/**
	 * 实现登入.<br />
	 * To realize login.<br />
	 * 
	 * @return
	 */
	public String doLogin() {

		// To check username
		if (!ValidateUtils.isValid(model.getUsername())) {
			addFieldError("username-error", "Username is must!");
			return "login";
		}
		// To check password
		if (!ValidateUtils.isValidPassword(model.getPassword())) {
			addFieldError("password-error", "Password is must!");
			return "login";
		}
		// To check username and password
		Staff staff = staffService.findByUsername(model.getUsername());
		if (staff == null) {
			addFieldError("username-error", "Username Or Password error!");
			return "login";
		}
		try {
			// To change the password
			model.setPassword(MD5Utils.getMD5String(model.getPassword()));
			if (model.getPassword().equals(staff.getPassword())) {
				session.put("staff", staff);
				return "login-success";
			} else {
				addFieldError("username-error", "Username Or Password error!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addFieldError("username-error", "Login Failure!");
		}
		return "login";
	}

	// session map
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

}
