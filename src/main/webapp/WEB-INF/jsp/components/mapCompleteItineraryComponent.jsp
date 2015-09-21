<%-- 
    Document   : mapPageComponent
    Created on : 23-apr-2015, 11.28.58
    Author     : Alex
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- INCLUSIONE GOOGLE-MAPS -->
<script type="text/javascript"src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyBKbphxUcFrE24FYlwrs6K-yzXBguXRhhg&sensor=true" >
</script>
<article class="component component-product">
    <div id="map" style="height: 210px;">

    </div>

    <div class="details">
        <div class="actions">
            <a href="Map?category=all" class="btn btn-round btn-soft-nbar">
                <span class="icon-map"></span>
            </a>           
        </div>
        <p style="margin-top: 5px">${poi.address}</p>
        <div class="intents">
            <span class="count" style="font-size: 14px; font-weight: normal;"><a href="./Map?category=all" >Vai alla mappa completa</a></span>
        </div>
    </div>
</article>  



<script>
	var infowindowprec;
    
	function attachInfo(object) {
    	map.panTo(object.getPosition());
       	
		var contentString =
        	'<div class="container-fluid text-center infowindowContent">'
            + '<b>'
            + object.name
            + '</b><br>';
            contentString += '<a href="./getPoi?id='
            + object.id
            + '">Maggiori Informazioni</a></div>';
            
		var infowindow = new google.maps.InfoWindow({
        	content: contentString
        });
        
		infowindowprec = infowindow;
        infowindow.open(map, object);
        object.setAnimation(google.maps.Animation.BOUNCE);
        
		window.setTimeout(function() {
	        object.setAnimation(null);
        }, 1400);
    }

    function initMap() {

        var mapStyle = [{
            featureType: "poi",
            elementType: "labels",
            stylers: [
                    {visibility: "off"}
            ]
        }];
            
        var mapOptions = {
            center: new google.maps.LatLng(40.852114, 14.268112),
            styles:mapStyle,
                    zoom: 11,
                mapTypeId: google.maps.MapTypeId.ROADMAP
        };
            
        map = new google.maps.Map(document.getElementById("map"),mapOptions);


	<c:forEach var="poi" items="${map_poi.keySet()}">

            var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(${map_poi.get(poi).location[0]}, ${map_poi.get(poi).location[1]}),
                    map: map,
                    icon: "./dist/img/markers/${map_poi.get(poi).categories[0]}/marker.png",
                    title: "${map_poi.get(poi).name}"
            });
            
            marker.id = "${map_poi.get(poi).id}";
            marker.name = "${map_poi.get(poi).name}";

            google.maps.event.addListener(marker, 'click', function() {
                if (infowindowprec) {
                        infowindowprec.close();
                }
                attachInfo(this);
            });
        </c:forEach>
    }
    initMap();
</script>
