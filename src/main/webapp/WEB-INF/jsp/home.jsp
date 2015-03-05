<%-- 
    Document   : fb_login_js
    Created on : 24-feb-2015, 14.27.15
    Author     : antonio
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:url value="logout" var="logoutUrl"/>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FB Login JS Nuovo</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>
        
        <link rel="stylesheet" href="./dist/css/bootstrap.min.css"/>
        
        <script>
            $(document).ready(function(){
                //alert("yo!");
                //$.reload_access_area();
            });
        </script>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
          <div class="container">
            <p id="loginArea" class="navbar-text navbar-right">
                <jsp:include page="access/loginArea.jsp" />
            </p>
          </div>
        </nav>
        
        <div style="margin-top: 100px">
            
            Ciao Bello
            
        </div>
        
    </body>
</html>

<jsp:include page="access/loginModal.jsp" />