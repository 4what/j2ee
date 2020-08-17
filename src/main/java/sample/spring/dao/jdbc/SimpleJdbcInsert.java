package sample.spring.dao.jdbc;

import sample.spring.domain.Pojo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
public class SimpleJdbcInsert {
	private org.springframework.jdbc.core.simple.SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void init(DataSource dataSource) {
		this.simpleJdbcInsert = new org.springframework.jdbc.core.simple.SimpleJdbcInsert(dataSource)
			.withTableName("pojo")
			.usingGeneratedKeyColumns("id");
	}

	@Test
	public void create() {
		Pojo pojo = new Pojo();

		pojo.setDate(new Date());

		int id = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(pojo)).intValue();
		System.out.println("id: " + id);
	}
}
