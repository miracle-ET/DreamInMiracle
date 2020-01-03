package com.cy.pj.sys.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SysOrderVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id;
	String url;
	String name;
	String price;
	Date time;
	String type;
	
}
