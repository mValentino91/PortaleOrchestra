<%-- 
    Document   : mapComponent
    Created on : 2-dic-2014, 22.30.13
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- INCLUSIONE GOOGLE-MAPS -->
<script type="text/javascript"src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyBKbphxUcFrE24FYlwrs6K-yzXBguXRhhg&sensor=true" >
</script>
<div class="panel panel-default">
    <div style="max-height: 3px; height: 3px; background-color: #ef5350;"></div>
    <div class="panel-thumbnail">
        <div id="map" style="height: 200px;">

        </div>
    </div>
    <div class="panel-body">
        ${poi.address}
        <a href="Map?category=all" style="float: right; font-size: 200%; text-decoration: none;" class="glyphicon glyphicon-globe"></a><br>

    </div>
</div>
        
<script>
    function initMap() {
        var mapOptions = {
            center: new google.maps.LatLng(${poi.location[0]}, ${poi.location[1]}),
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        map = new google.maps.Map(document.getElementById("map"),
                mapOptions);

        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(${poi.location[0]}, ${poi.location[1]}),
            map: map,
            icon:"./dist/img/bigMarker.png",
            title: "${poi.name}"});
    }
    initMap();
</script>
