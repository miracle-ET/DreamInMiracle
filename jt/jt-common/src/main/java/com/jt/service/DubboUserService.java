package com.jt.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import com.jt.pojo.User;



public interface DubboUserService {
	
	List<User> findAll();	
	@Transactional
	void saveUser(User user);
	String doLogin(User user,String ip);
	void logout(String ticket);
}
