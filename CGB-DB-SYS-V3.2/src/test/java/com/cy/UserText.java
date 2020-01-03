package com.cy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cy.pj.sys.entity.User;
import com.cy.pj.sys.service.SysUserService;

@SpringBootTest
public class UserText {
	@Autowired
	SysUserService sysUserService;
	/*@Test
	public void user1() {
		User user=new User();
		user.setUsername("111345645546651");
		user.setPassword("1142111");
		user.setEmail("11194546451");
		System.out.println(sysUserService.insertUser(user));
	}*/
	@Test
	public void user2() {
		User user=new User();
		user.setUsername("1111");
		user.setPassword("114211231211");
		System.out.println(sysUserService.updateUser(user));
	}
}
