package com.example.cache;

public abstract class Cache {
	/**
	 * @param k
	 * @param v
	 */
	public abstract void add(String k, String v);

	/**
	 * @param k
	 */
	public abstract void delete(String k);

	/**
	 * @param k
	 * @return
	 */
	public abstract Object get(String k);
}
