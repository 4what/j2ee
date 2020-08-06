package com.example.cache.redis;

import com.example.cache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JedisCluster extends Cache {
	@Autowired
	private redis.clients.jedis.JedisCluster jedisCluster;

	@Override
	public void add(String k, String v) {
		jedisCluster.set(k, v);
	}

	@Override
	public void delete(String k) {
		jedisCluster.del(k);
	}

	@Override
	public Object get(String k) {
		return jedisCluster.get(k);
	}
}
