<%@ page contentType="text/html; charset=UTF-8" %>

<%

%>

<html>
<head>
	<title></title>
</head>
<body>

<h2>attribute</h2>
<p>global: <%= request.getAttribute("global") %></p>

<p>local: <%= request.getAttribute("local") %></p>

<p>data: <%= request.getAttribute("data") %></p>

</body>
</html>
