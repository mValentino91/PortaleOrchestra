<%-- 
    Document   : infopoi
    Created on : 2-dic-2014, 12.10.28
    Author     : Alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <!--<link href="./dist/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>


        <script type="text/javascript" src="./dist/nanoscroller/jquery.nanoscroller.min.js"></script>
        <link rel="stylesheet" href="./dist/nanoscroller/nanoscroller.css" type="text/css" media="screen" />
        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 
        <script src="./dist/js/section.js"></script>
        <script src="./dist/js/composite.js"></script>
        <script src="./dist/googlePlusDesign/js/bootstrap.min.js"></script>
        <script src="./dist/js/readmore.js"></script>
        <script src="./dist/js/favorite_ajax.js"></script>
        <script src="./dist/js/favorite.js"></script>
        <link rel="stylesheet" href="./dist/ion-range/css/normalize.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.skinFlat.css" />
        <link href="./dist/css/itinerary.css" rel="stylesheet">

        <title><spring:message code="label.favoritespoi"></spring:message></title>   
            
        <script>
            $(document).ready(function () {
                $(".delit").each(function (index) {
                    var sel = $(this);
                    $(this).on("click", function () {
                        var idItinerary = sel.parent().attr("idItinerary");
                         $.ajax({

                            type: "GET",
                            url: "./removeItinerary",
                            data: "idItinerary="+ idItinerary,

                            success: function(){
                                alert("itinerario cancellato!");
                            }
                        });

                    });
                });

                $(".complit").each(function (index) {
                    var sel = $(this);
                    $(this).on("click", function () {
                        var idItinerary = sel.parent().attr("idItinerary");
                         $.ajax({

                            type: "GET",
                            url: "./completeItinerary",
                            data: "idItinerary="+ idItinerary,

                            success: function(){
                                alert("itinerario completato!");
                            }
                        });

                    });
                });

                $("#btn_new_it").click(function () {
                    var content = $("#it_name").val();
                    
                    $.ajax({
                        type: "GET",
                        url: "./newItinerary",
                        data: "name=" + content,
                        success: function(){
                            alert("itinerario creato!");
                            window.location="./myItinerary";
                        }
                    });
                });
            });
        </script>

    </head>
        
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <jsp:include page="components/CoverComponentFavorites.jsp"/>
            </div>

            

            <div class="col-md-5" style="margin-top:5px;">
               <!-- Modal -->
                <div id="myModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Crea un nuovo itinerario</h4>
                            </div>
                            <div class="modal-body">


                                <input id="it_name" style="border:0px; width: 300px;" type="text" placeholder="Assegna un nome dell'itinerario.."/>
                            </div>
                            <div class="modal-footer">
                                <button id="btn_new_it" type="button" class="btn btn-primary" data-dismiss="modal">Crea</button>  
                                <button type="button" class="btn btn-primary" data-dismiss="modal">Annulla</button>
                            </div>
                        </div>

                    </div>
                </div>
                
                

                <div id="nuovo_it" class="new_it_button" data-toggle="modal" data-target="#myModal">Crea un nuovo itinerario</div>

                <article class="component component-text">
                    <div class="details details_itinerary" style="padding-left: 10px; padding-right: 10px;">
                        <div class="paragrafo">

                            <div class="itinerary_container">
                                <c:choose>
                                    <c:when test="${not empty itinerary}">
                                        <c:forEach var="it" items="${itinerary}">
                                            <div class="itinerary" >
                                                <div class="img_container">
                                                    <c:set var="itn" value="${it.name}"/>
                                                    <c:set var="it_name" value="${fn:substring(itn, 0, 2)}" />
                                                    <c:set var="tx" value="${fn:toUpperCase(it_name)}" />
                                                    
                                                    <div class="rnd" style="background-color:${it.color};">
                                                        <span class="rnd-text">${tx}</span>
                                                    </div>
                                                </div>
                                                <div class="name_container" idItinerary="${it.idItinerary}">${it.name}</div>
                                                
                                                    <c:choose>
                                                        <c:when test="${it.status == 0}">
                                                            <div class="status_container">attivo</div>
                                                            <a href="./myItineraryDetail?idItinerary=${it.idItinerary}"><div class="modify_btn">Modifica</div></a>
                                                        </c:when>

                                                        <c:when test="${it.status == 1}">
                                                            <div class="status_container">completato</div>
                                                            <a href="./myItineraryDetail?idItinerary=${it.idItinerary}"><div class="modify_btn">Visualizza</div></a>
                                                        </c:when>    

                                                        <c:when test="${it.status == 2}">
                                                            <div class="status_container">scaduto</div>
                                                            <a href="./myItineraryDetail?idItinerary=${it.idItinerary}"><div class="modify_btn">Visualizza</div></a>
                                                        </c:when>                                                        
                                                    </c:choose> 
                                                
                                                

	                                    </div>
                                            
                                            
                                            
                                            
                                            
                                        </c:forEach>
                                    </c:when>    

                                    <c:otherwise>
                                        Nessun itinerario
                                    </c:otherwise>
                                </c:choose>
                                <div class="clear"></div>    
                            </div>
                        </div>    
                    </div>
                </article>
            </div>

            <div class="col-md-7">
                <article class="component component-text">
                    <div class="details details_infographic" style="margin-top:5px; margin-bottom:30px; margin-bottom:5px; padding-left:0px; padding-right:0px;">
                    	<div class="inf_row">
                    		<div class="inf_number">1</div>
                        	<div class="inf_content">Scopri i poi e crea il tuo itinerario</div>
                    	</div>
                        
                    	<div class="inf_row">
                    		<div class="inf_number">2</div>
                        	<div class="inf_content">aaa</div>
                    	</div>
                        
                    	<div class="inf_row">
                    		<div class="inf_number">3</div>
                        	<div class="inf_content">aaa</div>
                    	</div>
                        
                    </div>
                </article>
            </div>

            <div class="col-xs-12" id="footer">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>

        </div>

        <jsp:include page="access/loginModal.jsp" />
        

    </body>
</html>