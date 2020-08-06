package com.example.cache.redis;

import com.example.cache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringDataRedis extends Cache {
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public void add(String k, String v) {
		redisTemplate.opsForValue().set(k, v);
	}

	@Override
	public void delete(String k) {
		redisTemplate.delete(k);
	}

	@Override
	public Object get(String k) {
		return redisTemplate.opsForValue().get(k);
	}
}
