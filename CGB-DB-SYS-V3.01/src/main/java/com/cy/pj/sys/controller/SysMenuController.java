package com.cy.pj.sys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.sys.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {
	@Autowired
	SysMenuService sysMenuService;
	
	/**查询所有菜单*/
	@RequestMapping("doFindObjects")
	@ResponseBody
	public JsonResult doFindObjects() {
		return new JsonResult(sysMenuService.findObjects());
	}
	
	/**删除菜单和子菜单*/
	@RequestMapping("doDeleteObject")
	@ResponseBody
	 public JsonResult doDeleteObject(Integer id){
		  sysMenuService.deleteObject(id);
		  return new JsonResult("delete ok");
	  }
	@RequestMapping("doFindZtreeMenuNodes")
	@ResponseBody
	 public JsonResult doFindZtreeMenuNodes() {
		return new JsonResult(sysMenuService.findZtreeMenuNodes());
	 }
	@RequestMapping("doSaveObject")
	@ResponseBody
	 public JsonResult doSaveObject(SysMenu sysMenu) {
		sysMenuService.saveObject(sysMenu);
		return new JsonResult("save ok");
		 
	 }
	@RequestMapping("doUpdateObject")
	@ResponseBody
	 public JsonResult doUpdateObject(SysMenu sysMenu) {
		sysMenuService.updateObject(sysMenu);
		return new JsonResult("update ok");	 
	 }
}
