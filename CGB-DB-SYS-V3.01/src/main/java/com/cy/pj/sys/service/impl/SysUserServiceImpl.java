package com.cy.pj.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.annotation.RequiredLog;
import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.common.config.Config;

import com.cy.pj.sys.common.exception.ServiceException;
import com.cy.pj.sys.common.vo.PageObject;
import com.cy.pj.sys.common.vo.SysUserDeptVo;
import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;



@Service
public class SysUserServiceImpl implements SysUserService{



	/*可能需要实现一个Service接口*/
	/*可能需要自动注入一个dao接口*/
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysDeptDao sysDeptDao;
	@Autowired
	private Config config;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	//private DemoDao demoDao
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(
			String username,Integer pageCurrent) {
		//1.对参数进行校验
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值无效");
		//2.查询总记录数并进行校验
		int rowCount=sysUserDao.getRowCount("sys_users","name" , username);
		if(rowCount==0)
		throw new ServiceException("没有找到对应记录");
		//3.查询当前页记录
		int pageSize=config.getPageSize();
		int startIndex=(pageCurrent-1)*pageSize;
		
		List<SysUserDeptVo> records=new ArrayList<SysUserDeptVo>();
		
		
		
		
		//将map转换为对象
		List<SysUser> findPage = new ArrayList<SysUser>();
		List<Map<String, Object>> list = sysUserDao.findPageObjects("sys_users", "name", username, startIndex, pageSize);
		for (Map<String, Object> map : list) {
			findPage.add((SysUser)mapWithEntity.mapToBean(map, SysUser.class));
		}
		
		
		
		//sysUser.getDeptId()
		for (SysUser sysUser : findPage) {
			records.add(new SysUserDeptVo(sysUser,(SysDept)mapWithEntity.mapToBean(sysDeptDao.findObjectsByParams("sys_depts", "id", sysUser.getDeptId()).get(0), SysDept.class)));
		}

		//4.对查询结果进行封装并返回
		return new PageObject<>(pageCurrent, pageSize, rowCount, records);
	}
	@RequiredLog(value = "insert")
	@Override
	public int saveObject(SysUser entity, Integer[] roleIds) {

    	//1.参数校验
    	if(entity==null)
    		throw new ServiceException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getUsername()))
    		throw new ServiceException("用户名不能为空");
    	if(StringUtils.isEmpty(entity.getPassword()))
    		throw new ServiceException("密码不能为空");
    	if(roleIds==null || roleIds.length==0)
    		throw new ServiceException("至少要为用户分配角色");
    	//2.保存用户自身信息
        //2.1对密码进行加密
    	String source=entity.getPassword();
    	String salt=UUID.randomUUID().toString();
    	SimpleHash sh=new SimpleHash(//Shiro框架
    			"MD5",//algorithmName 算法
    			 source,//原密码
    			 salt, //盐值
    			 1);//hashIterations表示加密次数
    	entity.setSalt(salt);
    	entity.setPassword(sh.toHex());
    	int rows=sysUserDao.insertObject("sys_users", mapWithEntity.beanToMap(entity));
    	//3.保存用户角色关系数据
    	sysUserRoleDao.insertObjects(entity.getId(), roleIds);

    	//4.返回结果
    	return rows;

	}
}