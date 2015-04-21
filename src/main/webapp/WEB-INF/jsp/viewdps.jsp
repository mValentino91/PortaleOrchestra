<%-- 
    Document   : viewdps
    Created on : 21-apr-2015, 12.19.44
    Author     : Alex
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../dist/css/poi_view.css" rel="stylesheet">
        <script src="../dist/js/jquery.js"></script>
        <script src="../dist/js/jquery.autocomplete.js"></script>
        <title>Visualizza pagine d'approfondimento</title>
    </head>
    <body>
    <center>
        <article class="component component-text" style="margin-top:50px; width: 600px;">
            <div class="big-header " style="background-color: #f0ad4e;">
                <span class="caps">Pagine d'approfondimento inserite</span>
            </div> 
            <c:if test="${not empty list}">
            <div class="details" style="text-align: center;">
                <c:forEach var="page" items="${list}">
                    <a href="../getDP?id=${page.id}" class="btn btn-primary" style="width: 400px;">${page.name}</a><br><br>
                </c:forEach>
            </div>
            </c:if>
        </article>
        <a href="../admin" class="btn btn-primary" style="margin-top: 20px;">Torna a Gestione Orchestra</a>
    </center>
</body>
<script>
