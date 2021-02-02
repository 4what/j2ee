<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.Date" %>

<%

%>

<html>
<head>
	<title></title>
</head>
<body>

<h2>bean</h2>
<jsp:useBean id="bean" class="sample.java.Bean" scope="page">
	<jsp:setProperty name="bean" property="id" value="1" />
</jsp:useBean>

id: <jsp:getProperty name="bean" property="id" />

<jsp:setProperty name="bean" property="date" value="<%= new Date() %>" />

date: <%= bean.getDate() %>

</body>
</html>
