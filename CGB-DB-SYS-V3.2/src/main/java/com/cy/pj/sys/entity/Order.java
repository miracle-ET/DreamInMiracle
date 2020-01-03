package com.cy.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id;
	Integer commodityid;
	Integer userid;
	Date time;
	Integer type;
	Integer addid;
	Integer count;
	

}
