package com.jt.util;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;


@Configuration
public class RedisUtil {
	//单台redis
	/*@Value("${redis.redisIp}")
	private String redisIp;
	@Value("${redis.redisPort}")
	private int redisPort;
	@Value("${redis.redisOn}")
	private boolean on;
	@Bean
	@Scope
	public Jedis getJedis() {
		if(on) {
			return new Jedis(redisIp, redisPort);
		}else {
			return null;
		}
	}*/
	

	
	//多台redis
	/*@Value("${redis.redisnode}")
	private String node;
	@Scope("prototype")
	@Bean
	public ShardedJedis getShardedJedis() {
		if(!on) {
			return null;
		}
		List<JedisShardInfo> list=new ArrayList<JedisShardInfo>();
		String[] arrayNode =node.split(",");
		for (String string : arrayNode) {
			JedisShardInfo jedisShardInfo=new JedisShardInfo(string.split(":")[0],Integer.valueOf(string.split(":")[1]));
			list.add(jedisShardInfo);
		}
		return new ShardedJedis(list);
	}*/
	
	//reids集群
	
	@Value("${redis.nodes}")
	private String nodes;	//node,node,node
	
	@Scope("prototype")
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodeSet = new HashSet<>();
		String[] arrayNode = nodes.split(",");
		for (String node : arrayNode) { //host:port
			String host = node.split(":")[0];
			int port = Integer.parseInt(node.split(":")[1]);
			HostAndPort hostAndPort = new HostAndPort(host, port);
			nodeSet.add(hostAndPort);
		}
		
		return new JedisCluster(nodeSet);
	}

	

	
	
	
	
	
	
	
	
	
}
