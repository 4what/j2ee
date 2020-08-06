<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="sample.spring.aop.Class" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	Class cls = ctx.getBean(Class.class);
	cls.method();
%>
