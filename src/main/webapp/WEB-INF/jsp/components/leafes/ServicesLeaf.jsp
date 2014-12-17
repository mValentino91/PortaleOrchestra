<%-- 
    Document   : ServicesLeaf
    Created on : 13-dic-2014, 10.21.31
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<center>
    <c:forEach var="service" items="${services.servicesList}">
        
            <div class="col-md-6"><i class="fa fa-certificate"></i> ${service}</div>
            
        

    </c:forEach>
</center>
