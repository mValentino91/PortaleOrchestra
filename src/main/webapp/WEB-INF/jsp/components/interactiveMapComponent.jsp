<%-- 
    Document   : interactiveMap
    Created on : 11-dic-2014, 17.44.55
    Author     : Marco Valentino
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!--STREET VIEW
===================================================-->
<div class="modal fade"
     id="panoContainer"
     tabindex="-1" 
     role="dialog" 
     aria-labelledby="mySmallModalLabel" 
     aria-hidden="true"
     >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" 
                        class="close" 
                        data-dismiss="modal" 
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">
                    <span class="glyphicon glyphicon-map-marker">
                    </span>
                    &nbsp;Street View
                </h4>
            </div>
            <div class="modal-body">   
                <div id="pano">
                </div>
            </div>
        </div>
    </div>
</div>
<!--/STREET VIEW
===================================================-->
<div class="modal fade"
     id="anmModal"
     tabindex="-1" 
     role="dialog" 
     aria-labelledby="mySmallModalLabel" 
     aria-hidden="true"
     >
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" 
                        class="close" 
                        data-dismiss="modal" 
                        aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">
                    <span class="glyphicon glyphicon-map-marker">
                    </span>
                    &nbsp;ANM Service
                </h4>
            </div>
            <div class="modal-body"> 
                <label>
                    Linea:
                    <input id="lineValue" class="form-control" type="text" placeholder="Inserisci Linea"/>
                </label>
                <br>
                <button class="btn btn-primary" onclick="interactiveMap.showLine()">
                    Ok
                </button>
            </div>
        </div>
    </div>
</div>
<!--SEARCH MODAL
===============================================-->
<div class="modal fade"
     id="searchModal"
     data-backdrop="static" 
     data-keyboard="false"
     >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" 
                        class="close" 
                        data-dismiss="modal" 
                        aria-hidden="true"
                        onclick="interactiveMap.checkSearchButtonChecked()"
                        >&times;</button>
                <h4 class="modal-title" id="myModalLabel">
                    <span class="glyphicon glyphicon-search">
                    </span>
                    &nbsp; Ricerca Avanzata
                </h4>
            </div>
            <div class="modal-body"> 
                <form>
                    <div class="form-group">
                        <input id="nameInputSearchMap" type="text" class="form-control" placeholder="Nome"/>
                    </div>
                    <div class="form-group">
                        <input id="addressInputSearchMap" type="text" class="form-control" placeholder="Indirizzo"/>
                    </div>
                    <div class="form-group">
                        <input id="categoryInputSearchMap" type="text" class="form-control" placeholder="Categoria"/>
                    </div>
                </form>
                <button class="btn btn-primary center-block" onclick="interactiveMap.searchPoi()">Cerca!</button>
            </div>
        </div>
    </div>
</div>
<!--/SEARCH MODAL
===================================================-->
<!--Search Result Modal
===================================================-->
<div class="modal fade"
     id="searchResultModal"
     data-backdrop="static" 
     data-keyboard="false"
     >
    <div class="modal-dialog" style="width:800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" 
                        class="close" 
                        data-dismiss="modal" 
                        aria-hidden="true"
                        onclick="interactiveMap.checkSearchButtonChecked()"
                        >&times;</button>
                <h4 class="modal-title">
                    <span class="glyphicon glyphicon-star">      
                    </span> 
                    Risultati
                </h4>
            </div>
            <div class="modal-body"> 
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th></th>
                            <th>Nome</th>
                            <th>Indirizzo</th>
                            <th>Descrizione</th>
                            <th>Nei Dintorni</th>
                        </tr>
                    </thead>
                    <tbody id="tableBodySearchResultMap" style="max-height: 50px; overflow: auto; margin-bottom: 20px">
                    </tbody>
                </table>
                <button class="btn btn-primary center-block" onclick="interactiveMap.showSelectedPois()">Mostra Su Mappa!</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade"
     id="searchNoResultModal"
     data-backdrop="static" 
     data-keyboard="false"
     >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" 
                        class="close" 
                        data-dismiss="modal" 
                        aria-hidden="true"
                        onclick="interactiveMap.checkSearchButtonChecked()"
                        >&times;</button>
                <h4 class="modal-title">
                    <span class="glyphicon glyphicon-star">      
                    </span> 
                    Risultati
                </h4>
            </div>
            <div class="modal-body"> 
                <h4 class="text-center">Nessun Risultato!</h4>
            </div>
        </div>
    </div>
</div>
<!--/Search Result Modal
====================================================-->
<!--Map
===================================================-->
<div id="mapContainer">
    <div id="map" class="col-md-12">
    </div>
</div>
<!--/Map
===================================================-->
<script>
    function initInteractiveMap() {

        var mapStyle = [{
                featureType: "poi",
                elementType: "labels",
                stylers: [
                    {visibility: "off"}
                ]
            }];

        var mapOptions = {
            center: new google.maps.LatLng(40.8485091, 14.25574759999995),
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            styles: mapStyle,
            zoomControl: true,
            panControl: false,
            mapTypeControl: true,
            streetViewControl: false,
            zoomControlOptions: {
                position: google.maps.ControlPosition.LEFT
            },
            mapTypeControlOptions: {
                position: google.maps.ControlPosition.TOP
            }
        };

        var panoramaOptions = {
            position: new google.maps.LatLng(40.8485091, 14.25574759999995),
            pov: {
                heading: 0,
                pitch: 0
            }
        };
        interactiveMap.map = new google.maps.Map(document.getElementById("map"),
                mapOptions);

        interactiveMap.panorama = new google.maps.StreetViewPanorama(document.getElementById('pano'), panoramaOptions);
        interactiveMap.streetView = new google.maps.StreetViewService();

        $(document).bind("category_added", function(event) {
            interactiveMap.categoryAddHandler(event);
        });
        $(document).bind("category_removed", function(event) {
            interactiveMap.categoryRemoveHandler(event);
        });
        //interactiveMap.initAnmService();

    <% int i = 0;%>

    <c:forEach var = "poi" items = "${poiList}">
        interactiveMap.markers[<%=i%>] = new google.maps.Marker({
            position: new google.maps.LatLng(${poi.location[0]}, ${poi.location[1]}),
            icon: "./dist/img/marker.png",
            title: "${poi.name}"});
        interactiveMap.markers[<%=i%>].id = "${poi.id}";
        interactiveMap.markers[<%=i%>].name = "${poi.name}";
        interactiveMap.markers[<%=i%>].address = "${poi.address}";
        interactiveMap.markers[<%=i%>].categories = "${poi.categories}";
        interactiveMap.markers[<%=i%>].shortDescription = "${poi.shortDescription}";
        google.maps.event.addListener(interactiveMap.markers[<%=i%>], 'click', function() {
            interactiveMap.attachInfo(this);
        });
        <%i++;%>
    </c:forEach>

        interactiveMap.mcOptions = {maxZoom: 15};

        interactiveMap.mcluster = new MarkerClusterer(interactiveMap.map, interactiveMap.markers, interactiveMap.mcOptions);
    }
    initInteractiveMap();
</script>
<jsp:include page="poiList.jsp"/>