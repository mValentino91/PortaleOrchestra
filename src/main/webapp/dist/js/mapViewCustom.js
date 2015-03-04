//interactive map
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
    var nearRadius = 0.15;
    var supportIntersectIdList;
    var markersEvent = jQuery.Event("markers_changed");

    function drawCircleAroundPoi(id, radius, enable) {
        var marker = null;
        for (var i = 0; i < interactiveMap.markers.length; i++) {
            if (interactiveMap.markers[i].id === id) {
                marker = interactiveMap.markers[i];
                if (enable === false && marker.circle) {
                    marker.circle.setMap(null);
                }
                break;
            }
        }
        if (marker !== null && enable === true) {
            var circleOptions = {
                strokeColor: '#FF0000',
                strokeOpacity: 0.2,
                strokeWeight: 2,
                fillColor: '#FF0000',
                fillOpacity: 0.2,
                map: interactiveMap.map,
                center: marker.getPosition(),
                radius: radius * 1000
            };
            marker.circle = new google.maps.Circle(circleOptions);
        }
    }
    function markersChangend(markers) {

        markersEvent.target = markers;
        $(document).trigger(markersEvent);
    }
    function poiClickedHandler(id) {
        var object = null;

        for (var i = 0; i < interactiveMap.markers.length; i++) {

            if (interactiveMap.markers[i].id === id) {

                object = interactiveMap.markers[i];
                break;
            }
        }
        if (object !== null) {

            interactiveMap.map.panTo(object.getPosition());
            interactiveMap.map.setZoom(16);
        }
    }
    function poiHoverHandler(id, enable) {

        var object = null;

        for (var i = 0; i < interactiveMap.markers.length; i++) {

            if (interactiveMap.markers[i].id === id) {

                object = interactiveMap.markers[i];
                break;
            }
        }
        if (object !== null) {
            if (enable) {
                object.setIcon('./dist/img/bigMarker.png');
                object.setAnimation(google.maps.Animation.BOUNCE);
            } else {
                object.setIcon('./dist/img/marker.png');
                object.setAnimation(null);
            }
        }
    }
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
        var baseClass = 'btn btn-default btn-xs';
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
            url: "./anmServices/stops/prevision/" + object.code,
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
                '<div class="container-fluid text-center infowindowContent">'
                + '<b>'
                + object.name
                + '</b><br>'
                + object.address
                + '<center><img class="img-rounded" src="./dist/poi/img/'
                + object.id
                + '/cover.jpg" onError="this.src=' + "'./dist/img/notFound.png';" + '"'
                + 'height="60" style="margin: 5px; max-width:110px; height:auto;" alt=""/></center>'
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
    function categoryHandler(event) {
        disableSearchState();
        $('#loadingImg').show();
       
            $.ajax({
                type: "GET",
                url: "./Map/JSON",
                data: "category=" + event.target,
                success: function(data) {
                    var poi = JSON.parse(data);
                    showPois(poi);
                    markersChangend(interactiveMap.markers);
                    $('#loadingImg').hide();
                }
            });
    }
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
        checkSearchButtonChecked: checkSearchButtonChecked,
        poiHoverHandler: poiHoverHandler,
        poiClickedHandler: poiClickedHandler,
        drawCircleAroundPoi: drawCircleAroundPoi
    };
})();

//categoriesTail
var categoriesTail = (function() {
    var indexCategories = 0;
    var openedSlug = new Array();
    var lastSelected = null;

    function labelHandler(slug, id) {
        if ($(id).hasClass('in') && lastSelected === slug) {
            lastSelected = null;
        }
        else {
            triggerEvent(slug);
        }
    }
    function removeCategory(id, slug) {
        $(id).slideUp(150, function() {
            $(id).remove();
        });
        for (var i = 0; i < openedSlug.length; i++) {
            if (openedSlug[i] === slug) {
                delete openedSlug[i];
            }
        }
        if (lastSelected === slug) {
            lastSelected = null;
        }
    }

    function triggerEvent(slug) {
        lastSelected = slug;
        var catEvent = jQuery.Event('category_changed');
        catEvent.target = slug;
        $(document).trigger(catEvent);
    }

    function macroCategoryHandler(slug, title) {
        if (lastSelected !== slug) {
            triggerEvent(slug);
            var finded = false;
            for (var i = 0; i < openedSlug.length; i++) {
                if (openedSlug[i] === slug) {
                    finded = true;
                    break;
                }
            }
            if (!finded) {
                openedSlug.push(slug);
                document.getElementById('categoriesPanelGroup').innerHTML +=
                        '<div class="panel panel-default" style="display:none" id="categoryPanel-' + indexCategories + '">'
                        + '<div class="panel-heading">'
                        + '<a onclick="categoriesTail.labelHandler(' + "'" + slug + "'" + ',' + "'#categoryCollapse-" + indexCategories + "'" + ')" class="' + slug + '" data-toggle="collapse" data-parent="#categoriesPanelGroup" href="#categoryCollapse-' + indexCategories + '">'
                        + title
                        + '</a>'
                        + '<button type="button" class="close"'
                        + 'onclick="categoriesTail.removeCategory(' + "'" + '#categoryPanel-'
                        + indexCategories
                        + "'" + ',' + "'" + slug + "'" + ')">'
                        + '×</button>'
                        + '</div>'
                        + '<div id="categoryCollapse-' + indexCategories + '" class=" panel-collapse collapse out">'
                        + '<div class="panel-body">'
                        + '</div>'
                        + '</div>'
                        + '</div>';
                $('#categoryPanel-' + indexCategories).show(150);
                $('.' + slug).trigger('click');
                indexCategories++;
            } else {
                $('.' + slug).trigger('click');
            }
        }
    }
    return {
        labelHandler: labelHandler,
        removeCategory: removeCategory,
        macroCategoryHandler: macroCategoryHandler
    };
})();

//poilist
var poiList = (function() {

    var timeoutId;

    function initPoiList() {
        $(document).bind("markers_changed", function(event) {
            var markers = event.target;
            document.getElementById('poiListComponent').innerHTML = '';
            var content = '';
            for (var i = 0; i < markers.length; i++) {
                content += ' <div id="' + markers[i].id + '" class="container-fluid poiBox" '
                        + 'onmouseover="poiList.poiBoxDown(' + "'" + markers[i].id + "'" + ', ' + "'" + markers[i].id + "'" + ', true)"'
                        + 'onmouseout="poiList.poiBoxDown(' + "'" + markers[i].id + "'" + ', ' + "'" + markers[i].id + "'" + ', false)"'
                        + 'onmouseleave="poiList.poiBoxUp(' + "'" + markers[i].id + "'" + ',' + "'" + markers[i].id + "'" + ')"'
                        + 'onclick="poiList.poiClicked(' + "'" + markers[i].id + "'" + ')">'
                        + '<div class="col-md-4"> '
                        + '<img class="img-responsive"  style="height:45px;"src="./dist/poi/img/' + markers[i].id + '/cover.jpg" onError="this.src=' + "'" + './dist/img/notFound.png' + "'" + ';" alt=""/>'
                        + '</div>'
                        + '<div class="col-md-6">'
                        + '<span class="text-center" style="color: #242424">'
                        + markers[i].name
                        + '</span>'
                        + '</div>'
                        + '<div class="col-md-1 text-right"><span onmouseover="this.className = ' + "'" + 'fa fa-star fa-2x' + "'" + ';"'
                        + 'onmouseleave="this.className=' + "'" + 'fa fa-star-o fa-2x' + "'" + ';"'
                        + 'onclick="poiList.addToCart(' + "'" + markers[i].id + "'" + ')"'
                        + 'data-toggle="tooltip" title="Aggiungi Ai Preferiti!"'
                        + 'class="fa fa-star-o fa-2x"></span></div>'
                        + '<div class="col-md-12">'
                        + '<p style="padding-top: 5px;" class="poiBoxShortDescription ' + markers[i].id + '">'
                        + '<span style="color:gray; font-size: 11px;">' + markers[i].address
                        + '<br>'
                        + '<label>'
                        + '<input type="checkbox" onclick="interactiveMap.drawCircleAroundPoi(' + "'" + markers[i].id + "'" + ', 0.15, this.checked)">'
                        + ' Nei dintorni (150 metri)'
                        + '</label>'
                        + '</span>'
                        + '<br><br>'
                        + markers[i].shortDescription
                        + '<a href="./getPoi?id=' + markers[i].id + '">Altro...</a>'
                        + '</p>'
                        + '</div>'
                        + '</div>';
            }
            document.getElementById('poiListComponent').innerHTML = content;
        });
    }
    function  poiBoxDown(nameClass, id, enable) {
        if (enable) {
            timeoutId = setTimeout(function() {
                $('.' + nameClass).slideDown();
                interactiveMap.poiHoverHandler(id, true);
            }, 700);
        } else {
            if (timeoutId) {
                clearInterval(timeoutId);
            }
        }
    }
    function poiBoxUp(nameClass, id) {
        $('.' + nameClass).slideUp();
        interactiveMap.poiHoverHandler(id, false);
    }
    function poiClicked(id) {
        interactiveMap.poiClickedHandler(id);
    }
    function addToCart(id) {

        $.ajax({
            type: "GET",
            url: "./Cart/AddPoi",
            data: "id=" + id,
            success: function(data) {

                alert('Aggiunto a preferiti!');
            }
        });
    }
    return {
        poiBoxUp: poiBoxUp,
        poiBoxDown: poiBoxDown,
        initPoiList: initPoiList,
        poiClicked: poiClicked,
        addToCart: addToCart
    };
})();


