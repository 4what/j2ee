package sample.spring.timertask.quartz;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class Task {
	@PostConstruct
	private void init() {
	}

	public void run(Object o) {
		System.out.println("<" + o + ">: " + new Date());
	}
}
