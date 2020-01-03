package com.jt.dubbocontroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.Util.UserThreadLocal;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;
import com.jt.vo.SysResult;


@Controller
@RequestMapping("/cart")
public class DubboCartController {
	@Reference(timeout=3000,check=true)
	private DubboCartService dubboCartService;
	
	
	@RequestMapping("/show")
	public String show(Model model) {
		
		Long userId=UserThreadLocal.get().getId();
		List<Cart> cart= dubboCartService.findAll(userId);
		model.addAttribute(cart);
		return "cart";
	}
	@RequestMapping("/add/{itemId}")
	public String addCart(Cart cart) {
		Long userId=UserThreadLocal.get().getId();
		cart.setUserId(userId);
		dubboCartService.addCart(cart);
		return "redirect:/cart/show.html";
	}
	@ResponseBody
	@RequestMapping("/update/num/{itemId}/{num}")
	public SysResult updateCartNum(Cart cart) {
		Long userId=UserThreadLocal.get().getId();
		cart.setUserId(userId);
		dubboCartService.updateCartNum(cart);
		return SysResult.success();
	}
}
