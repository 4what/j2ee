package sample.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class Jedis {


	public static void main(String[] args) {
		//redis.clients.jedis.Jedis jedis = new redis.clients.jedis.Jedis("localhost", 6379); // (!) thread safe

		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);

		redis.clients.jedis.Jedis jedis = jedisPool.getResource();

		try {
			//
			String key = "key";

			jedis.set(key, "value");
			System.out.println("GET: " + jedis.get(key));

			jedis.del(key);
			System.out.println("EXISTS: " + jedis.exists(key));

			//
			String counter = "counter";

			System.out.println("INCR: " + jedis.incr(counter));
			System.out.println("INCRBY: " + jedis.incrBy(counter, 100));

			System.out.println("DECR: " + jedis.decr(counter));
			System.out.println("DECRBY: " + jedis.decrBy(counter, 100));

			//
			String list = "list";

			jedis.lpush(list, "0");
			jedis.rpush(list, "1");

			System.out.println("LRANGE: " + jedis.lrange(list, 0, -1));
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

		jedisPool.destroy();


/*
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
*/
	}
}
