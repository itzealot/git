package com.zt.actions;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.zt.entities.Staff;
import com.zt.service.BaseService;

/**
 * 注销登入Action
 * 
 * @author zengtao
 *
 */
@Controller
@Scope("prototype")
public class LogoutAction extends BaseAction<Staff> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7165261671764214518L;

	@Resource(name = "staffService")
	public void setBaseService(BaseService<Staff> baseService) {
		// TODO Auto-generated method stub
		super.setBaseService(baseService);
	}

	/**
	 * 退出登入，清除session.<br />
	 * To logout the system by remove the attribute where the map session string
	 * staff.<br />
	 * 
	 * @return
	 */
	public String doLogout() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("staff");
		return "login";
	}

}
