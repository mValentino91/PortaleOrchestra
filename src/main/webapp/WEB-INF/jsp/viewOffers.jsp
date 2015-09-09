<%-- 
    Document   : viewOffer
    Created on : 9-ago-2015, 11.34.12
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
        <title>JSP Page</title>
        
        <style>

            .modal-dialog {
                width: 1000px;
            }

            .off{
                padding: 0;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }

            .add-success, .remove-success{
                padding: 0px; 
                margin-bottom: 5px; 
                border:1px solid;
                color: #fff;
                font-weight: bold;
                height: 50px;
                padding-top: 12px;
                text-align: center;
                line-height: 25px;
                vertical-align: middle;
            }

            .add-success{
                background-color: #2ECC71;
            }

            .remove-success{
                background-color: #D24D57;
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

            .off-add{
                user-drag: none; 
                -moz-user-select: none;
                -webkit-user-drag: none;
                background-color: #1e88e5;
                color: #fff;
                cursor: pointer;
                text-align: center;
            }



        </style>
        

        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">Offerte disponibili per ${poi_name}</h4>

        </div>
        <div class="modal-body">


            <div id="add-off" class="col-md-12 add-success" style="display:none;">Offerta aggiunta correttamente</div>
            <div id="rem-off" class="col-md-12 remove-success" style="display:none;">Offerta rimossa correttamente</div>

            
            
               
                <c:forEach var="off" items="${offers}">
                    
                    
                        <div type="CARD" idItinerary="${idItinerary}" idOffer="${off.idOffer}" idPoi="${idPoi}" fullPrice="${off.fullPrice}" discountedPrice="${off.discountedPrice}" rateOffer="${off.rateDiscount}">
                            <c:choose>
                                <c:when test="${not empty user_cardChoices}">
                                    <c:forEach var="idc" items="${user_cardChoices}">
                                        <c:if test="${idc.idOffer == off.idOffer}">
                                            <span id="select"class="off add" style="float: left; text-align: center; width:5%; border: 1px solid">+</span>
                                            <span class="off del" style="float: left; width: 5%; text-align: center; border: 1px solid">x</span>
                                        </c:if>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <span id="select"class="off add" style="float: left; text-align: center; width:5%; border: 1px solid">+</span>    
                                    <span class="off del" style="display:none; float: left; width: 5%; text-align: center; border: 1px solid">x</span>

                                </c:otherwise>
                            </c:choose>
                            
                        
                        <span class="off name" style="float:left; width: 20%; border:1px solid;" data-placement="bottom" data-toggle="tooltip" title="${off.nome}">${off.nome}</span>
                        <span class="off desc" style="float:left; width: 35%; border:1px solid;" data-placement="bottom" data-toggle="tooltip" title="${off.desc}">${off.desc}</span>
                        <span class="off-ctr" style="float:left; width: 10%; border:1px solid;">
                            <span id="up" class="control" style="cursor: pointer; text-align: center; font-weight: bold; float: left;border:1px solid; width:20%">+</span>
                            <input id="qta" class="qta" style="text-align: center; float:left; width:60%; height:22px;" type="text" value="0"/>
                            <span id="down" class="control" style="cursor: pointer; text-align: center; font-weight: bold; float: left;border:1px solid; width:20%">-</span>
                        </span> 
                        <span class="off fullPrice" style="text-align: center; float:left; width: 5%; border:1px solid;">${off.fullPrice}</span>
                        <span class="off discPrice" style="text-align: center; float:left; width: 5%; border:1px solid;">${off.discountedPrice}</span>
                        <span class="off" style="padding-left: 2px; float:left; width: 5%; border:1px solid;">-${off.rateDiscount}%</span>
                        <span class="off" style="float:left; width: 10%; border:1px solid;">11/11/2001</span>
                        <div style="clear: both"></div>
                    </div>                   
                
             
            </c:forEach>
            
            
            Offerte non associate alla Orchestra Card
            <c:forEach var="stock" items="${price_comp}">
                <div type="STOCK" idItinerary="${idItinerary}" idPoi="${idPoi}" fullPrice="${stock.get("price")}">
                    <span id="select"class="off add" style="float: left; text-align: center; width:5%; border: 1px solid">+</span>
                    <span class="off del" style="display:none; float: left; width: 5%; text-align: center; border: 1px solid">x</span>
                    <span class="off name" style="float:left; width: 20%; border:1px solid;" data-placement="bottom" data-toggle="tooltip" title="${stock.get("type")}">${stock.get("type")}</span>
                    <span class="off desc" style="float:left; width: 35%; border:1px solid;" data-placement="bottom" data-toggle="tooltip" title="${stock.get("desc")}">${stock.get("desc")}</span>

                    <span class="off-ctr" style="float:left; width: 10%; border:1px solid;">
                        <span id="up" class="control" style="cursor: pointer; text-align: center; font-weight: bold; float: left;border:1px solid; width:20%">+</span>
                        <input id="qta" class="qta" style="text-align: center; float:left; width:60%; height:22px;" type="text" value="0"/>
                        <span id="down" class="control" style="cursor: pointer; text-align: center; font-weight: bold; float: left;border:1px solid; width:20%">-</span>
                    </span>                        

                    <span class="off fullPrice" style="text-align: center; float:left; width: 5%; border:1px solid;">${stock.get("price")}</span>
                    <div style="clear: both"></div>
                </div>        

            </c:forEach>
        </div>
        
<script>
            
                $('[data-toggle="tooltip"]').tooltip();


            <%-- per attivare il box verde di successo aggiunta...
            $("#add-off").toggle();
            --%>

            $(".control").each(function (index) {
                var sel = $(this);
                var operator = sel.prop("id");
                var ret;

                $(this).on("click", function () {

                    var qta = sel.siblings(".qta");
                    var type = sel.parent().parent().attr("type");
                    var fullPrice = sel.parent().parent().attr("fullPrice");
                    var discountedPrice = sel.parent().parent().attr("discountedPrice");
                    
                    var fullPrice_box = sel.parent().parent().find(".fullPrice");
                    var discPrice_box = sel.parent().parent().find(".discPrice");
                    
                    // alert(type);
                    
                    
                    
                    if (operator == "up") {
                        //fare controllo se l'offerta è un tipo stock 
                        ret = modify_qty(qta, +1);
                        qta.val(ret);
                        
                    }
                    else {
                        ret = modify_qty(qta, -1);
                        qta.val(ret);
                    }
                    if (type == "CARD") {
                        var sommaFull = parseInt(fullPrice) * parseInt(ret);
                        var sommaDisc = parseFloat(discountedPrice) * parseInt(ret);
                        fullPrice_box.html(sommaFull);
                        discPrice_box.html(sommaDisc);
                    }
                    else{
                        var sommaFull = parseInt(fullPrice) * parseInt(ret);
                        fullPrice_box.html(sommaFull);
                    }
                    
                });
            });
            
            
            $(".del").each(function (index) {
                var sel = $(this);
                $(this).on("click", function () {
                    var sel_name = sel;
                    var add = sel.siblings(".add");
                    var idItinerary = sel.parent().attr("idItinerary");
                    var idOffer = -1;
                    var name = null;
                    var type = sel.parent().attr("type");
                    
                    if (type == "CARD") {
                        idOffer = sel.parent().attr("idOffer");
                    }
                    else{
                        name = sel.siblings(".name").text();
                    }
                    

                    $.ajax({
                        
                        type: "GET",
                        url: "./removeOfferItinerary",
                        data: "id_offer=" + idOffer + "&idItinerary="+ idItinerary + "&name="+ name + "&type="+type,
                        
                        success: function(){
                            add.text("+");
                            sel.hide();
                        }
                    });

                });
            });
            
            
            
            $(".add").each(function (index) {
                var sel = $(this);
                $(this).on("click", function () {
                    var sum=0;
                    var discPrice=null;
                    var idOffer = -1;
                    var rateOffer = null;
                    var name = null;
                    var desc = null;
                    var sel_name = sel;
                    
                    var del = sel.siblings(".del");
                    var idItinerary = sel.parent().attr("idItinerary");
                    var idPoi = sel.parent().attr("idPoi");
                    var fullPrice = sel.parent().attr("fullPrice");
                    var qta = sel.siblings(".off-ctr").find(".qta");
                    var type = sel.parent().attr("type");
                    
                    if (type == "CARD") {
                        
                        discPrice = sel.parent().attr("discountedPrice");
                        idOffer = sel.parent().attr("idOffer");
                        rateOffer = sel.parent().find(".rateOffer").text();
                        sum = discPrice;
                    }
                    else {
                        name = sel.siblings(".name").text();
                        desc = sel.siblings(".desc").text();
                        sum = fullPrice;
                    }
                    
                    $.ajax({
                        
                        type: "GET",
                        url: "./addOfferItinerary",
                        data: "qta=" + qta.val() + "&id_offer=" + idOffer +"&sum=" + sum + "&type=" + type + "&idItinerary="+ idItinerary + "&name="+ name + "&desc=" + desc,
                        
                        success: function(){
                            sel_name.text("V");
                            del.show();
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

        </script>