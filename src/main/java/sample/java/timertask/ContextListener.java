package sample.java.timertask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public final class ContextListener implements ServletContextListener {
	private Timer timer;

	public ContextListener() {
		timer = new Timer();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("<TimerTask>: Start");

		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-01-01 00:00:00");

			timer.schedule(new Task(),
				//date
				1000 * 60
			);

/*
			timer.
				schedule
				//scheduleAtFixedRate
					(new Task(),
						0, 1000 * 60
					);
*/
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();

		System.out.println("<TimerTask>: End");
	}
}
