package sample.spring.hibernate;

import sample.hibernate.domain.Pojo;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Deprecated
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
public class HibernateTemplate {
	@Autowired
	private org.springframework.orm.hibernate5.HibernateTemplate hibernateTemplate;

	@Test
	public void count() {
		long count = hibernateTemplate.execute(new HibernateCallback<BigInteger>() {
			@Override
			public BigInteger doInHibernate(Session session) throws HibernateException {
				return (BigInteger) session.createSQLQuery("SELECT COUNT(*) FROM Pojo").uniqueResult();
			}
		}).longValue();
		System.out.println("count: " + count);
	}

	@Test
	@Transactional
	@Commit
	public void create() {
		Pojo pojo = new Pojo();

		pojo.setDate(new Date());

		int id = (Integer) hibernateTemplate.save(pojo);
		System.out.println("id: " + id);
	}

	@Test
	@Transactional
	@Commit
	public void delete() {
		Pojo pojo = hibernateTemplate.get(Pojo.class, 1);

		hibernateTemplate.delete(pojo);
	}

	@Test
	public void get() {
		Pojo pojo = hibernateTemplate.get(Pojo.class, 1);
		System.out.println("pojo: " + pojo);
	}

	@Test
	public void list() {
		List<Pojo> list = (List<Pojo>) hibernateTemplate
/*
			.execute(new HibernateCallback<List<Pojo>>() {
				@Override
				public List<Pojo> doInHibernate(Session session) throws HibernateException {
					return session.createQuery("FROM Pojo").list();
				}
			})
*/
			.find("FROM Pojo")
		;

		for (Pojo pojo : list) {
			System.out.println("pojo: " + pojo);
		}
	}

	@Test
	@Transactional
	@Commit
	public void update() {
		Pojo pojo = hibernateTemplate.get(Pojo.class, 1);

		pojo.setDate(new Date());

		hibernateTemplate.update(pojo);
	}
}
