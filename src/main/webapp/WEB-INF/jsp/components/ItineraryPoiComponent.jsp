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


<article class="component component-text">
    <div class="details">
        <div class="itinerary_header" style="height: 60px;">
            <i class="fa fa-map-marker favorite"></i>
            <span class="add_favorite">Aggiungi il poi ai tuoi itinerari</span>
            
        </div>
        
        <div class="itinerary_content">
            <div class="styled-select">
                <select class="it_select">
                    <option value="-1">>> Seleziona <<</option>
                    <c:forEach var="itinerary" items="${userItinerary}">
                        <option selected="select" value="${itinerary.idItinerary}">${itinerary.name}</option>
                    </c:forEach>
                </select>
                
                <script>
                    $(".it_select").change(function() {
                        $("select option:selected").each(function() {
                            var idPoi = "${poi.id}";
                            var idItinerary = $(this).val();
                            alert(idPoi);
                            alert(idItinerary);
                            
                            $.ajax({    
                                type: "GET",
                                url: "./addPoiItinerary",
                                data: "idPoi=" + idPoi + "&idItinerary=" + idItinerary,

                                success: function(data){
                                    if(data === "ok")
                                    alert("Poi aggiunto all'itinerario!");
                                }
                            });
                            
                            
                        });
                    });
                </script>
            </div>

        </div>
        
    </div>
</article>