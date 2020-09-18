package sample.spring.redis;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
public class RedisTemplate {
	@Autowired
	private org.springframework.data.redis.core.RedisTemplate<String, String> redisTemplate;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOperations;

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOperations;

	@Resource(name = "redisTemplate")
	private SetOperations<String, String> setOperations;

	@Resource(name = "redisTemplate")
	private ZSetOperations<String, String> zSetOperations;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOperations;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void string() {
		String key = "foo";

		redisTemplate.opsForValue()
		//valueOperations
		//stringRedisTemplate.opsForValue()
			.set(key, "bar")
		;

		System.out.println(
			"GET: " +
				redisTemplate.opsForValue()
				//valueOperations
				//stringRedisTemplate.opsForValue()
					.get(key)
		);

		redisTemplate
		//stringRedisTemplate
			.delete(key)
		;

		System.out.println("EXISTS: " +
			redisTemplate
			//stringRedisTemplate
				.hasKey(key)
		);
	}

	@Test
	public void increment() {
		String key = "counter";

		System.out.println(
			"INCRBY: " +
				redisTemplate.opsForValue()
				//valueOperations
				//stringRedisTemplate.opsForValue()
					.increment(key, 100)
		);

		System.out.println(
			"DECRBY: " +
				redisTemplate.opsForValue()
				//valueOperations
				//stringRedisTemplate.opsForValue()
					.increment(key, -100))
		;
	}

	@Test
	public void list() {
		String key = "list";

		redisTemplate.opsForList()
		//listOperations
		//stringRedisTemplate.opsForList()
			.leftPush(key, "foo")
		;

		redisTemplate.opsForList()
		//listOperations
		//stringRedisTemplate.opsForList()
			.rightPush(key, "bar")
		;

		System.out.println("LLEN: " +
			redisTemplate.opsForList()
			//listOperations
			//stringRedisTemplate.opsForList()
				.size(key)
		);

		System.out.println(
			"LRANGE: " +
				redisTemplate.opsForList()
				//listOperations
				//stringRedisTemplate.opsForList()
					.range(key, 0, -1)
		);

		System.out.println("LINDEX: " +
			redisTemplate.opsForList()
			//listOperations
			//stringRedisTemplate.opsForList()
				.index(key, 0)
		);

		System.out.println("LPOP: " +
			redisTemplate.opsForList()
			//listOperations
			//stringRedisTemplate.opsForList()
				.leftPop(key)
		);

		System.out.println("RPOP: " +
			redisTemplate.opsForList()
			//listOperations
			//stringRedisTemplate.opsForList()
				.rightPop(key)
		);
	}

	@Test
	public void set() {
		String key = "set";

		redisTemplate.opsForSet()
		//setOperations
		//stringRedisTemplate.opsForSet()
			.add(key, "foo", "bar")
		;

		System.out.println("SCARD: " +
			redisTemplate.opsForSet()
			//setOperations
			//stringRedisTemplate.opsForSet()
				.size(key)
		);

		System.out.println("SMEMBERS: " +
			redisTemplate.opsForSet()
			//setOperations
			//stringRedisTemplate.opsForSet()
				.members(key)
		);

		System.out.println("SRANDMEMBER: " +
			redisTemplate.opsForSet()
			//setOperations
			//stringRedisTemplate.opsForSet()
				.randomMembers(key, 1)
		);

		System.out.println("SPOP: " +
			redisTemplate.opsForSet()
			//setOperations
			//stringRedisTemplate.opsForSet()
				.pop(key/*, 1*/)
		);

		redisTemplate.opsForSet()
		//setOperations
		//stringRedisTemplate.opsForSet()
			.remove(key, "foo")
		;

		System.out.println("SISMEMBER: " +
			redisTemplate.opsForSet()
			//setOperations
			//stringRedisTemplate.opsForSet()
				.isMember(key, "foo")
		);
	}

	@Test
	public void sortedSet() {
		String key = "sortedSet";

		for (int i = 1; i <= 100; i++) {
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.add(key, "id:" + i, i)
			;
		}

		System.out.println("ZCARD: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.size(key)
		);

		System.out.println("ZRANGE: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.
					range
					//rangeWithScores
						(key, 0, -1)
		);

		System.out.println("ZREVRANGE: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.
					reverseRange
					//reverseRangeWithScores
						(key, 0, -1)
		);

		System.out.println("ZCOUNT: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.count(key, 1, 100)
		);

		System.out.println("ZRANGEBYSCORE: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.
					rangeByScore
					//rangeByScoreWithScores
						(key, 1, 100, 0, 20)
		);

		System.out.println("ZREVRANGEBYSCORE: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.
					reverseRangeByScore
					//reverseRangeByScoreWithScores
						(key, 100, 1, 0, 20)
		);

		redisTemplate.opsForZSet()
		//zSetOperations
		//stringRedisTemplate.opsForZSet()
			.incrementScore(key, "id:100", 1)
		;

		System.out.println("ZSCORE: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.score(key, "id:100")
		);

		System.out.println("ZRANK: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.rank(key, "id:100")
		);

		System.out.println("ZREVRANK: " +
			redisTemplate.opsForZSet()
			//zSetOperations
			//stringRedisTemplate.opsForZSet()
				.reverseRank(key, "id:100")
		);

		redisTemplate.opsForZSet()
		//zSetOperations
		//stringRedisTemplate.opsForZSet()
			.remove(key, "id:100")
		;
	}

	@Test
	public void hash() {
		String key = "hash";

		redisTemplate.opsForHash()
		//hashOperations
		//stringRedisTemplate.opsForHash()
			.put(key, "foo", "bar")
		;

		System.out.println("HLEN: " +
			redisTemplate.opsForHash()
			//hashOperations
			//stringRedisTemplate.opsForHash()
				.size(key)
		);

		System.out.println("HGETALL: " +
			redisTemplate.opsForHash()
			//hashOperations
			//stringRedisTemplate.opsForHash()
				.entries(key)
		);

		System.out.println("HGET: " +
			redisTemplate.opsForHash()
			//hashOperations
			//stringRedisTemplate.opsForHash()
				.get(key, "foo")
		);

		System.out.println("HKEYS: " +
			redisTemplate.opsForHash()
			//hashOperations
			//stringRedisTemplate.opsForHash()
				.keys(key)
		);

		System.out.println("HVALS: " +
			redisTemplate.opsForHash()
			//hashOperations
			//stringRedisTemplate.opsForHash()
				.values(key)
		);

		redisTemplate.opsForHash()
		//hashOperations
		//stringRedisTemplate.opsForHash()
			.delete(key, "foo")
		;

		System.out.println("HEXISTS: " +
			redisTemplate.opsForHash()
			//hashOperations
			//stringRedisTemplate.opsForHash()
				.hasKey(key, "foo")
		);
	}

	@Test
	public void tx() {
		String key = "tx";

		redisTemplate.opsForValue().set(key, String.valueOf(0));

		List<Object> result = redisTemplate.execute(new SessionCallback<List<Object>>() {
			@Override
			public List<Object> execute(RedisOperations operations) throws DataAccessException {
				operations.watch(key);

				long value = Long.parseLong(String.valueOf(operations.opsForValue().get(key)));
				value++;

				//redisTemplate.opsForValue().set(key, String.valueOf(100)); // new Connection

				operations.multi();

				operations.opsForValue().set(key, String.valueOf(value));

				//System.out.println(0 / 0);

				return operations.exec();
			}
		});

		System.out.println("result: " + result);

		if (result == null) {
			System.out.println("FAILURE");
		}

		System.out.println("value: " + redisTemplate.opsForValue().get(key));
	}

/*
	@Test
	public void lock() {
		String id = UUID.randomUUID().toString();

		while (true) {
			boolean result = redisTemplate.opsForValue().setIfAbsent("lock", id, 30, TimeUnit.SECONDS); // for 2.0+

			if (result) {
				break;
			}
		}

		System.out.println("id: " + id);
	}

	@Test
	public void unlock() {
		String target = "63c9ac4b-8465-43a0-a258-19043504b9d9";
		String id = redisTemplate.opsForValue().get("lock");

		if (id != null && id.equals(target)) {
			redisTemplate.delete("lock");
		}
	}
*/

	@Deprecated
	@Test
	public void publisher() {
		redisTemplate.convertAndSend("channel", "message-" + System.currentTimeMillis());
	}
}
