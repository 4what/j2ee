package sample.redis;

import org.junit.Before;
import org.junit.Test;

import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;
import org.redisson.transaction.TransactionException;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Redisson {
	RedissonClient redisson;

	RKeys keys;

	@Before
	public void init() {
		Config config = new Config();

		config
			.useSingleServer().setAddress("redis://localhost:6379")
			//.useClusterServers().addNodeAddress("redis://localhost:6379")
		;

		redisson = org.redisson.Redisson.create(config);

		keys = redisson.getKeys();
	}

	@Test
	public void string() {
		String key = "foo";

		RBucket<String> bucket = redisson.getBucket(key);

		bucket.set("bar");

		System.out.println("GET: " + bucket.get());

		keys.delete(key);

		System.out.println("EXISTS: " + bucket.isExists());
	}

	@Test
	public void increment() {
		String key = "counter";

		RAtomicLong atomicLong = redisson.getAtomicLong(key);

		System.out.println("INCR: " + atomicLong.incrementAndGet());
		System.out.println("INCRBY: " + atomicLong.addAndGet(100));

		System.out.println("DECR: " + atomicLong.decrementAndGet());
		System.out.println("DECRBY: " + atomicLong.addAndGet(-100));
	}

	@Test
	public void list() {
		String key = "list";

		RList<String> list = redisson.getList(key);

		list.add("foo");
		list.add("bar");

		System.out.println("LLEN: " + list.size());

		//System.out.println("LRANGE: " + list.range(0, -1)); // for 3.11+

		System.out.println("LINDEX: " + list.get(0));
	}

	@Test
	public void deque() {
		String key = "deque";

		RDeque<String> deque = redisson.getDeque(key);

		deque.addFirst("foo");
		deque.addLast("bar");

		System.out.println("LLEN: " + deque.size());

		System.out.println(deque.readAll());

		System.out.println("LPOP: " + deque.pollFirst());
		System.out.println("RPOP: " + deque.pollLast());
	}

	@Test
	public void set() {
		String key = "set";

		RSet<String> set = redisson.getSet(key);

		set.add("foo");
		set.add("bar");

		System.out.println("SCARD: " + set.size());

		System.out.println("SMEMBERS: " + set.readAll());

		System.out.println("SRANDMEMBER: " + set.random(1));
		System.out.println("SPOP: " + set.removeRandom(1));

		set.remove("foo");

		System.out.println("SISMEMBER: " + set.contains("foo"));
	}

	@Test
	public void sortedSet() {
		String key = "sortedSet";

		RScoredSortedSet<String> sortedSet = redisson.getScoredSortedSet(key);

		for (int i = 1; i <= 100; i++) {
			sortedSet.add(i, "id:" + i);
		}

		System.out.println("ZCARD: " + sortedSet.size());

		System.out.println("ZRANGE: " + sortedSet.
			valueRange
			//entryRange
				(0, -1)
		);
		System.out.println("ZREVRANGE: " + sortedSet.
			valueRangeReversed
			//entryRangeReversed
				(0, -1)
		);

		System.out.println("ZCOUNT: " + sortedSet.count(1, true, 100, true));

		System.out.println("ZRANGEBYSCORE: " + sortedSet.
			valueRange
			//entryRange
				(1, true, 100, true, 0, 20)
		);
		System.out.println("ZREVRANGEBYSCORE: " + sortedSet.
			valueRangeReversed
			//entryRangeReversed
				(1, true, 100, true, 0, 20)
		);

		sortedSet.addScore("id:100", 1);

		System.out.println("ZSCORE: " + sortedSet.getScore("id:100"));

		System.out.println("ZRANK: " + sortedSet.rank("id:100"));
		System.out.println("ZREVRANK: " + sortedSet.revRank("id:100"));

		sortedSet.remove("id:100");
	}

	@Test
	public void hash() {
		String key = "hash";

		RMap<String, String> map = redisson.getMap(key);

		map.put("foo", "bar");

		System.out.println("HLEN: " + map.size());
		System.out.println("HGETALL: " + map.readAllEntrySet());

		System.out.println("HGET: " + map.get("foo"));

		System.out.println("HKEYS: " + map.readAllKeySet());
		System.out.println("HVALS: " + map.readAllValues());

		map.remove("foo");

		System.out.println("HEXISTS: " + map.containsKey("foo"));
	}

	/**
	 * Isolation level: READ COMMITTED
	 */
	@Test
	public void tx() {
		String key = "foo";

		RTransaction transaction = redisson.createTransaction(TransactionOptions.defaults());

		try {
			transaction.getBucket(key).set("bar");

			//System.out.println(0 / 0);

			transaction.commit();
		} catch (TransactionException e) {
			e.printStackTrace();

			transaction.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("value: " + redisson.getBucket(key).get());
	}

	@Test
	public void lock() {
		String id = UUID.randomUUID().toString();

		while (true) {
			boolean result = redisson.getBucket("lock").trySet(id, 30, TimeUnit.SECONDS);

			if (result) {
				break;
			}
		}

		System.out.println("id: " + id);
	}

	@Test
	public void unlock() {
		String target = "a528d98d-a8d5-4176-98e8-8c3e0a5a49a5";
		String id = (String) redisson.getBucket("lock").get();

		if (id != null && id.equals(target)) {
			keys.delete("lock");
		}
	}

	@Deprecated
	@Test
	public void producer() {
		RDeque<String> queue = redisson.getDeque("queue");

		for (int i = 0; i < 10; i++) {
			queue.addFirst(String.valueOf(i));
		}
		System.out.println(queue.readAll());
	}

	@Deprecated
	@Test
	public void consumer() throws InterruptedException {
		RBlockingDeque<String> queue = redisson.getBlockingDeque("queue");
		RDeque<String> processing = redisson.getDeque("processing");

		while (true) {
			String message = queue.pollLastAndOfferFirstTo("processing", 0, TimeUnit.SECONDS);
			System.out.println("message: " + message);

			System.out.println(queue.readAll());
			System.out.println(processing.readAll());

			//System.out.println(0 / 0);

			processing.remove(message);
		}
	}

	@Deprecated
	@Test
	public void publisher() {
		RTopic topic = redisson.getTopic("channel");

		topic.publish("message-" + System.currentTimeMillis());
	}

	@Deprecated
	@Test
	public void subscriber() {
		RTopic topic = redisson.getTopic("channel");

		topic.addListener(String.class, new MessageListener<String>() {
			@Override
			public void onMessage(CharSequence channel, String message) {
				System.out.println("channel: " + channel);
				System.out.println("message: " + message);
			}
		});

		while (true) {}
	}

	@Test
	public void flush() {
		keys.flushall();
	}
}
