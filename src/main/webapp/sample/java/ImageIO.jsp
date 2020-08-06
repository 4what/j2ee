<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.File" %>

<%
	response.setContentType("image/png");

	ImageIO.write(ImageIO.read(new File(application.getRealPath("/") + "qrcode.png")), "png", response.getOutputStream());
%>
