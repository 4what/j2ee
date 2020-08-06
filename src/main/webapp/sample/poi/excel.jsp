<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="$java.CommonsFileUpload" %>
<%@ page import="$java.poi.Excel" %>

<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.util.List" %>

<%
	String method = request.getParameter("method");

	if (method.equals("export")) {
		String filename = "workbook.xls";

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=" + filename + ";");

		OutputStream output = response.getOutputStream();

		Excel.write(output, Excel.getWorkbook(new String[]{"ID", "DATE"}));
	} else if (method.equals("import")) {
		for (InputStream input : CommonsFileUpload.upload(CommonsFileUpload.parseRequest(request))) {
			List<List> result = Excel.read(input);

			for (List items : result) {
				out.println(items);
				out.println("<br />");
				out.println(items.get(0));
				out.println("<br />");
			}
		}
	}
%>
