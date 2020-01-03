package com.cy.pj.sys.service.impl;

import org.springframework.stereotype.Service;

import com.cy.pj.sys.common.IsNull;
import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.dao.SysAddDao;
import com.cy.pj.sys.entity.Add;
import com.cy.pj.sys.service.SysAddService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SysAddServiceImpl implements SysAddService{

	@Autowired
	SysAddDao sysAddDao;
	
	@Override
	public int insert(Add add) {
		if(add==null) {
			return 0;
		}
		if(add.getUserid()!=null&&add.getAdd1()!=null&&add.getAdd2()!=null&&add.getAdd4()!=null&&add.getName()!=null&&add.getMobile()!=null&&add.getType()!=null) {
			return sysAddDao.insertObject("sys_add", mapWithEntity.beanToMap(add));
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		if(id!=null) {
		return sysAddDao.deleteObjectsByParams("sys_add", "id", id);
		}
		else {
			return 0;
		}
	}

	@Override
	public int update(Add add) {
		if(IsNull.isNull(add)) {
			return 0;
		}
		else {
			return sysAddDao.updateObject("sys_add", mapWithEntity.beanToMap(add), "id");
		}
	}

	@Override
	public List<Add> findAll(Integer userid) {
		// TODO Auto-generated method stub
		if(userid!=null) {
			List<Add> add=new ArrayList<Add>();
			List<Map<String, Object>> findObjectsByParams = sysAddDao.findObjectsByParams("sys_add", "userid", userid);
			if(findObjectsByParams!=null&&findObjectsByParams.size()!=0) {
				for (Map<String, Object> map : findObjectsByParams) {
					add.add((Add)mapWithEntity.mapToBean(map, Add.class));
				}
				return add;
			}
		}
		return null;
	}
	/*可能需要实现一个Service接口*/
	/*可能需要自动注入一个dao接口*/

	@Override
	public Add findById(Integer id) {
		if(id!=null) {
			List<Map<String, Object>> findObjectsByParams = sysAddDao.findObjectsByParams("sys_add", "id", id);
			if(findObjectsByParams!=null&&findObjectsByParams.size()!=0) {
				System.out.println((Add)mapWithEntity.mapToBean(findObjectsByParams.get(0), Add.class));
				return (Add)mapWithEntity.mapToBean(findObjectsByParams.get(0), Add.class);
				
			}
		}
		return null;
	}

	@Override
	public int set(Integer id) {
		if(id!=null) {
			return sysAddDao.set(id);
		}
		return 0;
	}
}