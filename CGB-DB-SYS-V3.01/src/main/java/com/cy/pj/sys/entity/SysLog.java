package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysLog implements Serializable{
	
	public SysLog(Integer id, String username, String operation, String method, String params, Long time, String ip,
			Date createdTime) {

		this.id = id;
		this.username = username;
		this.operation = operation;
		this.method = method;
		this.params = params;
		this.time = time;
		this.ip = ip;
		this.createdTime = createdTime;
	}
	public SysLog() {

	}
	private static final long serialVersionUID = 1L;
	private Integer id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createdTime;

}
