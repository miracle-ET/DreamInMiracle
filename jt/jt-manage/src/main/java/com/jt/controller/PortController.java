package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {
	@Value("${server.port}")
	private Integer port;
	@RequestMapping("/port")
	public Integer port() {
		return port;
	}
}
