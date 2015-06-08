                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        <%-- 
    Document   : cardDetail
    Created on : 27-mag-2015, 17.18.35
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
        <script src="./dist/js/qrcode.js"></script>
        <script src="./dist/js/jquery.js"></script>
        <link rel="stylesheet" href="./dist/css/cart.css" />

        <title></title>   

    </head>

    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-xs-12">
                <jsp:include page="components/CoverComponentFavorites.jsp"/>
            </div>

            <div class="col-xs-8">

                <article class="component component-text">
                    <div class="details" id="detItinerary" id_iti="${id_iti}" id_card="${idcard}">
                        <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");%>
                        Riepilogo itinerario creato in data: <%= df.format(new java.util.Date())%>
                        <br>
                        Offerte inserite nella card
                        <div id="user" firstName="${user.firstName}" lastName="${user.lastName}">
                            <h1>${user.firstName} ${user.lastName}</h1>
                        </div>
                        <c:forEach var="poi" items="${elenco_poi}">
                            <strong>${poi.name}</strong>

                            <c:forEach var="offer" items="${offerte}">
                                <br>
                                <div>${offer.desc}</div> 
                                <div>Prezzo intero: ${offer.fullPrice}</div> 
                                <div>Prezzo scontato: ${offer.discountedPrice}</div>
                            </c:forEach>
                            <hr>
                        </c:forEach>
                    </div>
                </article>
            </div>
            <div class="col-xs-4">
                <article class="component component-text">
                    <div class="details">
                        <div id="qrcode"></div>

                        <script>

                            var qrcode = new QRCode(document.getElementById("qrcode"), {
                                width: 260,
                                height: 260
                            });
                            function makeCode() {
                                var id_iti=$("#detItinerary").attr("id_iti");
                                var id_card=$("#detItinerary").attr("id_card");
                                var code =id_iti+"-"+id_card;
                                qrcode.makeCode(code);
                            }

                            makeCode();




                        </script>
                    </div>
                </article>
                <div class="big-header contact" id="crea_it" style="background-color:#285E8E; cursor: pointer;">
                    <a href="#" style="color:#fff; text-decoration: none">Stampa</a>
                </div>
                
                
            </div>
        </div>
        <!--

        -->
    </body>
</html>
