<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="nl.captcha.Captcha" %>
<%@ page import="nl.captcha.audio.AudioCaptcha" %>

<%
	session.removeAttribute(Captcha.NAME);
	session.removeAttribute(AudioCaptcha.NAME);
%>
