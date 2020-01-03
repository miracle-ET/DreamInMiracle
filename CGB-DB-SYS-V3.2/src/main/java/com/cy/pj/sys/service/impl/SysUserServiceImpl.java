package com.cy.pj.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.User;
import com.cy.pj.sys.service.SysUserService;
import com.cy.pj.sys.vo.PageObject;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SysUserServiceImpl implements SysUserService{
	/*可能需要实现一个Service接口*/
	/*可能需要自动注入一个dao接口*/
	@Autowired
	SysUserDao sysUserDao;
	//private DemoDao demoDao

	@Override
	//注册
	public int insertUser(User user) {
		if(user==null) {
			throw new ServiceException("用户不能为空");
		}
		if(StringUtils.isEmpty(user.getPassword())){
			throw new ServiceException("用户名不能为空");
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			throw new ServiceException("密码不能为空");
		}
		if(!(sysUserDao.getCountFromParams("sys_users", "username", user.getUsername())==0)) {
			throw new ServiceException("用户名已存在");
		}
		if(!(sysUserDao.getCountFromParams("sys_users", "email", user.getEmail())==0)) {
			throw new ServiceException("邮箱已存在");
		}
    	String source=user.getPassword();
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh=new SimpleHash(//Shiro框架
    			"MD5",//algorithmName 算法
    			 source,//原密码
    			 salt, //盐值
    			 1);//hashIterations表示加密次数
    	user.setSalt(salt);
    	user.setPassword(sh.toHex());
    	user.setCreatedTime(new Date(System.currentTimeMillis()));
    	user.setModifiedTime(user.getCreatedTime());
    	return sysUserDao.insertObject("sys_users", mapWithEntity.beanToMap(user));	
	}

	@Override
	public int deleteUser(Integer id) {
		if(id!=null) {
		return sysUserDao.deleteObjectsByParams("sys_users", "id", id);
		}
		return 0;

	}

	@Override
	public int deleteUser(String username) {
		if(username!=null) {
			return sysUserDao.deleteObjectsByParams("sys_users", "username",username);
		}
		return 0;
		
	}

	@Override
	//修改密码
	public int updateUser(User user) {
		if(user==null) {
			throw new ServiceException("用户不能为空");
		}
		if(StringUtils.isEmpty(user.getPassword())){
			throw new ServiceException("用户名不能为空");
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			throw new ServiceException("密码不能为空");
		}
		if(sysUserDao.getCountFromParams("sys_users", "username", user.getUsername())==0) {
			System.out.println("用户名不存在");
			throw new ServiceException("用户名不存在");
		}
    	String source=user.getPassword();
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh=new SimpleHash(//Shiro框架
    			"MD5",//algorithmName 算法
    			 source,//原密码
    			 salt, //盐值
    			 1);//hashIterations表示加密次数
    	user.setSalt(salt);
    	user.setPassword(sh.toHex());

    	user.setEmail((String)sysUserDao.findParamByParams("sys_users", "username", "email", user.getUsername()).get(0).get("email"));
    	user.setId((Integer)sysUserDao.findParamByParams("sys_users", "username", "id", user.getUsername()).get(0).get("id"));
    	
    	user.setCreatedTime((Date)sysUserDao.findParamByParams("sys_users", "username", "createdTime", user.getUsername()).get(0).get("createdTime"));
    	user.setModifiedTime(new Date(System.currentTimeMillis()));
    	return sysUserDao.updateObject("sys_users", mapWithEntity.beanToMap(user), "username");
	}

	@Override
	public PageObject<User> findPageObjects(String username, Integer pageCurrent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUser(Integer id) {
		
		return null;
	}

	@Override
	public List<User> findUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}