package sample.java.collection;

import java.util.LinkedList;

public class Queue {


	public static void main(String[] args) {
		java.util.Queue<Object> queue = new LinkedList<>();

		for (int i = 0; i < 10; i++) {
			queue.add(i);
		}

		while (!queue.isEmpty()) {
			System.out.println(queue.remove());
		}
	}
}
