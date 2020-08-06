package sample.java.collection;

import java.util.Comparator;
import java.util.TreeMap;

public class SortedMap {


	public static void main(String[] args) {
		java.util.SortedMap<String, Object> map = new TreeMap<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return 0;
			}
		});
	}
}
