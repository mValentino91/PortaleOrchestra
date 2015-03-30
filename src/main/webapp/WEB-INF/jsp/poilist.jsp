<%-- 
    Document   : poilist
    Created on : 12-mar-2015, 11.42.48
    Author     : Alex
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <c:forEach var="id" items="${lista}">
        ${id}<br>
        </c:forEach>
    </body>
</html>
