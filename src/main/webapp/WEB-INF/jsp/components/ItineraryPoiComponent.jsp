<%-- 
    Document   : AddToFavoritesComponent
    Created on : 7-giu-2015, 17.20.13
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
    .nano { width: 100%; max-height: 400px;  }
    .nano .nano-pane   { background: #d9d9d9!important; }
    .nano .nano-slider { background: #B6B6B6!important; }
</style>

<article class="component component-text">
    <div class="details" style="overflow: scroll;overflow-x:hidden;">
        <div class="itinerary_header">
            <i class="fa fa-map-marker addit_header" style="font-size: 20px;"></i>
            <span class="addit_content">Aggiungi il poi ai tuoi itinerari</span>
            <div class="clear"></div>
        </div>
        
        <c:choose>
            <c:when test="${not empty userItinerary}">
                <c:forEach var="itinerary" items="${userItinerary}">
        
                    <div class="itinerary_list_content" idItinerary="${itinerary.idItinerary}">
                        <div class="img_container">
                            <c:set var="itn" value="${itinerary.name}"/>
                            <c:set var="it_name" value="${fn:substring(itn, 0, 2)}" />
                            <c:set var="tx" value="${fn:toUpperCase(it_name)}" />

                            <div class="rnd" style="background-color:${itinerary.color};">
                                <span class="rnd-text">${tx}</span>
                            </div>
                        </div>
                        <a href="./myItineraryDetail?idItinerary=${itinerary.idItinerary}"><div class="it_list_name">${itinerary.name}</div></a>
                            <c:choose>
                                <c:when test="${not empty poi_inItinerary.get(itinerary.idItinerary)}">
                                    <div class="it_list_add"><i class="fa_list_itinerary_add fa fa-check addToIt"></i></div>
                                    <div class="it_list_del"><i class="fa_list_itinerary_del delToIt fa fa-times"></i></div>
                        
                                </c:when>
                                <c:otherwise>
                                    <div class="it_list_add"><i class="fa_list_itinerary_add fa fa-plus addToIt"></i></div>
                                    <div class="it_list_del"><i class="fa_list_itinerary_del delToIt fa fa-times" style="display:none;"></i></div>
                        
                                </c:otherwise>    
                            </c:choose>
                        
                        <div class="clear"></div>
                    </div>   
                </c:forEach>
            </c:when>
            <c:otherwise>
                Nessun itinerario è stato creato!
            </c:otherwise>
        </c:choose>
    </div>
</article>

<script>
    $(".delToIt").each(function (index) {
        var sel = $(this);
        var idItinerary = sel.parent().parent().attr("idItinerary");
        var idPoi="${poi.id}";
        $(this).on("click", function () {
            $.ajax({
                type: "GET",
                url: "./removePoiItinerary",
                data: "idItinerary=" + idItinerary + "&idPoi=" + idPoi,
                success: function () {
                    var add = sel.parent().parent().find(".addToIt");
                    add.removeClass("fa-check");
                    add.addClass("fa-plus");
                    sel.hide();
                    
                    alert("poi rimosso dall'itinerario");
                }
            });
        });            
    });
    
    $(".addToIt").each(function (index) {
        var sel = $(this);
        var idItinerary = sel.parent().parent().attr("idItinerary");
        var idPoi="${poi.id}";
        $(this).on("click", function () {
            $.ajax({
                type: "GET",
                url: "./addPoiItinerary",
                data: "idPoi=" + idPoi + "&idItinerary=" + idItinerary,
                success: function (data) {
                    sel.removeClass("fa-plus");
                    sel.addClass("fa-check");
                    var del = sel.parent().parent().find(".delToIt");
                    del.show();
                    
                    alert("poi aggiunto dall'itinerario");
                }
            });
        });            
    });
                    
</script>