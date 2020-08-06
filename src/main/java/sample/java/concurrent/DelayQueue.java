package sample.java.concurrent;

import java.util.Date;

public class DelayQueue {


	public static void main(String[] args) throws InterruptedException {
		java.util.concurrent.DelayQueue<Delayed> delayQueue = new java.util.concurrent.DelayQueue<>();

		delayQueue.put(new Delayed("message-0", 1000 * 10));
		delayQueue.put(new Delayed("message-1", 1000 * 5));

		while (true) {
			Delayed delayed = delayQueue.take();
			System.out.println(delayed.getMessage());
			System.out.println(new Date(delayed.getExpire()));
		}
	}
}
