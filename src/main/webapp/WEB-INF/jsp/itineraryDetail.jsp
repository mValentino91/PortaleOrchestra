<%-- 
    Document   : ItineraryDetail
    Created on : 8-ago-2015, 11.28.06
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
        <!--CSS-->
	<link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
	<link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
	<link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/itinerary.css" rel="stylesheet">

        <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 

        <!--SCRIPT-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

        <title><spring:message code="label.favoritespoi"></spring:message></title>   
    </head>
    
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <div class="cover_itinerary_detail">
                	<div class="cover_itd cover_itd_sx">
                		<div class="itinerary_det">
                			<div class="img_poi_header">
                                <div class="rnd_it_detail" style="background-color:#cdcdcd;"></div> 
                            </div>
                            <div class="it_det_info">Itinerario di Andrea</div>
                            <div class="it_det_info it_det_date">Creato il 22/12/2015</div>

                		</div>
                	</div>
                	
                	<div class="cover_itd cover_itd_dx">
                		<div class="price_detail">
                			Totale da pagare: 50 euro
                		</div>
                	</div>



                    
                </div>
            </div>


            <div class="col-md-4">
                <!-- Modal -->
                <div id="modalOffer" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div id="modalContent" class="modal-content">
                            
                        </div>

                    </div>
                </div>

                <div class="it_detail_action">
                    <div class="it_detail_button it_detail_complete">
                            <i class="fa fa-check"></i>
                            completa
                    </div>

                    <div class="it_detail_button it_detail_delete">
                            <i class="fa fa-times"></i>
                            cancella
                    </div>

                </div>
                
                <article class="component component-text">
                    <div class="details details_poi_itinerary" style="padding-left: 10px; padding-right: 10px;">
                        <div class="paragrafo">
                            <c:choose>
                                <c:when test="${not empty pois}">
                                    <div class="poi_container">


                                        <c:forEach var="poi" items="${pois}">
                                            <div class="poi" >
                                                <div class="img_poi">

	                                                <img class="rnd_it_detail" src="./dist/img/default_avatar.png" /> 
                                                </div>
                                                
                                               
                                                
                                                <div class="info_container">
                                                	<div class="poi_name_container">${poi.name}</div>
                                                	<div class="icons">
                                                		<i class="fa fa-info-circle info" style="font-size:14px; color:#2980B9;"></i>
                                                            <i class="fa fa-heart" style="font-size:14px; color:#ED5565"></i>
                                                            <i class="fa fa-credit-card" style="font-size:14px;"></i>
                                                            <i class="fa fa-times delPoi" style="font-size:14px; color:#ED5565;"></i>

                                                	</div>
                                                	<div class="select_off">Seleziona Offerte</div>
                                                </div>    
	                                    </div>
                                            
                                            
                                        </c:forEach>

                                    </div>
                                </c:when>
                                <c:otherwise>
                                    Non hai aggiunto nessun poi all'itinerario
                                </c:otherwise>
                            </c:choose>
                            
                        </div>    
                    </div>
                </article>
            </div>

            <div class="col-md-8">
                <!-- aggiungere controllo che visualizza una scritta se nn ci sono preferiti -->
                <jsp:include page="components/mapItineraryComponent.jsp"/>

            </div>

            <div class="col-xs-12" id="footer">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>

        </div>


        <jsp:include page="access/loginModal.jsp" />
        <script src="./dist/ion-range/js/ion.rangeSlider.js"></script>
        
        <script>
            $("#complit").click(function () {
                $.ajax({
                        
                    type: "GET",
                    url: "./completeItinerary",
                    data: "idItinerary="+ ${id},

                    success: function(){
                        alert("itinerario completato!");
                        window.location="./myItinerary";
                    }
                });
            });
            
            $("#delit").click(function () {
                $.ajax({    
                    type: "GET",
                    url: "./removeItinerary",
                    data: "idItinerary="+ ${id},

                    success: function(){
                        alert("itinerario cancellato!");
                        window.location="./myItinerary";
                    }
                });
            });
            
            $(".delPoi").each(function (index) {
                var sel = $(this);
               
                $(this).on("click", function () {
                    var idPoi = sel.parent().parent().parent().parent().attr("idPoi");
                    
                    $.ajax({
                        type: "GET",
                        url: "./removePoiItinerary",
                        data: "idItinerary=" + ${id} + "&idPoi=" + idPoi,
                        success: function () {
                            alert("Il poi è stato cancellato dall'itinerario");
                            window.location="./myItineraryDetail?idItinerary=" + ${id};
                        }
                   });
                });
            });
            
            
            $(".selPoiOffer").each(function (index) {
                var sel = $(this);
               
                $(this).on("click", function () {
                     var parents = sel.parents();
                     var idPoi = sel.parent().parent().parent().attr("idPoi");
                     var idItinerary = sel.parent().parent().parent().attr("idItinerary");
                     
                     $.ajax({
                        type: "GET",
                        url: "./viewOfferPoi",
                        data: "idPoi=" + idPoi + "&idItinerary=" + idItinerary,
                        success: function (data) {
                             $("#modalContent").html(data);
                             $("#modalOffer").modal('show');
                        }
                    });
                });
            });
            
            
        </script>

    </body>
</html>
