<%-- 
    Document   : carrello_preview
    Created on : 6-giu-2015, 11.26.34
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
        <script src="./dist/js/bootstrap.min.js"></script>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>


        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 


        <title>JSP Page</title>
        <style>
            .poi_preview_box{
                height: 70px; 
                width: 100%; 

                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;

                position: relative;

                //border: 1px solid #E9EAED;
            }  

            .poi_preview_img{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 10%; 
                float: left;
                background-size: cover;
                background-position: center center;
                border-radius: 0px;        

            }

            .poi_preview_content{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 35%; 

                float: left;
                padding: 10px;
                //border: 1px solid red; 
            }

            .poi_preview_content2{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 10%; 
                float: left;
                padding: 10px;

                //border: 1px solid red; 
            }


            .poi_preview_title{
                text-align: left!important;
                padding-top: 3px;
            }

            .poi_preview_delete{
                position: absolute;
                top: 0px;
                right: 0px;
            }

            .poi_icons{
                padding-top:5px;
            }

            .poi_price_total{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 

                //border: 1px solid yellow; 
                float: left;
                text-align: right;
                padding: 10px;
                padding-right: 0px;
                width: 45%;
            }

            .poi_prices{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 30%; 
                //border: 1px solid yellow; 
                float: left;
                //text-align: right;
                padding-top: 5px;
                padding-left: 5px;

            }



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

            .poi_offer{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 100%; 
                //border: 1px solid yellow; 
                float: left;
                text-align: left;
                margin-top: 10px;
                margin-bottom: 10px;


            }

            .poi_offer_detail{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 100%; 
                float: left;
                text-align: left;
                

                //border: 1px solid brown; 
            }

            .card_offers_section{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 100%; 
                float: left;
                text-align: left;
                
                padding-top: 5px;
                padding-bottom: 5px;

                //border: 1px solid yellow; 
            }

            .card_offers_section_info{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width:45%;
                float: left;
                text-align: left;
                padding: 10px;
                padding-top: 0px;
                font-weight: bold;
                text-decoration: underline;
                //border-bottom: 1px solid #bdbdbd;


            }

            .poi_offer_detail_info{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width:35%;
                float: left;
                text-align: left;
                padding: 10px;
                padding-top: 0px;
                //border: 1px solid #285E8E;


            }

            .off_name{
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
                font-weight: bold;
            }

            .off_desc{
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }

            .poi_offer_detail_quantity{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width:25%;
                float: left;
                text-align: left;
                padding: 10px;
                padding-top: 0px;
                //border: 1px solid #285E8E;
                //border-bottom: 1px solid #bdbdbd;
            }

            .poi_offer_detail_price{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width:30%;
                float: left;
                text-align: right;
                padding: 10px;
                padding-bottom: 10px;
                padding-top: 1px;
                //border:none;
                //border: 1px solid #285E8E;
                //border-bottom: 1px solid #bdbdbd;
            }

            .poi_offer_detail_rate{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width:30%;
                float: left;
                text-align: right;
                padding: 0px;
                padding-top: 3px;
                //border:none;
                //border: 1px solid #285E8E;
            }

            .seleziona{
                user-drag: none; 
                -moz-user-select: none;
                -webkit-user-drag: none;
                display: inline-block;
                background-color: #8ABBE4;
                color: #fff;
                padding-left: 5px;
                padding-right: 5px;
                cursor: pointer;
            }

            .clear{
                clear:both;
            }

            .category_label{
                font-weight: bold;
                cursor: pointer;

                //border: 1px solid black;
                //color: #fff;
                //background-color: blue;
            }

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

            }

            .control{
                cursor: pointer;
            }

            .fullPrice, .fullPrice_stock,.discPrice, .rateOffer{
                width:50px;
                text-align: right;
                display: inline-block;
            }

            .rateOffer{
                font-weight: bold;
                color: red;
            }

            .discPrice{
                font-weight: bold;
            }

            .fullPrice{
                text-decoration: line-through;

            }
            
            .valuta{
                width: 1px;
                display: inline-block;
            }
            
            
            .list_poi_element{
                clear: both;
                margin: 0px;
                position: relative;
                height: 35px;
                cursor: pointer;
            }	
            
            .add_offer{
                user-drag: none; 
                -moz-user-select: none;
                -webkit-user-drag: none;
                display: inline-block;
                background-color: #1e88e5;
                color: #fff;
                padding: 3px;
                cursor: pointer;
            }
            
            .del_offer{
                user-drag: none; 
                -moz-user-select: none;
                -webkit-user-drag: none;
                display: inline-block;
                background-color: #ef5350;
                color: #fff;
                padding: 3px;
                cursor: pointer;
            }

            


        </style>    


        <script>
        <%--   
            window.onbeforeunload = function(e) {
                return 'Dialog text here.';
              };
        --%>        
            $(document).ready(function () {
                console.log("*************************");
                $(".del_offer").each(function (index) {

                    var sel = $(this);
                    $(this).on("click", function () {

                        var type = sel.parent().parent().attr("type");
                        var idPoi = sel.parent().parent().attr("idPoi");
                        var idOffer = sel.parent().parent().attr("idOffer");
                        
                        $.ajax({
                            type: "GET",
                            url: "./deleteOffer",
                            data: "id_offer=" + idOffer + "&id_poi=" + idPoi + "&type=" + type,
                            success: function (data) {
                                alert(data);
                            }
                        });
                      
                    });
                });
                
                
                $(".add_offer").each(function (index) {

                    var sel = $(this);
                    $(this).on("click", function () {

                        var qta = sel.parent().parent().find(".qta");
                        var off_name = sel.siblings(".off_name").text();
                        var off_desc = sel.siblings(".off_desc").text();

                        var type = sel.parent().parent().attr("type");
                        var fullPrice = sel.parent().parent().attr("fullPrice");
                        var discPrice = sel.parent().parent().attr("discPrice");

                        var idPoi = sel.parent().parent().attr("idPoi");
                        var idOffer = sel.parent().parent().attr("idOffer");

                        var rateOffer = sel.parent().parent().find(".rateOffer").text();




                        if (type == "CARD") {
                            var sum = discPrice;
                        }
                        else {
                            var sum = fullPrice;
                        }
                        
                        $.ajax({
                            type: "GET",
                            url: "./saveOffer",
                            data: "id_offer=" + idOffer + "&id_poi=" + idPoi + "&qta=" + qta.val() + "&sum=" + sum + "&type=" + type,
                            success: function () {
                                alert("Offerta inserita nella card");
                            }
                        });

                    });
                });



                $(".seleziona").each(function (index) {
                    var sel = $(this);
                    $(this).on("click", function () {
                        slideOfferPoi(sel);

                    });
                });

                $(".control").each(function (index) {
                    var sel = $(this);
                    var operator = sel.prop("id");

                    //var qta = sel.parent().find(".qta");

                    var off_name = sel.siblings(".off_name").text();
                    var off_desc = sel.siblings(".off_desc").text();
                    var type = sel.parent().parent().attr("type");




                    var ret;
                    var tp = document.getElementById("poi_total");

                    $(this).on("click", function () {

                        var qta = sel.siblings(".qta");
                        var type = sel.parent().parent().attr("type");
                        var fullPrice = sel.parent().parent().attr("fullPrice");
                        var discPrice = sel.parent().parent().attr("discPrice");

                        var fullPrice_box = sel.parent().parent().find(".fullPrice");
                        var discPrice_box = sel.parent().parent().find(".discPrice");


                        if (operator == "up") {
                            //fare controllo se l'offerta è un tipo stock 
                            ret = modify_qty(qta, +1);
                            qta.val(ret);

                            if (type == "CARD") {
                                var sommaFull = parseInt(fullPrice) * parseInt(ret);
                                var sommaDisc = parseFloat(discPrice) * parseInt(ret);
                                fullPrice_box.html(sommaFull);
                                discPrice_box.html(sommaDisc);
                            }
                            else {
                                var sommaFull = parseInt(fullPrice) * parseInt(ret);
                                fullPrice_box.html(sommaFull);

                            }

                        }
                        else {
                            ret = modify_qty(qta, -1);
                            qta.val(ret);

                            if (type == "CARD") {
                                var sommaFull = parseInt(fullPrice) * parseInt(ret);
                                var sommaDisc = parseFloat(discPrice) * parseInt(ret);
                                fullPrice_box.html(sommaFull);
                                discPrice_box.html(sommaDisc);
                            }
                            else {
                                var sommaFull = parseInt(fullPrice) * parseInt(ret);
                                fullPrice_box.html(sommaFull);

                            }

                        }





                    });

                });


            });


            function modify_qty(qta, val) {
                var qtaval = qta.val();
                var new_qty = parseInt(qtaval, 10) + val;
                //alert(new_qty);



                if (new_qty < 0) {
                    new_qty = 0;
                }

                return new_qty;
            }

            function slideOfferPoi(box_select) {

                var offer = box_select.parent().parent().parent().siblings(".poi_offer");

                console.log(offer);
                if (offer.is(":hidden")) {
                    offer.slideDown("slow");
                }
                else {
                    offer.slideUp("slow");
                }
                //son.slideUp("slow");
            }





        </script>

    </head>
    <body  style="min-width:600px;">
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <jsp:include page="components/CoverComponentFavorites.jsp"/>
            </div>

            <div class="col-md-8">
                <article class="component component-text">
                    <div class="details">
                        <div class="paragrafo">
                            
                            
                            
                            
                            <%-- ciclo su poi --%>
                            <c:forEach var="idpoi" items="${map.map_poi.keySet()}">
                                <div class="poi" id="${map.map_poi.get(idpoi).id}"><!--style="border: 1px solid purple;"-->


                                    <div class="poi_preview_box">
                                        <div class="poi_preview_img">
                                            <img src="./dist/poi/img/${map.map_poi.get(idpoi).id}/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/>
                                            <!-- <img src="./dist/poi/img/550885d0edc9635d04573597/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/> -->
                                        </div>
                                        <div class="poi_preview_content">

                                            <div class="poi_preview_title">
                                                ${map.map_poi.get(idpoi).name}
                                            </div>

                                            <div class="poi_preview_text">
                                                <div class="seleziona">Seleziona</div>
                                            </div>
                                        </div>


                                        <div class="poi_preview_content2">

                                            <div class="poi_icons">
                                                <a href="./getPoi?id=${map.map_poi.get(idpoi).id}" target="_blank"><i class="fa fa-info-circle info" style="cursor:pointer; color: #2980B9; font-size:16px;" data-toggle="tooltip" data-original-title="Maggiori informazioni"></i></a>
                                                <c:if test="${not empty map.map_off}"><i class="fa fa-credit-card" style="font-size:16px;" data-toggle="tooltip" data-original-title="Orchestra Card"></i></c:if>
                                            </div>
                                        </div>

                                        <%-- se map off not empty stampa questo div--%>
                                        <div class="poi_price_total"><c:if test="${not empty map.map_off}"><div style="color:red;">Sconti con Orchestra Card</div></c:if> </div>

                                        </div>
                                        <div class="poi_offer" style="display:none">

                                        <%-- OFFERTE STOCK --%>
                                        <c:if test="${not empty map_comp.get(idpoi)}">
                                            
                                            <div class="card_offers_section" >
                                                <div class="poi_offer_filler"></div>
                                                <div class="card_offers_section_info"><li>Altre offerte</li></div>
                                            </div>
                                            
                                            
                                            <c:forEach items="${map_comp.get(idpoi).prices}" var="stock">

                                                    <div class="poi_offer_detail" idPoi="${idpoi}" type="${stock.type}" typeDesc="${stock.type}" fullPrice="${stock.price}">

                                                        <div class="poi_offer_filler"></div>
                                                        <div  class="poi_offer_detail_info">
                                                            <div class="off_name" >${stock.type} </div>
                                                            <div class="off_desc" data-toggle="tooltip" data-original-title="${stock.type_description}">${stock.type_description}</div>

                                                            <div class="add_offer">Aggiungi</div>
                                                            <div class="del_offer">Elimina</div>


                                                        </div>

                                                        <div class="poi_offer_detail_quantity">
                                                            <div style="display:inline-block">Quantità: </div>
                                                            <i id="up" style="display:inline-block" class="control fa fa-plus"></i>
                                                            <input id="qta" class="qta" style="display:inline-block; width:30px; text-align: center;" type="text" value="0"/>
                                                            <i id="down" style="display:inline-block" class="control fa fa-minus"></i>

                                                        </div>

                                                        <div class="poi_prices">
                                                            <div class="fullPrice fullPrice_stock" style="text-decoration:none;">${stock.price}</div><div class="valuta">€</div>
                                                        </div>
                                                    </div>

                                            </c:forEach>
                                            
                                        </c:if>

                                        <c:choose>
                                            <c:when test="${empty map.map_off.get(idpoi)}">
                                                <div class="card_offers_section" >
                                                    <div class="poi_offer_filler"></div>
                                                    <div class="card_offers_section_info">Nessuna offerta disponibile</div>
                                                </div>
                                            </c:when>

                                            <c:otherwise>
                                                <div class="card_offers_section" >
                                                    <div class="poi_offer_filler"></div>
                                                    <div class="card_offers_section_info"><li>Offerte Orchestra Card Pass&Spass</li></div>
                                                </div>
                                                <%-- CICLO OFF CARD  --%>

                                                <c:forEach var="offer" items="${map.map_off.get(idpoi)}">


                                                    <div class="poi_offer_detail" type="CARD" idPoi="${idpoi}" idOffer="${offer.idOffer}" fullPrice="${offer.fullPrice}" discPrice="${offer.discountedPrice}">

                                                        <div class="poi_offer_filler"></div>
                                                        <div  class="poi_offer_detail_info">
                                                            <div class="off_name" >${offer.nome}</div>
                                                            <div class="off_desc"  data-toggle="tooltip" data-original-title="${offer.desc}" >${offer.desc}</div>
                                                            <div class="add_offer">Aggiungi</div>
                                                            <div class="del_offer">Elimina</div>
                                                        </div>
                                                            
                                                        <c:choose>
                                                            <c:when test="${offer.fullPrice!=0}">     
                                                                <div class="poi_offer_detail_quantity">
                                                                    <div style="display:inline-block">Quantità: </div>
                                                                    <i id="up" style="display:inline-block" class="control fa fa-plus"></i>
                                                                    <input id="qta" class="qta" style="display:inline-block; width:30px; text-align: center;" type="text" value="0"/>
                                                                    <i id="down" style="display:inline-block" class="control fa fa-minus"></i>
                                                                </div>


                                                                <div class="poi_prices">
                                                                    <div class="fullPrice">${offer.fullPrice}</div><div class="valuta">€</div>
                                                                    <div class="discPrice">${offer.discountedPrice}</div><div class="valuta">€</div>
                                                                    <div class="rateOffer">-${offer.rateDiscount}</div><div class="valuta" style="color:red;">%</div>
                                                                </div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="hidden" id="qta" class="qta" value="0"/>
                                                                <div class="rateOffer">-${offer.rateDiscount}</div><div class="valuta" style="color:red;">%</div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </div>
                                                </c:forEach>


                                            </c:otherwise>
                                        </c:choose>






                                    </div>
                                    <div class="clear"></div>     
                                </c:forEach> 
                            </div>
                        </div>
                    </div>
                </article>
            </div>

            <div class="col-md-4">
                <%--
                <article class="component component-text">
                    <div class="details">
                        <h2>Riepilogo</h2>
                        <hr>
                        <!-- implementare il foreach dei preferiti per categorie-->
                        <div class="paragrafo">
                            <input type="text" id="poi_total" value="0"/>
                        </div>
                    </div>
                --%>

                </article>
                <a style="display:block" href="./saveInCard">
                    <div id="nuovo_it" class="new_it_button">Completa l'itinerario</div>
                </a>



            </div>


            <div class="col-xs-12" id="footer">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>

        </div>

        <script>
            $(function () {
                $('.off_desc').tooltip();

            });
        </script>


    </body>
</html>
