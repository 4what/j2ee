package sample.hibernate;

import sample.hibernate.domain.Pojo;

import org.junit.BeforeClass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;
import java.util.List;

public class Test {
	private static Session session;

	@BeforeClass
	public static void init() {
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(/*"hibernate.cfg.xml"*/).build();

		Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

		session = sessionFactory.getCurrentSession();
	}

	@org.junit.Test
	public void count() {
		session.getTransaction().begin();

		long count = (Long) session.createQuery("SELECT COUNT(*) FROM Pojo").uniqueResult();
		System.out.println("count: " + count);

		session.getTransaction().commit();
	}

	@org.junit.Test
	public void create() {
		session.getTransaction().begin();

		Pojo pojo = new Pojo();

		pojo.setDate(new Date());

		int id = (Integer) session.save(pojo);
		System.out.println("id: " + id);

		session.getTransaction().commit();
	}

	@org.junit.Test
	public void delete() {
		session.getTransaction().begin();

		Pojo pojo = session.get(Pojo.class, 1);

		session.delete(pojo);

		session.getTransaction().commit();
	}

	@org.junit.Test
	public void get() {
		session.getTransaction().begin();

		Pojo pojo = session.get(Pojo.class, 1);
		System.out.println("pojo: " + pojo);

		session.getTransaction().commit();
	}

	@org.junit.Test
	public void list() {
		session.getTransaction().begin();

		List<Pojo> list = session
			.createQuery("FROM Pojo") // HQL
			//.createSQLQuery("SELECT * FROM pojo").addEntity(Pojo.class) // SQL
			.setFirstResult(0).setMaxResults(20)
			.list();

		for (Pojo pojo : list) {
			System.out.println("pojo: " + pojo);
		}

		session.getTransaction().commit();
	}

	@org.junit.Test
	public void update() {
		session.getTransaction().begin();

		Pojo pojo = session.get(Pojo.class, 61);

		pojo.setDate(new Date());

		session.update(pojo);

		session.getTransaction().commit();
	}
}
