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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <!--SCRIPT-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="./dist/googlePlusDesign/js/bootstrap.min.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--CSS-->
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>
        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 
        <link href="./dist/css/itinerary.css" rel="stylesheet">
        
        
        <title>${Itinerary.name}</title>   
        
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip(); 
                
                $("#complit").click(function () {
                    
                    $.ajax({

                        type: "GET",
                        url: "./completeItinerary",
                        data: "idItinerary="+ ${itinerary.idItinerary},

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
                        data: "idItinerary="+ ${itinerary.idItinerary},

                        success: function(){
                            alert("itinerario cancellato!");
                            window.location="./myItinerary";
                        }
                    });
                });

                $(".delPoi").each(function (index) {
                    var sel = $(this);
                    

                    $(this).on("click", function () {
                        var idPoi = sel.parent().parent().parent().attr("idPoi");
                        
                        $.ajax({
                            type: "GET",
                            url: "./removePoiItinerary",
                            data: "idItinerary=" + ${itinerary.idItinerary} + "&idPoi=" + idPoi,
                            success: function () {
                                alert("Il poi è stato cancellato dall'itinerario");
                                window.location="./myItineraryDetail?idItinerary=" + ${itinerary.idItinerary};
                            }
                       });
                       
                    });
                });


                $(".select_off").each(function (index) {
                    var sel = $(this);

                    $(this).on("click", function () {
                         var parents = sel.parents();
                         
                         var idPoi = sel.parent().parent().attr("idPoi");
                         var idItinerary = sel.parent().parent().attr("idItinerary");
                         
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
            });
        </script>
        
        
    </head>
    
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <div class="cover_itinerary_detail" style="background: url(./dist/img/skyline.png); background-color:${itinerary.color};">
                	<div class="cover_itd cover_itd_sx">
                            
                	</div>
                	
                	<div class="cover_itd cover_itd_dx">
                		<div class="price_detail">
                		</div>
                	</div>



                    
                </div>
            </div>


            <div class="col-md-4">
                 <!-- Modal -->
                <div id="modalOffer" class="modal fade" role="dialog">
                    <div class="modal-dialog modal-lg">

                        <!-- Modal content-->
                        <div id="modalContent" class="modal-content">
                            
                        </div>

                    </div>
                </div>
                <article class="component component-text">
                    <div class="details">
                        <div class="it_det_info">${itinerary.name}</div>
                        <div class="it_det_info it_det_date">Creato il: <fmt:formatDate value="${itinerary.dateCreation}" pattern="dd/MM/yyyy HH:mm"/></div>
                    </div>
                </article>     
                <div class="it_detail_action">
                    <div id="complit" class="it_detail_button it_detail_complete">
                            <i class="fa fa-check"></i>
                            completa
                    </div>

                    <div id="delit" class="it_detail_button it_detail_delete">
                            <i class="fa fa-times"></i>
                            elimina
                    </div>

                </div>
                
                <article class="component component-text">
                    <div class="details details_poi_itinerary" style="padding-left: 10px; padding-right: 10px;">
                        <div class="paragrafo">
                            <c:choose>
                                <c:when test="${not empty pois}">
                                    <div class="poi_container">


                                        <c:forEach var="poi" items="${pois}">
                                            <div class="poi" idPoi="${poi.id}" idItinerary="${itinerary.idItinerary}">
                                                <div class="img_poi">

	                                                <img class="rnd_it_detail" src="./dist/poi/img/${poi.id}/cover.jpg" /> 
                                                </div>
                                                
                                               
                                                
                                                <div class="info_container">
                                                	<div class="poi_name_container">${poi.name}</div>
                                                	<div class="icons">
                                                            <a href="./getPoi?id=${poi.id}" target="_blank"><i class="fa fa-info-circle info" style="font-size:14px; color:#2980B9;"></i></a>
                                                            <c:set var="rat_fav" value="${fav_poi.get(poi.id)}"/>
                                                            <c:if test="${rat_fav > 0}">
                                                                <i class="fa fa-heart" style="font-size:14px; color:#ED5565" data-toggle="tooltip" data-original-title="E'tra i tuoi preferiti!"></i>
                                                            </c:if>
                                                            <c:set var="noff" value="${off_card.get(poi.id)}"/>
                                                            <c:if test="${noff > 0}">
                                                                <i class="fa fa-credit-card" style="font-size:14px;" data-toggle="tooltip" data-original-title="Orchestra Card"></i>
                                                            </c:if>
                                                            <i class="fa fa-times delPoi" style="font-size:14px; color:#ED5565;"></i>
                                                            
                                                        </div>
                                                        <span id="sum_off_${poi.id}" class="sum_off">Hai aggiunto ${poi_offc.get(poi.id)} offerte</span>
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
    </body>
</html>
