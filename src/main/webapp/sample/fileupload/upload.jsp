<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="$java.CommonsFileUpload" %>

<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>

<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
	out.clear();

	request.setCharacterEncoding("UTF-8");


	// for CORS
	response.setHeader("Access-Control-Allow-Origin", "*");


	List<FileItem> items = CommonsFileUpload.parseRequest(request);


	Map params = CommonsFileUpload.getParams(items);

	for (Object o : params.entrySet()) {
		Map.Entry entry = (Map.Entry) o;

		System.out.println(entry.getKey() + ": " + entry.getValue());
	}


/*
	// InputStream
	List<InputStream> result = CommonsFileUpload.upload(request, items);

	for (InputStream input : result) {
		out.println("input: " + input);

		input.close();
	}
*/


	String path = application.getRealPath("/") + "sample/fileupload/";

	List<String> result = CommonsFileUpload.upload(items, path);

	for (String pathname : result) {
		System.out.println("pathname: " + pathname);
	}


	String data = "\"data\": \"" + StringUtils.join(result.toArray(), ",") + "\"";

	if (request.getParameter("windowname") != null) {
%>

<script type="text/javascript">
window.name = '{' + '<%= data %>' + '}';
</script>

<%
	} else {
		// for AFNetworking
		//response.setContentType("application/json");

		// for ExtJS 3.x
		//response.setContentType("text/html");
%>

{ <%= data %> }

<%
	}
%>
