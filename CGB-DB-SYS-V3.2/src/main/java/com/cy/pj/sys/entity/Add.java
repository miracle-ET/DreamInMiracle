package com.cy.pj.sys.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class Add implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userid;
	private String name;
	private String add1;
	private String add2;
	private String add4;
	private String mobile;
	private Integer type;

}
