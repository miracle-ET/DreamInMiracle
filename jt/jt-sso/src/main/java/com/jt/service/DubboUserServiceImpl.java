package com.jt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.util.CookieUtil;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;

@Service
public class DubboUserServiceImpl implements DubboUserService{
	@Autowired
	private JedisCluster jedis;
	@Value("${redis.redisOn}")
	private boolean on;
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> findAll() {
		return userMapper.selectList(null);
	}

	@Override
	public void saveUser(User user) {
		String md5Pass=DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		//暂时使用电话代替em
		user.setEmail(user.getPhone());
		user.setCreated(new Date()).setUpdated(user.getCreated());
		userMapper.insert(user);
	}

	@Override
	public String doLogin(User user,String ip) {
		
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		QueryWrapper<User> queryWrapper =new QueryWrapper<User>(user);
		User selectOne = userMapper.selectOne(queryWrapper);
		if(selectOne==null) {
			return null;
		}	
		//可以登入
		//添加缓存
		
		//脱敏
		selectOne.setPassword("123456");
		
		String ticket=UUID.randomUUID().toString();	
		
		jedis.hset(ticket, "JT_USER", ObjectMapperUtil.toJSON(selectOne));
		jedis.hset(ticket, "JT_USER", ObjectMapperUtil.toJSON(selectOne));
		jedis.hset(ticket, "JT_USER_IP", ip);
		jedis.expire(ticket, 7*24*3600);
		jedis.setex("JT_USER_"+user.getUsername(), 7*24*3600, ticket);
		return ticket;
	}

	@Override
	public void logout(String ticket) {
		Map<String, String> hgetAll = jedis.hgetAll(ticket);
		User user=ObjectMapperUtil.toObject(hgetAll.get("JT_USER"), User.class);
		if(user==null) {
			return;
		}
		jedis.del("JT_USER_"+user.getUsername());
		jedis.del(ticket);
	}
}
