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
<script type="text/javascript" src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyBKbphxUcFrE24FYlwrs6K-yzXBguXRhhg&sensor=true" >
</script>
<script type="text/javascript" src = "./dist/components/interactiveMap/interactiveMap.js">
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
</style>
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
                position: google.maps.ControlPosition.RIGHT
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

        map = new google.maps.Map(document.getElementById("map"),
                mapOptions);

    <% int i = 0;%>

    <c:forEach var = "poi" items = "${poiList}">
        markers[<%=i%>] = new google.maps.Marker({
            position: new google.maps.LatLng(${poi.location[0]}, ${poi.location[1]}),
            map: map,
            icon: "./dist/img/marker.png",
            title: "${poi.name}"});
        markers[<%=i%>].id = "${poi.id}";
        markers[<%=i%>].name = "${poi.name}";
        markers[<%=i%>].address = "${poi.address}";
        markers[<%=i%>].categories = "${poi.categories}";
        markers[<%=i%>].shortDescription = "${poi.shortDescription}";
        google.maps.event.addListener(markers[<%=i%>], 'click', function() {
            attachInfo(<%=i%>);
        });
        <%i++;%>
    </c:forEach>

        panorama = new google.maps.StreetViewPanorama(document.getElementById('pano'), panoramaOptions);
        streetView = new google.maps.StreetViewService();

        google.maps.event.addListener(map, 'rightclick', function(e) {

            if (rectangle !== null && rectangle !== undefined) {

                rectangle.setMap(null);
                rectangle = null;
            }
            else {
                bounds = new google.maps.LatLngBounds(
                        e.latLng,
                        e.latLng
                        );

                rectangle = new google.maps.Rectangle({
                    bounds: bounds,
                    editable: true,
                });

                rectangle.setMap(map);

                google.maps.event.addListener(rectangle, 'bounds_changed', function() {
                    var ne = rectangle.getBounds().getNorthEast();
                    var sw = rectangle.getBounds().getSouthWest();

                    alert("Nord Est:" + ne + "\nSud Ovest:" + sw);
                });
            }
        });
    }

    initMap();
</script>
