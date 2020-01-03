package com.cy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.dao.SysOrderDao;
import com.cy.pj.sys.service.SysCommodityService;

@SpringBootTest
public class CommodityTest {
	@Autowired
	SysCommodityService sysCommodityService;
	/*@Test
	public void test1() {
		Commondity commondity=new Commondity();
		commondity.setPrice1(100);
		commondity.setShopid(2);
		sysCommodityService.insert(commondity);
	}*/
	/*@Test
	public void test2() {

		sysCommodityService.delete(3);
	}*/
	/*@Test
	public void test3() {
		Commondity commondity=new Commondity();
		commondity.setPrice1(3000);
		commondity.setPrice2(2000);
		commondity.setId(5);
		commondity.setShopid(20);
		commondity.setNote("商品111111");
		System.out.println(sysCommodityService.update(commondity));
	}*/
	/*@Test
	public void test4() {
		List<Commondity> findObjByUserid = sysCommodityService.findObjByUserid(3);
		for (Commondity commondity2 : findObjByUserid) {
			System.out.println(commondity2);
		}
		
	}*/
	/*@Test
	public void test5() {
		List<Integer> findObjByUserid = sysCommodityService.findIDByKey("1");
		for (Integer commondity2 : findObjByUserid) {
			System.out.println(commondity2);
		}
		
	}*/
	/*@Test
	public void test6() {
		List<Integer> findObjByUserid = sysCommodityService.findIDByKey("1");
		for (Integer commondity2 : findObjByUserid) {
			System.out.println(sysCommodityService.findUrlById(commondity2));
		}
	}*/
	/*@Test
	public void test6() {
		List<Integer> findObjByUserid = sysCommodityService.findIDByKey("1");
		for (Integer commondity2 : findObjByUserid) {
			System.out.println(sysCommodityService.findUrlById(commondity2));
		}
	}*/
	
}
