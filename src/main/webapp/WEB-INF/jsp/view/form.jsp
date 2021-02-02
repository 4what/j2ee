<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>

<%

%>

<html>
<head>
	<title></title>
</head>
<body>

<h2>form</h2>
<springform:form action="submit" method="POST" commandName="pojo">
	<p>
		id:
		<springform:input path="id" />
		<springform:errors path="id" />
	</p>
	<p><input type="submit" /></p>
</springform:form>

</body>
</html>
