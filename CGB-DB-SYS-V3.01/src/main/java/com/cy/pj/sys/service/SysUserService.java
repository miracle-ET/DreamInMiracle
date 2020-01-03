package com.cy.pj.sys.service;

import com.cy.pj.sys.common.vo.PageObject;
import com.cy.pj.sys.common.vo.SysUserDeptVo;
import com.cy.pj.sys.entity.SysUser;

public interface SysUserService {

	PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent);

	int saveObject(SysUser entity, Integer[] roleIds);

}
