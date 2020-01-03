package com.jt.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jt.aoon.CacheFind;

import com.jt.util.ObjectMapperUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

//定义缓存切面
@Component //万能注解 交给容器管理
@Aspect	   //自定义切面
@Configuration
public class CacheAOP {
	
	@Autowired(required = false)
	//单台redis
	//private Jedis jedis;
	//多台redis
	//private ShardedJedis shardedJedis;
	//redis集群
	private JedisCluster jedis;
	@Value("${redis.redisOn}")
	private boolean on;

	/**
	 * 	环绕通知的语法
	 * 	返回值类型:  任意类型用Obj包裹 
	 * 	参数说明:      必须包含并且位置是第一个  
	 * 			   ProceedingJoinPoint
	 * 	通知标识:	 
	 * 		1.@Around("切入点表达式")
	 * 		2.@Around(切入点())
	 */
	@SuppressWarnings("unchecked")
	@Around("@annotation(cacheFind)")
	public Object around(ProceedingJoinPoint joinPoint,CacheFind cacheFind) {

		//定义数据的返回值
		Object result = null;
		if(!on) {
			try {
				result = joinPoint.proceed();
				return result;
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		
		String key = getKey(joinPoint,cacheFind);
		String value=null;
		try {
			value = jedis.get(key);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		if(StringUtils.isEmpty(value)) {
			try {
				//缓存数据为null.查询数据库
				result = joinPoint.proceed();
				String json = ObjectMapperUtil.toJSON(result);
				
				if(cacheFind.seconds() >0) {
					//需要添加超时时间
					jedis.setex(key,cacheFind.seconds(), json);
				}else {
					jedis.set(key, json);
				}
			} catch (Throwable e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}else {
			//缓存数据不为null 需要将json转化为对象
			Class returnType = getReturnType(joinPoint);
			result = ObjectMapperUtil.toObject(value, returnType);
		}
		
		return result;
	}

	/**
	 * 如何获取方法的返回值类型????
	 * 利用反射机制,动态获取当前方法对象Method对象
	 * @param joinPoint
	 * @return
	 */
	private Class getReturnType(ProceedingJoinPoint joinPoint) {
		
		//实现了方法对象的封装
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		return signature.getReturnType();
	}

	/**
	 *   获取key数据
	 *   !null 以用户的key为主
	 *   null  自动生成key 包名.类名.方法名::第一个参数
	 * @param joinPoint
	 * @param cacheFind
	 * @return
	 */
	private String getKey(ProceedingJoinPoint joinPoint, CacheFind cacheFind) {
		
		String key = cacheFind.key();
		if(StringUtils.isEmpty(key)) {
			
			String className = 
				joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			Object firstArgs = joinPoint.getArgs()[0];
			return className+"."+methodName+"::"+firstArgs;
			
		}else {
			//以用户的数据为主
			return key;
		}
	}
}
