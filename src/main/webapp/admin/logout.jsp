<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="$java.Utilities" %>

<%@ page import="com.example.dao.GenericRepository" %>
<%@ page import="com.example.domain.Session" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>


<%
	/* session */
/*
	session.removeAttribute("_admin_id");
*/


	/* cookie */
	Cookie cookie = Utilities.cookie(request, "_admin_sessionid");

	if (cookie == null) {
		response.sendRedirect(request.getContextPath() + "/admin/login.jsp");

		return;
	}

	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	GenericRepository genericRepository = ctx.getBean(GenericRepository.class);

	for (Session s : genericRepository.list(Session.class, "WHERE value = ?", cookie.getValue())) {
		genericRepository.delete(s);
	}

	cookie.setMaxAge(0);
	cookie.setPath("/");

	response.addCookie(cookie);


	response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
%>
