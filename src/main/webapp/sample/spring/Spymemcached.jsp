<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="net.spy.memcached.MemcachedClient" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	MemcachedClient mc = ctx.getBean(MemcachedClient.class);

	mc.set("key", 0 ,"value");
	out.println(mc.get("key"));
%>
