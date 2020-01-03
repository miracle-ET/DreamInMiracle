package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.sys.common.vo.CheckBox;
import com.cy.pj.sys.common.vo.PageObject;
import com.cy.pj.sys.common.vo.SysRoleMenuVo;
import com.cy.pj.sys.entity.SysRole;

public interface SysRoleService {
	 PageObject<SysRole> findPageObjects(
			 String name,
			 Integer pageCurrent);
	 int deleteObject(Integer id);
	 SysRole findObjectById(Integer id);
	 int insertObject(SysRole sysRole,Integer... menuIDs);
	 int findIDByName(String name);
	 SysRoleMenuVo findVOById(Integer id);
	 int updateObject(SysRole sysRole,Integer... menuIDs);
	 List<CheckBox> findObjects();
}
