package com.jt.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.CookieUtil;
import com.jt.util.IPUtil;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private JedisCluster jedis;
	@Value("${redis.redisOn}")
	private boolean on;
	@Autowired
	private UserMapper userMapper;
	@Override
	public User findUserById(Long id) {
		return userMapper.selectById(id);
	}
	@Override
	public List<User> findAll() {
		return userMapper.selectList(null);
	}
	@Override
	public boolean check(String param, int type) {
		String column="username";
		if(type==2) {
			column="phone";
		}
		if(type==3) {
			column="email";
		}
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq(column, param);
		if(userMapper.selectCount(queryWrapper)>0) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public JSONPObject query(String ticket,String callback,HttpServletRequest request,HttpServletResponse response) {
		//根据ticket获取reids中的信息
		Map<String, String> hgetAll = jedis.hgetAll(ticket);
		//获取访问者ip
		String ip=IPUtil.getIpAddress(request);
		//如果没有对应则说明ticket不对
		if(hgetAll==null) {
			return new JSONPObject(callback, SysResult.fail());
		}
		//如果ip不相等则说明访问用户不对
		if(!ip.equals(hgetAll.get("JT_USER_IP"))) {
			CookieUtil.deleteCookie("JT_TICKET", "/", "jt.com", response);
			return new JSONPObject(callback, SysResult.fail());
		}
		User user=ObjectMapperUtil.toObject(hgetAll.get("JT_USER"), User.class);
		if(user==null) {
			return new JSONPObject(callback, SysResult.fail());
		}
		//不是同一个用户
		if(!ticket.equals(jedis.get("JT_USER_"+user.getUsername()))){
			CookieUtil.deleteCookie("JT_TICKET", "/", "jt.com", response);
			return new JSONPObject(callback, SysResult.fail());
		}
		return new JSONPObject(callback, SysResult.success(ObjectMapperUtil.toJSON(user)));
	}
}
