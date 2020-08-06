package sample.spring.timertask.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobDetailFactoryBean {
	public void run() {
		System.out.println("<JobDetailFactoryBean>: " + new Date());
	}
}
