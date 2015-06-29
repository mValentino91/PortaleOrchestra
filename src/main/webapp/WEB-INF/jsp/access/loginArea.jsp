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
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
                    <i class="fa fa-sign-in"></i> <spring:message code="label.login"></spring:message>
                </a>
            </li>
            <li class="divider"></li>
            <li><a href="./privacy"><i class="fa fa-lock"></i> <spring:message code="label.privacypolicy"></spring:message></a></li>
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
            <li><a href="./userInfo"><i class="fa fa-cog"></i> <spring:message code="label.yourprofile"></spring:message></a></li>
            <li class="divider"></li>
            <li><a href="./privacy"><i class="fa fa-lock"></i> <spring:message code="label.privacypolicy"></spring:message></a></li>            
            <li class="divider"></li>
            <sec:authorize access="hasRole('ROLE_SUPERADMIN')">
            <li><a href="./admin"><i class="fa fa-cog"></i> <spring:message code="label.admin"></spring:message></a></li>
            <li class="divider"></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="./getOwnPois"><i class="fa fa-cog"></i> <spring:message code="label.admin"></spring:message></a></li>
            <li class="divider"></li>
            </sec:authorize>            
                 
            <li><a href="./favorites"><i class="fa fa-heart"></i> <spring:message code="label.favoritespoi"></spring:message></a></li>
               <li class="divider"></li>     
            <li><a href="#" onclick="doLogout();"><i class="fa fa-sign-out"></i> <spring:message code="label.logout"></spring:message></a></li>
            <a id="logout_url" href="${logoutUrl}" style="display: none"></a>
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
    <script type="text/javascript">
        function doLogout()
        {
            <sec:authorize access="hasRole('ROLE_FB')">
            FbLogout();
            </sec:authorize>
            window.location=document.getElementById('logout_url').href;
        }        
    </script>        
</sec:authorize>

