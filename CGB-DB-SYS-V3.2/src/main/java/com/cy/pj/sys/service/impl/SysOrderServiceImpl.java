package com.cy.pj.sys.service.impl;

import org.springframework.stereotype.Service;

import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.dao.SysCommonDao;
import com.cy.pj.sys.dao.SysOrderDao;
import com.cy.pj.sys.entity.Order;
import com.cy.pj.sys.service.SysOrderService;
import com.cy.pj.sys.vo.SysOrderVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SysOrderServiceImpl implements SysOrderService {

	
	/*可能需要实现一个Service接口*/
	/*可能需要自动注入一个dao接口*/
	@Autowired
	//private DemoDao demoDao
	private SysOrderDao sysOrderDao;
	@Override
	public List<SysOrderVo> findOlder(Integer userid) {
		if(userid!=null) {
			List<SysOrderVo> sysOrderVo=new ArrayList<SysOrderVo>();
			List<Map<String, Object>> findObjectsByParams = sysOrderDao.findObjectsByParams("sys_order", "userid", userid);
			for (Map<String, Object> map : findObjectsByParams) {
				sysOrderVo.add(changeToVo((Order)mapWithEntity.mapToBean(map, Order.class)));
			}
			return sysOrderVo;
		}
		return null;
	}
	@Autowired
	SysCommonDao sysCommonDao;
	public SysOrderVo changeToVo(Order order) {
		String type = null;
		if(order.getType()==0) {
			type="已取消";
		}
		if(order.getType()==1) {
			type="未完成";
		}
		if(order.getType()==2) {
			type="已完成";
		}
		SysOrderVo sysOrderVo=new SysOrderVo();
		sysOrderVo.setId(order.getId());
		sysOrderVo.setTime(order.getTime());
		sysOrderVo.setType(type);
		sysOrderVo.setName((String)sysCommonDao.findParamByParams("sys_add", "id", "name", order.getAddid()).get(0).get("name"));
		sysOrderVo.setUrl((String)sysCommonDao.findParamByParams("sys_commodity", "id", "url", order.getCommodityid()).get(0).get("url"));
		Integer price2=(Integer)sysCommonDao.findParamByParams("sys_commodity","id", "price2", order.getCommodityid()).get(0).get("price2");
		String price="￥"+price2*order.getCount();
		sysOrderVo.setPrice(price);
		return sysOrderVo;
		
	}

	@Override
	public int deleteOlder(Integer id) {
		// TODO Auto-generated method stub
		if(id!=null) {
		return sysCommonDao.deleteObjectsByParams("sys_order", "id", id);
		}
		return 0;
	}

	@Override
	public int insert(Order order) {
		if(order!=null&&order.getCommodityid()!=null&&order.getAddid()!=null&&order.getCount()!=null) {
			order.setTime(new Date(System.currentTimeMillis()));
			order.setType(1);
			return sysCommonDao.insertObject("sys_order",mapWithEntity.beanToMap(order));
					
		}
		return 0;
	}

}