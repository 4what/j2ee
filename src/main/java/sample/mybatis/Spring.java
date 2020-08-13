package sample.mybatis;

import sample.mybatis.domain.Pojo;
import sample.mybatis.mapper.PojoMapper;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
@Repository
public class Spring {
	@Autowired
	private SqlSession session;

	@Autowired
	private PojoMapper pojoMapper;

	@Test
	public void create() {
		Pojo pojo = new Pojo();

		pojo.setDate(new Date());

		//session.insert("sample.mybatis.mapper.PojoMapper.create", pojo);
		pojoMapper.create(pojo);

		System.out.println("id: " + pojo.getId());
	}
}
