package com.cy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.entity.Order;
import com.cy.pj.sys.service.SysOrderService;
import com.cy.pj.sys.vo.SysOrderVo;

@SpringBootTest
public class OrderTest {
	@Autowired
	SysOrderService sysOrderService;
	/*@Test
	public void test1() {
		List<SysOrderVo> sysOrderVo=sysOrderService.findOlder(1);
		for (SysOrderVo sysOrderVo2 : sysOrderVo) {
			System.out.println(sysOrderVo2.toString());
		}
	}*/
	@Test
	public void test2() {
		Order order=new Order();
		order.setAddid(2);
		order.setCommodityid(1);
		order.setCount(3);
		order.setUserid(1);
		sysOrderService.insert(order);
	}

}
