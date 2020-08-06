package sample.spring;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@ActiveProfiles("test")
//@PropertySource("classpath:system.properties")
public class Properties {
	@Value("${sys.debug:default}")
	private String debug;

	@Test
	public void get() {
		System.out.println("sys.debug: " + debug);
	}
}
