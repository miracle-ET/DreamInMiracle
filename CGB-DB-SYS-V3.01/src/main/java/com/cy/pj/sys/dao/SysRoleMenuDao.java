package com.cy.pj.sys.dao;





import org.apache.ibatis.annotations.Mapper;




@Mapper
public interface SysRoleMenuDao extends SysCommonDao{
	int insertObject(Integer roleID,Integer... menuIDs);
}
