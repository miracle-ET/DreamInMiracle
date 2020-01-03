package com.cy.pj.sys.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.sys.common.mapWithEntity;
import com.cy.pj.sys.dao.SysCommodityDao;
import com.cy.pj.sys.dao.SysCommonDao;
import com.cy.pj.sys.entity.Commodity;
import com.cy.pj.sys.service.SysCommodityService;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SysCommodityServiceImpl implements SysCommodityService{
	@Autowired
	SysCommonDao sysCommonDao;
	@Autowired
	SysCommodityDao sysCommodityDao;
	@Override
	public List<Commodity> findIDByKey(String key) {
		 try {
			if(key==null) {
				return null;
			}
			 key=java.net.URLDecoder.decode(key,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(StringUtils.isEmpty(key)) {
			return null;
		}
		else {
			List<Commodity> Commodity= new ArrayList<>();
			List<Integer> list = sysCommodityDao.findIDByKey(key);
			for (Integer integer : list) {
				Commodity.add(findObjById(integer));
			}
			return Commodity;
		}
	}

	@Override
	public String findUrlById(Integer id) {
		// TODO Auto-generated method stub
		if(id==null) {
			return null;
		}
		else {
			List<Map<String, Object>> findParamByParams = sysCommonDao.findParamByParams("sys_commodity", "id", "url", id);
			if(findParamByParams!=null&&findParamByParams.size()!=0) {
				return (String)findParamByParams.get(0).get("url");
			}
			return null;
		}
		
	}

	@Override
	public Commodity findObjById(Integer id) {
		// TODO Auto-generated method stub
		if(id==null) {
			return null;
		}
		else {
			List<Map<String, Object>> findObjectsByParams = sysCommonDao.findObjectsByParams("sys_commodity", "id", id);
			if(findObjectsByParams!=null&&findObjectsByParams.size()!=0) {
				return (Commodity)mapWithEntity.mapToBean(findObjectsByParams.get(0),Commodity.class);
			}
			return null;
		}
	}

	@Override
	public int insert(Commodity commodity,Integer userid) {
		// TODO Auto-generated method stub
		if(commodity!=null&&commodity.getPrice1()!=null&&userid!=null) {
			if(commodity.getPrice2()==null) {
				commodity.setPrice2(commodity.getPrice1());
			}
			commodity.setTime(new Date(System.currentTimeMillis()));
			List<Map<String, Object>> findParamByParams = sysCommonDao.findParamByParams("sys_shop", "userid", "id", userid);
			if(findParamByParams!=null&&findParamByParams.size()!=0) {
				commodity.setShopid((Integer)findParamByParams.get(0).get("id"));
			}
			else {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("userid", userid);
				sysCommonDao.insertObject("sys_shop", map);
				List<Map<String, Object>> findParamByParams2 = sysCommonDao.findParamByParams("sys_shop", "userid", "id", userid);
				commodity.setShopid((Integer)findParamByParams2.get(0).get("id"));
			}
			return sysCommonDao.insertObject("sys_commodity", mapWithEntity.beanToMap(commodity));
		}
		return 0;
	}

	@Override
	public int delete(Integer id) {
		if(id!=null) {
			return sysCommonDao.deleteObjectsByParams("sys_commodity", "id", id);
		}
		return 0;
	}

	@Override
	public int update(Commodity commodity,Integer userid) {
		if(commodity!=null&&commodity.getId()!=null&&commodity.getPrice1()!=null&&userid!=null) {
			if(commodity.getPrice2()==null) {
				commodity.setPrice2(commodity.getPrice1());
			}
			commodity.setTime(new Date(System.currentTimeMillis()));
			List<Map<String, Object>> findParamByParams = sysCommonDao.findParamByParams("sys_shop", "userid", "id", userid);
			if(findParamByParams!=null&&findParamByParams.size()!=0) {
				commodity.setShopid((Integer)findParamByParams.get(0).get("id"));
			}
			else {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("userid", userid);
				sysCommonDao.insertObject("sys_shop", map);
				List<Map<String, Object>> findParamByParams2 = sysCommonDao.findParamByParams("sys_shop", "userid", "id", userid);
				commodity.setShopid((Integer)findParamByParams2.get(0).get("id"));
			}
			return sysCommonDao.updateObject("sys_commodity", mapWithEntity.beanToMap(commodity), "id");
		}
		return 0;
	}


	@Override
	public List<Commodity> findObjByUserid(Integer userid) {
		List<Map<String, Object>> findParamByParams = sysCommonDao.findParamByParams("sys_shop", "userid", "id",userid);
		if(findParamByParams!=null&&findParamByParams.size()!=0) {
			Integer shopid=(Integer)findParamByParams.get(0).get("id");
			
			List<Map<String, Object>> findObjectsByParams = sysCommonDao.findObjectsByParams("sys_commodity", "shopid", shopid);
			if(findObjectsByParams!=null&&findObjectsByParams.size()!=0) {
				List<Commodity> list=new ArrayList<Commodity>();
				for (Map<String, Object> map : findObjectsByParams) {
					list.add((Commodity)mapWithEntity.mapToBean(map, Commodity.class));
				}
				return list;
			}		
		}
		else {
			return null;
		}	
		return null;
	}
}