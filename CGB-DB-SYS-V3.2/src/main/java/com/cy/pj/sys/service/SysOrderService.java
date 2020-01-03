package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.sys.entity.Order;
import com.cy.pj.sys.vo.SysOrderVo;

public interface SysOrderService {
	public List<SysOrderVo> findOlder(Integer userid);
	public int deleteOlder(Integer id);
	public int insert(Order order);
}
