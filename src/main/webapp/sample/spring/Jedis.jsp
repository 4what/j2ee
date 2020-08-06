<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="redis.clients.jedis.Jedis" %>
<%@ page import="redis.clients.jedis.JedisPool" %>

<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);


	JedisPool jedisPool = ctx.getBean(JedisPool.class);
	Jedis jedis = jedisPool.getResource();

	try {
		jedis.set("key", "value");
		out.println(jedis.get("key"));
	} finally {
		if (jedis != null) {
			jedis.close();
		}
	}


/*
	// cluster
	JedisCluster jedisCluster = ctx.getBean(JedisCluster.class);

	jedisCluster.set("key", "value");
	out.println(jedisCluster.get("key"));
*/
%>
