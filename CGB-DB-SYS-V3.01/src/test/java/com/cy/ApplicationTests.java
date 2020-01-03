package com.cy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.service.SysLogService;

@SpringBootTest
class ApplicationTests {
	@Autowired
	SysLogDao sysLogDao;
	@Autowired
	SysLogService sysLogService;
	@Test
	void contextLoads() {
	}
	@Test
	public void test1() {
		//System.out.println(sysLogDao.getRowCount("admin"));
	}
	@Test
	public void test2() {
		System.out.println(sysLogService.findPageObjects("admin", 1).getPageCount());
	}

}
