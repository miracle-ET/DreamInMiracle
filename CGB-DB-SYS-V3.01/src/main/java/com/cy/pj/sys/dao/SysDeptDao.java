package com.cy.pj.sys.dao;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SysDeptDao extends SysCommonDao{
	  @Select("select c.*,p.name parentName from sys_depts c left join sys_depts p on c.parentId=p.id")
	  List<Map<String,Object>> findObjects();
}







