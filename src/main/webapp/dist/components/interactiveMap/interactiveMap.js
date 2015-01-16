var interactiveMap = (function() {

    var map;
    var markers = new Array();
    var anmStops = new Array();
    var infowindow = new google.maps.InfoWindow();
    var panorama;
    var streetView;
    var jqxhr;
    var mcOptions;
    var mcluster;
    var anmData;
    var anmState = false;

    function anmHandler() {

        //ANM GIA' ATTIVO SULLA MAPPA
        if (anmState) {
            for (var z = 0; z < anmStops.length; z++) {

                anmStops[z].setMap(null);
                anmStops[z] = null;
            }
            
            anmStops=new Array;
            anmState=false;
        }
        else{
            $('#anmModal').modal('show');
            anmState=true;
        }
    }

    function showPrevision(object) {

        var contentString = "<span style='padding:10px' class='fa fa-circle-o-notch fa-spin fa-5x'></span>";
        interactiveMap.infowindow.setContent(contentString);
        interactiveMap.infowindow.open(interactiveMap.map, object);
        object.setAnimation(google.maps.Animation.BOUNCE);
        window.setTimeout(function() {
            object.setAnimation(null);
        }, 1400);

        $.ajax({
            type: "GET",
            url: "./Services/Anm",
            data: "idStop=" + object.code,
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

    function showLine() {
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
                    showPrevision(this);
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

    function viewPanorama(index) {

        interactiveMap.streetView.getPanoramaByLocation(interactiveMap.markers[index].getPosition(), 30, function(result, status) {

            if (status === google.maps.StreetViewStatus.OK) {

                $("#panoContainer").modal('show');
                window.setTimeout(function() {
                    interactiveMap.panorama.setPosition(interactiveMap.markers[index].getPosition());
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

    function attachInfo(object) {

        interactiveMap.map.panTo(object.getPosition());
        var contentString =
                '<div class="container-fluid text-center" style="min-height:170px; max-height:300px; max-width:200px;">'
                + '<b>'
                + object.name
                + '</b><br>'
                + object.address
                + '<center><img class="img-rounded" src="./dist/poi/img/'
                + object.id
                + '/cover.jpg" height="60" style="margin: 5px; max-width:110px; height:auto;" alt=""/></center>'
                + '<p style="color:gray">'
                + object.shortDescription + '</p>';
        contentString += '<a href="./getPoi?id='
                + object.id
                + '">Maggiori Informazioni</a>'
                + ' <a style="cursor:pointer" onclick="interactiveMap.viewPanorama('
                + object.index
                + ')"><br>Guarda nei dintorni</a></div>';
        interactiveMap.infowindow.setContent(contentString);
        interactiveMap.infowindow.open(interactiveMap.map, object);
        object.setAnimation(google.maps.Animation.BOUNCE);
        window.setTimeout(function() {
            object.setAnimation(null);
        }, 1400);
    }

    function showPois(poi) {

        interactiveMap.mcluster.removeMarkers(interactiveMap.markers);

        for (var i = 0; i < interactiveMap.markers.length; i++) {

            interactiveMap.markers[i] = null;
        }

        if (poi) {
            interactiveMap.markers = new Array();
            for (var i = 0; i < poi.length; i++) {

                interactiveMap.markers[i] = new google.maps.Marker({
                    position: new google.maps.LatLng(poi[i].location[0], poi[i].location[1]),
                    icon: "./dist/img/marker.png",
                    title: poi.name});

                interactiveMap.markers[i].id = poi[i].id;
                interactiveMap.markers[i].index = i;
                interactiveMap.markers[i].name = poi[i].name;
                interactiveMap.markers[i].address = poi[i].address;
                interactiveMap.markers[i].shortDescription = poi[i].shortDescription;
                google.maps.event.addListener(interactiveMap.markers[i], 'click', function() {
                    interactiveMap.attachInfo(this);
                });
            }

            interactiveMap.mcluster.addMarkers(interactiveMap.markers);
        }
    }

    function showHotels(object) {

        var contentString = "<span style='padding:10px' class='fa fa-circle-o-notch fa-spin fa-5x'></span>";
        interactiveMap.infowindow.setContent(contentString);
        interactiveMap.infowindow.open(interactiveMap.map, object);
        object.setAnimation(google.maps.Animation.BOUNCE);
        window.setTimeout(function() {
            object.setAnimation(null);
        }, 1400);

        $.ajax({
            type: "GET",
            url: "./Services/Ibm/Albergo",
            data: "idHotel=" + object.id,
            success: function(data) {

                var info = JSON.parse(data);

                interactiveMap.map.panTo(object.getPosition());
                var contentString =
                        '<div class="container-fluid text-center" style="padding:10px">'
                        + '<b>'
                        + object.name
                        + '</b><br>'
                        + object.address
                        + '<br><span>'
                        + object.stars + '</span>'
                        + '<p class="text-left" style="color:gray">Web: ' + info.web +
                        '<br> Email: ' + info.email +
                        '</p></div>';
                interactiveMap.infowindow.setContent(contentString);

            }
        });
    }

    function categoryHandler(event) {

        if (event.target === 'hotel' || event.target === 'accommodation') {

            $.ajax({
                type: "GET",
                url: "./Services/Ibm/Alberghi",
                data: "",
                success: function(data) {

                    var poi = JSON.parse(data);
                    interactiveMap.mcluster.removeMarkers(interactiveMap.markers);

                    for (var i = 0; i < interactiveMap.markers.length; i++) {

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
                            interactiveMap.markers[i].id = poi[i].id;
                            interactiveMap.markers[i].name = poi[i].nome;
                            interactiveMap.markers[i].address = poi[i].indirizzo;
                            interactiveMap.markers[i].stars = poi[i].classificazione;
                            google.maps.event.addListener(interactiveMap.markers[i], 'click', function() {
                                showHotels(this);
                            });
                        }

                        interactiveMap.mcluster.addMarkers(interactiveMap.markers);
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
        viewPanorama: viewPanorama,
        attachInfo: attachInfo,
        categoryHandler: categoryHandler,
        map: map,
        markers: markers,
        panorama: panorama,
        streetView: streetView,
        infowindow: infowindow,
        initAnmService: initAnmService,
        anmHandler:anmHandler,
        showLine: showLine,
        mcluster: mcluster,
        mcOptions: mcOptions
    };
})();

