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
        <!--<link href="./dist/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>


        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 

        <title>JSP Page</title>
        <style>
            .poi_preview_box{
                height: 75px; 
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
                //font-weight: bold;
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
                width: 35%; 
                //border: 1px solid yellow; 
                float: left;
                text-align: right;
                padding: 10px;
                width: 45%;
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
                padding: 10px;
                padding-top: 5px;
                padding-bottom: 5px;

                //border: 1px solid yellow; 
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

            
            
        </style>    
        
        
        <script>
            $(document).ready(function () {
        
                console.log("*************************");
                $(".add_offer").each(function (index) { 
                    
                    var sel = $(this);
                    $(this).on("click", function () {
                        var idUser=$("#idUser").val();
                        //var off_name = sel.siblings(".off_name").text();
                        //var off_desc = sel.siblings(".off_desc").text();
                        var qta = sel.parent().parent().find(".qta").val();
                        var sum = sel.parent().parent().find("#val-discounted").val();
                        var idOffer = sel.parent().parent().prop("id");
                        var idPoi = sel.parent().parent().parent().parent().prop("id");
                        alert(qta);
                        console.log(idPoi);
                        //@RequestParam String id_offer, @RequestParam String id_poi, @RequestParam String qta
                        //"id_poi="+poiId+"&rating="+rating,
                       
                        $.ajax({
                            type: "GET",
                            url: "./saveOffer",
                            data: "id_offer="+idOffer+"&id_poi="+idPoi+"&qta="+qta+"&sum="+sum,	
                            success: function(){
                                alert("Offerta inserita");
                            }                 
                        }); 
                        
                        
                        <%--var idPoi = ${map.map_poi.get(idpoi).name}; 
                        
                        
                        
                        --%>
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
                    var ret;
                    var tp = document.getElementById("poi_total");
                    
                    $(this).on("click", function () {
                        
                        var qta = sel.siblings(".qta");
                        //alert(qta.val());
                        var stock = sel.parents().siblings(".stock").val();
                        var stock_disc = sel.parents().siblings(".stock_disc").val();
                        var total_poi = sel.parents().siblings(".tot");
                        
                        if(operator == "up"){
                            
                            ret=modify_qty(qta,+1);
                            qta.val(ret);
                            var tot_intero = sel.parents().siblings("#val");
                            var tot_scontato = sel.parents().siblings("#val-discounted");
                            tot_intero.val(stock*ret);
                            tot_scontato.val(stock_disc*ret);
                            //total_poi.val(stock*ret);
                            //var back_value=0;
                            //back_value = parseInt(tp.value)+parseInt(total_poi.val());
                            //tp.value=back_value;
                            
                        }
                        else{
                            ret=modify_qty(qta,-1);
                            qta.val(ret);
                            
                        }
                             
                        
                        
                        
                    });
                    
                });
                
                
            });
            
            
            function modify_qty(qta, val) {
                var qtaval = qta.val();
                 var new_qty = parseInt(qtaval,10) + val;
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
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <jsp:include page="components/CoverComponentFavorites.jsp"/>
            </div>

            <div class="col-md-8">
                <article class="component component-text">
                    <div class="details">
                        <div class="paragrafo">
                            <div class="favorite_container" style="min-height: 200px;">
                                <div class="category_container" >
                                    <input id="idUser" type="hidden" value="${id_user}"/>
                                    <c:forEach var="idpoi" items="${map.map_poi.keySet()}">
                                        <div class="poi" id="${map.map_poi.get(idpoi).id}"><!--style="border: 1px solid purple;"-->

                                            
                                        <div class="poi_preview_box">
                                            <div class="poi_preview_img">
                                                <!-- <img src="./dist/poi/img/${map.map_poi.get(idpoi).id}/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/> -->
                                                <img src="./dist/poi/img/550885d0edc9635d04573597/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/>
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
                                                    <i class="fa fa-credit-card" style="font-size:16px;" data-toggle="tooltip" data-original-title="Orchestra Card"></i>
                                                </div>
                                            </div>
                                            
                                            <div class="poi_price_total">Con Orchestra Card 10€</div>


                                        </div>
                                        <div class="poi_offer" style="display:none">
                                            <div class="poi_offer_filler"></div>
                                            
                                            <c:forEach items="${map_comp.get(idpoi).prices}" var="stock">
                                                <div class="poi_offer_detail">
                                                    
                                                    
                                                    
                                                    <div class="poi_offer_filler"></div>
                                                    <div  class="poi_offer_detail_info" data-toggle="tooltip" data-original-title="Descrizione dettagliata dell'offerta con ellipsis...">
                                                        <div class="off_name" >${stock.type}</div>
                                                        <div class="off_desc" >${stock.type_description}</div>
                                                        <div class="add_offer" style="cursor:pointer;">Aggiungi offerta</div>
                                                    </div>
                                                
                                                    <div class="poi_offer_detail_quantity">
                                                        <div style="display:inline-block">Quantità: </div>
                                                        <i id="up" style="display:inline-block" class="control fa fa-plus"></i>
                                                        <input id="qta" class="qta" style="display:inline-block; width:30px; text-align: center;" type="text" value="1"/>
                                                        <i id="down" style="display:inline-block" class="control fa fa-minus"></i>

                                                    </div>
                                                    <input style="border:1px solid red;" class="tot" type="hidden" value="0"/>
                                                    <input class="stock" type="hidden" value="${stock.price}" style="padding:0px;"/>
                                                    <input class="stock_disc" type="hidden" value="${stock.price}" style="padding:0px;"/>
                                                    <input id="val" class="poi_offer_detail_price" style="width:50px;padding:0px;" value="${stock.price}"/>
                                                    <input type="hidden"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        id="val-discounted" class="poi_offer_detail_price" style="padding:0px; display: inline-block; width:50px" value="${stock.price}"/>
                                                    
                                                </div>
                                                        
                                                        <%--${stock.get(idpoi).price}
                                                        ${stock.get(idpoi).type_description}--%>
                                            </c:forEach>
                                            
                                            <div class="poi_offer_detail_info"><strong>Offerte Card</strong></div>
                                            <c:forEach var="offer" items="${map.map_off.get(idpoi)}">
                                                <div id="${offer.idOffer}" class="poi_offer_detail">
                                                    
                                                    
                                                    
                                                    <div class="poi_offer_filler"></div>
                                                    <div  class="poi_offer_detail_info" data-toggle="tooltip" data-original-title="Descrizione dettagliata dell'offerta con ellipsis...">
                                                        <div class="off_name" >${offer.nome}</div>
                                                        <div class="off_desc" >${offer.desc}</div>
                                                        <div class="add_offer" style="cursor:pointer;">Aggiungi offerta</div>
                                                    </div>
                                                
                                                    <div class="poi_offer_detail_quantity">
                                                        <div style="display:inline-block">Quantità: </div>
                                                        <i id="up" style="display:inline-block" class="control fa fa-plus"></i>
                                                        <input id="qta" class="qta" style="display:inline-block; width:30px; text-align: center;" type="text" value="1"/>
                                                        <i id="down" style="display:inline-block" class="control fa fa-minus"></i>

                                                    </div>
                                                    <input style="border:1px solid red;" class="tot" type="hidden" value="0"/>
                                                    <input class="stock" type="hidden" value="${offer.fullPrice}" style="padding:0px;"/>
                                                    <input class="stock_disc" type="hidden" value="${offer.discountedPrice}" style="padding:0px;"/>
                                                    <input id="val" class="poi_offer_detail_price" style="width:50px;padding:0px;" value="${offer.fullPrice}"/>
                                                    <input id="val-discounted" class="poi_offer_detail_price" style="padding:0px; display: inline-block; width:50px" value="${offer.discountedPrice}"/>
                                                    <div class="poi_offer_detail_rate" style="display: inline-block; width:50px">${offer.rateDiscount}%</div>
                                                    
                                                </div>
                                            </c:forEach>
                                            
                                            
                                            
                                            
                                            
                                            
                                        </div>
                                        <div class="clear"></div>     
                                        <%-- </c:forEach> --%>
                                    </div>
                                    </c:forEach>    
                                    
                                </div>
                                <%-- </c:forEach> --%>
                            </div>
                        </div>
                    </div>
                </article>
            </div>

            <div class="col-md-4">
                <article class="component component-text">
                    <div class="details">
                        <h2>Riepilogo</h2>
                        <hr>
                        <!-- implementare il foreach dei preferiti per categorie-->
                        <div class="paragrafo">
                            <input type="text" id="poi_total" value="0"/>
                        </div>
                    </div>
                    
                    
                </article>
                <a style="display:block" href="./saveInCart">
                    <div id="nuovo_it" class="new_it_button">Completa l'itinerario</div>
                </a>
                
                
                
            </div>


            <div class="col-xs-12" id="footer">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>

        </div>




    </body>
</html>
