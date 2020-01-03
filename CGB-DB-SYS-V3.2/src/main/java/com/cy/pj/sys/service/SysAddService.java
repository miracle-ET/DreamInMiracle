package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.sys.entity.Add;

public interface SysAddService {
	public int insert(Add add);
	public int delete(Integer id);
	public int update(Add add);
	public List<Add> findAll(Integer userid);
	public Add findById(Integer id);
	public int set(Integer id);
}
