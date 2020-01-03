package com.jt.text;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TextRedis {
	private Jedis jadis;
	@Before
	public void init() {
		jadis=new Jedis("192.168.58.129", 6379);
	}
	@Test
	public void testString() {
		jadis.set("aaaaa", "bb");
		System.out.println(jadis.get("aaaaa"));
	}
	@Test
	public void testNXEX() {
		//成功返回ok
		//不成功返回null
		System.out.println(jadis.set("abc","1111","NX","EX",20));
	}

}
