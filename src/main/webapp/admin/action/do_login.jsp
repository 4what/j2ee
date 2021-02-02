<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="com.example.dao.GenericRepository" %>
<%@ page import="com.example.domain.Admin" %>
<%@ page import="com.example.domain.Session" %>

<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.databind.node.ObjectNode" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.transaction.support.TransactionTemplate" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<%@ page import="java.util.Date" %>

<%
	out.clear();

	request.setCharacterEncoding("UTF-8");

	String action = request.getParameter("action");
	if (StringUtils.isBlank(action)) {
		return;
	}

	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);

	ObjectMapper mapper = ctx.getBean(ObjectMapper.class);

	ObjectNode result = mapper.createObjectNode();

	/* ... */
	GenericRepository genericRepository = ctx.getBean(GenericRepository.class);

	TransactionTemplate transactionTemplate = ctx.getBean(TransactionTemplate.class);

	switch (action) {
		case "login":
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			result.put("msg", "用户名或密码错误");

			for (Admin admin : genericRepository.list(Admin.class, "WHERE username = ? AND password = ?", username, DigestUtils.sha1Hex(password))) {
				/* session */
/*
				session.setAttribute("_admin_id", admin.getAdminId());
*/


				/* cookie */
				for (Session s : genericRepository.list(Session.class, "WHERE adminid = ? ORDER BY createdate DESC", admin.getAdminId())) {
					genericRepository.delete(s);
				}

				String sessionId = admin.getAdminId() + ":" + session.getId() + ":" + System.currentTimeMillis();

				Session s = new Session();

				s.setAdminId(admin.getAdminId());

				s.setValue(sessionId);

				s.setCreateDate(new Date());

				genericRepository.create(s);

				Cookie cookie = new Cookie("_admin_sessionid", sessionId);

				cookie.setMaxAge(60 * 60 * 24 * 7); // second
				cookie.setPath("/");

				response.addCookie(cookie);


				result.put("success", true);
			}

			break;
		default:
			break;
	}

	out.println(result);
%>
