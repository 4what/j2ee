package com.example.json;

public abstract class Json {
	/**
	 * @param o
	 * @param <T>
	 * @return
	 */
	public abstract <T> String toJson(T o);

	/**
	 * @param type
	 * @param json
	 * @return
	 */
	public abstract <T> T toObject(Class<T> type, String json);
}
