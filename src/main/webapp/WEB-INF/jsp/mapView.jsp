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

        <!--balloon-->
        <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
        <link href="./dist/css/balloon.css" rel="stylesheet">
        <link rel="stylesheet" href="./dist/ion-range/css/normalize.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.skinFlat.css" />

        <!--balloon-->

        <!-- INCLUSIONE GOOGLE-MAPS -->
        <script type="text/javascript" 
                src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyBKbphxUcFrE24FYlwrs6K-yzXBguXRhhg&sensor=true">
        </script>
        <script src="./dist/js/favorite_ajax.js"></script>
        <script src="./dist/js/mapView.js"></script>
        <title>MapView</title>
        <link href="./dist/favicon.ico" rel="shortcut icon" type="image/x-icon" />
        
        <!-- Leaflet -->
	<link rel="stylesheet" href="./dist/leaflet/leaflet.css" />
	<script src="./dist/leaflet/leaflet.js"></script>        
        
    </head>
    <body onload="$('#loadingImg').hide();">
        <img id="loadingImg" src="./dist/img/loading.gif"/>
        <jsp:include page="components/topBar.jsp"/>
        <jsp:include page="components/interactiveMapComponent.jsp"/>
        <jsp:include page="access/loginModal.jsp" />

        <script src="./dist/ion-range/js/ion.rangeSlider.js"></script>
    </body>
</html>
