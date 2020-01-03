package com.cy.pj.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.common.exception.ServiceException;
import com.cy.pj.sys.common.vo.Node;
import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService {
	@Autowired
	private SysDeptDao sysDeptDao;
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list=
		sysDeptDao.findObjects();
		if(list==null||list.size()==0)
		throw new ServiceException("没有部门信息");
		return list;
	}
	@Override
	public List<Node> findZTreeNodes() {
		List<Node> node=new ArrayList<Node>();
		List<Map<String, Object>> list=
		sysDeptDao.findObjects("sys_depts");
		for (Map<String, Object> map : list) {
			node.add((Node)mapWithEntity.mapToBean(map, Node.class));
		}
		
		if(list==null||list.size()==0)
		throw new ServiceException("没有部门信息");
		return node;
	}
	@Override
	public int updateObject(SysDept entity) {
		//1.合法验证
		if(entity==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("部门不能为空");
		int rows;
		//2.更新数据
		try{
		rows=sysDeptDao.updateObject("sys_depts", mapWithEntity.beanToMap(entity), "name");
		}catch(Exception e){
		e.printStackTrace();
		throw new ServiceException("更新失败");
		}
		//3.返回数据
		return rows;
	}
	
	@Override
	public int saveObject(SysDept entity) {
		//1.合法验证
		if(entity==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new ServiceException("部门不能为空");
		//2.保存数据
		int rows=sysDeptDao.insertObject("sys_depts", mapWithEntity.beanToMap(entity));
		//if(rows==1)
		//throw new ServiceException("save error");
		//3.返回数据
		return rows;
	}
	@Override
	public int deleteObject(Integer id) {
		//1.合法性验证
		if(id==null||id<=0)
		throw new ServiceException("数据不合法,id="+id);
		//2.执行删除操作
		//2.1判定此id对应的菜单是否有子元素
		int childCount=sysDeptDao.getCountFromParams("sys_depts", "parentId", id);
		if(childCount>0)
		throw new ServiceException("此元素有子元素，不允许删除");
		//2.2判定此部门是否有用户
		//int userCount=sysUserDao.getUserCountByDeptId(id);
		//if(userCount>0)
		//throw new ServiceException("此部门有员工，不允许对部门进行删除");
		//2.2判定此部门是否已经被用户使用,假如有则拒绝删除
		//2.3执行删除操作
		int rows=sysDeptDao.deleteObjectsByParams("sys_depts", "id", id);
		if(rows==0)
		throw new ServiceException("此信息可能已经不存在");
		return rows;
	}


}
