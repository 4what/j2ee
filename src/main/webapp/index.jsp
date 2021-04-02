<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="util" uri="/WEB-INF/tld/util.tld" %>

<%@ page import="java.util.Map" %>

<html>
<head>
	<title></title>
</head>
<body>

<h2>Runtime.getRuntime().maxMemory() | -Xmx</h2>
<%
	out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
%>


<h2>System.getenv()</h2>
<%
	for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
		out.println(entry.getKey() + ": " + entry.getValue());
		out.println("<br />");
	}
%>


<h2>util:encode</h2>
${util:encode("http://www.example.com:8080/")}


<h2>util:href</h2>
${util:href(pageContext.request)}


<h2>util:httpget</h2>
${util:httpget("http://localhost:8080/")}


<h2>util:root</h2>
${util:root(pageContext.request)}

</body>
</html>
