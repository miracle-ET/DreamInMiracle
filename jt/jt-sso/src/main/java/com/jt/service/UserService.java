package com.jt.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.vo.SysResult;

public interface UserService {
	public User findUserById(Long id);
	public List<User> findAll();
	public boolean check(String param,int type);
	JSONPObject query(String ticket, String callback,HttpServletRequest request,HttpServletResponse response);
	
}
