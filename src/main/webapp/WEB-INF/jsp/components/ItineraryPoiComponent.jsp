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
            <i class="fa fa-map-marker favorite"></i>
            <span class="add_favorite">Aggiungi il poi ai tuoi itinerari</span>
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
                        <div class="it_list_add"><i class="fa_list_itinerary_add fa fa-plus addToIt"></i></div>
                        <div class="it_list_del"><i class="fa_list_itinerary_del delToIt fa fa-times"></i></div>
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
                    alert("poi rimosso dall'itinerario");
                    sel.removeClass("fa-check");
                    sel.addClass("fa-plus");
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
                    var del = sel.parent().find(".delToIt");
                    
                    alert("poi aggiunto dall'itinerario");
                }
            });
        });            
    });
                    
</script>