package com.cy.pj.sys.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class SysCartVo implements Serializable{
	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		Integer id;
		Integer commodityid;		
		String url;
	 	String note;
	 	String name;
	 	Double price;
	 	
}
