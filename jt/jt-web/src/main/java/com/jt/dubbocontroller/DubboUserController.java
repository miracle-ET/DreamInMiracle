package com.jt.dubbocontroller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.util.CookieUtil;
import com.jt.util.IPUtil;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/user")
public class DubboUserController {
	@Reference(timeout=3000,check=true)
	private DubboUserService dubboUserService;	
	
	@ResponseBody
	@RequestMapping("/findAll")
	public List<User> findAll(){	
		return dubboUserService.findAll();
	}
	@ResponseBody
	@RequestMapping("/doRegister")
	public SysResult saveUser(User user) {
		dubboUserService.saveUser(user);
		return SysResult.success();
	}
	@ResponseBody
	@RequestMapping("/doLogin")
	public SysResult doLogin(User user,HttpServletResponse response,HttpServletRequest request) {	
		String ticket = dubboUserService.doLogin(user, IPUtil.getIpAddress(request));
		if(ticket!=null) {
			CookieUtil.addCookie("JT_TICKET", ticket, 7*24*60*60, "/", "jt.com", response);
			return SysResult.success();
		}
		else {
			return SysResult.fail();
		}
	}
	//logout
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response,HttpServletRequest reques) {
		Cookie cookie = CookieUtil.getCookie("JT_TICKET", reques);
		String ticket=null;
		if(cookie==null) {
			return "redirect:/";		 
		}
		ticket=cookie.getValue();
		dubboUserService.logout(ticket);	
		CookieUtil.deleteCookie("JT_TICKET", "/", "jt.com", response);	
		return "redirect:/";
	}
}
