package sample.spring.dao;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
public class Transactional {
	@Test
	@org.springframework.transaction.annotation.Transactional(/*rollbackFor = {Exception.class}*/)
	@Commit // for JUnit
	public void rollback() {
		try {
			System.out.println(0 / 0);
		} catch (Exception e) {
			e.printStackTrace();

			throw new RuntimeException();
		}
	}
}
