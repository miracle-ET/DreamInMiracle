package com.cy.pj.sys.controller;


import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.sys.entity.Add;
import com.cy.pj.sys.entity.User;
import com.cy.pj.sys.service.SysAddService;
import com.cy.pj.sys.vo.JsonResult;



@Controller
/*需要提供一个访问路径*/
@RequestMapping("/add")

public class SysAddController {
	/*可能需要自动注入一个service接口*/
	@Autowired
	private SysAddService sysAddService;
	
	/*可能需要处理一个请求*/
	@RequestMapping("/doAddNewAddress")
	@ResponseBody
	public JsonResult doInsert(Add address) {
		address.setUserid(((User) SecurityUtils.getSubject().getPrincipal()).getId());
		if(sysAddService.insert(address)==1) {
			return new JsonResult("保存成功");
		}
		else {
			return new JsonResult("保存失败");
		}
	}
	@RequestMapping("/doUpdateAddressById")
	@ResponseBody
	public JsonResult doUpdate(Add address) {
		address.setUserid(((User) SecurityUtils.getSubject().getPrincipal()).getId());
		if(sysAddService.update(address)==1) {
			return new JsonResult("修改成功");
		}
		else {
			return new JsonResult("修改失败");
		}
	}
	
	@RequestMapping("/doSetUserAddresses")
	@ResponseBody
	public JsonResult doSetUserAddresses(Integer id) {
		if(sysAddService.set(id)==1) {
			return new JsonResult("设置成功");
		}
		else {
			return new JsonResult("设置失败");
		}
	}
	
	@RequestMapping("/doFindAddressById")
	@ResponseBody
	public JsonResult doFindById(Integer id) {
		return new JsonResult(sysAddService.findById(id));
	}
	
	
	
	@RequestMapping("/doDeleteUserAddresses")
	@ResponseBody
	public JsonResult doDelete(Integer id) {
		if(sysAddService.delete(id)==1) {
			return new JsonResult("设置成功");
		}
		else {
			return new JsonResult("删除失败");
		}
	}
	@RequestMapping("/doFindUserAddresses")
	@ResponseBody
	public JsonResult doFindall() {
		if(SecurityUtils.getSubject().getPrincipal()!=null) {
			return new JsonResult(sysAddService.findAll(((User) SecurityUtils.getSubject().getPrincipal()).getId()));
		}
		return null;
	}
}