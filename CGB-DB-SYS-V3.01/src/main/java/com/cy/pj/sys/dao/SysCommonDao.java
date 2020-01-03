package com.cy.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysCommonDao {
	/* 可能需要写入一些访问数据库的方法 并提供一个mapper.xml */
	/* 可能需要导入一些entity用来封装数据库数据 */

	/** 在name1表中插入一条map记录 */
	int insertObject(@Param("name1") String name1, @Param("params") Map<String, Object> params);

	/** 根据一个参数的值删除一个表中的记录 name1表名 name2参数名 param参数值 */
	int deleteObjectsByParams(@Param("name1") String name1, @Param("name2") String name2,
			@Param("params") Object... params);
	
	/** 在name1表中更新一条map记录 以name2字段的值为条件 */
	int updateObject(@Param("name1") String name1, @Param("params") Map<String, Object> params,
			@Param("name2") String name2);

	/** 分页查询 name1=表名 列名name2 like name */
	int getRowCount(@Param("name1") String name1, @Param("name2") String name2, @Param("name") String name);

	List<Map<String, Object>> findPageObjects(@Param("name1") String name1, @Param("name2") String name2,
			@Param("name") String name, @Param("startIndex") Object startIndex, @Param("pageSize") Object pageSize);

	/** 查询name1表所有的记录 返回一个list map */
	List<Map<String, Object>> findObjects(@Param("name1") String name1);

	/** 查询name1表中 name2列值为paramas数组的一条或多条记录 返回一个list map */
	List<Map<String, Object>> findObjectsByParams(@Param("name1") String name1, @Param("name2") String name2,
			@Param("params") Object... params);

	/** 查询name1表中name3的列 name2列值为paramas数组的一条或多条记录 返回一个值的list map */
	List<Map<String, Object>> findParamByParams(@Param("name1") String name1, @Param("name2") String name2,
			@Param("name3") String name3, @Param("params") Object... params);

	/** 统计name1表中name2列的值为为paramas数组的记录的个数 返回一个int */
	int getCountFromParams(@Param("name1") String name1, @Param("name2") String name2,
			@Param("params") Object... params);

}