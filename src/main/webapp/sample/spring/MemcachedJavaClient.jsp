<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.whalin.MemCached.MemCachedClient" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	MemCachedClient mc = ctx.getBean(MemCachedClient.class);

	mc.set("key", "value");
	out.println(mc.get("key"));
%>
