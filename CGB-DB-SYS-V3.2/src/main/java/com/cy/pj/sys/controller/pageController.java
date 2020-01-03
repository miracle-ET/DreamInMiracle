package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@Controller
public class pageController {
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "index";
	}
	@RequestMapping("doRegister")
	public String doRegister() {
		return "regist";
	}
	
	@RequestMapping("doUser")
	public String doUser() {
		return "user";
	}
	@RequestMapping("{moduleUI}")
	public String doModuleUI(@PathVariable String moduleUI) {
			return moduleUI;
	}
	
}
