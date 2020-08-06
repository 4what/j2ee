<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.Enumeration" %>

<%
	//request.getRequestDispatcher("/").forward(request, response);
	//response.sendRedirect("/");
%>

<html>
<head><title></title></head>
<body>

<h2>attribute</h2>
<%
	request.setAttribute("name", "value");
	out.println(request.getAttribute("name")); // ${requestScope.name}
%>


<h2>context-param</h2>
<%
	out.println(application.getInitParameter("context-param-name"));
%>


<h2>cookie</h2>
<%
	// set
	Cookie cookie = new Cookie("name", "value");

	//cookie.setDomain(".example.com");
	//cookie.setMaxAge(60 * 60 * 24 * 7); // second, -1, 0
	cookie.setPath("/");
	//cookie.setSecure(true);

	response.addCookie(cookie);

	// get
	Cookie[] cookies = request.getCookies();

	if (cookies != null) {
		for (Cookie item : cookies) {
			out.println(item.getName() + ": " + item.getValue());
			out.println("<br />");
		}
	}
%>


<h2>header</h2>
<%
	Enumeration headerNames = request.getHeaderNames();

	while (headerNames.hasMoreElements()) {
		Object item = headerNames.nextElement();

		out.println(item + ": " + request.getHeader(String.valueOf(item)));
		out.println("<br />");
	}
%>


<h2>host</h2>
<%
	out.println(request.getHeader("host"));
%>


<h2>hostname</h2>
<%
	out.println(request.getServerName());
%>


<h2>ip</h2>
<%
	out.println(request.getRemoteAddr());
	out.println("<br />");
	out.println("X-Forwarded-For: " + request.getHeader("x-forwarded-for"));
	out.println("<br />");
	out.println("X-Real-IP: " + request.getHeader("x-real-ip"));
%>


<h2>method</h2>
<%
	out.println(request.getMethod());
%>


<h2>parameter</h2>
<%
	out.println(request.getParameter("name"));

/*
	for (String value : request.getParameterValues("name")) {
		out.println(value);
		out.println("<br />");
	}
*/
%>


<h2>path</h2>
<%
	out.println(application.getRealPath("/"));
	out.println("<br />");
	out.println(request.getContextPath());
	out.println("<br />");
	out.println(request.getRequestURI());
	out.println("<br />");
	out.println(request.getServletPath());
%>


<h2>port</h2>
<%
	out.println(request.getServerPort());
%>


<h2>protocol</h2>
<%
	out.println(request.getProtocol());
%>


<h2>query</h2>
<%
	out.println(request.getQueryString());
%>


<h2>referrer</h2>
<%
	out.println(request.getHeader("referer"));
%>


<h2>session</h2>
<%
	out.println(session.getId());
	out.println("<br />");

	session.setAttribute("name", "value");
	out.println(session.getAttribute("name")); // ${sessionScope.name}
%>


<h2>url</h2>
<%
	out.println(request.getRequestURL());
%>

</body>
</html>
