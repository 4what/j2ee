package sample.redis;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

/**
 * TODO: cluster
 */
public class Lettuce {


	public static void main(String[] args) {
		RedisClient client = RedisClient.create("redis://localhost:6379");

		StatefulRedisConnection<String, String> connection = client.connect();
		try {
			RedisCommands<String, String> sync = connection.sync();

			//
			String key = "key";

			sync.set(key, "value");
			System.out.println("GET: " + sync.get(key));

			sync.del(key);
			System.out.println("EXISTS: " + sync.exists(key));

			//
			String counter = "counter";

			System.out.println("INCR: " + sync.incr(counter));
			System.out.println("INCRBY: " + sync.incrby(counter, 100));

			System.out.println("DECR: " + sync.decr(counter));
			System.out.println("DECRBY: " + sync.decrby(counter, 100));

			//
			String list = "list";

			sync.lpush(list, "0");
			sync.rpush(list, "1");

			System.out.println("LRANGE: " + sync.lrange(list, 0, -1));
		} finally {
			connection.close();
		}

		client.shutdown();
	}
}
