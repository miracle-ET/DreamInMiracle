package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysCommodityDao {
	/*可能需要写入一些访问数据库的方法 并提供一个mapper.xml*/
	/*可能需要导入一些entity用来封装数据库数据*/
	List<Integer> findIDByKey(String key);
}