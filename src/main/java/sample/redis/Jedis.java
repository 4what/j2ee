package sample.redis;

import org.junit.After;
import org.junit.Test;

import redis.clients.jedis.*;

import java.util.*;

public class Jedis {
	private JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
	private redis.clients.jedis.Jedis jedis = jedisPool.getResource();

	@Test
	public void string() {
		String key = "foo";

		jedis.set(key, "bar");

		System.out.println("GET: " + jedis.get(key));

		jedis.del(key);

		System.out.println("EXISTS: " + jedis.exists(key));
	}

	@Test
	public void increment() {
		String key = "counter";

		System.out.println("INCR: " + jedis.incr(key));
		System.out.println("INCRBY: " + jedis.incrBy(key, 100));

		System.out.println("DECR: " + jedis.decr(key));
		System.out.println("DECRBY: " + jedis.decrBy(key, 100));
	}

	@Test
	public void list() {
		String key = "list";

		jedis.lpush(key, "foo");
		jedis.rpush(key, "bar");

		System.out.println("LLEN: " + jedis.llen(key));

		System.out.println("LRANGE: " + jedis.lrange(key, 0, -1));

		System.out.println("LINDEX: " + jedis.lindex(key, 0));

		System.out.println("LPOP: " + jedis.lpop(key));
		System.out.println("RPOP: " + jedis.rpop(key));
	}

	@Test
	public void set() {
		String key = "set";

		jedis.sadd(key, "foo", "bar");

		System.out.println("SCARD: " + jedis.scard(key));

		System.out.println("SMEMBERS: " + jedis.smembers(key));

		System.out.println("SRANDMEMBER: " + jedis.srandmember(key, 1));
		System.out.println("SPOP: " + jedis.spop(key, 1));

		jedis.srem(key, "foo");

		System.out.println("SISMEMBER: " + jedis.sismember(key, "foo"));
	}

	@Test
	public void sortedSet() {
		String key = "sortedSet";

		for (int i = 1; i <= 100; i++) {
			jedis.zadd(key, i, "id:" + i);
		}

		System.out.println("ZCARD: " + jedis.zcard(key));

		System.out.println("ZRANGE: " + jedis.
			zrange
			//zrangeWithScores
				(key, 0, -1)
		);
		System.out.println("ZREVRANGE: " + jedis.
			zrevrange
			//zrevrangeWithScores
				(key, 0, -1)
		);

		System.out.println("ZCOUNT: " + jedis.zcount(key, 1, 100));

		System.out.println("ZRANGEBYSCORE: " + jedis.
			zrangeByScore
			//zrangeByScoreWithScores
				(key, 1, 100, 0, 20)
		);
		System.out.println("ZREVRANGEBYSCORE: " + jedis.
			zrevrangeByScore
			//zrevrangeByScoreWithScores
				(key, 100, 1, 0, 20)
		);

		jedis.zincrby(key, 1, "id:100");

		System.out.println("ZSCORE: " + jedis.zscore(key, "id:100"));

		System.out.println("ZRANK: " + jedis.zrank(key, "id:100"));
		System.out.println("ZREVRANK: " + jedis.zrevrank(key, "id:100"));

		jedis.zrem(key, "id:100");
	}

	@Test
	public void hash() {
		String key = "hash";

		jedis.hset(key, "foo", "bar");

		System.out.println("HLEN: " + jedis.hlen(key));
		System.out.println("HGETALL: " + jedis.hgetAll(key));

		System.out.println("HGET: " + jedis.hget(key, "foo"));

		System.out.println("HKEYS: " + jedis.hkeys(key));
		System.out.println("HVALS: " + jedis.hvals(key));

		jedis.hdel(key, "foo");

		System.out.println("HEXISTS: " + jedis.hexists(key, "foo"));
	}

	@Test
	public void tx() {
		String key = "tx";

		jedis.set(key, String.valueOf(0));

		jedis.watch(key);

		long value = Long.parseLong(jedis.get(key));
		value++;

/*
		redis.clients.jedis.Jedis jedis2 = jedisPool.getResource();
		jedis2.set(key, String.valueOf(100));
		jedis2.close();
*/

		Transaction tx = jedis.multi();

		try {
			tx.set(key, String.valueOf(value));

			//System.out.println(0 / 0);

			List<Object> result = tx.exec();
			System.out.println("result: " + result);

			if (result == null) {
				System.out.println("FAILURE");
			}
		} catch (Exception e) {
			e.printStackTrace();

			tx.discard();
		}

		System.out.println("value: " + jedis.get(key));
	}

	@Test
	public void lock() {
		String id = UUID.randomUUID().toString();

		while (true) {
			String result = jedis.set("lock", id, "NX", "EX", 30);

			if (result != null && result.equals("OK")) {
				break;
			}
		}

		System.out.println("id: " + id);
	}

	@Test
	public void unlock() {
		String target = "63c9ac4b-8465-43a0-a258-19043504b9d9";
		String id = jedis.get("lock");

		if (id != null && id.equals(target)) {
			jedis.del("lock");
		}
	}

	@Deprecated
	@Test
	public void producer() {
		for (int i = 0; i < 10; i++) {
			jedis.lpush("queue", String.valueOf(i));
		}
		System.out.println(jedis.lrange("queue", 0, -1));
	}

	@Deprecated
	@Test
	public void consumer() {
		while (true) {
			String message = jedis.brpoplpush("queue", "processing", 0);
			System.out.println("message: " + message);

			System.out.println(jedis.lrange("queue", 0, -1));
			System.out.println(jedis.lrange("processing", 0, -1));

			//System.out.println(0 / 0);

			jedis.lrem("processing", 1, message);
		}
	}

	@Deprecated
	@Test
	public void publisher() {
		jedis.publish("channel", "message-" + System.currentTimeMillis());
	}

	@Deprecated
	@Test
	public void subscriber() {
		jedis.subscribe(new PubSubListener(), "channel");
	}

	class PubSubListener extends JedisPubSub {
		@Override
		public void onMessage(String channel, String message) {
			System.out.println("channel: " + channel);
			System.out.println("message: " + message);
		}

		@Override
		public void onPMessage(String pattern, String channel, String message) {
		}

		@Override
		public void onSubscribe(String channel, int subscribedChannels) {
		}

		@Override
		public void onPSubscribe(String pattern, int subscribedChannels) {
		}

		@Override
		public void onUnsubscribe(String channel, int subscribedChannels) {
		}

		@Override
		public void onPUnsubscribe(String pattern, int subscribedChannels) {
		}
	}

	@Test
	public void flush() {
		jedis.flushAll();
	}

	@After
	public void destroy() {
		if (jedis != null) {
			jedis.close();
		}

		jedisPool.close();
	}


	public static void main(String[] args) {
/*
		// (!) thread safe
		redis.clients.jedis.Jedis jedis = new redis.clients.jedis.Jedis("localhost", 6379);
*/


/*
		// shard
		List<JedisShardInfo> shards = new ArrayList<>();
		shards.add(new JedisShardInfo("localhost", 7000));
		shards.add(new JedisShardInfo("localhost", 7001));
		shards.add(new JedisShardInfo("localhost", 7002));

		ShardedJedisPool pool = new ShardedJedisPool(new JedisPoolConfig(), shards);
		ShardedJedis jedis = pool.getResource();

		jedis.set("key", "value");
		System.out.println(jedis.get("key"));

		jedis.close();
		pool.close();
*/


/*
		// sentinel
		Set<String> sentinels = new HashSet<>();
		sentinels.add("localhost:5000");
		sentinels.add("localhost:5001");
		sentinels.add("localhost:5002");

		JedisSentinelPool pool = new JedisSentinelPool("mymaster", sentinels);
		redis.clients.jedis.Jedis jedis = pool.getResource();

		jedis.set("key", "value");
		System.out.println(jedis.get("key"));

		jedis.close();
		pool.close();
*/


		// cluster
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("localhost", 7000));
		nodes.add(new HostAndPort("localhost", 7001));
		nodes.add(new HostAndPort("localhost", 7002));
		nodes.add(new HostAndPort("localhost", 7003));
		nodes.add(new HostAndPort("localhost", 7004));
		nodes.add(new HostAndPort("localhost", 7005));

		JedisCluster jedisCluster = new JedisCluster(nodes);

		jedisCluster.set("key", "value");
		System.out.println(jedisCluster.get("key"));
	}
}
