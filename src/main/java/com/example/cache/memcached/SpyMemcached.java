package com.example.cache.memcached;

import com.example.cache.Cache;

import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpyMemcached extends Cache {
	@Autowired
	private MemcachedClient mc;

	@Override
	public void add(String k, String v) {
		mc.add(k, 0, v);
	}

	@Override
	public void delete(String k) {
		mc.delete(k);
	}

	@Override
	public Object get(String k) {
		return mc.get(k);
	}
}
