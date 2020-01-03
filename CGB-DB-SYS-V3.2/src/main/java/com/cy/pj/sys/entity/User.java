package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;//md5
	private String salt;
	private String email;
	private String mobile;

	


	private Date createdTime;
	private Date modifiedTime;

	private String ip;
	
	private Integer valid=1;
	
	private Integer deptid;
	
	
}
