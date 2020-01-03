package com.cy.pj.sys.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix="db.page")
public class Config {
	private Integer pageSize=3;
}
