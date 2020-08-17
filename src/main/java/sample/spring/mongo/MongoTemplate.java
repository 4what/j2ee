package sample.spring.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
public class MongoTemplate {
	@Autowired
	private org.springframework.data.mongodb.core.MongoTemplate mongoTemplate;

	@Test
	public void count() {
		long count = mongoTemplate.count(null, Pojo.class);
		System.out.println("count: " + count);
	}

	@Test
	public void create() {
		Pojo pojo = new Pojo();

		//pojo.setId("1");
		pojo.setDate(new Date());

		mongoTemplate.insert(pojo);
		System.out.println("id: " + pojo.getId());
	}

	@Test
	public void delete() {
		Pojo pojo = mongoTemplate.findById("1", Pojo.class);

		mongoTemplate.remove(pojo);
	}


	@Test
	public void get() {
		Pojo pojo = mongoTemplate.findById("1", Pojo.class);
		System.out.println("pojo: " + pojo);
	}

	@Test
	public void list() {
		List<Pojo> list = mongoTemplate.findAll(Pojo.class);

		for (Pojo pojo : list) {
			System.out.println("pojo: " + pojo);
		}
	}

	@Test
	public void update() {
		Pojo pojo = mongoTemplate.findById("1", Pojo.class);

		pojo.setDate(new Date());

		mongoTemplate.save(pojo);
	}
}
