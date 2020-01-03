package com.cy.pj.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.sys.entity.User;
import com.cy.pj.sys.service.SysCartService;
import com.cy.pj.sys.vo.JsonResult;



@Controller
/*需要提供一个访问路径*/
@RequestMapping("/cart")

public class SysCartController {
	/*可能需要自动注入一个service接口*/
	@Autowired
	private SysCartService sysCartService;
	
	/*可能需要处理一个请求*/
	@RequestMapping("/doInsert")
	@ResponseBody
	public JsonResult doInsert(Integer id) {
		if(sysCartService.insert(id,((User) SecurityUtils.getSubject().getPrincipal()).getId())==1) {
			return new JsonResult("加入购物车成功");
		}
		else {
			return new JsonResult("加入购物车失败");
		}
	}
	@RequestMapping("/doDeleteCommodityById")
	@ResponseBody
	public JsonResult doDdelete(Integer commodityId) {
		if(sysCartService.delete(commodityId)==1) {
			return new JsonResult("删除成功");
		}
		else {
			return new JsonResult("删除失败");
		}
	}
	@RequestMapping("/doFindAllCommodity")
	@ResponseBody
	public JsonResult doFindall(Integer userid) {
			return new JsonResult(sysCartService.findAll(((User) SecurityUtils.getSubject().getPrincipal()).getId()));
	}
	@RequestMapping("/doPayMoney")
	@ResponseBody
	public JsonResult fun_1() {
		return new JsonResult("支付成功");
	}

}