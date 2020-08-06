package sample.java.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Map {


	public static void main(String[] args) {
		java.util.Map<String, Object> map = new
			HashMap<>()
			//LinkedHashMap<>()
		;

		map.put("key", "value");

		for (java.util.Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

		//map.forEach((k, v) -> System.out.println(k + ": " + v)); // for Java 8+
	}
}
