package com.cy.pj.sys.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id;
	Integer userid;
	Integer commodityid;
}
