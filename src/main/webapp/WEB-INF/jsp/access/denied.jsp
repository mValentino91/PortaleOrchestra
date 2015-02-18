<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css"/>
	<title>Home</title>
</head>

<body>
	<jsp:include page="../menu.jsp" />
	<h1 id="banner">ERRORE</h1>
	<hr/>
	
	<p class="message">Accesso Negato</p>
</body>
</html>