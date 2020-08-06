<%@ page contentType="text/html; charset=UTF-8" %>

<%
	String action =
		"upload.jsp"
		//request.getContextPath() + "/upload"
	;
%>

<html>
<head><title></title></head>
<body>

<form action="<%= action %>" method="post" enctype="multipart/form-data">
	<p>
		file:
		<input type="file" name="file" />
		<input type="file" name="file" />
	</p>
	<p>
		name: <input type="text" name="name" value="value" />
	</p>
	<p>
		<button type="submit">Upload</button>
	</p>
</form>

</body>
</html>
