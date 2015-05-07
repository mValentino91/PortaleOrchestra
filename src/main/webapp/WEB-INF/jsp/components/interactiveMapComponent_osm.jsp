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
                    &nbsp;<spring:message code="label.anmtitle"></spring:message>
                </h4>
            </div>
            <div class="modal-body"> 
                <label>
                    <spring:message code="label.anmline"></spring:message>
                    <input id="lineValue" class="form-control" type="text" placeholder="<spring:message code='label.anminsertline'></spring:message>"/>
                </label>
                <br>
                <button class="btn btn-primary" onclick="interactiveMap.showLine()">
                    <spring:message code="label.anmok"></spring:message>
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
                    &nbsp; <spring:message code="label.advancedsearch"></spring:message>
                </h4>
            </div>
            <div class="modal-body"> 
                <form>
                    <div class="form-group">
                        <input id="nameInputSearchMap" type="text" class="form-control" placeholder="<spring:message code='label.advancedsearchname'></spring:message>"/>
                    </div>
                    <div class="form-group">
                        <input id="addressInputSearchMap" type="text" class="form-control" placeholder="<spring:message code='label.advancedsearchaddress'></spring:message>"/>
                    </div>
                    <div class="form-group">
                        <input id="categoryInputSearchMap" type="text" class="form-control" placeholder="<spring:message code='label.advancedsearchcategory'></spring:message>"/>
                    </div>
                </form>
                <button class="btn btn-primary center-block" onclick="interactiveMap.searchPoi()"><spring:message code="label.search"></spring:message></button>
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
                    <spring:message code="label.results"></spring:message>
                </h4>
            </div>
            <div class="modal-body"> 
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th></th>
                            <th><spring:message code="label.searchname"></spring:message></th>
                            <th><spring:message code="label.searchaddress"></spring:message></th>
                            <th><spring:message code="label.rearchdescription"></spring:message></th>
                            <th><spring:message code="label.nearby"></spring:message></th>
                        </tr>
                    </thead>
                    <tbody id="tableBodySearchResultMap" style="max-height: 50px; overflow: auto; margin-bottom: 20px">
                    </tbody>
                </table>
                <button class="btn btn-primary center-block" onclick="interactiveMap.showSelectedPois()"><spring:message code="label.showonmap"></spring:message></button>
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
                    <spring:message code="label.results"></spring:message>
                </h4>
            </div>
            <div class="modal-body"> 
                <h4 class="text-center"><spring:message code="label.noresult"></spring:message></h4>
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
    /*
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
        $(document).bind("category_all", function(event) {
            interactiveMap.categoryAllHandler(event);
        });
        $(document).bind("category_removed", function(event) {
            interactiveMap.categoryRemoveHandler(event);
        });
        //interactiveMap.initAnmService();

    

        interactiveMap.mcOptions = {maxZoom: 15};

        interactiveMap.mcluster = new MarkerClusterer(interactiveMap.map, interactiveMap.markers, interactiveMap.mcOptions);
    }
    initInteractiveMap();
    */
</script>

<script type="text/javascript">
    //create a map object
    var map = L.map('map');

    //set a view (coordinates of center)
    map.setView([40.847026, 14.257899], 16);

    // create the tile layer with correct attribution
    var osmUrl='http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
    var mapquestUrl='http://otile1.mqcdn.com/tiles/1.0.0/map/{z}/{x}/{y}.jpg';
    var osmHot='http://{s}.tile.openstreetmap.fr/hot/{z}/{x}/{y}.png';
    var osmAttrib='Map data © <a href="http://openstreetmap.org">OpenStreetMap</a> contributors';
    L.tileLayer(osmHot, {
        attribution: osmAttrib,
        maxZoom: 18
            }).addTo(map);
            
    interactiveMap.map = map;
    
    $(document).bind("category_added", function(event) {
        interactiveMap.categoryAddHandler(event);
    });
    $(document).bind("category_all", function(event) {
        interactiveMap.categoryAllHandler(event);
    });
    $(document).bind("category_removed", function(event) {
        interactiveMap.categoryRemoveHandler(event);
    });    
    
    <% int i = 0;%>

    <c:forEach var = "poi" items = "${poiList}">
        var icon = "./dist/img/markers/${poi.categories[0]}/marker.png".toLowerCase();
        var customIcon = L.icon({
		    iconUrl: icon,
		    iconSize:     [13, 13]
		});
                
        interactiveMap.markers[<%=i%>] = L.marker([${poi.location[0]}, ${poi.location[1]}], {icon: customIcon});
        interactiveMap.markers[<%=i%>].id = "${poi.id}";
        interactiveMap.markers[<%=i%>].name = "${poi.name}";
        interactiveMap.markers[<%=i%>].address = "${poi.address}";
        interactiveMap.markers[<%=i%>].categories = "${poi.categories}";
        interactiveMap.markers[<%=i%>].shortDescription = "${poi.shortDescription}";
        interactiveMap.markers[<%=i%>].visibility = "${poi.visibility}";
        
        interactiveMap.markers[<%=i%>].addTo(map);
        
        
        interactiveMap.attachInfo(interactiveMap.markers[<%=i%>]);

        
        /*
        interactiveMap.markers[<%=i%>].on('click', function(p) {
            interactiveMap.attachInfo(p.target);
           
        });
        */

        <%i++;%>
    </c:forEach>
    
    
</script>
<jsp:include page="poiList.jsp"/>