package sample.spring.timertask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskScheduler {
/*
	@Scheduled(
		fixedDelay = 1000
		//cron = "* * * * * ?" // Seconds, Minutes, Hours, Day-of-month, Month, Day-of-Week, Year (Optional)
	)
*/
	public void run() {
		System.out.println("<TaskScheduler>: " + new Date());
	}
}
