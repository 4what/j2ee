<%@ page contentType="text/html; charset=UTF-8" %>

<%

%>

<html>
<head>
	<title></title>
</head>
<body>

<h2>Excel</h2>
<a href="excel.jsp?method=export">Export</a>

<form action="excel.jsp?method=import" method="post" enctype="multipart/form-data">
	<p>
		<input type="file" name="file" />

		<input type="submit" value="Import" />
	</p>
</form>

</body>
</html>
