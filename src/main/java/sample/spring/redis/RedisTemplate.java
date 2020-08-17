package sample.spring.redis;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

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

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void test() {
		//
		String key = "key";

		//redisTemplate.opsForValue()
		//valueOperations
		stringRedisTemplate.opsForValue()
			.set(key, "value")
		;

		System.out.println(
			"GET: " +
				//redisTemplate.opsForValue()
				//valueOperations
				stringRedisTemplate.opsForValue()
					.get(key)
		);

		//
		String counter = "counter";

		System.out.println(
			"INCRBY: " +
				//redisTemplate.opsForValue()
				//valueOperations
				stringRedisTemplate.opsForValue()
					.increment(counter, 1)
		);

		System.out.println(
			"DECRBY: " +
				//redisTemplate.opsForValue()
				//valueOperations
				stringRedisTemplate.opsForValue()
					.increment(counter, -1))
		;

		//
		String list = "list";

		//redisTemplate.opsForList()
		//listOperations
		stringRedisTemplate.opsForList()
			.leftPush(list, "0")
		;

		//redisTemplate.opsForList()
		//listOperations
		stringRedisTemplate.opsForList()
			.rightPush(list, "1")
		;

		System.out.println(
			"LRANGE: " +
				//redisTemplate.opsForList()
				//listOperations
				stringRedisTemplate.opsForList()
					.range(list, 0, -1)
		);
	}
}
