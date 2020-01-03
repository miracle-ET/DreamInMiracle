package com.cy.pj.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.pj.sys.entity.User;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.JsonResult;

@Controller
@RequestMapping("/user")
public class SysUserController {
		@Autowired
		SysUserService sysUserService;
	   @RequestMapping("/doLogin")
	   @ResponseBody
	   public JsonResult doLogin(String username,String password){
		   //1.获取Subject对象
		   Subject subject=SecurityUtils.getSubject();
		   //2.通过Subject提交用户信息,交给shiro框架进行认证操作
		   //2.1对用户进行封装
		   UsernamePasswordToken token=
		   new UsernamePasswordToken(
				   username,//身份信息
				   password);//凭证信息
		   //2.2对用户信息进行身份认证
		   subject.login(token);
		   //分析:
		   //1)token会传给shiro的SecurityManager
		   //2)SecurityManager将token传递给认证管理器
		   //3)认证管理器会将token传递给realm
		   return new JsonResult("登录成功");
	   }
	   @RequestMapping("/doSaveUser")
	   @ResponseBody
	   public JsonResult doSaveUser(User user){
		   sysUserService.insertUser(user);
		   return new JsonResult("注册成功");
	   }
}
