package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.sys.vo.SysCartVo;

public interface SysCartService {
	public int insert(Integer commodityId,Integer userId);
	public int delete(Integer commodityId);
	public List<SysCartVo> findAll(Integer userid);
}
