package com.cy.pj.sys.common.vo;

import java.io.Serializable;
import java.util.Date;

import com.cy.pj.sys.entity.SysDept;
import com.cy.pj.sys.entity.SysUser;

import lombok.Data;



@Data
public class SysUserDeptVo implements Serializable {

	/**
	 * 
	 */
	public SysUserDeptVo(SysUser sysUser,SysDept sysDept) {
		this.id=sysUser.getId();
		this.username=sysUser.getUsername();
		this.password=sysUser.getPassword();
		this.salt=sysUser.getSalt();
		this.email=sysUser.getEmail();
		this.mobile=sysUser.getMobile();
		this.valid=sysUser.getValid();
		this.sysDept=sysDept;
		this.createdTime=sysUser.getCreatedTime();
		this.modifiedTime=sysUser.getModifiedTime();
		this.createdUser=sysUser.getCreatedUser();
		this.modifiedUser=sysUser.getModifiedUser();
	}
	public SysUserDeptVo() {

	}

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String password;//md5
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	private SysDept sysDept;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;


}
