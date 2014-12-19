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
    var anmData;

    function showPrevision(index, code) {

        var contentString = "<span style='padding:10px' class='fa fa-circle-o-notch fa-spin fa-5x'></span>";
        interactiveMap.infowindow.setContent(contentString);
        interactiveMap.infowindow.open(interactiveMap.map, anmStops[index]);
        anmStops[index].setAnimation(google.maps.Animation.BOUNCE);
        window.setTimeout(function() {
            anmStops[index].setAnimation(null);
        }, 1400);

        $.ajax({
            type: "GET",
            url: "./Services/Anm",
            data: "idStop=" + code,
            success: function(data) {

                var info = JSON.parse(data);

                var contentString = "<div style='width:100px;'>";

                if (info[0].codice === "null") {
                    contentString += "Previsioni non disponibili!";
                }
                else {
                    for (var i = 0; i < info.length; i++) {

                        contentString += "Linea: " + info[i].codice + "    " + info[i].time + "<br>";
                    }
                }

                contentString += "</div>";

                interactiveMap.infowindow.setContent(contentString);
            }
        });
    }

    function showLine()
    {
        $("#anmModal").modal('hide');
        var value = $("#lineValue").val();
        var line;

        if (anmData) {

            for (var z = 0; z < anmStops.length; z++) {

                anmStops[z].setMap(null);
                anmStops[z] = null;
            }

            anmStops = new Array();

            for (var i = 0; i < anmData.length; i++) {

                if (anmData[i].linea === value)
                {
                    line = anmData[i];
                    break;
                }
            }

            for (var j = 0; j < line.percorso.length; j++) {

                var img = './dist/img/andata.png';

                if (line.percorso[j].verso === "Di") {
                    img = './dist/img/ritorno.png';
                }

                anmStops[j] = new google.maps.Marker({
                    position: new google.maps.LatLng(line.percorso[j].location[0], line.percorso[j].location[1]),
                    map: interactiveMap.map,
                    icon: img,
                    title: line.percorso[j].nome});

                anmStops[j].code = line.percorso[j].codice;
                anmStops[j].nome = line.percorso[j].nome;
                anmStops[j].index = j;

                google.maps.event.addListener(anmStops[j], 'click', function() {
                    showPrevision(this.index, this.code);
                });

            }

        }
    }
    function initAnmService() {

        jqxhr = $.getJSON("dist/jsonLinee.json", function() {
            console.log("success");
        })
                .done(function(data) {
                    anmData = data;
                    console.log(anmData);

                })
                .fail(function(jqxhr, textStatus, error) {
                    console.log(textStatus + error);
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

    function showHotels(index) {

        interactiveMap.map.panTo(interactiveMap.markers[index].getPosition());
        var contentString =
                '<div class="container-fluid text-center" style="padding:10px">'
                + '<b>'
                + interactiveMap.markers[index].name
                + '</b><br>'
                + interactiveMap.markers[index].address
                + '<div style="color:gray">'
                + interactiveMap.markers[index].stars + '</div>';
        interactiveMap.infowindow.setContent(contentString);
        interactiveMap.infowindow.open(interactiveMap.map, interactiveMap.markers[index]);
        interactiveMap.markers[index].setAnimation(google.maps.Animation.BOUNCE);
        window.setTimeout(function() {
            interactiveMap.markers[index].setAnimation(null);
        }, 1400);

    }

    function categoryHandler(event) {

        if (event.target === 'hotel' || event.target === 'accommodation') {
  
            $.ajax({
                type: "GET",
                url: "./Services/Ibm/Alberghi",
                data: "",
                success: function(data) {

                    var poi = JSON.parse(data);
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
                                title: poi.nome});

                            interactiveMap.markers[i].index = i;
                            interactiveMap.markers[i].name = poi[i].nome;
                            interactiveMap.markers[i].address = poi[i].indirizzo;
                            interactiveMap.markers[i].stars = poi[i].classificazione;
                            google.maps.event.addListener(interactiveMap.markers[i], 'click', function() {
                                showHotels(this.index);
                            });
                        }
                    }

                }
            });
        }
        else {
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
        initAnmService: initAnmService,
        showLine: showLine
    };
})();

