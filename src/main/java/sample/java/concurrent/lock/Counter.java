package sample.java.concurrent.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Counter {
	private int c = 0;

	public void increment() {
		synchronized (this) {
			c++;
		}
	}

	public synchronized void decrement() {
		c--;
	}

	public int value() {
		return c;
	}


	public static void main(String[] args) {
		Counter counter = new Counter();

		ExecutorService executorService = Executors.newCachedThreadPool();

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				counter.increment();
			}
		});

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				counter.decrement();
			}
		});

		System.out.println(counter.value());
	}
}
