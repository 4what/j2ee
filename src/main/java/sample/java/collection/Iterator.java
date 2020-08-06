package sample.java.collection;

import java.util.ArrayList;
import java.util.List;

public class Iterator {


	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(i, i);
		}

/*
		java.util.Iterator iterator = list.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
*/

		for (java.util.Iterator iterator = list.iterator(); iterator.hasNext(); ) {
			System.out.println(iterator.next());
		}
	}
}
