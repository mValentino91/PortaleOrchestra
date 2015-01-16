var interactiveMap = (function() {
    var map;
    var mcOptions;
    var mcluster;
    var markers = new Array();
    var infowindow = new google.maps.InfoWindow();
    var panorama;
    var streetView;
    var anmState = false;
    var anmStops = new Array();
    var anmData;
    var anmLineeJson;
    var searchState = false;
    var searchedPoi;
    var searchedMarkers;
    var nearRadius = 0.1;
    var supportIntersectIdList;

    function searchHandler() {
        if (searchState === false) {
            $('#searchModal').modal('show');
        }
        else {
            disableSearchState();
        }
    }
    function disableSearchState() {
        if (searchState === true) {
            supportIntersectIdList = null;
            for (var z = 0; z < searchedMarkers.length; z++) {
                if (searchedMarkers[z] !== null && searchedMarkers[z] !== undefined) {
                    if (searchedMarkers[z].circle !== null && searchedMarkers[z].circle !== undefined)
                        searchedMarkers[z].circle.setMap(null);
                    if (searchedMarkers[z].nearPois !== null && searchedMarkers[z].nearPois !== undefined) {
                        for (var k = 0; k < searchedMarkers[z].nearPois.length; k++) {
                            if (searchedMarkers[z].nearPois[k] !== null && searchedMarkers[z].nearPois[k] !== undefined) {
                                searchedMarkers[z].nearPois[k].setMap(null);
                                searchedMarkers[z].nearPois[k] = null;
                            }
                        }
                    }
                    searchedMarkers[z].setMap(null);
                    searchedMarkers[z] = null;
                }
            }
            searchedMarkers = new Array();
            searchState = false;
        }
    }
    function searchNearPois(index) {
        $.ajax({
            type: "GET",
            url: "./Search/JSON/Near",
            data: "id=" + searchedMarkers[index].id + "&radius=" + nearRadius,
            success: function(data) {
                var pois = JSON.parse(data);
                var circleOptions = {
                    strokeColor: '#FF0000',
                    strokeOpacity: 0.2,
                    strokeWeight: 2,
                    fillColor: '#FF0000',
                    fillOpacity: 0.2,
                    map: interactiveMap.map,
                    center: new google.maps.LatLng(
                            searchedMarkers[index].location[0],
                            searchedMarkers[index].location[1]),
                    radius: nearRadius * 1000
                };
                searchedMarkers[index].circle = new google.maps.Circle(circleOptions);
                searchedMarkers[index].nearPois = new Array();
                for (var i = 0; i < pois.length; i++) {
                    var finded = false;
                    for (var j = 0; j < supportIntersectIdList.length; j++) {
                        if (supportIntersectIdList[j] === pois[i].id) {
                            finded = true;
                            break;
                        }
                    }
                    if (finded === false) {
                        searchedMarkers[index].nearPois[i] = new google.maps.Marker({
                            position: new google.maps.LatLng(pois[i].location[0],
                                    pois[i].location[1]),
                            map: interactiveMap.map,
                            icon: './dist/img/marker.png',
                            title: pois[i].name});
                        supportIntersectIdList.push(pois[i].id);
                        searchedMarkers[index].nearPois[i].id = pois[i].id;
                        searchedMarkers[index].nearPois[i].name = pois[i].name;
                        searchedMarkers[index].nearPois[i].address = pois[i].address;
                        searchedMarkers[index].nearPois[i].shortDescription = pois[i].shortDescription;
                        searchedMarkers[index].nearPois[i].location = pois[i].location;

                        google.maps.event.addListener(searchedMarkers[index].nearPois[i], 'click', function() {
                            attachInfo(this);
                        });
                    }
                }
            }
        });
    }
    function showSelectedPois() {
        var checkList = $('.checkboxForSearchResult');
        var nearCheckList = $('.checkboxForSearchAround');
        var count = 0;

        if (checkList.length > 0) {

            searchedMarkers = new Array();
            supportIntersectIdList = new Array();

            for (var i = 0; i < checkList.length; i++) {

                if (checkList[i].checked) {

                    var temp = searchedPoi[checkList[i].value];

                    searchedMarkers[count] = new google.maps.Marker({
                        position: new google.maps.LatLng(
                                temp.location[0],
                                temp.location[1]),
                        map: interactiveMap.map,
                        icon: './dist/img/bigMarker.png',
                        title: temp.name});

                    supportIntersectIdList.push(temp.id);

                    searchedMarkers[count].location = temp.location;
                    searchedMarkers[count].id = temp.id;
                    searchedMarkers[count].name = temp.name;
                    searchedMarkers[count].address = temp.address;
                    searchedMarkers[count].shortDescription = temp.shortDescription;

                    google.maps.event.addListener(searchedMarkers[count], 'click', function() {
                        attachInfo(this);
                    });

                    count++;
                }
            }
            if (count > 0) {
                interactiveMap.mcluster.removeMarkers(interactiveMap.markers);
                for (var i = 0; i < interactiveMap.markers.length; i++) {

                    interactiveMap.markers[i] = null;
                }
                searchState = true;
            }
            for (var j = 0; j < nearCheckList.length; j++) {

                if (nearCheckList[j].checked)
                    searchNearPois(j);
            }
            $('#searchResultModal').modal('hide');
            checkSearchButtonChecked();
        }
    }
    function checkSearchButtonChecked() {
        var baseClass = 'btn btn-default';
        if (searchState === true) {
            var element = document.getElementById('searchMapCheckButton');
            element.className = baseClass + ' active';
        } else {
            var element = document.getElementById('searchMapCheckButton');
            element.className = baseClass;
        }
    }
    function searchPoi() {
        var name = $('#nameInputSearchMap').val();
        var address = $('#addressInputSearchMap').val();
        var category = $('#categoryInputSearchMap').val();
        $.ajax({
            type: "GET",
            url: "./Search/JSON/Find",
            data: "name=" + name + "&address=" + address + "&category=" + category,
            success: function(data) {
                $('#searchModal').modal('hide');
                var pois = JSON.parse(data);
                searchedPoi = pois;
                if (pois.length === 0) {
                    $('#searchNoResultModal').modal('show');
                }
                else {
                    var contentString = "";
                    for (var i = 0; i < pois.length; i++) {
                        contentString += "<tr><td><input type='checkbox' class='checkboxForSearchResult' checked='true' value='"
                                + i + "'/></td>"
                                + "<td>" + pois[i].name + "</td>"
                                + "<td>" + pois[i].address + "</td>"
                                + "<td>" + pois[i].shortDescription + "</td>"
                                + "<td><input type='checkbox' class='checkboxForSearchAround'/></td></tr>";
                    }
                    document.getElementById('tableBodySearchResultMap').innerHTML = contentString;
                    $('#searchResultModal').modal('show');
                }
            }
        });
    }
    function anmHandler() {
        //ANM GIA' ATTIVO SULLA MAPPA
        if (anmState) {
            for (var z = 0; z < anmStops.length; z++) {
                anmStops[z].setMap(null);
                anmStops[z] = null;
            }
            anmStops = new Array();
            anmState = false;
        }
        else {
            $('#anmModal').modal('show');
            anmState = true;
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
                    position: new google.maps.LatLng(line.percorso[j].location[0],
                            line.percorso[j].location[1]),
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
        anmLineeJson = $.getJSON("dist/jsonLinee.json", function() {
            console.log("success");
        })
                .done(function(data) {
                    anmData = data;
                })
                .fail(function(anmLineeJson, textStatus, error) {
                    console.log(textStatus + error);
                    console.log(anmLineeJson);
                })
                .always(function() {
                    console.log("complete");
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
        disableSearchState();
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
        anmHandler: anmHandler,
        showLine: showLine,
        mcluster: mcluster,
        mcOptions: mcOptions,
        searchHandler: searchHandler,
        showSelectedPois: showSelectedPois,
        searchPoi: searchPoi,
        checkSearchButtonChecked: checkSearchButtonChecked
    };
})();

