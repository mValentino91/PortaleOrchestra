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
  
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
          <img id="userImg" src="./dist/img/default_avatar.png" class="profile-image img-circle">
           
        </a>
        <ul class="dropdown-menu">
            <li>        
                <a href="#" id='modal-launcher' data-toggle="modal" data-target="#login-modal">
                    <i class="fa fa-sign-in"></i> Login
                </a>
            </li>
            <li class="divider"></li>
            <li><a href="./privacy"><i class="fa fa-lock"></i> Privacy Policy</a></li>
        </ul>
    
    <script type="text/javascript">
        function ifAuth()
        {
            return false;
        }        
    </script>    
</sec:authorize>    
<sec:authorize access="isAuthenticated()">
    
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <img id="userImg" src="${avatar}" class="profile-image img-circle">
        </a>
        <ul class="dropdown-menu">
            <li><a href="./userInfo"><i class="fa fa-cog"></i> Il tuo profilo</a></li>
            <li class="divider"></li>
            <li><a href="./privacy"><i class="fa fa-lock"></i> Privacy Policy</a></li>            
            <li class="divider"></li>
            <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_SUPERADMIN')">
            <li><a href="./admin"><i class="fa fa-cog"></i> Admin</a></li>
            <li class="divider"></li>
            </sec:authorize>
            <li><a href="${logoutUrl}"><i class="fa fa-sign-out"></i> Logout</a></li>
        </ul>
 
    <script type="text/javascript">
        function getUserId()
        {
            return ${uid};
        }
        
        function ifAuth()
        {
            return true;
        }        
    </script>
</sec:authorize>

