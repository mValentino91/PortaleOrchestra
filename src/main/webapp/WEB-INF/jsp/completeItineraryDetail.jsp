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
        <script src="./dist/js/qrcode.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--CSS-->
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>
        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 
        <link href="./dist/css/itinerary.css" rel="stylesheet">
        <link href="./dist/fontawesome/font-awesome.min.css" rel="stylesheet">
        
        
        
        <title>${Itinerary.name}</title>   
        
    </head>
    
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <div class="cover_itinerary_detail">
                    <div class="cover_itd cover_itd_sx">
                        <div class="itinerary_det">
                            <div class="img_poi_header">
                                <c:set var="itn" value="${itinerary.name}"/>
                                <c:set var="it_name" value="${fn:substring(itn, 0, 2)}" />
                                <c:set var="tx" value="${fn:toUpperCase(it_name)}" />
                                <div class="rnd" style="background-color:${itinerary.color};">
                                    <span class="rnd-text">${tx}</span>
                                </div> 
                            </div>
                            <div class="it_det_info">${itinerary.name}</div>
                            <div class="it_det_info it_det_date">Creato il: <fmt:formatDate value="${itinerary.dateCreation}" pattern="dd/MM/yyyy HH:mm"/></div>
                        </div>
                    </div>
                    
                    <div class="cover_itd cover_itd_dx">
                        <div class="price_detail">
                            
                        </div>
                    </div>



                    
                </div>
            </div>


            <div class="col-md-7">
              
                
                <article class="component component-text">
                    <div class="details details_poi_offers" style="padding-left: 10px; padding-right: 10px;">

                        <div class="paragrafo">
                    
                            <c:choose>
                                <c:when test="${not empty map_poi.keySet()}">
                                    <div class="poi_container">
                                        
                                        <c:forEach var="poi" items="${map_poi.keySet()}">
                                            <div class="poi_off">
                                                <div class="img_poi">
                                                    <img class="rnd_it_detail" src="./dist/poi/img/${poi}/cover.jpg" /> 
                                                </div>
                                                
                                                
                                                <div class="info_container" style="margin-bottom:5px; border:1px solid #cdcdcd">
                                                    <div class="poi_name_container">${map_poi.get(poi).name}</div>
                                                    <div class="poi_info" style=" margin-top:10px; margin-bottom:10px">
                                                        
                                                        
                                                        <div class="poi_info poi_info_address">
                                                            <i class="fa-ov fa fa-location-arrow"></i>
                                                            <div class="addressContainer" style="float: left;">${map_poi.get(poi).address}</div>
                                                            <div class="clear"></div>
                                                        </div>
                                                        
                                                        <c:if test="${not empty poi_tel.get(poi)}">
                                                            <div class="poi_info poi_info_tel">
                                                                <i class="fa-ov fa fa-phone"></i>
                                                                <div class="phoneContainer" style="float: left;">
                                                                    <c:forEach var="tel" items="${poi_tel.get(poi)}">
                                                                        <div>${tel}</div>
                                                                    </c:forEach>
                                                                </div>
                                                                <div class="clear"></div>
                                                            </div>  
                                                        </c:if>
                                                        
                                                        <c:if test="${not empty poi_mail.get(poi)}">
                                                            <div class="poi_info poi_info_mail">
                                                                <i class="fa-ov fa fa-envelope-o"></i>
                                                                <div class="mailContainer" style="float: left;">
                                                                    <c:forEach var="mail" items="${poi_mail.get(poi)}">
                                                                        <div>${mail}</div>
                                                                    </c:forEach>
                                                                </div>
                                                                <div class="clear"></div>
                                                            </div>
                                                        </c:if>
                                                    </div>
                                                            
                                                    <c:if test="${not empty map_cardChoice.get(poi) || not empty map_stockChoice.get(poi)}" >       
                                                        Hai acquistato le seguenti offerte:
                                                        <div class="poi_info_off">
                                                            <ul>
                                                                <c:forEach var="card" items="${map_cardChoice.get(poi)}">
                                                                    <c:set var="x" value="${card.idOffer}"/>
                                                                    <li>
                                                                        <div class="poi_off_container">
                                                                            <div class="offer_name">${map_dealerOffer.get(x).nome}</div>
                                                                            <div class="offer_description">${map_dealerOffer.get(x).desc}</div>

                                                                            <div class="offer_info_detail">
                                                                                <div class="offer_date_end">Scedenza: <fmt:formatDate value="${map_dealerOffer.get(x).dateEnd}" pattern="dd/MM/yyyy HH:mm"/></div>
                                                                                <div class="offer_qta">Numero: ${card.qta}</div>
                                                                                <div class="offer_price">Totale: €${card.sum}</div>
                                                                            </div>
                                                                            <div class="clear"></div>
                                                                        </div>
                                                                    </li>
                                                                </c:forEach>
                                                                    
                                                                <c:forEach var="stock" items="${map_stockChoice.get(poi)}">
                                                                    <li>
                                                                        <div class="poi_off_container">
                                                                            <div class="offer_name">${stock.stockType} </div>
                                                                            <div class="offer_description">${stock.description}</div>

                                                                            <div class="offer_info_detail">
                                                                                <div class="offer_qta_stock">Numero: ${stock.qta}</div>
                                                                                <div class="offer_price_stock">Totale: €${stock.price}</div>
                                                                            </div>
                                                                            <div class="clear"></div>
                                                                        </div>
                                                                    </li>
                                                                </c:forEach>    
                                                            </ul>
                                                        </div>
                                                    </c:if>
                                                </div>   
                                                <div class="clear"></div> 
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

            <div class="col-md-5">
                <div class="qr-code-box">
                    <div class="qr-code">
                        
                        <div id="qrcode" style="margin-left: 80px; width:210px; height:210px;"></div>
                        
                        <script>
                            var qrcode = new QRCode(document.getElementById("qrcode"), {
                                width: 210,
                                height: 210
                            });
                            function makeCode() {
                                var code ="${itinerary.keyString}";
                                qrcode.makeCode(code);
                            }
                            makeCode();

                        </script>
                    </div>
                </div>
                
                <jsp:include page="components/mapCompleteItineraryComponent.jsp"/>

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
