package sample.java.concurrent.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {


	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.
			newSingleThreadScheduledExecutor()
			//newScheduledThreadPool(4)
		;

		scheduledExecutorService.
			schedule(new Runnable(), 10, TimeUnit.SECONDS)

/*
			//scheduleAtFixedRate
			scheduleWithFixedDelay
				//(new Runnable(), 0, 10, TimeUnit.SECONDS)
*/
		;
	}
}
