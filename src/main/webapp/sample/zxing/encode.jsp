<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="$java.ZXing" %>

<%@ page import="java.util.Date" %>

<%
	/* encode */
	String content = "中文, " + new Date();
	int width = 200;
	int height = 200;
	String format = "png";
	String pathname = application.getRealPath("/") + "sample/zxing/qrcode." + format;

	out.println(ZXing.encode(content, width, height, format, pathname));
%>
