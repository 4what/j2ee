package sample.gof.flyweight;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Pool {
	private Map<String, Object> map;

	public Pool() {
		map = new ConcurrentHashMap<>();
	}

	public Object get(String key) {
		Object o;
		if (!map.containsKey(key)) {

			o = new Object();

			map.put(key, o);
		} else {
			o = map.get(key);
		}
		return o;
	}


	public static void main(String[] args) {
		Pool pool = new Pool();

		Object o = pool.get("key");
		System.out.println("o: " + o);
	}
}
