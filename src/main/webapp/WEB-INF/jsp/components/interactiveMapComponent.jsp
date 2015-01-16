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
<!-- INCLUSIONE GOOGLE-MAPS -->
<script type="text/javascript" 
        src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyBKbphxUcFrE24FYlwrs6K-yzXBguXRhhg&sensor=true" >
</script>
<script type="text/javascript" 
        src = "./dist/components/interactiveMap/markerclusterer.js">
</script>
<script type="text/javascript" 
        src = "./dist/components/interactiveMap/interactiveMap.js">
</script>
<style>
    #mapContainer{
        position: fixed;
        top: 50px;
        bottom:-5px;
        width: 100%;
        padding: 0;
    }
    #map{
        height: 99%;
    }
    #pano{
        height: 400px;
    }
    #mapInfo {
        width: 300px;
        box-shadow: rgba(0, 0, 0, 0.298039) 0px 1px 4px -1px;
        background-color: white;
        padding: 10px;
        font-family: Roboto, Arial;
        font-size: 13px;
        margin: 10px;
    }
</style>
<!--CONTROLLI MAPPA
===================================================-->
<div id="mapControls" style="margin: 10px;">
    <div  class="btn-group" role="group" data-toggle="buttons-checkbox">
        <button type="button" class="btn btn-default" onclick="$('#searchModal').modal('show')">
            <i class="fa fa-search"></i>
        </button>
        <button type="button" class="btn btn-default">
            <i class="glyphicon glyphicon-road"></i>
        </button>
        <button type="button" class="btn btn-default" onclick="interactiveMap.anmHandler()">
            <i class="fa fa-bus"></i>
        </button>
    </div>
</div>
<!--/CONTROLLI MAPPA
===================================================-->
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
     tabindex="-1" 
     role="dialog" 
     aria-labelledby="mySmallModalLabe2" 
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
                    <span class="glyphicon glyphicon-search">
                    </span>
                    &nbsp; Ricerca Avanzata
                </h4>
            </div>
            <div class="modal-body"> 
                <form>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Nome"/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Indirizzo"/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Categoria"/>
                    </div>
                    <div class="form-group">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Cerca i POI nei dintorni
                            </label>
                        </div>
                    </div>
                </form>
                <button class="btn btn-primary center-block" onclick="$('#searchResultModal').modal('show')">Cerca!</button>
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
     >
    <div class="modal-dialog" style="width:800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" 
                        class="close" 
                        data-dismiss="modal" 
                        aria-hidden="true">&times;</button>
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
                            <th>Categoria</th>
                            <th>Nei Dintorni</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="checkbox"/></td>
                            <td>Pio Monte della misericordia</td>
                            <td>Via dei Tribunali 253, 80138 Napoli</td>
                            <td>Culture</td>
                            <td><input type="checkbox"/></td>
                        </tr>
                    </tbody>
                </table>
                <button class="btn btn-primary center-block" onclick="alert('cerco!')">Mostra Su Mappa!</button>
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

        var mapOptions = {
            center: new google.maps.LatLng(40.8485091, 14.25574759999995),
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            zoomControl: true,
            panControl: false,
            mapTypeControl: true,
            streetViewControl: false,
            zoomControlOptions: {
                position: google.maps.ControlPosition.RIGHT
            },
            mapTypeControlOptions: {
                position: google.maps.ControlPosition.BOTTOM
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

        var mapControls = document.getElementById('mapControls');
        interactiveMap.map.controls[google.maps.ControlPosition.TOP].push(mapControls);

        $(document).bind("category_changed", function(event) {
            interactiveMap.categoryHandler(event);
        });

        interactiveMap.initAnmService();

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
