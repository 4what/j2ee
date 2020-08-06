package sample.spring.dao.jdbc;

import sample.spring.domain.Pojo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
@Repository
public class NamedParameterJdbcTemplate {
	@Autowired
	private org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Test
	public void count() {
		long count = namedParameterJdbcTemplate.queryForObject("SELECT COUNT(*) FROM pojo", new HashMap<String, Object>() {}, Long.class);
		System.out.println("count: " + count);
	}

	@Test
	public void create() {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		Pojo pojo = new Pojo();

		pojo.setDate(new Date());

		namedParameterJdbcTemplate.update("INSERT INTO pojo (date) VALUES (:date)", new BeanPropertySqlParameterSource(pojo), keyHolder);

		int id = keyHolder.getKey().intValue();
		System.out.println("id: " + id);
	}

	@Test
	public void delete() {
		Pojo pojo = new Pojo(); // get()

		pojo.setId(1);

		namedParameterJdbcTemplate.update("DELETE FROM pojo WHERE id = :id", new BeanPropertySqlParameterSource(pojo));
	}

	@Test
	public void get() {
		Pojo pojo = namedParameterJdbcTemplate.queryForObject("SELECT * FROM pojo WHERE id = :id", new HashMap<String, Object>() { { put("id", 1); } }, BeanPropertyRowMapper.newInstance(Pojo.class));
		System.out.println("pojo: " + pojo);
	}

	@Test
	public void list() {
		List<Pojo> list = namedParameterJdbcTemplate.query("SELECT * FROM pojo", new HashMap<String, Object>() {}, BeanPropertyRowMapper.newInstance(Pojo.class));

		for (Pojo pojo : list) {
			System.out.println("pojo: " + pojo);
		}
	}

	@Test
	public void update() {
		Pojo pojo = new Pojo(); // get()

		pojo.setId(1);
		pojo.setDate(new Date());

		namedParameterJdbcTemplate.update("UPDATE pojo SET date = :date WHERE id = :id", new BeanPropertySqlParameterSource(pojo));
	}
}
