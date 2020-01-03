package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.sys.entity.User;
import com.cy.pj.sys.vo.PageObject;


public interface SysUserService {
	int insertUser(User user);
	int deleteUser(Integer id);
	int deleteUser(String username);
	
	int updateUser(User user);
	
	PageObject<User> findPageObjects(
			 String username,
			 Integer pageCurrent);
	List<User> findUser(Integer id);
	List<User> findUser(String username);
	
}
