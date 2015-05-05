<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
	
	<title>Home</title>
</head>

<body>
	
	<h1 id="banner"><spring:message code="label.error"></spring:message></h1>
	<hr/>
	
	<p class="message"><spring:message code="label.accessdenied"></spring:message></p>
</body>
</html>