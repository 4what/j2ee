package com.example.dao;

import java.util.List;
import java.util.Map;

public abstract class AbstractRepository {
	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public abstract <T> long count(Class<T> type, String sql, Map<String, ?> args);

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public abstract <T> long count(Class<T> type, String sql, Object... args);

	/**
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public abstract <T> int create(T entity);

	/**
	 * @param <T>
	 * @param entity
	 */
	public abstract <T> void delete(T entity);

	/**
	 * @param <T>
	 * @param type
	 * @param id
	 * @return
	 */
	public abstract <T> T get(Class<T> type, int id);

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public abstract <T> List<Integer> idList(Class<T> type, String sql, Map<String, ?> args);

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public abstract <T> List<Integer> idList(Class<T> type, String sql, Object... args);

	/**
	 * @param type
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 * @param args
	 * @return
	 */
	public abstract <T> List<Integer> idPagedList(Class<T> type, String sql, int pageNum, int pageSize, Map<String, ?> args);

	/**
	 * @param type
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 * @param args
	 * @return
	 */
	public abstract <T> List<Integer> idPagedList(Class<T> type, String sql, int pageNum, int pageSize, Object... args);

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public abstract <T> List<T> list(Class<T> type, String sql, Map<String, ?> args);

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param args
	 * @return
	 */
	public abstract <T> List<T> list(Class<T> type, String sql, Object... args);

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 * @param args
	 * @return
	 */
	public abstract <T> List<T> pagedList(Class<T> type, String sql, int pageNum, int pageSize, Map<String, ?> args);

	/**
	 * @param <T>
	 * @param type
	 * @param sql
	 * @param pageNum
	 * @param pageSize
	 * @param args
	 * @return
	 */
	public abstract <T> List<T> pagedList(Class<T> type, String sql, int pageNum, int pageSize, Object... args);

	/**
	 * @param <T>
	 * @param entity
	 */
	public abstract <T> void update(T entity);
}
