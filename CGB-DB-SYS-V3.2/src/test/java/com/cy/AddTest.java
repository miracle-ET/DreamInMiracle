package com.cy;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.cy.pj.sys.service.SysAddService;

@SpringBootTest
public class AddTest {
	@Autowired
	SysAddService sysAddService;
	/*@Test
	public void test1() {
		Add add=new Add();
		add.setAdd1("X省");
		add.setAdd2("X市");
		add.setAdd3("x区");
		add.setAdd4("xxxxx");
		add.setMobile("111111111");
		add.setName("李四");
		add.setType(0);
		add.setUserid(1);
		sysAddService.insert(add);
	}*/
	/*@Test
	public void test2() {
		Add add=new Add();
		add.setAdd1("X1省");
		add.setAdd2("X1132市");
		add.setAdd3("x1区");
		add.setAdd4("xxxx213123x");
		add.setMobile("111111111");
		add.setName("1李四");
		add.setType(0);
		add.setUserid(21);

		sysAddService.insert(add);
	}*/
	/*@Test
	public void test3() {

		List<Add> findAll = sysAddService.findAll(null);
		for (Add add : findAll) {
			System.out.println(add);
		}
	}*/
	/*@Test
	public void test4() {
		sysAddService.delete(2);
	}*/
	
}
