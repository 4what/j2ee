package sample.java.concurrent;

import java.util.concurrent.TimeUnit;

public class Delayed implements java.util.concurrent.Delayed {
	private long expire;
	private Object message;

	public Delayed(Object message, long duration) {
		this.expire = System.currentTimeMillis() + duration;
		this.message = message;
	}

	public long getExpire() {
		return expire;
	}

	public Object getMessage() {
		return message;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return this.expire - System.currentTimeMillis();
	}

	@Override
	public int compareTo(java.util.concurrent.Delayed o) {
		return (int) (this.expire - ((Delayed) o).expire);
	}
}
