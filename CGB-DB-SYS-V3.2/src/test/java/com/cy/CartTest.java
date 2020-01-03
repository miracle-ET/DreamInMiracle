package com.cy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.entity.Cart;
import com.cy.pj.sys.service.SysCartService;

@SpringBootTest
public class CartTest {
	@Autowired
	SysCartService sysCartService;
	/*@Test
	public void test1() {
		Cart cart=new Cart(); 
		cart.setUserid(2);
		cart.setCommodityid(4);
		System.out.println(sysCartService.insert(cart));
	}*/
	/*@Test
	public void test2() {
		System.out.println(sysCartService.findAll(1));
	}*/
	@Test
	public void test3() {
		System.out.println(sysCartService.delete(2));
	}
}
