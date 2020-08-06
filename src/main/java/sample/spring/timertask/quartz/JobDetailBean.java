package sample.spring.timertask.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

public class JobDetailBean extends QuartzJobBean {
	private Object foo;

	public void setFoo(Object foo) {
		this.foo = foo;
	}

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		System.out.println("<JobDetailBean>: " + new Date());
		System.out.println("foo: " + foo);
	}
}
