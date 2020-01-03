package com.jt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.Util.UserThreadLocal;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Reference(timeout=3000,check=true)
	private DubboCartService dubboCartService;
	
	@Reference(timeout=3000,check=true)
	private DubboOrderService dubboOrderService;
	@RequestMapping("/create")
	public String create(Model model) {
		Long userId=UserThreadLocal.get().getId();
		List<Cart> carts = dubboCartService.findAll(userId);
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order) {
		order.setUserId(UserThreadLocal.get().getId());
		String orderId=dubboOrderService.saveOrder(order);
		return SysResult.success(orderId);
	}
	@RequestMapping("success")
	public String findOrderById(String id,Model model) {
		Order order=dubboOrderService.findById(id);
		model.addAttribute(order);
		return "success";
	}

}
