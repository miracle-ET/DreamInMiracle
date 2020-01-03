package com.cy.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.cy.pj.sys.entity.Order;
import com.cy.pj.sys.service.SysOrderService;
import com.cy.pj.sys.vo.JsonResult;



@Controller
/*需要提供一个访问路径*/
@RequestMapping("/order")

public class SysOrderController {
	/*可能需要自动注入一个service接口*/
	@Autowired
	private SysOrderService sysOrderService;
	
	/*可能需要处理一个请求*/
	@RequestMapping("/doInsert")
	@ResponseBody
	public JsonResult doInsert(Order order) {
		if(sysOrderService.insert(order)==1) {
			return new JsonResult("保存成功");
		}
		else {
			return new JsonResult("保存失败");
		}
	}
	@RequestMapping("/doDelete")
	@ResponseBody
	public JsonResult doDelete(Integer id) {
		if(sysOrderService.deleteOlder(id)==1) {
			return new JsonResult("删除成功");
		}
		else {
			return new JsonResult("删除失败");
		}
	}
	@RequestMapping("/doFindall")
	@ResponseBody
	public JsonResult doFindall(Integer userid) {
			return new JsonResult(sysOrderService.findOlder(userid));
	}
}