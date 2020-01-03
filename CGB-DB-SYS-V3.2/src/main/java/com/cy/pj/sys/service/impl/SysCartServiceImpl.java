package com.cy.pj.sys.service.impl;

import org.springframework.stereotype.Service;

import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.dao.SysCartDao;
import com.cy.pj.sys.entity.Cart;
import com.cy.pj.sys.entity.Commodity;
import com.cy.pj.sys.service.SysCartService;
import com.cy.pj.sys.vo.SysCartVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SysCartServiceImpl implements SysCartService{
	@Autowired
	private SysCartDao sysCartDao;
	@Autowired
	private SysCommodityServiceImpl sysCommodityServiceImpl;

	@Override
	public int insert(Integer commodityId,Integer userId) {
		System.out.println();
		if(commodityId!=null&&userId!=null) {
			Cart cart=new Cart();
			cart.setUserid(userId);
			cart.setCommodityid(commodityId);
			return sysCartDao.insertObject("sys_cart", mapWithEntity.beanToMap(cart));
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		if(id!=null){
			return sysCartDao.deleteObjectsByParams("sys_cart", "id", id);
		}	
		return 0;
	}

	@Override
	public List<SysCartVo> findAll(Integer userid) {
		List<SysCartVo> list=new ArrayList<SysCartVo>();
		List<Map<String, Object>> findObjectsByParams = sysCartDao.findObjectsByParams("sys_cart", "userid",userid);
		if(findObjectsByParams!=null&&findObjectsByParams.size()!=0) {
			for (Map<String, Object> map : findObjectsByParams) {
				SysCartVo sysCartVo=new SysCartVo();
				sysCartVo.setId((Integer)map.get("id"));
				sysCartVo.setCommodityid((Integer)map.get("commodityid"));
				Commodity findObjById = sysCommodityServiceImpl.findObjById(sysCartVo.getCommodityid());
				if(findObjById!=null) {
					sysCartVo.setName(findObjById.getName());
					sysCartVo.setNote(findObjById.getNote());
					sysCartVo.setPrice(findObjById.getPrice2());
					sysCartVo.setUrl(findObjById.getUrl());
				}
				else {
					continue;
				}
				list.add(sysCartVo);		
			}
		}	
		return list;
	}
	/*可能需要实现一个Service接口*/
	/*可能需要自动注入一个dao接口*/

}