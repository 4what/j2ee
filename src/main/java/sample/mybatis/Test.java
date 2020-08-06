package sample.mybatis;

import sample.mybatis.domain.Pojo;
import sample.mybatis.mapper.PojoMapper;

import org.junit.BeforeClass;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Test {
	private static SqlSessionFactory sqlSessionFactory;

	@BeforeClass
	public static void init() throws IOException {
		String resource = "mybatis-config.xml";

		sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
	}

	@org.junit.Test
	public void count() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			long count = session
				//.selectOne("sample.mybatis.mapper.PojoMapper.count")
				.getMapper(PojoMapper.class).count()
			;
			System.out.println("count: " + count);
		} finally {
			session.close();
		}
	}

	@org.junit.Test
	public void create() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Pojo pojo = new Pojo();

			pojo.setDate(new Date());

			session
				//.insert("sample.mybatis.mapper.PojoMapper.create", pojo);
				.getMapper(PojoMapper.class).create(pojo)
			;

			session.commit();
		} finally {
			session.close();
		}
	}

	@org.junit.Test
	public void delete() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Pojo pojo = session.getMapper(PojoMapper.class).get(1);

			session
				//.delete("sample.mybatis.mapper.PojoMapper.delete", pojo);
				.getMapper(PojoMapper.class).delete(pojo)
			;

			session.commit();
		} finally {
			session.close();
		}
	}

	@org.junit.Test
	public void get() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Pojo pojo = session
				//.selectOne("sample.mybatis.mapper.PojoMapper.get", 1)
				.getMapper(PojoMapper.class).get(1)
			;
			System.out.println("pojo: " + pojo);
		} finally {
			session.close();
		}
	}

	@org.junit.Test
	public void list() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<Pojo> list = session
				//.selectList("sample.mybatis.mapper.PojoMapper.list")
				.getMapper(PojoMapper.class).list()
			;

			for (Pojo pojo : list) {
				System.out.println("pojo: " + pojo);
			}
		} finally {
			session.close();
		}
	}

	@org.junit.Test
	public void update() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Pojo pojo = session.getMapper(PojoMapper.class).get(1);

			pojo.setDate(new Date());

			session
				//.update("sample.mybatis.mapper.PojoMapper.update", pojo);
				.getMapper(PojoMapper.class).update(pojo)
			;

			session.commit();
		} finally {
			session.close();
		}
	}


	/**/
	@org.junit.Test
	public void find() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			List<Pojo> list = session
/*
				.selectList("sample.mybatis.mapper.PojoMapper.find", new HashMap<String, Object>() {
					{
						put("id", 0);
					}
				})
*/
				.getMapper(PojoMapper.class).find(0)
			;

			for (Pojo pojo : list) {
				System.out.println("pojo: " + pojo);
			}
		} finally {
			session.close();
		}
	}
}
