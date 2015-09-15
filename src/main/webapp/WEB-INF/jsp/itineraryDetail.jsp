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
        <link href="./dist/css/composite.css" rel="stylesheet">
        <script src="./dist/js/section.js"></script>
        <script src="./dist/js/composite.js"></script>
        <script src="./dist/googlePlusDesign/js/bootstrap.min.js"></script>
        <script src="./dist/js/readmore.js"></script>
        <script src="./dist/js/favorite_ajax.js"></script>
        <script src="./dist/js/favorite.js"></script>
        <link rel="stylesheet" href="./dist/ion-range/css/normalize.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.skinFlat.css" />
        <link rel="stylesheet" href="./dist/css/favorites.css" />

        <title><spring:message code="label.favoritespoi"></spring:message></title>   
            <style>
                .new_it_button{
                    user-drag: none; 
                    -moz-user-select: none;
                    -webkit-user-drag: none;
                    background-color:#285E8E;
                    color:#fff;
                    height: 50px;
                    text-align: center;
                    vertical-align: middle;
                    line-height: 50px; 
                    font-weight: bold;
                    cursor: pointer;
                    margin-bottom: 20px;

                }

                .poi_it_container{
                    width: 100%;
                    -moz-box-sizing: border-box;
                    -webkit-box-sizing: border-box;
                    box-sizing: border-box;  
                    clear: both;
                }

                .poi_it_img{
                    width: 60px;
                    -moz-box-sizing: border-box;
                    -webkit-box-sizing: border-box;
                    box-sizing: border-box;  
                    float:left;
                }

                .poi_it_name_container{
                    display: table;
                    border:0px solid green;
                    width: 180px;
                    height: 60px;
                    float: left;
                    -moz-box-sizing: border-box;
                    -webkit-box-sizing: border-box;
                    box-sizing: border-box;				
                    border: 0px solid red;
                }

                .poi_it_name{
                    display: table-cell;
                    overflow: hidden;
                    vertical-align: middle;
                    border: 0px solid red;
                }

                .poi_it_status{
                    display: table-cell;
                    overflow: hidden;
                    vertical-align: middle;
                    border: 0px solid red;
                    color: green;
                    font-weight: bold;
                }
                


            </style>

        </head>
        <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <div class="cover_favorite_img" style="background-color: #285E8E;">
                    <div class="poi_it_img" style="line-height: 200px; margin-left: 100px;">
                                            <img src="./dist/poi/img/${poi.id}/cover.jpg" style="width:30px; height:30px; border-radius: 50%; margin-top:5px;"/> 
                                        </div>
                    <span style="line-height: 200px; margin-right: 500px; color:#fff; border: 1px solid #fff;">Nome itinerario</span>
                    <span style="color:#fff; border: 1px solid #fff;">Paga 50€</span>
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

                <article class="component component-text">
                    <div class="details">
                        <div class="paragrafo">
                            <div>
                                <span id="complit"><i class="fa fa-check" style="font-size:14px;"></i> Completa itinerario</span>
                            </div>
                            
                            <div>
                                <span id="delit"><i class="fa fa-times" style="font-size:14px;"></i> Cancella itinerario</span>
                            </div>
                            
                        </div>
                    </div>
                </article>
                <article class="component component-text">
                    <div class="details">
                        <div class="paragrafo">
                            <c:choose>
                                <c:when test="${not empty pois}">
                                    <div class="itinerary-container">


                                        <c:forEach var="poi" items="${pois}">
                                            <div class="poi_it_container" idPoi="${poi.id}" idItinerary="${id}" style="border-bottom:1px solid #cdcdcd;">
                                                <div class="poi_it_img">
                                                    <%-- src="./dist/poi/img/${poi.id}/cover.jpg" --%>
                                                    <img src="./dist/img/default_avatar.png" style="width:30px; height:30px; border-radius: 50%; margin-top:5px;"/> 
                                                </div>

                                                <div class="poi_it_name_container" >
                                                    <div class="poi_it_name">
                                                        ${poi.name}       
                                                        <div class="poiIcons" style="display:inline-block">
                                                            <i class="fa fa-info-circle info" style="font-size:14px; color:#2980B9;"></i>
                                                            <i class="fa fa-heart" style="font-size:14px; color:#ED5565"></i>
                                                            <i class="fa fa-credit-card" style="font-size:14px;"></i>
                                                            <i class="fa fa-times delPoi" style="font-size:14px; color:#ED5565;"></i>
                                                        </div>
                                                        <div class="selPoiOffer">Seleziona Offerte</div>
                                                    </div>


                                                </div>
                                            </div>
                                        </c:forEach>



                                        <div class="clear"></div>    
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
