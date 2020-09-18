package sample.redis;

import org.junit.After;
import org.junit.Test;

import com.lambdaworks.redis.ReadFrom;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.SetArgs;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;
import com.lambdaworks.redis.cluster.RedisClusterClient;
import com.lambdaworks.redis.cluster.api.StatefulRedisClusterConnection;
import com.lambdaworks.redis.cluster.api.sync.RedisAdvancedClusterCommands;
import com.lambdaworks.redis.codec.StringCodec;
import com.lambdaworks.redis.masterslave.MasterSlave;
import com.lambdaworks.redis.masterslave.StatefulRedisMasterSlaveConnection;
import com.lambdaworks.redis.pubsub.RedisPubSubListener;
import com.lambdaworks.redis.pubsub.StatefulRedisPubSubConnection;
import com.lambdaworks.redis.pubsub.api.sync.RedisPubSubCommands;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Lettuce {
	private RedisClient client = RedisClient.create(
		"redis://localhost:6379"
		//RedisURI.Builder.redis("localhost", 6379).build()
	);
	private StatefulRedisConnection<String, String> connection = client.connect();

	private RedisCommands<String, String> commands = connection.sync();

	@Test
	public void string() {
		String key = "foo";

		commands.set(key, "bar");

		System.out.println("GET: " + commands.get(key));

		commands.del(key);

		System.out.println("EXISTS: " + commands.exists(key));
	}

	@Test
	public void increment() {
		String key = "counter";

		System.out.println("INCR: " + commands.incr(key));
		System.out.println("INCRBY: " + commands.incrby(key, 100));

		System.out.println("DECR: " + commands.decr(key));
		System.out.println("DECRBY: " + commands.decrby(key, 100));
	}

	@Test
	public void list() {
		String key = "list";

		commands.lpush(key, "foo");
		commands.rpush(key, "bar");

		System.out.println("LLEN: " + commands.llen(key));

		System.out.println("LRANGE: " + commands.lrange(key, 0, -1));

		System.out.println("LINDEX: " + commands.lindex(key, 0));

		System.out.println("LPOP: " + commands.lpop(key));
		System.out.println("RPOP: " + commands.rpop(key));
	}

	@Test
	public void set() {
		String key = "set";

		commands.sadd(key, "foo", "bar");

		System.out.println("SCARD: " + commands.scard(key));

		System.out.println("SMEMBERS: " + commands.smembers(key));

		System.out.println("SRANDMEMBER: " + commands.srandmember(key, 1));
		System.out.println("SPOP: " + commands.spop(key, 1));

		commands.srem(key, "foo");

		System.out.println("SISMEMBER: " + commands.sismember(key, "foo"));
	}

	@Test
	public void sortedSet() {
		String key = "sortedSet";

		for (int i = 1; i <= 100; i++) {
			commands.zadd(key, i, "id:" + i);
		}

		System.out.println("ZCARD: " + commands.zcard(key));

		System.out.println("ZRANGE: " + commands.
			zrange
			//zrangeWithScores
				(key, 0, -1)
		);
		System.out.println("ZREVRANGE: " + commands.
			zrevrange
			//zrevrangeWithScores
				(key, 0, -1)
		);

		System.out.println("ZCOUNT: " + commands.zcount(key, 1, 100));

		System.out.println("ZRANGEBYSCORE: " + commands.
			zrangebyscore
			//zrangebyscoreWithScores
				(key, 1, 100, 0, 20)
		);
		System.out.println("ZREVRANGEBYSCORE: " + commands.
			zrevrangebyscore
			//zrevrangebyscoreWithScores
				(key, 100, 1, 0, 20)
		);

		commands.zincrby(key, 1, "id:100");

		System.out.println("ZSCORE: " + commands.zscore(key, "id:100"));

		System.out.println("ZRANK: " + commands.zrank(key, "id:100"));
		System.out.println("ZREVRANK: " + commands.zrevrank(key, "id:100"));

		commands.zrem(key, "id:100");
	}

	@Test
	public void hash() {
		String key = "hash";

		commands.hset(key, "foo", "bar");

		System.out.println("HLEN: " + commands.hlen(key));
		System.out.println("HGETALL: " + commands.hgetall(key));

		System.out.println("HGET: " + commands.hget(key, "foo"));

		System.out.println("HKEYS: " + commands.hkeys(key));
		System.out.println("HVALS: " + commands.hvals(key));

		commands.hdel(key, "foo");

		System.out.println("HEXISTS: " + commands.hexists(key, "foo"));
	}

	@Test
	public void tx() {
		String key = "tx";

		commands.set(key, String.valueOf(0));

		commands.watch(key);

		long value = Long.parseLong(commands.get(key));
		value++;

/*
		RedisCommands<String, String> commands2 = connection.sync();
		commands2.set(key, String.valueOf(100));
*/

		commands.multi();

		try {
			commands.set(key, String.valueOf(value));

			//System.out.println(0 / 0);

			List<Object> result = commands.exec();
			System.out.println("result: " + result);

			if (result.isEmpty()) {
				System.out.println("FAILURE");
			}
		} catch (Exception e) {
			e.printStackTrace();

			commands.discard();
		}

		System.out.println("value: " + commands.get(key));
	}

	@Test
	public void lock() {
		String id = UUID.randomUUID().toString();

		while (true) {
			String result = commands.set("lock", id, SetArgs.Builder.nx().ex(30));

			if (result != null && result.equals("OK")) {
				break;
			}
		}

		System.out.println("id: " + id);
	}

	@Test
	public void unlock() {
		String target = "af22a837-ab5c-4385-9aed-eec90e4875ec";
		String id = commands.get("lock");

		if (id != null && id.equals(target)) {
			commands.del("lock");
		}
	}

	@Deprecated
	@Test
	public void producer() {
		for (int i = 0; i < 10; i++) {
			commands.lpush("queue", String.valueOf(i));
		}
		System.out.println(commands.lrange("queue", 0, -1));
	}

	@Deprecated
	@Test
	public void consumer() {
		while (true) {
			String message = commands.brpoplpush(0, "queue", "processing");
			System.out.println("message: " + message);

			System.out.println(commands.lrange("queue", 0, -1));
			System.out.println(commands.lrange("processing", 0, -1));

			//System.out.println(0 / 0);

			commands.lrem("processing", 1, message);
		}
	}

	@Deprecated
	@Test
	public void publisher() {
		commands.publish("channel", "message-" + System.currentTimeMillis());
	}

	@Deprecated
	@Test
	public void subscriber() {
		StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();

		connection.addListener(new RedisPubSubListener<String, String>() {
			@Override
			public void message(String channel, String message) {
				System.out.println("channel: " + channel);
				System.out.println("message: " + message);
			}

			@Override
			public void message(String pattern, String channel, String message) {
			}

			@Override
			public void subscribed(String channel, long count) {
			}

			@Override
			public void psubscribed(String pattern, long count) {
			}

			@Override
			public void unsubscribed(String channel, long count) {
			}

			@Override
			public void punsubscribed(String pattern, long count) {
			}
		});

		RedisPubSubCommands<String, String> pubSubCommands = connection.sync();

		pubSubCommands.subscribe("channel");

		try {
			while (true) {}
		} finally {
			connection.close();
		}
	}

	@Test
	public void flush() {
		commands.flushall();
	}

	@After
	public void destroy() {
		connection.close();

		client.shutdown();
	}


	public static void main(String[] args) {
/*
		// replication
		List<RedisURI> nodes = Arrays.asList(
			RedisURI.create("localhost", 7000)
			, RedisURI.create("localhost", 7001)
			, RedisURI.create("localhost", 7002)
		);

		RedisClient client = RedisClient.create();

		StatefulRedisMasterSlaveConnection<String, String> connection = MasterSlave.connect(client, StringCodec.UTF8,
			//nodes
			//RedisURI.create("localhost", 7000) // discovery

			RedisURI.create("redis-sentinel://localhost:5000#mymaster") // sentinel
		);
		connection.setReadFrom(ReadFrom.SLAVE);

		RedisCommands<String, String> commands = connection.sync();

		commands.set("key", "value");
		System.out.println(commands.get("key"));

		connection.close();
		client.shutdown();
*/


		// cluster
		List<RedisURI> nodes = Arrays.asList(
			RedisURI.create("localhost", 7000)
			, RedisURI.create("localhost", 7001)
			, RedisURI.create("localhost", 7002)
			, RedisURI.create("localhost", 7003)
			, RedisURI.create("localhost", 7004)
			, RedisURI.create("localhost", 7005)
		);

		RedisClusterClient client = RedisClusterClient.create(
			//nodes
			"redis://localhost:7000" // discovery
		);

		StatefulRedisClusterConnection<String, String> connection = client.connect();
		connection.setReadFrom(ReadFrom.SLAVE);

		RedisAdvancedClusterCommands<String, String> commands = connection.sync();

		commands.set("key", "value");
		System.out.println(commands.get("key"));

		connection.close();
		client.shutdown();
	}
}
