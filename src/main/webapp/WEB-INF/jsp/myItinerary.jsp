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
                
                
                .delit{
                    display: table-cell;
                    overflow: hidden;
                    vertical-align: middle;
                    border: 0px solid red;
                    color: red;
                    font-weight: bold;
                    cursor: pointer;
                }
                
                .complit{
                    display: table-cell;
                    overflow: hidden;
                    vertical-align: middle;
                    border: 0px solid red;
                    color: green;
                    font-weight: bold;
                    cursor: pointer;
                }

                .modal-dialog {
                    width: 90%;
                }
                

            </style>
            
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
                        alert(content);

                        $.ajax({
                            type: "GET",
                            url: "./newItinerary",
                            data: "name=" + content
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


            <div class="col-md-4">
                <!-- Modal -->
                <div id="myModal" class="modal fade modal-lg" role="dialog">
                    <div class="modal-dialog modal-lg">

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
                    <div class="details">
                        <div class="paragrafo">

                            <b>I tuoi itinerari</b>
                            <div class="itinerary-container">
                                <c:choose>
                                    <c:when test="${not empty itinerary}">
                                        <c:forEach var="it" items="${itinerary}">
                                            <div class="poi_it_container">
                                                <div class="poi_it_img">
                                                    <img src="./dist/poi/img/${poiR.id}/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/> 
                                                </div>

                                                <div class="poi_it_name_container" idItinerary="${it.idItinerary}">
                                                    <div class="poi_it_name">
                                                        <a href="./myItineraryDetail?idItinerary=${it.idItinerary}">${it.name}</a>                                       
                                                    </div>

                                                    <c:choose>
                                                        <c:when test="${it.status == 0}">
                                                            <div class="poi_it_status">ATTIVO</div>
                                                            <div class="complit">V</div>
                                                            <div class="delit">X</div>     
                                                        </c:when>

                                                        <c:when test="${it.status == 1}">
                                                            <div class="poi_it_status">COMPLETO</div>
                                                            <div class="delit">X</div>     
                                                        </c:when>    

                                                        <c:when test="${it.status == 2}">
                                                            <div class="poi_it_status">SCADUTO</div>
                                                        </c:when>                                                        
                                                    </c:choose>

                                                    
                                                </div>
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

            <div class="col-md-8">
                <article class="component component-text">
                    <div class="details">
                        infografica
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