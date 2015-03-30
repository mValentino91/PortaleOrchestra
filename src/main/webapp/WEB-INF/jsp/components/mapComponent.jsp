<%-- 
    Document   : mapComponent
    Created on : 2-dic-2014, 22.30.13
    Author     : Marco Valentino
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
        <div id="map" style="height: 250px;">

        </div>
                  
<div class="details">
				<div class="actions">
				  <a href="Map?category=all" class="btn btn-round btn-soft-blue">
					<span class="icon-map"></span>
				  </a>           
				</div>
				<p style="margin-top: 5px">${poi.address}</p>
				<div class="intents">
				  <span class="count">Nelle vicinanze</span>
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
       infowindowprec= infowindow;
       infowindow.open(map, object);
        object.setAnimation(google.maps.Animation.BOUNCE);
        window.setTimeout(function() {
            object.setAnimation(null);
        }, 1400);
    }
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
    
    //POI NELLE VICINANZE ( 300 METRI ) 
    <c:forEach var = "poiv" items = "${poivicini}">
     <c:if test="${!poi.name.equals(poiv.name)}">
    var marker = new google.maps.Marker({
            position: new google.maps.LatLng(${poiv.location[0]}, ${poiv.location[1]}),
            map: map,
            icon:"./dist/img/marker.png",
            title: "${poiv.name}"});
      
            marker.id= '${poiv.id}';
            marker.name ='${poiv.name}';
      
         
    
      google.maps.event.addListener(marker, 'click', function() {
           if(infowindowprec) {
               infowindowprec.close();
        }
            attachInfo(this);
  });
  </c:if>
  </c:forEach>
  }
    initMap();
</script>
