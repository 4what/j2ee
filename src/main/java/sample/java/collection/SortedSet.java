package sample.java.collection;

import java.util.Comparator;
import java.util.TreeSet;

public class SortedSet {


	public static void main(String[] args) {
		java.util.SortedSet<Object> set = new TreeSet<>(new Comparator<Object>() {
			@Override
			public int compare(Object o1, Object o2) {
				return 0;
			}

			@Override
			public boolean equals(Object obj) {
				return false;
			}
		});
	}
}
