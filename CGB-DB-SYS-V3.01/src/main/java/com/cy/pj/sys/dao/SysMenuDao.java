package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SysMenuDao extends SysCommonDao{
	List<Map<String,Object>> findObjects();
}
