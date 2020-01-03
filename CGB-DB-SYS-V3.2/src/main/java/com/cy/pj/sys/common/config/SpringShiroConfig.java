package com.cy.pj.sys.common.config;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

@Configuration //bean
public class SpringShiroConfig {
	@Bean
	public SecurityManager securityManager(Realm realm) {
			 DefaultWebSecurityManager sManager=
			 new DefaultWebSecurityManager();
			 sManager.setRealm(realm);
			 return sManager;
	}
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactory (
				 SecurityManager securityManager) {
			 ShiroFilterFactoryBean sfBean=
			 new ShiroFilterFactoryBean();
			 sfBean.setSecurityManager(securityManager);
			 sfBean.setLoginUrl("/doLoginUI");
			 //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
			 LinkedHashMap<String,String> map=
					 new LinkedHashMap<>();
			 map.put("/bower_components/**","anon");
				map.put("/build/**","anon");
				map.put("/dist/**","anon");
				map.put("/plugins/**","anon");
				map.put("/images/*","anon");
				map.put("/js/*","anon");
				map.put("/style/*","anon");
				map.put("/doIndexUI","anon");
				map.put("/doRegister","anon");
				map.put("/user/doSaveUser","anon");
				map.put("/user/doLogin","anon");
				map.put("/user/doJudge","anon");
				map.put("/doLogout","logout");
				map.put("/commodity/doInsert","anon");
			 sfBean.setFilterChainDefinitionMap(map);
			 return sfBean;
		 }


}
