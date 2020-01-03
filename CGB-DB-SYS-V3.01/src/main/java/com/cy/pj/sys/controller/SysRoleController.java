package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.sys.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@Controller
@RequestMapping("/role/")
public class SysRoleController {
	@Autowired
	SysRoleService sysRoleService;
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(String name,Integer pageCurrent) {
		return new JsonResult(sysRoleService.findPageObjects(name, pageCurrent));
	}
	//doSaveObject
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(SysRole sysRole, Integer... menuIds) {
		sysRoleService.insertObject(sysRole, menuIds);
		return new JsonResult("save ok");
	}
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id) {
		sysRoleService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id) {
		return new JsonResult(sysRoleService.findVOById(id));
	}
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysRole sysRole, Integer... menuIds) {
		sysRoleService.updateObject(sysRole, menuIds);
		return new JsonResult("Update ok");
	}
	@RequestMapping("doFindRoles")
	@ResponseBody
	 public JsonResult doFindRoles() {
		 return new JsonResult(sysRoleService.findObjects());
	 }

}
