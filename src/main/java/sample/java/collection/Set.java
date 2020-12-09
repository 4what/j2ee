package sample.java.collection;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Set {


	public static void main(String[] args) {
		java.util.Set<Object> set = new
			HashSet<>()
			//LinkedHashSet<>()
		;

		for (int i = 0; i < 10; i++) {
			set.add("foobar"); // union
		}

		for (Object o : set) {
			System.out.println(o);
		}
	}
}
