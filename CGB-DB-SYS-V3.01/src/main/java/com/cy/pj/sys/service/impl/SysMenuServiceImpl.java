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
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;

	/**储存ID和子ID*/
	private List<Integer> listID=new ArrayList<>();
	
	
	
	/**查询所有菜单*/
	@Override
	public List<Map<String, Object>> findObjects() {
		List<Map<String, Object>> list=sysMenuDao.findObjects();
		return list;
	}
	
	
	/**删除菜单和子菜单*/
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<=0) {
			throw new IllegalArgumentException("请先选择");
		}
		else {
			//将List转换为数组
			getIDs(id);
			Integer []ids=new Integer[listID.size()];
			for(int i=0;i<listID.size();i++) {
				ids[i]=listID.get(i);
			}
			listID.clear();
			sysRoleMenuDao.deleteObjectsByParams("sys_role_menus", "menu_id", ids);
			int rows=sysMenuDao.deleteObjectsByParams("sys_menus", "id", ids);
			if(rows==0) {
				throw new ServiceException( "MenuId="+id+"的菜单不存在");
			}
			return rows;
		}
	}
	
	/**递归获取菜单的所有子菜单储存到list中*/
	private int getIDs(Integer id) {
		
		List<Map<String, Object>> list=sysMenuDao.findParamByParams("sys_menus", "parentId", "id", id);
		
		
		if(list.size()==0) {
			listID.add(id);
			return 1;
		}
		else {
			for (Map<String, Object> id2 : list) {
				getIDs((Integer)id2.get("id"));
			}
			listID.add(id);
			return 1;
		}
	}

	/**查找节点*/
	@Override
	public List<Node> findZtreeMenuNodes() {
		List<Node> node=new ArrayList<Node>();
		List<Map<String, Object>> list=sysMenuDao.findObjects();
		for (Map<String, Object> map : list) {
			node.add((Node)mapWithEntity.mapToBean(map, Node.class));
		}
		return node;
	}

	/**添加菜单*/
	@Override
	public int saveObject(SysMenu sysMenu) {
		

		
		int rows=0;
		if(sysMenu==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(sysMenu.getName()))
		throw new ServiceException("菜单名不能为空");
		
		//将List转换为数组
		getIDs(sysMenu.getId());
		int parentId=sysMenu.getParentId();
		for (Integer integer : listID) {
			if(parentId==integer) {
				throw new ServiceException("父菜单不能为菜单本身及其子菜单");
			}
		}
		listID.clear();
		/**数据校验*/
		//。。。未完成
		
		System.out.println(sysMenu.toString());
		
		//保存数据
		try{
		rows=sysMenuDao.insertObject("sys_menus", mapWithEntity.beanToMap(sysMenu));
		}catch(Exception e){
		e.printStackTrace();
		throw new ServiceException("保存失败");
		}
		return rows;
	}


	@Override
	public int updateObject(SysMenu sysMenu) {
		
		int rows=0;
		if(sysMenu==null)
		throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(sysMenu.getName()))
		throw new ServiceException("菜单名不能为空");
		
		
		//将List转换为数组
		getIDs(sysMenu.getId());
		int parentId=sysMenu.getParentId();
		for (Integer integer : listID) {
			if(parentId==integer) {
				throw new ServiceException("父菜单不能为菜单本身及其子菜单");
			}
		}
		listID.clear();
		/**数据校验*/
		//。。。未完成
		
		System.out.println(sysMenu.toString());
		
		//保存数据
		try{
		rows=sysMenuDao.updateObject("sys_menus", mapWithEntity.beanToMap(sysMenu), "id");
		}catch(Exception e){
		e.printStackTrace();
		throw new ServiceException("保存失败");
		}
		if(rows==0) {
			throw new ServiceException("记录不存在");
		}
		return rows;
	}
}
