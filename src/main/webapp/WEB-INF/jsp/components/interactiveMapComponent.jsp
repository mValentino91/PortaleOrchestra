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
<script type="text/javascript"src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyBKbphxUcFrE24FYlwrs6K-yzXBguXRhhg&sensor=true" >
</script>

<div id="map" style="height: 100%;">
</div>
<script>
    var markers = new Array();
    var infowindow = new google.maps.InfoWindow();

    function attachInfo(index) {

        map.setCenter(markers[index].getPosition());
        var contentString = '<b>'
                + markers[index].name
                + '</b><br>'
                + markers[index].categories
                + '<br>'
                + markers[index].address
                + '<br><br>'
                + markers[index].shortDescription;

        contentString += '<br><a target="_blank" href="./getPoi?id='
                + markers[index].id
                + '">Maggiori Informazioni</a>';
        
        infowindow.setContent(contentString);
        infowindow.open(map, markers[index]);
        markers[index].setAnimation(google.maps.Animation.BOUNCE);
        window.setTimeout(function() {
            markers[index].setAnimation(null);
        }, 1400);
    }

    function initMap() {
        var mapOptions = {
            center: new google.maps.LatLng(40.8485091, 14.25574759999995),
            zoom: 15,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };

        map = new google.maps.Map(document.getElementById("map"),
                mapOptions);

    <% int i = 0;%>

    <c:forEach var = "poi" items = "${poiList}">
        markers[<%=i%>] = new google.maps.Marker({
            position: new google.maps.LatLng(${poi.location[0]}, ${poi.location[1]}),
            map: map,
            icon:"./dist/img/marker.png",
            title: "${poi.name}"});
        markers[<%=i%>].id = "${poi.id}";
        markers[<%=i%>].name = "${poi.name}";
        markers[<%=i%>].address = "${poi.address}";
        markers[<%=i%>].categories="${poi.categories}";
        markers[<%=i%>].shortDescription = "${poi.shortDescription}";
        google.maps.event.addListener(markers[<%=i%>], 'click', function() {
            attachInfo(<%=i%>);
        });
        <%i++;%>
    </c:forEach>
    }

    initMap();
</script>
