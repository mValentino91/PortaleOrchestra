<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--
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
--%>

    <div class="modal-body" style="padding:0px;">
        <div class="offer_header"><span class="offer_header_text">Offerte per ${poi.name}</span></div>
        <div class="offer_container">


            <div style="width:50%; float:right;">
                <div class="off_header_info">%sconto</div>
                <div class="off_header_info">ridotto(€)</div>	                                            
                <div class="off_header_info">intero(€)</div>
                <div class="off_header_info">numero</div>
                <div class="off_header_info">totale(€)</div>
            </div>
            <div class="clear"></div>
        </div>

        <c:choose>
            <c:when test="${not empty offers}">
                <div class="offer_container_row_card">
                    <c:forEach var="off" items="${offers}"> 

                        <div class="offer_container_row" type="CARD" idItinerary="${idItinerary}" idOffer="${off.idOffer}" idPoi="${poi.id}" fullPrice="${off.fullPrice}" discountedPrice="${off.discountedPrice}" rateOffer="${off.rateDiscount}">
                            <c:choose>
                                <c:when test="${not empty map_userChoice_card.get(off.idOffer)}">
                                    <div class="offer_action">
                                        <i class="fa fa-check add" style="color:#4caf50; font-size:14px;  padding:2px;"></i>
                                        <i class="fa fa-times del" style="color:#f44446; font-size:14px; padding:2px;"></i>
                                    </div>

                                    <div class="offer_poi_name" data-placement="bottom" data-toggle="tooltip" title="${off.nome}: ${off.desc}">${off.nome}</div>
                                    <div class="offer_action">
                                        <img src="./dist/img/iconcina-card.png" style="width:20px; height:19px;" data-toggle="tooltip" data-original-title="Orchestra Card"/>
                                    </div>
                                    <div class="offer_poi_date_end">Scadenza:<fmt:formatDate value="${off.dateEnd}" pattern="dd/MM/yyyy "/></div>
                                     <div class="offer_poi_discrating"><div class="offer_poi_int rateOffer">${off.rateDiscount}</div></div>
                                    <div class="offer_poi_ridotto"><div class="offer_poi_int">${off.discountedPrice}</div></div>
                                    <div class="offer_poi_intero"><div class="offer_poi_int">${off.fullPrice}</div></div>
                                    <div class="offer_poi_controls ctr">
                                        <div id="up" class="offer_poi_int_cont control" style="width:20%; border-right:1px solid; float:left;">+</div>
                                        <div class="offer_poi_int_cont qta" style="width:60%; float:left;">${map_userChoice_card.get(off.idOffer).qta}</div>
                                        <div id="down" class="offer_poi_int_cont control" style="width:20%; border-left:1px solid; float:left;">-</div>
                                    </div>
                                    <div class="offer_poi_total"><div class="offer_poi_int total">${off.discountedPrice * map_userChoice_card.get(off.idOffer).qta}</div></div>
                                </c:when>
                                <c:otherwise>
                                    <div class="offer_action">
                                        <i class="fa fa-plus add" style="color:#4caf50; font-size:14px;  padding:2px;"></i>
                                        <i class="fa fa-times del" style="visibility:hidden; color:#f44446; font-size:14px; padding:2px;"></i>
                                    </div>

                                    <div class="offer_poi_name" data-placement="bottom" data-toggle="tooltip" title="${off.nome}: ${off.desc}">${off.nome}</div>
                                    <div class="offer_action">
                                        <img src="./dist/img/iconcina-card.png" style="width:20px; height:19px;" data-toggle="tooltip" data-original-title="Orchestra Card"/>
                                    </div>
                                    <div class="offer_poi_date_end">Scadenza:<fmt:formatDate value="${off.dateEnd}" pattern="dd/MM/yyyy "/></div>
                                     <div class="offer_poi_discrating"><div class="offer_poi_int rateOffer">${off.rateDiscount}</div></div>
                                    <div class="offer_poi_ridotto"><div class="offer_poi_int">${off.discountedPrice}</div></div>
                                    <div class="offer_poi_intero"><div class="offer_poi_int">${off.fullPrice}</div></div>
                                    <div class="offer_poi_controls ctr">
                                        <div id="up" class="offer_poi_int_cont control" style="width:20%; border-right:1px solid; float:left;">+</div>
                                        <div class="offer_poi_int_cont qta" style="width:60%; float:left;">0</div>
                                        <div id="down" class="offer_poi_int_cont control" style="width:20%; border-left:1px solid; float:left;">-</div>
                                    </div>
                                    <div class="offer_poi_total"><div class="offer_poi_int total">${off.fullPrice * map_userChoice_card.get(off.idOffer).qta}</div></div>

                                </c:otherwise>
                            </c:choose>

                        </div>
                    </c:forEach>
                </div>    
            </c:when>

            <c:otherwise>
                <div style="margin-left:5px; font-weight: bold; color:#285E8E;"><i class="fa fa-circle" style="color:#285E8E; font-size:12px;"></i> Non ci sono offerte CARD!</div>
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not empty price_comp}">
                <!-- offerte stock -->
                <div class="offer_container_row_stock">
                    <c:forEach var="stock" items="${price_comp}">
                        <div class="offer_container_row" type="STOCK" idItinerary="${idItinerary}" idPoi="${poi.id}" fullPrice="${stock.get("price")}" name="${stock.get('type')}" desc="${stock.get('desc')}">
                            <c:choose>
                                <c:when test="${not empty map_userChoice_stock.get(stock.get('type'))}">
                                    <div class="offer_action">
                                        <i class="fa fa-check add" style="color:#4caf50; font-size:14px;  padding:2px;"></i>
                                        <i class="fa fa-times del" style="color:#f44446; font-size:14px; padding:2px;"></i>
                                    </div>
                                    <div class="offer_poi_name" data-placement="bottom" data-toggle="tooltip" title="${stock.get("desc")}" style="float:left;">${stock.get("type")}</div>

                                    <div class="stock_prices" style="float:right;" type="STOCK" idItinerary="${idItinerary}" idPoi="${poi.id}" fullPrice="${stock.get("price")}">
                                        <div class="stock_poi_total">
                                            <div class="offer_poi_int total">
                                                <c:set var="fullPrice" value="${stock.get('price') * map_userChoice_stock.get(stock.get('type')).qta}"/>
                                                ${fullPrice}
                                            </div>
                                        </div>

                                        <div class="stock_poi_controls ctr">
                                            <div id="up" class="offer_poi_int_cont control" style="width:20%; border-right:1px solid; float:left;">+</div>
                                            <div class="offer_poi_int_cont qta" style="width:60%; float:left;">${map_userChoice_stock.get(stock.get('type')).qta}</div>
                                            <div id="down" class="offer_poi_int_cont control" style="width:20%; border-left:1px solid; float:left;">-</div>
                                        </div>
                                        <div class="stock_poi_intero"><div class="offer_poi_int">${stock.get('price')}</div></div>	                                        

                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="offer_action">
                                        <i class="fa fa-plus add" style="color:#4caf50; font-size:14px;  padding:2px;"></i>
                                        <i class="fa fa-times del" style="visibility: hidden; color:#f44446; font-size:14px; padding:2px;"></i>
                                    </div>
                                    <div class="offer_poi_name" style="float:left;" data-placement="bottom" data-toggle="tooltip" title="${stock.get("desc")}">${stock.get("type")}</div>

                                    <div class="stock_prices" style="float:right;" type="STOCK" idItinerary="${idItinerary}" idPoi="${poi.id}" fullPrice="${stock.get("price")}">
                                        <div class="stock_poi_total">
                                            <div class="offer_poi_int total">
                                                <c:set var="fullPrice" value="${stock.get('price') * map_userChoice_stock.get(stock.get('type')).qta}"/>
                                                ${fullPrice}
                                            </div>
                                        </div>

                                        <div class="stock_poi_controls ctr" >
                                            <div id="up" class="offer_poi_int_cont control" style="width:20%; border-right:1px solid; float:left;">+</div>
                                            <div class="offer_poi_int_cont qta" style="width:60%; float:left;">0</div>
                                            <div id="down" class="offer_poi_int_cont control" style="width:20%; border-left:1px solid; float:left;">-</div>
                                        </div>
                                        <div class="stock_poi_intero"><div class="offer_poi_int">${stock.get('price')}</div></div>	                                        

                                    </div>

                                </c:otherwise>

                            </c:choose>

                        </div>
                    </c:forEach>
                </div>
            </c:when>
            <c:otherwise>
                <div style="margin-left:5px; font-weight: bold; color:#285E8E;"><i class="fa fa-circle" style="color:#285E8E; font-size:12px;"></i> Non ci sono offerte STOCK!</div>
            </c:otherwise>
        </c:choose>
            
        <div class="offer_review">
            <div class="offer_total_full">
                <span class="btn_poi_int" style="margin-left: 10px;float:left">Totale(€):</span>
                <div id="total_full" class="btn_poi_int">10</div>    
            </div>	
            <div class="offer_confirm_full" data-dismiss="modal"><div class="btn_poi_int" style="cursor:pointer;">OK</div></div>	
        </div>
        <div class="clear"></div>                                   
    </div>
        
    <script>
        
        $( document ).ready(function() {
            getTotal();
        });
        
        
        $('[data-toggle="tooltip"]').tooltip();
        $(".control").each(function (index) {
            var sel = $(this);
            var operator = sel.prop("id");
            var ret;

            $(this).on("click", function () {
                var sel = $(this);
                var operator = sel.prop("id");
                var ret;
                var qta = sel.parent().find(".qta");
                var type = sel.parent().parent().attr("type");
                var fullPrice = sel.parent().parent().attr("fullPrice");
                var discountedPrice = sel.parent().parent().attr("discountedPrice");
                var total = sel.parent().parent().find(".total");
                var total_full = $("#total_full").text();
                
                
                if (operator == "up") {
                    
                    ret = modify_qty(qta, +1);
                    qta.text(ret);

                }
                else {
                    ret = modify_qty(qta, -1);
                    qta.text(ret);
                }
                
                if (type == "CARD") {
                    var sommaDisc = parseFloat(discountedPrice) * parseInt(ret);
                    total.text(sommaDisc);
                    getTotal();
                }else{
                    var sommaFull = parseFloat(fullPrice) * parseInt(ret);
                    total.text(sommaFull);
                    getTotal();
                }
                
            });
        });
        
        $(".add").each(function (index) {
            var sel = $(this);
            $(this).on("click", function () {
                var idOffer = -1;
                var rateOffer = null;
                var name = null;
                var desc = null;
                var sel_name = sel;
                var type = sel.parent().parent().attr("type");
                
                var idItinerary = sel.parent().parent().attr("idItinerary");
                var idPoi=sel.parent().parent().attr("idPoi");
                var qta = sel.parent().parent().find(".qta");
                var total = sel.parent().parent().find(".total");    
                var discPrice = 0;
                var fullPrice = 0;
                var sum=0;
                
                var del = sel.siblings(".del");
                
                if (type == "CARD") {
                    idOffer = sel.parent().parent().attr("idOffer");
                    rateOffer = sel.parent().parent().find(".rateOffer").text();
                    discPrice=sel.parent().parent().attr("discountedPrice");
                    sum=discPrice;
                    
                }else{
                    name = sel.parent().parent().attr("name");
                    desc = sel.parent().parent().attr("desc");
                    fullPrice = sel.parent().parent().attr("fullPrice");
                    sum = fullPrice;
                }
                
        
                $.ajax({
                    type: "GET",
                    url: "./addOfferItinerary",
                    data: "qta=" + qta.text() + "&idPoi=" + idPoi +"&id_offer=" + idOffer + "&sum=" + sum + "&type=" + type + "&idItinerary=" + idItinerary + "&name=" + name + "&desc=" + desc,
                    success: function (data) {
                        //sel_name.text("V");
                        del.css("visibility","visible");
                        sel.removeClass("fa-plus");
                        sel.addClass("fa-check");
                        
                        var id_row = "sum_off_"+idPoi;
                        var count_off = $("#"+id_row);
                        count_off.text("Hai aggiunto "+data+" offerte");
                    }
                });
            
                
            });
        });
        
        $(".del").each(function (index) {
            var sel = $(this);
            $(this).on("click", function () {
                var idOffer = -1;
                var name = null;
                var desc = null;
                var type = sel.parent().parent().attr("type");
                var add = sel.siblings(".add");
                
                var idItinerary = sel.parent().parent().attr("idItinerary");
                var idPoi=sel.parent().parent().attr("idPoi");
                var qta = sel.parent().parent().find(".qta");
                var total = sel.parent().parent().find(".total");
                
                var del = sel.siblings(".del");
                
                if (type == "CARD") {
                    idOffer = sel.parent().parent().attr("idOffer");
                    
                }else{
                    name = sel.parent().parent().attr("name");
                    desc = sel.parent().parent().attr("desc");
                }
        
                $.ajax({
                    type: "GET",
                    url: "./removeOfferItinerary",
                    data: "id_offer=" + idOffer + "&idItinerary=" + idItinerary + "&idPoi=" + idPoi + "&name=" + name + "&type=" + type,
                    success: function (data) {
                        sel.css("visibility","hidden");
                        add.removeClass("fa-check");
                        add.addClass("fa-plus");
                        qta.text("0");
                        total.text("0");
                        var id_row = "sum_off_"+idPoi;
                        var count_off = $("#"+id_row);
                        count_off.text("Hai aggiunto "+data+" offerte");
                    }
                });
               
            });
        });
        
        function modify_qty(qta, val) {
            var qtaval = qta.text();
            var new_qty = parseInt(qtaval, 10) + val;
            //alert(new_qty);



            if (new_qty < 0) {
                new_qty = 0;
            }

            return new_qty;
        }
        
        function getTotal(){
            var totale = 0;
            $(".total").each(function(index){
               var sel = $(this);
               totale = totale + parseInt(sel.text());  
               $("#total_full").text(totale);
            });
        }
    </script>
       
        
    <%--
    <script>

        $('[data-toggle="tooltip"]').tooltip();


       

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
                else {
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
                var idPoi = sel.parent().attr("idPoi");
                var idOffer = -1;
                var name = null;
                var type = sel.parent().attr("type");

                if (type == "CARD") {
                    idOffer = sel.parent().attr("idOffer");
                }
                else {
                    name = sel.siblings(".name").text();
                }


                $.ajax({
                    type: "GET",
                    url: "./removeOfferItinerary",
                    data: "id_offer=" + idOffer + "&idItinerary=" + idItinerary + "&idPoi=" + idPoi + "&name=" + name + "&type=" + type,
                    success: function () {
                        add.text("+");
                        sel.hide();
                    }
                });

            });
        });



        $(".add").each(function (index) {
            var sel = $(this);
            $(this).on("click", function () {
                var sum = 0;
                var discPrice = null;
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
                    data: "qta=" + qta.val() + "&idPoi=" + idPoi +"&id_offer=" + idOffer + "&sum=" + sum + "&type=" + type + "&idItinerary=" + idItinerary + "&name=" + name + "&desc=" + desc,
                    success: function (data) {
                        sel_name.text("V");
                        del.show();
                        
                        var id_row = "sum_off_"+idPoi;
                        var count_off = $("#"+id_row);
                        count_off.text("Hai aggiunto "+data+" offerte");
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
    --%>