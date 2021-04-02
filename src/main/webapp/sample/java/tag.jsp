<%@ page contentType="text/html; charset=UTF-8" %>

<%--
	Tag File Location:
		/WEB-INF/
			lib/
			tags/
--%>
<%@ taglib prefix="x" tagdir="/WEB-INF/tags" %>

<x:hello hidden="false">Text</x:hello>


<%@ taglib prefix="hello" uri="/WEB-INF/tld/hello.tld" %>

<hello:action hidden="false">Text</hello:action>
