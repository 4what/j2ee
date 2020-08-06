package com.example.cache.redis;

import com.example.cache.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
public class Jedis extends Cache {
	@Autowired
	private JedisPool jedisPool;

	private redis.clients.jedis.Jedis getJedis() {
		return jedisPool.getResource();
	}

	private void close(redis.clients.jedis.Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	@Override
	public void add(String k, String v) {
		redis.clients.jedis.Jedis jedis = getJedis();

		try {
			jedis.set(k, v);
		} finally {
			close(jedis);
		}
	}

	@Override
	public void delete(String k) {
		redis.clients.jedis.Jedis jedis = getJedis();

		try {
			jedis.del(k);
		} finally {
			close(jedis);
		}
	}

	@Override
	public Object get(String k) {
		redis.clients.jedis.Jedis jedis = getJedis();

		try {
			return jedis.get(k);
		} finally {
			close(jedis);
		}
	}
}
