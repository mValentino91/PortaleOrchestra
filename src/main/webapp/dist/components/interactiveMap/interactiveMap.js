var interactiveMap = (function() {

    var map;
    var markers = new Array();
    var anmStops = new Array();
    var infowindow = new google.maps.InfoWindow();
    var panorama;
    var streetView;
    var bounds;
    var rectangle;
    var drawState = false;
    var jqxhr;

    function initAnmService() {

        jqxhr = $.getJSON("dist/jsonLinee.json", function() {
            console.log("success");
        })
                .done(function() {
                    console.log("second success");
                })
                .fail(function(jqxhr, textStatus, error) {
                    console.log(textStatus+error);
                    console.log(jqxhr);
                })
                .always(function() {
                    console.log("complete");
                });

        jqxhr.complete(function() {
            console.log("second complete");
        });
    }

    function drawOnMap() {

        if (!interactiveMap.drawState) {

            interactiveMap.drawState = true;
            google.maps.event.addListener(interactiveMap.map, 'click', function(e) {

                if (interactiveMap.rectangle !== null && interactiveMap.rectangle !== undefined) {

                    interactiveMap.rectangle.setMap(null);
                    interactiveMap.rectangle = null;
                }
                else {

                    interactiveMap.bounds = new google.maps.LatLngBounds(
                            e.latLng,
                            e.latLng
                            );
                    interactiveMap.rectangle = new google.maps.Rectangle({
                        bounds: interactiveMap.bounds,
                        editable: true
                    });
                    interactiveMap.rectangle.setMap(interactiveMap.map);

                    google.maps.event.addListener(interactiveMap.rectangle, 'bounds_changed', function() {
                    });
                }
            });
        }
        else {
            google.maps.event.clearListeners(interactiveMap.map, 'click');
            interactiveMap.drawState = false;
            if (interactiveMap.rectangle !== null && interactiveMap.rectangle !== undefined) {
                interactiveMap.rectangle.setMap(null);
                interactiveMap.rectangle = null;
            }
        }
    }

    function viewPanorama(index) {

        interactiveMap.streetView.getPanoramaByLocation(interactiveMap.markers[index].getPosition(), 30, function(result, status) {

            if (status === google.maps.StreetViewStatus.OK) {

                $("#panoContainer").modal('show');
                window.setTimeout(function() {
                    interactiveMap.panorama.setPosition(markers[index].getPosition());
                    interactiveMap.map.setStreetView(interactiveMap.panorama);
                    interactiveMap.map.getStreetView().setVisible(true);
                }, 500);
            }
            else {
                alert("Il panorama del Punto Di Interesse non e' disponibile!");
            }
        }

        );
    }

    function attachInfo(index) {

        interactiveMap.map.panTo(interactiveMap.markers[index].getPosition());
        var contentString =
                '<div class="container-fluid text-center" style="min-height:170px; max-height:300px; max-width:200px;">'
                + '<b>'
                + interactiveMap.markers[index].name
                + '</b><br>'
                + interactiveMap.markers[index].address
                + '<center><img class="img-rounded" src="./dist/poi/img/'
                + interactiveMap.markers[index].id
                + '/cover.jpg" height="60" style="margin: 5px;" alt=""/></center>'
                + '<p style="color:gray">'
                + interactiveMap.markers[index].shortDescription + '</p>';
        contentString += '<a target="_blank" href="./getPoi?id='
                + interactiveMap.markers[index].id
                + '">Maggiori Informazioni</a>'
                + ' <a style="cursor:pointer" onclick="interactiveMap.viewPanorama('
                + index
                + ')"><br>Guarda nei dintorni</a></div>';
        interactiveMap.infowindow.setContent(contentString);
        interactiveMap.infowindow.open(interactiveMap.map, interactiveMap.markers[index]);
        interactiveMap.markers[index].setAnimation(google.maps.Animation.BOUNCE);
        window.setTimeout(function() {
            interactiveMap.markers[index].setAnimation(null);
        }, 1400);
    }

    function showPois(poi) {

        for (var i = 0; i < interactiveMap.markers.length; i++) {

            interactiveMap.markers[i].setMap(null);
            interactiveMap.markers[i] = null;
        }

        if (poi) {
            interactiveMap.markers = new Array();
            for (var i = 0; i < poi.length; i++) {

                interactiveMap.markers[i] = new google.maps.Marker({
                    position: new google.maps.LatLng(poi[i].location[0], poi[i].location[1]),
                    map: interactiveMap.map,
                    icon: "./dist/img/marker.png",
                    title: poi.name});

                interactiveMap.markers[i].id = poi[i].id;
                interactiveMap.markers[i].index = i;
                interactiveMap.markers[i].name = poi[i].name;
                interactiveMap.markers[i].address = poi[i].address;
                interactiveMap.markers[i].shortDescription = poi[i].shortDescription;
                google.maps.event.addListener(interactiveMap.markers[i], 'click', function() {
                    interactiveMap.attachInfo(this.index);
                });
            }
        }
    }

    function categoryHandler(event) {

        $.ajax({
            type: "GET",
            url: "./Map/JSON",
            data: "category=" + event.target,
            success: function(data) {

                var poi = JSON.parse(data);
                showPois(poi);
            }
        });
    }

//Return the id list for the object to call
    return {
        drawOnMap: drawOnMap,
        viewPanorama: viewPanorama,
        attachInfo: attachInfo,
        categoryHandler: categoryHandler,
        map: map,
        markers: markers,
        panorama: panorama,
        streetView: streetView,
        bouds: bounds,
        infowindow: infowindow,
        rectangle: rectangle,
        drawState: drawState,
        initAnmService: initAnmService
    };
})();

