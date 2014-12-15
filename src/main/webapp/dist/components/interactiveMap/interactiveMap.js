var interactiveMap = (function() {
    
    var map;
    var markers = new Array();
    var infowindow = new google.maps.InfoWindow();
    var panorama;
    var streetView;
    var bounds;
    var rectangle;
    var drawState = false;

    function drawOnMap() {

        if (!interactiveMap.drawState) {

            interactiveMap.drawState = true;
            google.maps.event.addListener(interactiveMap.map, 'click', function(e) {

                if (interactiveMap.rectangle !== null && interactiveMap.rectangle !== undefined) {

                    interactiveMap.rectangle.setMap(null);
                    interactiveMap.rectangle = null;
                    $('#bounds').hide();
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

                    var ne = interactiveMap.rectangle.getBounds().getNorthEast();
                    var sw = interactiveMap.rectangle.getBounds().getSouthWest();

                    document.getElementById('boundsValue').innerHTML =
                            "Nord Est:<br>"
                            + ne
                            + "<br>Sud Ovest:<br>"
                            + sw;
                    $('#bounds').show();

                    google.maps.event.addListener(interactiveMap.rectangle, 'bounds_changed', function() {

                        var ne = interactiveMap.rectangle.getBounds().getNorthEast();
                        var sw = interactiveMap.rectangle.getBounds().getSouthWest();
                        document.getElementById('boundsValue').innerHTML =
                                "Nord Est:<br>"
                                + ne
                                + "<br>Sud Ovest:<br>"
                                + sw;
                        $('#bounds').show();
                    });
                }
            });
        }
        else {
            google.maps.event.clearListeners(interactiveMap.map, 'click');
            interactiveMap.drawState = false;
            $("#bounds").hide();
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

    //Return the id list for the object to call
    return {
        drawOnMap: drawOnMap,
        viewPanorama: viewPanorama,
        attachInfo: attachInfo,
        map:map,
        markers:markers,
        panorama:panorama,
        streetView:streetView,
        bouds:bounds,
        infowindow:infowindow,
        rectangle:rectangle,
        drawState:drawState      
    };
    
})();

