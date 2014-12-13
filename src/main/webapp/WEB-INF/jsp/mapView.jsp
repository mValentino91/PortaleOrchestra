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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <!--<link href="./dist/css/bootstrap.min.css" rel="stylesheet">-->
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/googlePlusDesign/css/bootstrap.min.css" rel="stylesheet">
        <link href="./dist/googlePlusDesign/css/styles.css" rel="stylesheet">
        <link href="./dist/css/section.css" rel="stylesheet">
        <script src="./dist/js/section.js"></script>
        <script src="./dist/googlePlusDesign/js/bootstrap.min.js"></script>
        <script src="./dist/js/readmore.js"></script>
        <style>
            body{
                background-color: lightgray;
                color: #285e8e;
            }
        </style>
        <title>MapView</title>
        <link href="./dist/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    </head>
    <body>
        <jsp:include page="components/sideBar.jsp"/>
        <div style="position: absolute; width: 100%; height: 100%; top:50px;">
            <jsp:include page="components/interactiveMapComponent.jsp"/>
        </div>
    </body>
</html>
