<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="org.springframework.transaction.PlatformTransactionManager" %>
<%@ page import="org.springframework.transaction.TransactionStatus" %>
<%@ page import="org.springframework.transaction.support.TransactionCallbackWithoutResult" %>
<%@ page import="org.springframework.transaction.support.TransactionTemplate" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%@ page import="java.sql.Timestamp" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);

	/* tx */
	PlatformTransactionManager transactionManager = (PlatformTransactionManager) ctx.getBean("transactionManager");

	/* PlatformTransactionManager */
/*
	DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();

	TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);

	try {
		jdbcTemplate.update("INSERT INTO pojo (date) VALUES (?)", new Timestamp(System.currentTimeMillis()));

		System.out.println(0 / 0);

		transactionManager.commit(transactionStatus);

		out.println("***** commit *****");
	} catch (Exception e) {
		e.printStackTrace();

		transactionManager.rollback(transactionStatus);

		out.println("***** rollback *****");
	}
*/

	/* TransactionTemplate */
	TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

	try {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				try {
					jdbcTemplate.update("INSERT INTO pojo (date) VALUES (?)", new Timestamp(System.currentTimeMillis()));

					System.out.println(0 / 0);
				} catch (Exception e) {
					e.printStackTrace();

					//transactionStatus.setRollbackOnly();
					throw new RuntimeException(e.getMessage());
				}
			}
		});

		out.println("***** commit *****");
	} catch (Exception e) {
		//e.printStackTrace();

		out.println("***** rollback *****");
	}
%>
