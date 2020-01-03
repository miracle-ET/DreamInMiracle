package com.jt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.service.UserService;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;



@RestController
/*需要提供一个访问路径*/
@RequestMapping("/user")

public class UserController {
	/*可能需要自动注入一个service接口*/
	@Autowired
	private UserService userService;
	
	/*可能需要处理一个请求*/
	@RequestMapping("/findAll")
	public String findAll() {
		return ObjectMapperUtil.toJSON(userService.findAll());
	}
	/*可能需要处理一个请求*/
	@RequestMapping("/check/{param}/{type}")
	public JSONPObject check(@PathVariable String param,@PathVariable int type,String callback) {	
		return new JSONPObject(callback, new SysResult(200,"ok",userService.check(param, type)));
	}
	@RequestMapping("/query/{ticket}")
	public JSONPObject query(@PathVariable String ticket,String callback,HttpServletRequest request,HttpServletResponse response) {
		return userService.query(ticket, callback, request, response);
	}
}