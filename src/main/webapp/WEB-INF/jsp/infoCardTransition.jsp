<!doctype html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./dist/css/poi_view.css">
        <link rel="stylesheet" type="text/css" href="./dist/css/font-awesome.min.css">
        <script src="./dist/js/jquery.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>   
    </head>
    <body>
        ${idcard}
        ${iditi}
    </body>
</html>
