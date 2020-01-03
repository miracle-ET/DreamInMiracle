package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SysAddDao extends SysCommonDao{
	/*可能需要写入一些访问数据库的方法 并提供一个mapper.xml*/
	/*可能需要导入一些entity用来封装数据库数据*/
	@Update("update sys_add set type = 1 where id = #{id}")
	public int set(Integer id);
}