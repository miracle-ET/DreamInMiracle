package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Commodity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id;
	Double price1;
	Double price2;
	Date time;
	Integer evaluatecount;
	Integer evaluategrade;
	Integer shopid;
	Integer count;
	String key1;
	String key2;
	String note;
	String url;
	String name;
	
}
