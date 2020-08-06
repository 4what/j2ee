package sample.java.timertask;

import java.util.Date;
import java.util.TimerTask;

public class Task extends TimerTask {
	@Override
	public void run() {
		System.out.println("<Task>: " + new Date());
	}
}
