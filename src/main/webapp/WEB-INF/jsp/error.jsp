<%-- 
    Document   : error
    Created on : 13-giu-2015, 21.58.10
    Author     : andrea
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
        <script src="./dist/js/jquery.js"></script>
        
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>
        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="./dist/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraIconFont.css" rel="stylesheet">  

    </head>
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed" style="min-height: 100px;">
            <article class="component component-text">
                <div class="details">
                    ${err}
                  
                </div>
            </article>
        </div>   
    </body>
</html>
