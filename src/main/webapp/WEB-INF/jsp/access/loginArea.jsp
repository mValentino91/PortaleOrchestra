<%-- 
    Document   : login_area
    Created on : 2-mar-2015, 15.06.35
    Author     : antonio
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<c:url value="logout" var="logoutUrl"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sec:authorize access="isAnonymous()">
    <a href="#" id='modal-launcher' class="navbar-link" data-toggle="modal" data-target="#login-modal">Login</a>
</sec:authorize>    
<sec:authorize access="isAuthenticated()">
   <a href="${logoutUrl}" class="navbar-link">Logout</a>
</sec:authorize>
   
