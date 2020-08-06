package sample.spring.dao.jdbc;

import sample.spring.domain.Pojo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
@Repository
public class JdbcTemplate {
	@Autowired
	private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

	@Test
	public void count() {
		long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM pojo", Long.class);
		System.out.println("count: " + count);
	}

	@Test
	public void create() {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(
			new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement("INSERT INTO pojo (date) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

					ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));

					return ps;
				}
			},
			keyHolder
		);

		int id = keyHolder.getKey().intValue();
		System.out.println("id: " + id);
	}

	@Test
	public void delete() {
		jdbcTemplate.update("DELETE FROM pojo WHERE id = ?", 1);
	}

	@Test
	public void get() {
		Pojo pojo = jdbcTemplate.queryForObject("SELECT * FROM pojo WHERE id = ?", BeanPropertyRowMapper.newInstance(Pojo.class), 1);
		System.out.println("pojo: " + pojo);
	}

	@Test
	public void list() {
		List<Pojo> list = jdbcTemplate.query("SELECT * FROM pojo", BeanPropertyRowMapper.newInstance(Pojo.class));

		for (Pojo pojo : list) {
			System.out.println("pojo: " + pojo);
		}
	}

	@Test
	public void update() {
		jdbcTemplate.update("UPDATE pojo SET date = ? WHERE id = ?", new Timestamp(System.currentTimeMillis()), 1);
	}
}
