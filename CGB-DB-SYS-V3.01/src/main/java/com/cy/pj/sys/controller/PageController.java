package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class PageController {
	/** 返回首页页面 */
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}

	/** 返回分页页面 */
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	/** 返回登入页面 */
	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}

	/** 返回日志列表页面 */
	@RequestMapping("log/log_list")
	public String doLogUI() {
		return "sys/log_list";
	}
	/**返回菜单页面*/
	@RequestMapping("menu/menu_list")
	private String doMenuUI() {
		return "sys/menu_list";
	}
	/**返回菜单页面*/
	@RequestMapping("menu/menu_edit")
	private String doMenuEditUI() {
		return "sys/menu_edit";
	}
	/**返回部门页面*/
	@RequestMapping("dept/dept_list")
	private String doDeptEditUI() {
		return "sys/dept_list";
	}
	/**返回角色页面*/
	@RequestMapping("role/role_list")
	private String doRoleUI() {
		return "sys/role_list";
	}
	/**返回角色修改页面*/
	@RequestMapping("role/role_edit")
	private String doRoleEditUI() {
		return "sys/role_edit";
	}
	/**返回用户页面*/
	@RequestMapping("user/user_list")
	private String doUserUI() {
		return "sys/user_list";
	}
	/**返回用户编辑*/
	@RequestMapping("user/user_edit")
	private String doUserEditUI() {
		return "sys/user_edit";
	}
	


}
