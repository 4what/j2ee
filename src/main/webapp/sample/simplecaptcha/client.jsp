<%@ page contentType="text/html; charset=UTF-8" %>

<%

%>

<html>
<head>
	<title></title>
</head>
<body>

<form action="server.jsp" method="post">
	<p>
		<img src="<%= request.getContextPath() %>/SimpleCaptcha" alt="" />
	</p>
	<p><input type="text" name="answer" value="" /></p>
	<p><input type="submit" value="Submit" /></p>
</form>

</body>
</html>
