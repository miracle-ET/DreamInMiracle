package com.cy.pj.sys.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringShiroConfig {
	@Bean
	public SecurityManager securityManager(Realm realm) {
		 DefaultWebSecurityManager sManager=new DefaultWebSecurityManager();
		 sManager.setRealm(realm);
		 return sManager;
	}
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean=
				 new ShiroFilterFactoryBean();
		
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/doLoginUI");
		
		
		LinkedHashMap<String,String> map=
				 new LinkedHashMap<>();
		
		map.put("/bower_components/**","anon");
		 map.put("/build/**","anon");
		 map.put("/dist/**","anon");
		 map.put("/plugins/**","anon");
		 map.put("/user/doLogin","anon");
		 map.put("/**","authc");
		
		
		 sfBean.setFilterChainDefinitionMap(map);
		
		
		
		return sfBean;
		
	}
}
