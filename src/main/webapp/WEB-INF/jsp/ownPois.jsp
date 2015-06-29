<%-- 
    Document   : ownPois
    Created on : 29-giu-2015, 14.26.01
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>
        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="./dist/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraIconFont.css" rel="stylesheet"> 
        <link rel="stylesheet" href="./dist/css/favorites.css" />
        
        <script src="./dist/js/jquery.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>

        <title>Admin - POI Manager</title>
    </head>
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">
            <sec:authorize access="hasRole('ROLE_CREATE')">
                <div class="col-md-12">
                    <div class="component component-text">
                        <div class="details">
                            <a href="./admin/newpoi">Crea Nuovo Poi</a>
                        </div>
                    </div>
                </div>
            </sec:authorize>
            
            <div class="col-md-12">

                <div class="component component-text">
                    <div class="details">

                        <!-- implementare il foreach dei preferiti per categorie-->
                        <div class="paragrafo">                
                            
                            <strong>Gestisci i tuoi POI:</strong>
                            <br><br>
                           
                                <c:forEach var="p" items="${pois}">
                                    <div class="poi_preview_box">
                                        <div class="poi_preview_img">
                                            <img src="./dist/poi/img/${p.id}/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/>
                                        </div>
                                        <div class="poi_preview_content">
                                            <div class="poi_preview_title">
                                                ${p.name}
                                            </div>
                                            <div class="poi_preview_text">
                                                <a target="_blank" href="./getPoi?id=${p.id}">Visualizza</a>
                                                <sec:authorize access="hasRole('ROLE_EDIT')">
                                                 | <a href="./admin/editpoi?id=${p.id}">Modifica</a>
                                                </sec:authorize> 
                                                <sec:authorize access="hasRole('ROLE_CREATE')">
                                                 | <a href="./admin/deletepoi?id=${p.id}">Elimina</a>   
                                                </sec:authorize>                                               
                                            </div>                                
                                        </div>
                                        <div class="clear"></div>     
                                    </div>
                                </c:forEach>
                           
                        </div>

                    </div>
                </div>


            </div>
        </div>


        <jsp:include page="access/loginModal.jsp" />
    </body>
</html>
