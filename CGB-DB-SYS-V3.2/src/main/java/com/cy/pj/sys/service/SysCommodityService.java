package com.cy.pj.sys.service;

import java.util.List;

import com.cy.pj.sys.entity.Commodity;

public interface SysCommodityService {
		/*前台*/
		public List<Commodity> findIDByKey(String key);
		public String findUrlById(Integer id);
		public Commodity findObjById(Integer id);
		
		/*商家*/
		public int insert(Commodity commodity,Integer userid);
		public int delete(Integer id);
		public int update(Commodity commodity,Integer userid);
		public List<Commodity> findObjByUserid(Integer userid);
		
		/*后台*/
		//public List<Commondity> findObjByUserid(Integer userid);
		//public int delete(Integer id);
		
		
}	
