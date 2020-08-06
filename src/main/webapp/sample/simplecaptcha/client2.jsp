<%@ page contentType="text/html; charset=UTF-8" %>

<%
	request.getRequestDispatcher("/SimpleCaptcha?timestamp=" + System.currentTimeMillis()).forward(request, response);
	// response.sendRedirect(request.getContextPath() + "/SimpleCaptcha");
%>
