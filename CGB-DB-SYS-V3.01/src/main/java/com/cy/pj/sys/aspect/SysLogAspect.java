package com.cy.pj.sys.aspect;



import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cy.annotation.RequiredLog;
import com.cy.pj.sys.common.IPUtils;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Aspect
@Component


public class SysLogAspect {
	@Pointcut("@annotation(com.cy.annotation.RequiredLog)")
	public void logPointCut() {}
	@Autowired
	SysLogService sysLogService;
	
	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		long t1=System.currentTimeMillis();
		Object result=jp.proceed();
		long t2=System.currentTimeMillis();
		savaLog(jp, t1,t2);
		return result;
	 }

	private void savaLog(ProceedingJoinPoint jp,long t1,long t2) throws JsonProcessingException, NoSuchMethodException, SecurityException {
		
		
		SysUser user= (SysUser)SecurityUtils.getSubject().getPrincipal();
		
		Integer id = null;
		
		String username=user.getUsername();
		

	
		MethodSignature methodSignature=(MethodSignature)jp.getSignature();
		Class<?> clazz=jp.getTarget().getClass();
		Method declaredMethod = clazz.getDeclaredMethod(methodSignature.getName(),  methodSignature.getParameterTypes());

		
		
		//获取操作
		String operation = null;
		RequiredLog annotation = declaredMethod.getAnnotation(RequiredLog.class);
		if(annotation!=null) {
			operation=annotation.value();
		}
		
		//获取类名+方法名
		String method=clazz.getName()+"."+declaredMethod.getName();
		
		//获取参数名
		Object[] args = jp.getArgs();
		Class[] parameterTypes = methodSignature.getParameterTypes();
		Map< String, Object> map=new HashMap<>();
		for(int i=0;i<args.length;i++) {
			map.put(parameterTypes[i].getName(), args[i]);
		}
		//
		String params=new ObjectMapper().writeValueAsString(map);
		
		//执行时长
		Long time=t2-t1;
		
		//获取ip
		String ip=IPUtils.getIpAddr();
		
		Date createdTime =new Date(System.currentTimeMillis());
		
		sysLogService.saveObject(new SysLog(id, username, operation, method, params, time, ip, createdTime));
		
	}	
}
