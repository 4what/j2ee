<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="$java.ZXing" %>

<%@ page import="com.google.zxing.Result" %>

<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.io.File" %>

<%
	// decode
	String pathname = application.getRealPath("/") + "sample/zxing/qrcode.png";

	Result result = ZXing.decode(ImageIO.read(new File(pathname)));

	out.println("text: " + result.getText());
	out.println("<br />");
	out.println("format: " + result.getBarcodeFormat());
%>
