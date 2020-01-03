package com.jt.interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.Util.UserThreadLocal;
import com.jt.pojo.User;
import com.jt.util.CookieUtil;
import com.jt.util.IPUtil;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster; 


@Component
public class UserInterceptor implements HandlerInterceptor{
	@Autowired
	private JedisCluster jedis;
	@Value("${redis.redisOn}")
	private boolean on;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie cookie = CookieUtil.getCookie("JT_TICKET", request);
		if(cookie==null) {
			response.sendRedirect("/user/login.html");
			return false;
		}
		String ticket=cookie.getValue();
		//根据ticket获取reids中的信息
		Map<String, String> hgetAll = jedis.hgetAll(ticket);
		//获取访问者ip
		String ip=IPUtil.getIpAddress(request);
		//如果没有对应则说明ticket不对
		if(hgetAll==null) {
			response.sendRedirect("/user/login.html");
			return false;
		}
		//如果ip不相等则说明访问用户不对
		if(!ip.equals(hgetAll.get("JT_USER_IP"))) {
			CookieUtil.deleteCookie("JT_TICKET", "/", "jt.com", response);
			response.sendRedirect("/user/login.html");
			return false;
		}
		User user=ObjectMapperUtil.toObject(hgetAll.get("JT_USER"), User.class);
		if(user==null) {
			response.sendRedirect("/user/login.html");
			return false;
		}
		//不是同一个用户
		if(!ticket.equals(jedis.get("JT_USER_"+user.getUsername()))){
			CookieUtil.deleteCookie("JT_TICKET", "/", "jt.com", response);
			response.sendRedirect("/user/login.html");
			return false;
		}
		UserThreadLocal.set(user);
		return true;

	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		//为了防止内存泄漏
		UserThreadLocal.remove();
	}


}
