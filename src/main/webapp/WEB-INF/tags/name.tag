<%@ tag pageEncoding="UTF-8" %>

<%@ attribute name="attr" required="false" rtexprvalue="true" %>

<%
	if (Boolean.valueOf(attr)) {
%>

<jsp:doBody />

<%
	}
%>
