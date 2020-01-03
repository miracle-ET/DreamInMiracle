package com.cy.pj.sys.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cy.pj.sys.common.TimeHandlerInterceptor;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new TimeHandlerInterceptor()).addPathPatterns("/doIndexUI");
	}
}
