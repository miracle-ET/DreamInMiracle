package com.jt.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static void deleteCookie(String cookieName,String path,String domain,HttpServletResponse httpServletResponse) {
		Cookie cookie=new Cookie(cookieName,"");
		cookie.setMaxAge(0);
		cookie.setPath(path);
		cookie.setDomain(domain);
		httpServletResponse.addCookie(cookie);
	}
	public static void addCookie(String cookieName,String value,int time,String path,String domain,HttpServletResponse httpServletResponse) {
		Cookie cookie=new Cookie(cookieName,value);
		cookie.setMaxAge(time);
		cookie.setPath(path);
		cookie.setDomain(domain);
		httpServletResponse.addCookie(cookie);
	}
	public static Cookie getCookie(String name,HttpServletRequest httpServletRequest) {
		Cookie[] cookies = httpServletRequest.getCookies();
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}
}
