package com.example.dao;

import com.example.util.OrmUtil;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Repository
public class HibernateRepository extends AbstractRepository {
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @param query
	 * @param args
	 * @return
	 */
	private Query getNamedParameterQuery(Query query, Map<String, ?> args) {
		for (Map.Entry<String, ?> entry : args.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

		return query;
	}

	/**
	 * @param query
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	private Query getPagedQuery(Query query, int pageNum, int pageSize) {
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);

		return query;
	}

	/**
	 * @param query
	 * @param args
	 * @return
	 */
	private Query getParameterQuery(Query query, Object... args) {
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}

		return query;
	}

	@Override
	public <T> long count(Class<T> type, String sql, Map<String, ?> args) {
		return ((BigInteger) getNamedParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getCountSql(type, sql)), args).uniqueResult()).longValue();
	}

	@Override
	public <T> long count(Class<T> type, String sql, Object... args) {
		return ((BigInteger) getParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getCountSql(type, sql)), args).uniqueResult()).longValue();
	}

	@Override
	public <T> int create(T entity) {
		return (Integer) sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public <T> void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public <T> T get(Class<T> type, int id) {
		return sessionFactory.getCurrentSession().get(type, id);
	}

	@Override
	public <T> List<Integer> idList(Class<T> type, String sql, Map<String, ?> args) {
		return getNamedParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getIdListSql(type, sql)), args).list();
	}

	@Override
	public <T> List<Integer> idList(Class<T> type, String sql, Object... args) {
		return getParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getIdListSql(type, sql)), args).list();
	}

	@Override
	public <T> List<Integer> idPagedList(Class<T> type, String sql, int pageNum, int pageSize, Map<String, ?> args) {
		return getPagedQuery(getNamedParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getIdListSql(type, sql)), args), pageNum, pageSize).list();
	}

	@Override
	public <T> List<Integer> idPagedList(Class<T> type, String sql, int pageNum, int pageSize, Object... args) {
		return getPagedQuery(getParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getIdListSql(type, sql)), args), pageNum, pageSize).list();
	}

	@Override
	public <T> List<T> list(Class<T> type, String sql, Map<String, ?> args) {
		return getNamedParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getListSql(type, sql)).addEntity(type), args).list();
	}

	@Override
	public <T> List<T> list(Class<T> type, String sql, Object... args) {
		return getParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getListSql(type, sql)).addEntity(type), args).list();
	}

	@Override
	public <T> List<T> pagedList(Class<T> type, String sql, int pageNum, int pageSize, Map<String, ?> args) {
		return getPagedQuery(getNamedParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getListSql(type, sql)).addEntity(type), args), pageNum, pageSize).list();
	}

	@Override
	public <T> List<T> pagedList(Class<T> type, String sql, int pageNum, int pageSize, Object... args) {
		return getPagedQuery(getParameterQuery(sessionFactory.getCurrentSession().createSQLQuery(OrmUtil.getListSql(type, sql)).addEntity(type), args), pageNum, pageSize).list();
	}

	@Override
	public <T> void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
	}
}
