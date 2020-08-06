package com.example.util;

import com.example.domain.Admin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class OrmUtil {
	/**
	 * @param type
	 * @param sql
	 * @param <T>
	 * @return
	 */
	public static <T> String getCountSql(Class<T> type, String sql) {
		return "SELECT COUNT(*) FROM " + getTable(type) + " " + sql;
	}

	/**
	 * @param type
	 * @return
	 */
	public static <T> String getCreateSql(Class<T> type) {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO " + getTable(type) + " (");

		List<String> fields = getFields(type);

		for (String field : fields) {
			sql.append(field + ",");
		}

		sql.deleteCharAt(sql.length() - 1).append(") VALUES (");

		for (String field : fields) {
			sql.append(":" + field + ",");
		}

		sql.deleteCharAt(sql.length() - 1).append(")");

		return sql.toString();
	}

	/**
	 * @param type
	 * @return
	 */
	public static <T> List<String> getFields(Class<T> type) {
		List<String> result = new ArrayList<>();

		Field[] fields = type.getDeclaredFields();

		String pk = getPk(type);

		for (Field field : fields) {
			String name = field.getName();

			if (!name.equalsIgnoreCase(pk)) {
				result.add(name.toLowerCase());
			}
		}

		return result;
	}

	/**
	 * @param t
	 * @return
	 */
	public static <T> int getId(T t) {
		try {
			Method method = t.getClass().getMethod("get" + t.getClass().getSimpleName() + "Id");
			return (Integer) method.invoke(t);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * @param type
	 * @param sql
	 * @param <T>
	 * @return
	 */
	public static <T> String getIdListSql(Class<T> type, String sql) {
		return "SELECT " + getPk(type) + " FROM " + getTable(type) + " " + sql;
	}

	/**
	 * @param type
	 * @param id
	 * @param <T>
	 * @return
	 */
	public static <T> String getKey(Class<T> type, int id) {
		return type.getSimpleName() + ":" + id;
	}

	/**
	 * @param t
	 * @param <T>
	 * @return
	 */
	public static <T> String getKey(T t) {
		return getKey(t.getClass(), OrmUtil.getId(t));
	}

	/**
	 * @param type
	 * @param sql
	 * @param <T>
	 * @return
	 */
	public static <T> String getListSql(Class<T> type, String sql) {
		return "SELECT * FROM " + getTable(type) + " " + sql;
	}

	/**
	 * @param type
	 * @return
	 */
	public static <T> String getPk(Class<T> type) {
		return getTable(type) + "id";
	}

	/**
	 * @param type
	 * @return
	 */
	public static <T> String getTable(Class<T> type) {
		return type.getSimpleName().toLowerCase();
	}


	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.setAdminId(1);

		System.out.println(getCreateSql(Admin.class));

		System.out.println(getFields(Admin.class));

		System.out.println(getId(admin));
	}
}
