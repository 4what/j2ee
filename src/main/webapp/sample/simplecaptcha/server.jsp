<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="nl.captcha.Captcha" %>

<%
	Captcha captcha = (Captcha) session.getAttribute(Captcha.NAME);

	String answer = request.getParameter("answer");

	if (captcha.isCorrect(answer)) {
		out.println("correct");
	} else {
		out.println("incorrect");
	}
%>
