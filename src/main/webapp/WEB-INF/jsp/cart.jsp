<%-- 
    Document   : FavoriteListComponent
    Created on : 19-apr-2015, 18.50.47
    Author     : andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
        <script src="./dist/js/cart.js"></script>
        <link rel="stylesheet" href="./dist/css/cart.css" />

        <title><spring:message code="label.favoritespoi"></spring:message></title>   

        <style>
            .poi_preview_box {
                height:150px;
            }
            
            .fa-shopping-cart{
                cursor:pointer;
            }
        </style>
        </head>

        <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-xs-12">
                <jsp:include page="components/CoverComponentFavorites.jsp"/>
            </div>

            <div class="col-xs-8">
                
                <c:forEach var="poi" items="${detailOffer.keySet()}">
                    <article class="component component-text">
                        <div class="details">

                            <!-- implementare il foreach dei preferiti per categorie-->
                            <div class="paragrafo">
                                <div class="favorite_container" style="min-height: 200px;">



                                    <div class="category_container">

                                        <span class="category_label category_title" >${poi.name}</span>

                                        <div class="poi">
                                            <c:if test="${empty detailOffer.get(poi)}">
                                                <div class="no_favorites_result">
                                                    nessuna offerta disponibile
                                                </div>
                                            </c:if>
                                            <c:forEach var="offer" items="${detailOffer.get(poi)}">
                                                <div class="poi_preview_box">
                                                    <input class="idoffer" type="hidden" value="${offer.idOffer}"/>
                                                    <input name="idpoi" type="hidden" value="${poi.id}"/>
                                                    
                                                    <div class="poi_preview_content">
                                                        <div class="poi_preview_title"><strong>${offer.nome}</strong></div>
                                                        <div class="poi_preview_title">${offer.desc}</div>
                                                        <div class="poi_preview_text">
                                                            Quantita:
                                                            <select id="quantity">
                                                                <option value="1">1</option>
                                                                <option value="2">2</option>
                                                                <option value="3">3</option>
                                                                <option value="4">4</option>
                                                            </select>
                                                            <i class="fa fa-credit-card" style="font-size:16px;" data-toggle="tooltip" data-original-title="Orchestra Card"></i>
                                                            <i class="fa fa-shopping-cart add-itinerary" offer="${offer.idOffer}" poi="${poi.id}" style="color:red; font-size:16px;" data-toggle="tooltip" data-original-title="Aggiungi all'itinerario"></i>
                                                            <div>Prezzo intero: ${offer.fullPrice}</div>
                                                            <div>Prezzo scontato: ${offer.discountedPrice}</div>
                                                            <div>Percentuale sconto: ${offer.rateDiscount}%</div>
                                                            
                                                            
                                                        </div>
                                                        
                                                    </div>

                                                </div>
                                                    <hr/>
                                                <div class="clear"></div>
                                                
                                            </c:forEach>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </article>
                </c:forEach>
                <script>
                    
                    $(function () {
                        $('.fa-shopping-cart').tooltip();
                        $('.fa-credit-card').tooltip();
                    });
                    
                    $(".add-itinerary").click(function(){
                         var idofferta= $(this).attr("offer");
                         var idpoi = $(this).attr("poi");
                         var qta = $( "#quantity option:selected" ).val();
                         alert(qta);
                         $.ajax({
                            type: "GET",
                            url: "./saveOffer",
                            data: "id_offer=" + idofferta + "&id_poi=" + idpoi+"&qta="+qta,
                            success: function(data) {
                                alert("Offerta aggiunta correttamente");
                            }
                         });
                         
                         
                    });
                </script>


            </div>
            <div class="col-xs-4">
                <article class="component component-text">
                    <div class="details">
                        <div class="big-header contact" id="crea_it" style="background-color:#285E8E; cursor: pointer;">
                            <a href="./saveInCard" style="color:#fff; text-decoration: none">Completa l'Itinerario</a>
                        </div>
                    </div>
            </div>
        </div>
    </body>
</html>
