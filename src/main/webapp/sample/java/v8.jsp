<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>

<%
	List<Object> list = new ArrayList<>();

	list.add(new Date());

	list.forEach(System.out::println);

	out.println("OK");
%>
