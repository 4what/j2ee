<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="sample.spring.timertask.quartz.Task" %>

<%@ page import="org.quartz.Scheduler" %>
<%@ page import="org.springframework.scheduling.quartz.CronTriggerFactoryBean" %>
<%@ page import="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean" %>
<%@ page import="org.springframework.scheduling.quartz.SimpleTriggerFactoryBean" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	try {
		Scheduler scheduler = ctx.getBean(Scheduler.class);


		// delete
		//scheduler.deleteJob("", "DEFAULT");


		// start
		if (!scheduler.isStarted()) {
			scheduler.start();
		}


		Task task = ctx.getBean(Task.class);


		// jobDetail
		MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();

		jobDetail.setName("jobDetail-" + System.currentTimeMillis());

		jobDetail.setTargetObject(task);

		jobDetail.setTargetMethod("run");
		jobDetail.setArguments(new Object[]{
			System.currentTimeMillis()
		});

		//jobDetail.setConcurrent(false);

		jobDetail.afterPropertiesSet();


		// simpleTrigger
		SimpleTriggerFactoryBean simpleTrigger = new SimpleTriggerFactoryBean();

		simpleTrigger.setBeanName("simpleTrigger-" + System.currentTimeMillis());

		simpleTrigger.setRepeatInterval(1000);

		//simpleTrigger.setRepeatCount(0);

		//simpleTrigger.setStartTime();
		//simpleTrigger.setEndTime();

		simpleTrigger.afterPropertiesSet();


		// cronTrigger
		CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();

		cronTrigger.setBeanName("cronTrigger-" + System.currentTimeMillis());

		cronTrigger.setCronExpression("* * * * * ?");

		//cronTrigger.setStartTime();
		//cronTrigger.setEndTime();

		cronTrigger.afterPropertiesSet();


		//
		scheduler.scheduleJob(jobDetail.getObject(),
			//simpleTrigger.getObject()
			cronTrigger.getObject()
		);
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
