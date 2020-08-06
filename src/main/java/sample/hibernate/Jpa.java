package sample.hibernate;

import sample.hibernate.domain.Pojo;

import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class Jpa {
	private static EntityManager entityManager;

	@BeforeClass
	public static void init() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.example.jpa");

		entityManager = entityManagerFactory.createEntityManager();
	}

	@org.junit.Test
	public void count() {
		entityManager.getTransaction().begin();

		long count = (Long) entityManager.createQuery("SELECT COUNT(*) FROM Pojo").getSingleResult();
		System.out.println("count: " + count);

		entityManager.getTransaction().commit();
	}

	@org.junit.Test
	public void create() {
		entityManager.getTransaction().begin();

		Pojo pojo = new Pojo();

		pojo.setDate(new Date());

		entityManager.persist(pojo);
		System.out.println("id: " + pojo.getId());

		entityManager.getTransaction().commit();
	}

	@org.junit.Test
	public void delete() {
		entityManager.getTransaction().begin();

		Pojo pojo = entityManager.find(Pojo.class, 1);

		entityManager.remove(pojo);

		entityManager.getTransaction().commit();
	}

	@org.junit.Test
	public void get() {
		entityManager.getTransaction().begin();

		Pojo pojo = entityManager.find(Pojo.class, 1);
		System.out.println("pojo: " + pojo);

		entityManager.getTransaction().commit();
	}

	@org.junit.Test
	public void list() {
		entityManager.getTransaction().begin();

		List<Pojo> list = entityManager
			.createQuery("FROM Pojo", Pojo.class) // JPQL
			//.createNativeQuery("SELECT * FROM pojo", Pojo.class) // SQL
			.setFirstResult(0).setMaxResults(20)
			.getResultList();

		for (Pojo pojo : list) {
			System.out.println("pojo: " + pojo);
		}

		entityManager.getTransaction().commit();
	}

	@org.junit.Test
	public void update() {
		entityManager.getTransaction().begin();

		Pojo pojo = entityManager.find(Pojo.class, 1);

		pojo.setDate(new Date());

		entityManager.merge(pojo);

		entityManager.getTransaction().commit();
	}
}
