<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="$java.spring.JavaMailSender" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%@ page import="java.util.Date" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	JavaMailSender javaMailSender = ctx.getBean(JavaMailSender.class);

	String from = "root@example.com";

	String[] to = new String[]{
		"user@localhost",
		"other@localhost"
	};

	String subject = "主题：" + new Date();
	String content = "正文：" + new Date();

	String[] attachments = {
		//"X:\\*.*",
		//"http://"
	};

	javaMailSender.send(from, to, subject, content, attachments);
%>
