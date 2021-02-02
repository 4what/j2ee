<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/admin/_auth.jspf" %>

<%@ page import="org.apache.commons.io.FileUtils" %>
<%@ page import="org.apache.commons.lang3.ArrayUtils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>

<%@ page import="java.io.File" %>

<%

%>

<!--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title></title>

	<%@ include file="_script_style.jspf" %>

	<script type="text/javascript">

	</script>
</head>
<body>

<ol>
<%
	String path = application.getRealPath("/") + "WEB-INF/log/";
	//System.out.println("path: " + path);

	String filename = request.getParameter("filename");

	if (StringUtils.isBlank(filename)) {
		File[] files = new File(path).listFiles();
		ArrayUtils.reverse(files);

		for (File file : files) {
%>
	<li><a href="?filename=<%= file.getName() %>"><%= file.getName() %></a></li>
<%
		}
	} else {
		for (String line : FileUtils.readLines(new File(path + filename), "UTF-8")) {
%>
	<li><%= line %></li>
<%
		}
	}
%>
</ol>

</body>
</html>
