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
        <style>
            .poi_offer_filler{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 10%; 
                //border: 1px solid yellow; 
                float: left;
                text-align: right;
                padding: 10px;

            }
            
            .offers_container{
                //border: 1px solid yellow; 
                margin-top: 10px; 
                margin-bottom: 10px;
            }
            
            .offer_container{
                margin-bottom:5px; 
                //border: 1px solid brown;
            }
        </style>
        
        <script>
            $(document).ready(function () {
                $("#print").click(function(){
                    window.print();
                });
            });
        </script>
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
                        
                        <div id="user" firstName="${user.firstName}" lastName="${user.lastName}">
                            <h1>Itinerario di ${user.firstName} ${user.lastName}</h1>
                        </div>
                        
                        
                        <ul>
                            <c:forEach var="idpoi" items="${offerte_id}">
                                <li><strong>${map_poi.get(idpoi).name}</strong></li>
                               
                                <div class="offers_container">
                                    <c:if test="${not empty map_off.get(idpoi)}">

                                        <div class="offer_container" >
                                            <div style="margin-left: 10%;">Offerte Orchestra PassSpass</div>
                                            <c:forEach var="offer" items="${map_off.get(idpoi)}">

                                                <div style="margin-left:10%;border: 1px solid #bdbdbd; padding: 3px; margin-bottom: 5px;">

                                                    <div><strong>Nome offerta:</strong> ${offer.nome}</div>
                                                    <div><strong>Descrizione: </strong>${offer.desc}</div>
                                                    <c:if test="${offer.fullPrice!=0}">
                                                        <div style="text-decoration:line-through">Prezzo intero: ${offer.fullPrice}€</div>
                                                        <div style="display:inline-block"><strong>Prezzo scontato:</strong> ${offer.discountedPrice}€</div>
                                                        <c:forEach var="det" items="${map_off_det.get(offer.idOffer)}">
                                                            <div><strong>Quantità acquistata:</strong> ${det.qta}</div> 
                                                            <div><strong>Totale acquistato:</strong> ${det.sum}</div>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>

                                            </c:forEach>
                                        </div>
                                    </c:if>


                                    <c:if test="${not empty map_comp.get(idpoi)}">
                                        <div class="offer_container">
                                            <div style="margin-left: 10%;">Altre offerte</div>

                                            <c:forEach var="stock" items="${map_comp.get(idpoi)}">

                                                <div style="margin-left:10%;border: 1px solid #bdbdbd; padding: 3px; margin-bottom: 5px;">

                                                    <div><strong>Nome offerta:</strong> ${stock.get("type")}</div>
                                                    <div><strong>Descrizione: </strong>${stock.get("desc")}</div>
                                                    <div><strong>Prezzo intero:</strong> ${stock.get("price")}€</div>
                                                    <div><strong>Quantità acquistata:</strong> ${stock.get("qta")}</div>
                                                    <div><strong>Totale acquistato:</strong> ${stock.get("sum")}€</div>
                                                </div>





                                                <%--
                                                <div style="margin-left:10%;border: 1px solid black; padding: 3px; margin-bottom: 5px;">


                                                    <c:if test="${not empty stock.type}">
                                                        <div><strong>Nome offerta:</strong> ${stock.type}</div>
                                                    </c:if>

                                                    <c:if test="${not empty stock.type_description}">
                                                        <div><strong>Descrizione: </strong>${stock.type_description}</div>
                                                    </c:if>
                                                    <div style="display:inline-block"><strong>Prezzo intero:</strong> ${stock.price}€</div>

                                                </div>
                                                --%>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </div>
                                <hr>
                                
                            </c:forEach>
                                
                        </ul>
                    </div>
                </article>
            </div>
            <div class="col-xs-4">
                <%--
                <article class="component component-text">
                    <div class="details">
                        

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
                --%>
                <div class="big-header contact" id="print" style="background-color:#285E8E; cursor: pointer;">
                    <a href="#" style="color:#fff; text-decoration: none">Stampa</a>
                </div>
                
                
            </div>
        </div>
        <!--

        -->
    </body>
</html>
