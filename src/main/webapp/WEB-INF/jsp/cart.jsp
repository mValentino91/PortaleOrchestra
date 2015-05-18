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
                                                    <div class="poi_preview_content">

                                                        <div class="poi_preview_text">
                                                            <i class="fa fa-heart"  style="cursor:pointer; font-size:16px; color: #ED5565" data-toggle="tooltip" data-original-title="Lo hai aggiunto ai preferiti"></i>
                                                            <a href="#" target="_blank"><i class="fa fa-info-circle info" style="cursor:pointer; color: #2980B9; font-size:16px;" data-toggle="tooltip" data-original-title="Maggiori informazioni"></i></a>
                                                            <i class="fa fa-credit-card" style="font-size:16px;" data-toggle="tooltip" data-original-title="Orchestra Card"></i>
                                                            <i class="fa fa-cart-plus" style="font-size:16px;"></i>
                                                            <i class="fa fa-plus" style="font-size:16px;"></i>
                                                            <i class="fa fa-shopping-cart" style="font-size:16px;"></i>
                                                        </div>
                                                        <div class="poi_preview_title">${offer.desc}</div>
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
                        $('.fa-heart').tooltip();
                        $('.fa-info-circle').tooltip();
                        $('.fa-credit-card').tooltip();
                    });
                </script>
                <!--          
        
                        <article class="component component-text">
        
                            <div class="details">
                <!-- implementare il foreach dei preferiti per categorie
                <div class="paragrafo">
                    <div class="favorite-container" style="min-height: 200px;">

                        <div class="category-container">
                <c:forEach var="poi" items="${detailOffer.keySet()}">
                    <div>
                        <span class="category-label">${poi.name}</span>
                    </div>

                    <c:forEach var="offer" items="${detailOffer.get(poi)}">
                        <div class="poi_preview_box">

                            <div class="poi_preview_content">
                                <div class="poi_preview_title">${offer.desc}</div>
                                <div class="poi_preview_text">
                                    <i class="fa fa-heart"  style="cursor:pointer; font-size:16px; color: #ED5565" ></i>
                                    <i class="fa fa-trash"  style="cursor:pointer; font-size:16px;" ></i>
                                </div>

                            </div>
                            <div class="clear"></div>     
                        </div>
                        <p></p>
                    </c:forEach>
                </c:forEach>
            </div>
            </article>

            <article class="component component-text">

                <div class="details">
                <!-- implementare il foreach dei preferiti per categorie
                <div class="paragrafo">
                    <div class="favorite-container" style="min-height: 200px;">

                        <div class="category-container">
                            <div>
                                <span class="category-label">Cappella San Severo</span>
                            </div>
                            <div class="poi">



                                <div class="poi_preview_box">

                                    <div class="poi_preview_content">
                                        <div class="poi_preview_title">
                                            Prezzo Intero Adulti

                                        </div>
                                        <div class="poi_preview_text">
                                            <i class="fa fa-heart"  style="cursor:pointer; font-size:16px; color: #ED5565" ></i>
                                            <i class="fa fa-trash"  style="cursor:pointer; font-size:16px;" ></i>

                                        </div>

                                    </div>
                                    <div class="clear"></div>     
                                </div>


                                <div class="poi_preview_box">
                                    <div class="poi_preview_content">
                                        <div class="poi_preview_title">
                                            Prezzo intero bambini (0-8 anni)

                                        </div>
                                        <div class="poi_preview_text">
                                            <i class="fa fa-heart"  style="cursor:pointer; font-size:16px; color: #ED5565" ></i>
                                            <i class="fa fa-trash"  style="cursor:pointer; font-size:16px;" ></i>

                                        </div>

                                    </div>
                                    <div class="clear"></div>     
                                </div>
                            </div>


                        </div>


                    </div>
                </div>
        </article>
      
        <article class="component component-text">
            <div class="details">

                <!-- implementare il foreach dei preferiti per categorie
                <div class="paragrafo">
                    <div class="favorite-container" style="min-height: 200px;">

                        <div class="category-container">
                            <div>
                                <span class="category-label">Pio Monte Misericordia</span>
                            </div>
                            <div class="poi">



                                <div class="poi_preview_box">

                                    <div class="poi_preview_content">
                                        <div class="poi_preview_title">
                                            Prezzo Intero Adulti

                                        </div>
                                        <div class="poi_preview_text">
                                            <i class="fa fa-heart"  style="cursor:pointer; font-size:16px; color: #ED5565" ></i>
                                            <i class="fa fa-trash"  style="cursor:pointer; font-size:16px;" ></i>

                                        </div>

                                    </div>
                                    <div class="clear"></div>     
                                </div>


                                <div class="poi_preview_box">
                                    <div class="poi_preview_content">
                                        <div class="poi_preview_title">
                                            Prezzo intero bambini (0-8 anni)

                                        </div>
                                        <div class="poi_preview_text">
                                            <i class="fa fa-heart"  style="cursor:pointer; font-size:16px; color: #ED5565" ></i>
                                            <i class="fa fa-trash"  style="cursor:pointer; font-size:16px;" ></i>

                                        </div>

                                    </div>
                                    <div class="clear"></div>     
                                </div>
                            </div>


                        </div>

                    
                    </div>
                </div>
            </div>
        </article>
                -->

            </div>
            <div class="col-xs-4">
                <article class="component component-text">
                    <div class="details">
                        riepilogo<br>
                        crea l'itinerario
                    </div>
            </div>
        </div>
        <script>

            $(document).ready(function () {
                enableRatingBar();
                $(".poi_preview_box").each(function (index) {
                    enableDeleteButton($(this));


                });

            });

        </script>
    </body>
</html>
