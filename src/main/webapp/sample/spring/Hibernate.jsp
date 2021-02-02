<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="sample.hibernate.domain.Pojo" %>

<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.springframework.orm.hibernate5.HibernateTransactionManager" %>
<%@ page import="org.springframework.transaction.PlatformTransactionManager" %>
<%@ page import="org.springframework.transaction.TransactionStatus" %>
<%@ page import="org.springframework.transaction.support.TransactionCallbackWithoutResult" %>
<%@ page import="org.springframework.transaction.support.TransactionTemplate" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%@ page import="java.util.Date" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);

	/* tx */
	PlatformTransactionManager transactionManager = ctx.getBean(HibernateTransactionManager.class);

	TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

	try {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				try {
					Pojo pojo = new Pojo();

					pojo.setDate(new Date());

					sessionFactory.getCurrentSession().save(pojo);

					System.out.println(0 / 0);
				} catch (Exception e) {
					e.printStackTrace();

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
