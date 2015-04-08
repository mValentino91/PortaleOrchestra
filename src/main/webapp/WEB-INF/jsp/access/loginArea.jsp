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

<script>
$(document).on("loginDone",reload_access_area);
function reload_access_area(event){
   $.reloadElement("loginArea","./loginArea");
}
</script>

<sec:authorize access="isAnonymous()">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <img src="./dist/img/default_avatar.png" class="profile-image img-circle">
        </a>
        <ul class="dropdown-menu">
            <li>        
                <a href="#" id='modal-launcher' data-toggle="modal" data-target="#login-modal">
                    <i class="fa fa-sign-in"></i> Login
                </a>
            </li>
        </ul>
    </li>
</sec:authorize>    
<sec:authorize access="isAuthenticated()">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <img src="${avatar}" class="profile-image img-circle">
        </a>
        <ul class="dropdown-menu">
            <li><a href="./userInfo"><i class="fa fa-cog"></i> Il tuo profilo</a></li>
            <li class="divider"></li>
            <li><a href="${logoutUrl}"><i class="fa fa-sign-out"></i> Logout</a></li>
        </ul>
    </li>
</sec:authorize>

