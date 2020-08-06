package com.example.dao;

import com.example.cache.Cache;
import com.example.json.Json;
import com.example.util.OrmUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class GenericRepository {
	@Autowired
	@Qualifier("hibernateRepository")
	private AbstractRepository abstractRepository;

	@Autowired
	@Qualifier(
		//"memcachedJavaClient"
		//"spyMemcached"

		//"jedis"
		//"jedisCluster"
		"springDataRedis"
	)
	private Cache cache;

	@Autowired
	@Qualifier("jackson")
	private Json json;

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public <T> long count(Class<T> type, String sql, Map<String, ?> args) {
		return abstractRepository.count(type, sql, args);
	}

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public <T> long count(Class<T> type, String sql, Object... args) {
		return abstractRepository.count(type, sql, args);
	}

	/**
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public <T> int create(T entity) {
		return abstractRepository.create(entity);
	}

	/**
	 * @param <T>
	 * @param entity
	 */
	public <T> void delete(T entity) {
		abstractRepository.delete(entity);
		cache.delete(OrmUtil.getKey(entity));
	}

	/**
	 * @param <T>
	 * @param type
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> type, int id) {
		T o;
		String k = OrmUtil.getKey(type, id);
		Object v = cache.get(k);
		if (v != null) {
			o = json.toObject(type, String.valueOf(v));
		} else {
			o = abstractRepository.get(type, id);
			cache.add(k, json.toJson(o));
		}
		return o;
	}

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public <T> List<T> list(Class<T> type, String sql, Map<String, ?> args) {
		List<T> result = new ArrayList<>();
		for (int id : abstractRepository.idList(type, sql, args)) {
			result.add(get(type, id));
		}
		return result;
	}

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public <T> List<T> list(Class<T> type, String sql, Object... args) {
		List<T> result = new ArrayList<>();
		for (int id : abstractRepository.idList(type, sql, args)) {
			result.add(get(type, id));
		}
		return result;
	}

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 * @param args
	 * @return
	 */
	public <T> List<T> pagedList(Class<T> type, String sql, int pageNum, int pageSize, Map<String, ?> args) {
		List<T> result = new ArrayList<>();
		for (int id : abstractRepository.idPagedList(type, sql, pageNum, pageSize, args)) {
			result.add(get(type, id));
		}
		return result;
	}

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 * @param args
	 * @return
	 */
	public <T> List<T> pagedList(Class<T> type, String sql, int pageNum, int pageSize, Object... args) {
		List<T> result = new ArrayList<>();
		for (int id : abstractRepository.idPagedList(type, sql, pageNum, pageSize, args)) {
			result.add(get(type, id));
		}
		return result;
	}

	/**
	 * @param <T>
	 * @param entity
	 */
	public <T> void update(T entity) {
		abstractRepository.update(entity);
		cache.delete(OrmUtil.getKey(entity));
	}
}
