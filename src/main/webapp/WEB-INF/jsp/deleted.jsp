<%-- 
    Document   : deleted
    Created on : 18-dic-2014, 9.38.23
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../dist/css/poi_view.css" rel="stylesheet">
        <title>Poi Eliminato</title>
    </head>
    <body>
        <center>
        <h1>POI ELIMINATO CORRETTAMENTE!</h1>
        <sec:authorize access="hasRole('ROLE_SUPERADMIN')">
        <h2><a href="../admin" class="btn btn-primary" style="margin-top: 20px;">Torna a Gestione Orchestra</a></h2>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
        <h2><a href="../getOwnPois" class="btn btn-primary" style="margin-top: 20px;">Torna a Gestione Orchestra</a></h2>
        </sec:authorize> 
            </center>
    </body>
</html>
