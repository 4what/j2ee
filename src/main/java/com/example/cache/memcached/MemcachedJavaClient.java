package com.example.cache.memcached;

import com.example.cache.Cache;

import com.whalin.MemCached.MemCachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemcachedJavaClient extends Cache {
	@Autowired
	private MemCachedClient mc;

	@Override
	public void add(String k, String v) {
		mc.add(k, v);
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
