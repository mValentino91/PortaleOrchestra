<%-- 
    Document   : newjsp
    Created on : 11-dic-2014, 17.09.36
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./dist/css/mapView.css" rel="stylesheet">
        <!-- INCLUSIONE GOOGLE-MAPS -->
        <script type="text/javascript" 
                src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyBKbphxUcFrE24FYlwrs6K-yzXBguXRhhg&sensor=true">
        </script>
        <script src="./dist/js/mapView.js"></script>
        <title>MapView</title>
        <link href="./dist/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    </head>
    <body onload="$('#loadingImg').hide();">
        <img id="loadingImg" src="./dist/img/loading.gif"/>
        <jsp:include page="components/sideBar.jsp"/>
        <jsp:include page="components/interactiveMapComponent.jsp"/>
        <jsp:include page="access/loginModal.jsp" />
    </body>
</html>
