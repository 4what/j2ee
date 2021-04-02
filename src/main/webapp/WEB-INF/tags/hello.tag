<%@ tag pageEncoding="UTF-8" %>

<%@ attribute name="hidden" required="false" rtexprvalue="true" %>

<%
	if (!Boolean.valueOf(hidden)) {
%>

<jsp:doBody />

<%
	}
%>
