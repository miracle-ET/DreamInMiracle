package com.cy.pj.sys.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.common.config.Config;
import com.cy.pj.sys.common.exception.ServiceException;
import com.cy.pj.sys.common.vo.CheckBox;
import com.cy.pj.sys.common.vo.PageObject;
import com.cy.pj.sys.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService{
	@Autowired
	Config config;
	@Autowired
	SysRoleDao sysRoleDao;
	@Autowired
	SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	SysUserRoleDao sysUserRoleDao;
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		// 1.验证参数合法性
		// 1.1验证pageCurrent的合法性，
		// 不合法抛出IllegalArgumentException异常
		if (pageCurrent == null || pageCurrent < 1)
			throw new IllegalArgumentException("当前页码不正确");
		// 2.基于条件查询总记录数
		// 2.1) 执行查询
		int rowCount = sysRoleDao.getRowCount("sys_roles", "name", name);
		// 2.2) 验证查询结果，假如结果为0不再执行如下操作
		if (rowCount == 0) {
			throw new ServiceException("系统没有查到对应记录");
		}
		// 3.基于条件查询当前页记录(pageSize定义为2)
		// 3.1)定义pageSize
		int pageSize = config.getPageSize();
		// 3.2)计算startIndex
		int startIndex = (pageCurrent - 1) * pageSize;
		// 3.3)执行当前数据的查询操作
		
		List<SysRole> records = new ArrayList<SysRole>();
		List<Map<String, Object>> list = sysRoleDao.findPageObjects("sys_roles", "name", name, startIndex, pageSize);
		for (Map<String, Object> map : list) {
			records.add((SysRole)mapWithEntity.mapToBean(map, SysRole.class));
		}
		
		
		
		/*时间显示设置*/
		for (SysRole sysRole : records) {
			//28800000
			//sysRole.getCreatedTime().setTime(sysRole.getCreatedTime().getTime());
			//sysRole.getModifiedTime().setTime(sysRole.getModifiedTime().getTime());
		}
		// 4.对分页信息以及当前页记录进行封装
		// 4.1)构建PageObject对象
		PageObject<SysRole> pageObject = new PageObject<>();
		// 4.2)封装数据
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount - 1) / pageSize + 1);
		// 5.返回封装结果。
		return pageObject;
	}
	@Override
	public int deleteObject(Integer id) {
		//需对id进行校验
		
		sysRoleMenuDao.deleteObjectsByParams("sys_role_menus", "role_id", id);
		sysUserRoleDao.deleteObjectsByParams("sys_user_roles", "role_id", id);
		sysRoleDao.deleteObjectsByParams("sys_roles", "id", id);

		return 1;
	}
	@Override
	public SysRole findObjectById(Integer id) {	
		return (SysRole)mapWithEntity.mapToBean(sysRoleDao.findObjectsByParams("sys_roles", "id", id).get(0), SysRole.class);
	}
	@Override
	public int insertObject(SysRole sysRole, Integer... menuIDs) {
			sysRoleDao.insertObject("sys_roles", mapWithEntity.beanToMap(sysRole));
			sysRoleMenuDao.insertObject(sysRole.getId(), menuIDs);
			//findIDByName(sysRole.getName())
		return 1;
	}
	@Override
	public int findIDByName(String name) {
		return (Integer)sysRoleDao.findParamByParams("sys_roles", "name", "id", name).get(0).get("id");
	}
	@Override
	public SysRoleMenuVo findVOById(Integer id) {
		if(id==null||id<=0)
	    	throw new ServiceException("id的值不合法");
		sysRoleDao.findObjectsByParams("sys_roles", "id", id).get(0).get("id");
		SysRoleMenuVo vo=new SysRoleMenuVo();


		List<Integer> listInt=new ArrayList<>();
		List<Map<String, Object>> list = sysRoleMenuDao.findParamByParams("sys_role_menus", "role_id", "menu_id", id);
		for (Map<String, Object> map : list) {
			listInt.add((Integer)map.get("id"));
		}
		vo.setMenuIds(listInt);
		
		vo.setId(((Long)sysRoleDao.findObjectsByParams("sys_roles", "id", id).get(0).get("id")).intValue());
		vo.setName((String)sysRoleDao.findObjectsByParams("sys_roles", "id", id).get(0).get("name"));
		vo.setNote((String)sysRoleDao.findObjectsByParams("sys_roles", "id", id).get(0).get("note"));
		return vo;
	}
	@Override
	public int updateObject(SysRole entity, Integer... menuIds) {
    	if(entity==null)
            throw new ServiceException("更新的对象不能为空");
        	if(entity.getId()==null)
        	throw new ServiceException("id的值不能为空");
        	
        	if(StringUtils.isEmpty(entity.getName()))
        	throw new ServiceException("角色名不能为空");
        	if(menuIds==null||menuIds.length==0)
        	throw new ServiceException("必须为角色指定一个权限");
        	
        	//2.更新数据
        	
        	try {
				entity=(SysRole) mapWithEntity.updateBean(entity, mapWithEntity.mapToBean(sysRoleDao.findObjectsByParams("sys_roles", "id", entity.getId()).get(0), SysRole.class));
			} catch (Exception e) {
				e.printStackTrace();
			}
        	entity.setModifiedTime(new Date(System.currentTimeMillis()));
        	int rows=sysRoleDao.updateObject("sys_roles", mapWithEntity.beanToMap(entity), "id");
        	if(rows==0)
            throw new ServiceException("对象可能已经不存在");
        	sysRoleMenuDao.deleteObjectsByParams("sys_role_menus", "role_id", entity.getId());
        	sysRoleMenuDao.insertObject(entity.getId(),menuIds);
        	//3.返回结果
        	return rows;

	}
	@Override
    public List<CheckBox> findObjects() {
		List<CheckBox> checkBox=new ArrayList<>();
		List<Map<String, Object>> list = sysRoleDao.findObjects("sys_roles");
		for (Map<String, Object> map : list) {
			checkBox.add((CheckBox)mapWithEntity.mapToBean(map, CheckBox.class));
		}
     	return  checkBox;
    }



}
