package com.cy.pj.sys.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class TimeHandlerInterceptor implements HandlerInterceptor{
		@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			System.out.println("111");
		return true;
	}
}
