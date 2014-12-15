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
<!--INFO MAP
===================================================-->
<div id="mapInfo">
    <center>
        <b>
            <span class="glyphicon glyphicon-map-marker"></span>
            Dettagli Mappa
        </b>
    </center>
    <div id="categoryOnMap">
        <b>Categoria: </b>Tutti
    </div>
    <div id="bounds" style="display: none">
        <b>Area:</b>
        <br>
        <span id="boundsValue"></span>
    </div>
</div>
<!--/INFO MAP
===================================================-->
<!--CONTROLLI MAPPA
===================================================-->
<div id="mapControls" style="margin: 10px;">
    <div  class="btn-group" role="group" data-toggle="buttons-checkbox">
        <button type="button" class="btn btn-default" 
                onclick="interactiveMap.drawOnMap()">
            <i class="fa fa-pencil-square-o"></i>
        </button>
    </div>
    <div  class="btn-group" role="group" data-toggle="buttons-checkbox">
        <button type="button" class="btn btn-primary">
            <i class="fa fa-search"></i>
        </button>
        <button type="button" class="btn btn-primary">
            <i class="glyphicon glyphicon-road"></i>
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
<!--Map
===================================================-->
<div id="mapContainer">
    <div id="map" class="col-md-12">
    </div>
</div>
<!--/Map
===================================================-->
<script>
    function initMap() {

        var mapOptions = {
            center: new google.maps.LatLng(40.8485091, 14.25574759999995),
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            zoomControl: true,
            panControl: false,
            mapTypeControl: true,
            streetViewControl: false,
            zoomControlOptions: {
                position: google.maps.ControlPosition.LEFT
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

    <% int i = 0;%>

    <c:forEach var = "poi" items = "${poiList}">
        interactiveMap.markers[<%=i%>] = new google.maps.Marker({
            position: new google.maps.LatLng(${poi.location[0]}, ${poi.location[1]}),
            map: interactiveMap.map,
            icon: "./dist/img/marker.png",
            title: "${poi.name}"});
        interactiveMap.markers[<%=i%>].id = "${poi.id}";
        interactiveMap.markers[<%=i%>].name = "${poi.name}";
        interactiveMap.markers[<%=i%>].address = "${poi.address}";
        interactiveMap.markers[<%=i%>].categories = "${poi.categories}";
        interactiveMap.markers[<%=i%>].shortDescription = "${poi.shortDescription}";
        google.maps.event.addListener(interactiveMap.markers[<%=i%>], 'click', function() {
            interactiveMap.attachInfo(<%=i%>);
        });
        <%i++;%>
    </c:forEach>

        interactiveMap.panorama = new google.maps.StreetViewPanorama(document.getElementById('pano'), panoramaOptions);
        interactiveMap.streetView = new google.maps.StreetViewService();

        var mapInfo = document.getElementById('mapInfo');
        var mapControls = document.getElementById('mapControls');
        interactiveMap.map.controls[google.maps.ControlPosition.TOP_RIGHT].push(mapInfo);
        interactiveMap.map.controls[google.maps.ControlPosition.TOP].push(mapControls);
    }

    initMap();
</script>
