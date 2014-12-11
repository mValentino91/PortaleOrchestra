
//Funzione per l'inizializzazione della pagina
function initPage() {

    var divPOI = document.getElementById('poiList');
    //creazione dinamica del div dei punti di interesse


    //assegnazione delle immagini ai markers in base alla categoria
    var cult = './img/cultura.png';
    var div = './img/divertimento.png';
    var mang = './img/mangiare.png';
    var dorm = './img/pernottamento.png';
    var arts = './img/arts.png';
    var street = './img/street.png';

    for (var i = 0; i < arrayMarkers.length; i++)
    {
        if (arrayMarkers[i].category === 'culture')
        {
            var icon = cult;
            arrayMarkers[i].setIcon(icon);
        }
        else if (arrayMarkers[i].category === 'entertainment')
        {
            arrayMarkers[i].setIcon(div);
        }
        else if (arrayMarkers[i].category === 'eating')
        {
            arrayMarkers[i].setIcon(mang);
        }
        else if (arrayMarkers[i].category === 'accomodation')
        {
            arrayMarkers[i].setIcon(dorm);
        }
        else if (arrayMarkers[i].category === 'arts')
        {
            arrayMarkers[i].setIcon(arts);
        }
        else if (arrayMarkers[i].category === 'street')
        {
            arrayMarkers[i].setIcon(street);
        }
    }

    //listeners
    var divCategories = document.getElementById('categories');
    divCategories.addEventListener('click', categoriesHandler, false);
    divPOI.addEventListener('click', poiHandler, false);
    $("#searchBar").keyup(function(event) {

        showSearchBackButton();
        if (event.keyCode === 13) {
            searchPOI();
        }
    });
    //layers Interattivi
    transitLayer = new google.maps.TransitLayer();
    trafficLayer = new google.maps.TrafficLayer();
    //layer per le fermate degli autobus
    busLayer = new google.maps.KmlLayer({
        url: 'http://www.smile.unina.it:8080/priscaxorchestra/file/autobus.kml',
        suppressInfoWindows: false,
        preserveViewport: true
    });
    //layer per le piazzole dei taxi
    taxiLayer = new google.maps.KmlLayer({
        url: 'http://www.smile.unina.it:8080/priscaxorchestra/file/taxi.kml',
        suppressInfoWindows: false,
        preserveViewport: true
    });

    ciroLayer = new google.maps.KmlLayer({
        url: 'http://www.smile.unina.it:8080/priscaxorchestra/file/ciro.kml',
        suppressInfoWindows: false,
        preserveViewport: true
    });

    napoliparkLayer = new google.maps.KmlLayer({
        url: 'http://www.smile.unina.it:8080/priscaxorchestra/file/napolipark.kml',
        suppressInfoWindows: false,
        preserveViewport: true
    });

    artigianiLayer = new google.maps.KmlLayer({
        url: 'http://www.smile.unina.it:8080/priscaxorchestra/file/artigiani.kml',
        suppressInfoWindows: false,
        preserveViewport: true
    });

    vieMestieriLayer = new google.maps.KmlLayer({
        url: 'http://www.smile.unina.it:8080/priscaxorchestra/file/vieDeiMestieri.kml',
        suppressInfoWindows: false,
        preserveViewport: true
    });

    var styles = [
        {
            featureType: "poi",
            elementType: "labels",
            stylers: [
                {
                    visibility: "off"
                }
            ]
        }, {
            featureType: "transit.station.bus",
            elementType: "labels",
            stylers: [
                {
                    visibility: "off"
                }
            ]
        }
    ];
    map.setOptions({styles: styles});
    
    var destinationNames = new Array();
    for (var i = 0; i < arrayMarkers.length; i++) {
        destinationNames[i] = arrayMarkers[i].name.toLowerCase();
    }

    $("#destinationPOI").autocomplete({
        source: destinationNames
    });
    $("#searchBar").autocomplete({
        source: destinationNames
    });

    selectMarkers('all');

    //inserimento dati per ricerca approfondita
    for (var i = 0; i < arrayMarkers.length; i++) {

        document.getElementById('tableBody').innerHTML +=
                '<tr style="cursor:pointer" onclick="window.location=' + "'./PoiTab?idPoi="
                + arrayMarkers[i].id + "'" + '">' +
                '<td>' + arrayMarkers[i].name + '</td>' +
                '<td>' + arrayMarkers[i].address + '</td>' +
                '<td>' + arrayMarkers[i].category + '</td>' +
                '<td>' + arrayMarkers[i].rating + '</td>' +
                '</tr>';
    }

    $(document).ready(function() {
        $('#poiTable').dataTable({
            "language": {
                "lengthMenu": "Visualizza _MENU_ risultati per pagina",
                "zeroRecords": "Nessun risultato soddisfa la ricerca",
                "info": "Visualizza pagina _PAGE_ di _PAGES_",
                "infoEmpty": "Nessun elemento disponibile",
                "infoFiltered": "(filtrati da _MAX_ elementi totali)"
            }
        });
    });
}

//Funzione per la gestione della lista dei punti di interesse
function poiHandler(e) {
    var target = e.target;
    var attr = "";
    if (target.tagName === 'A')
    {
        attr = target.getAttribute('value');
        attachInfo(attr);
        e.preventDefault();
        $('#POI').modal('hide');
    }
}

//Funzione che associa un InfoWindow a un marker
function attachInfo(index) {

    map.setCenter(arrayMarkers[index].getPosition());
    var contentString = '<div class="container-fluid" style="text-transform: capitalize;">'
            + '<div class="row">'
            + '<div class="col-md-12" style="padding:5px">'
            + '<img src="' + arrayMarkers[index].getIcon() + '"/>'
            + '<h5>' + arrayMarkers[index].name
            + '</h5>'
            + 'Indirizzo:&nbsp;' + arrayMarkers[index].address
            + '<br>Categoria:&nbsp' + arrayMarkers[index].category
            + '<br>Valutazione:&nbsp;' + arrayMarkers[index].rating + '<br>';
    var i = 0;
    for (i = 0; i < arrayMarkers[index].rating; i++) {

        if (arrayMarkers[index].rating - i < 1) {

            contentString += '<i class="fa fa-star-half-o fa-lg star"></i>';
        }
        else {
            contentString += '<i class="fa fa-star fa-lg star"></i>';
        }
    }

    while (i < 5) {
        contentString += '<i class="fa fa-star-o fa-lg star"></i>';
        i++;
    }

    contentString += '<br><br><a href="./PoiTab?idPoi=' + arrayMarkers[index].id + '">Maggiori Informazioni</a>'
            + '&nbsp;&nbsp;<a style="cursor:pointer" onclick="viewPanorama(' + index + ')"> Visualizza Panorama</a>'
            + '</div></div></div>';
    infowindow.setContent(contentString);
    infowindow.open(map, arrayMarkers[index]);
    arrayMarkers[index].setAnimation(google.maps.Animation.BOUNCE);
    window.setTimeout(function() {
        arrayMarkers[index].setAnimation(null);
    }, 1400);
}


//Funzione per visualizzare/nascondere i marker in base alla categoria
function selectMarkers(cat) {

    var dim = arrayMarkers.length;
    var divPOI = document.getElementById("poiList");
    var i = 0;

    enableArtigianiLayer(false);

    divPOI.innerHTML = '';

    //abilita layer artigiani e vie dei mestieri disabilitando tutti i POI
    if (cat === 'mestieri') {

        enableArtigianiLayer(true);
        for (i = 0; i < dim; i++)
        {
            arrayMarkers[i].setVisible(false);
        }
    }
    else if (cat === 'all') {
        for (i = 0; i < dim; i++)
        {
            arrayMarkers[i].setVisible(true);
            divPOI.innerHTML += '<div class="row" style="margin-top:5px; padding-bottom:5px; padding-top:5px; border-radius:4px 4px 4px 4px;">'
                    + '<div class="col-md-1 col-xs-1"><img  src="'
                    + arrayMarkers[i].getIcon()
                    + '" style="height:25px"/></div><div class="col-md-10 col-xs-10"><a value="'
                    + i
                    + '" class="text-info" href="#">&nbsp;'
                    + arrayMarkers[i].name
                    + '</a></div></div>';
        }
    }
    else if (cat === 'mustDo')
    {
        for (i = 0; i < dim; i++)
        {
            if (arrayMarkers[i].mustSee)
            {
                arrayMarkers[i].setVisible(true);
                divPOI.innerHTML += '<div class="row" style="margin-top:5px; padding-bottom:5px; padding-top:5px; border-radius:4px 4px 4px 4px;">'
                        + '<div class="col-md-1 col-xs-1"><img  src="'
                        + arrayMarkers[i].getIcon()
                        + '" style="height:25px"/></div><div class="col-md-10 col-xs-10"><a value="'
                        + i
                        + '" class="text-info" href="#">&nbsp;'
                        + arrayMarkers[i].name
                        + '</a></div></div>';
            }
            else
            {
                arrayMarkers[i].setVisible(false);
            }
        }
    }
    else
    {
        for (i = 0; i < dim; i++)
        {
            if (arrayMarkers[i].category.match(cat))
            {
                arrayMarkers[i].setVisible(true);
                divPOI.innerHTML += '<div class="row" style="margin-top:5px; padding-bottom:5px; padding-top:5px; border-radius:4px 4px 4px 4px;">'
                        + '<div class="col-md-1 col-xs-1"><img  src="'
                        + arrayMarkers[i].getIcon()
                        + '" style="height:25px"/></div><div class="col-md-10 col-xs-10"><a value="'
                        + i
                        + '" class="text-info" href="#">&nbsp;'
                        + arrayMarkers[i].name
                        + '</a></div></div>';
            }
            else
            {
                arrayMarkers[i].setVisible(false);
            }
        }
    }
}

//funzione per la visualizzazione dello street view relativo ad un POI
function viewPanorama(index) {

    streetView.getPanoramaByLocation(arrayMarkers[index].getPosition(), 30, function(result, status) {

        if (status === google.maps.StreetViewStatus.OK) {

            $("#panoContainer").modal('show');

            window.setTimeout(function() {
                panorama.setPosition(arrayMarkers[index].getPosition());
                map.setStreetView(panorama);
                map.getStreetView().setVisible(true);
            }, 500);
        }
        else {

            bootbox.alert("Il panorama del Punto Di Interesse non e' disponibile!");
        }
    }

    );
}

//funzione per il controllo dei parametri relativi alle indicazioni stradali
function checkAddress() {

    var positionCheck = document.getElementById('positionCheckbox').checked;
    var start = document.getElementById('startAddress');
    var end = document.getElementById('destinationPOI');
    var addr = null;
    //indicazioni dalla posizione dell'utente
    if (positionCheck === true) {

        if (end.value === "")
        {
            bootbox.alert('<b>Attenzione:</b> destinazione non inserita!');
            document.getElementById('destinationForm').className = 'form-group has-error';

        }
        else {

            for (var i = 0; i < arrayMarkers.length; i++)
            {

                if (arrayMarkers[i].name.toUpperCase() === end.value.toUpperCase()) {

                    addr = arrayMarkers[i].address;
                    break;
                }
            }

            if (addr === null)
            {
                bootbox.alert('<b>Attenzione:</b> inserire un punto di interesse esistente!');
                document.getElementById('destinationForm').className = 'form-group has-error';

            }
            else
            {

                document.getElementById('destinationForm').className = 'form-group has-success';

                geocoder.geocode({'latLng': userPosition.getPosition()}, function(results, status) {
                    if (status === google.maps.GeocoderStatus.OK) {
                        // Esito positivo richiesta

                        start.value = results[0].formatted_address;
                        /* Qui viene calcolato il percorso tra l' indirizzo appena trovato (start) 
                         * e l' indirizzo dell' attivita' ( end ) con modalita "selectedMode" (DRVING - WALKING) */
                        calcRoute(start.value, addr);
                    }
                    else {
                        bootbox.alert('<b>Attenzione:</b> indirizzo di partenza non valido!');
                        document.getElementById('startAddressGroup').className = 'form-group has-error';
                    }
                }
                );
            }
        }
    } else { //indicazioni dall'indirizzo inserito

        if (start.value === "")
        {
            bootbox.alert('<b>Attenzione:</b> Indirizzo di partenza non inserito!');
            document.getElementById('startAddressGroup').className = 'form-group has-error';

        }
        else if (end.value === "")
        {
            bootbox.alert('<b>Attenzione:</b> destinazione non inserita!');
            document.getElementById('destinationForm').className = 'form-group has-error';
        }
        else {

            for (var i = 0; i < arrayMarkers.length; i++)
            {

                if (arrayMarkers[i].name.toUpperCase() === end.value.toUpperCase()) {

                    addr = arrayMarkers[i].address;
                    break;
                }
            }

            if (addr === null)
            {
                bootbox.alert('<b>Attenzione:</b> inserire un punto di interesse esistente!');
                document.getElementById('destinationForm').className = 'form-group has-error';
            }
            else
            {
                document.getElementById('destinationForm').className = 'form-group has-success';
                geocoder.geocode({'address': start.value}, function(results, status) {
                    if (status === google.maps.GeocoderStatus.OK) {
                        // Esito positivo richiesta

                        document.getElementById('startAddressGroup').className = 'form-group has-success';
                        start.value = results[0].formatted_address;
                        /* Qui viene calcolato il percorso tra l' indirizzo appena trovato (start) 
                         * e l' indirizzo dell' attivita' ( end ) con modalita "selectedMode" (DRVING - WALKING) */
                        calcRoute(start.value, addr);
                    }
                    else {
                        bootbox.alert('<b>Attenzione:</b> indirizzo di partenza non valido!');
                        document.getElementById('startAddressGroup').className = 'form-group has-error';
                    }
                }
                );
            }
        }
    }
}

// CALCOLA IL PERCORSO PIU BREVE TRA DUE PUNTI NELLA MAPPA
function calcRoute(start, destination) {

    var mode = document.getElementById('mode').value;
    var request = {
        origin: start,
        destination: destination,
        travelMode: google.maps.TravelMode[mode],
        provideRouteAlternatives: true
    };
    directionsService.route(request, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK)
        {
            $('#directions').modal('hide');
            $('#directionBackButton').show(150);
            $('#directionPanelContainer').modal('show');
            window.setTimeout(function() {
                google.maps.event.trigger(mapDirections, 'resize');
                directionsDisplay.setMap(mapDirections);
                directionsDisplay.setDirections(response);
                directionsDisplay.setPanel(document.getElementById('directionPanel'));
            }, 150);
        }
        else
        {
            directionsDisplay.setMap(null);
            bootbox.alert('<b>Attenzione:</b> Impossibile calcolare il percorso tra i due punti selezionati!');
        }
    });
}

//funzione per la ricerca della posizione utente
function getUserPosition() {
    // individuare il supporto del browser per Geolocation
    if (navigator.geolocation !== 'undefined') {
        navigator.geolocation.getCurrentPosition(displayLocation, displayError);
    } else {
        displayError();
    }
}

//funzione callback di successo per la ricerca della posizione
function displayLocation(position) {
    var lat = position.coords.latitude;
    var lon = position.coords.longitude;
    //alert("Posizione corrente: " + lat + ' , ' + lon);

    if (userPosition !== undefined && userPosition !== null)
        userPosition.setMap(null);

    userPosition = new google.maps.Marker(
            {
                position: new google.maps.LatLng(lat, lon),
                map: mapDirections
            });

    userPosition.setVisible(false);

    geocoder.geocode({'latLng': userPosition.getPosition()}, function(results, status) {

        if (status === google.maps.GeocoderStatus.OK) {
            // Esito positivo richiesta
            document.getElementById('startAddress').value = results[0].formatted_address;
            document.getElementById('startAddressGroup').className = 'form-group has-success';
        }
        else {
            bootbox.alert("<b>Attenzione:</b> Impossibile determinare l'indirizzo di partenza!");
            document.getElementById('startAddressGroup').className = 'form-group has-error';
        }
    }
    );

}

//funzione callback di errore per la ricerca della posizione utente
function displayError() {

    bootbox.alert('<b>Attenzione:</b>Impossibile verificare la tua posizione');
    document.getElementById('positionCheckbox').checked = false;
    //$("#startAddressGroup").show(150);
    document.getElementById('startAddress').disabled = false;
    document.getElementById('startAddress').value = '';
}


//Funzione per la gestione del menu delle categorie
function categoriesHandler(e) {
    var target = e.target;
    var attr = "";
    if (target.getAttribute('name') === 'optionsRadios')
    {
        attr = target.getAttribute('value');
        infowindow.close();
        selectMarkers(attr);
    }
}

//funzione per mostrare il div dei punti di interesse
function showPOI() {

    $("#POI").modal('show');
}

//funzione per mostrare il div dei layers interattivi
function showMenu() {
    $("#mapOption").toggle(250);
}

//funzione per mostrare il div delle indicazioni
function showDirections() {
    $("#directions").modal('show');
}

//funzione per mostrare la barra di ricerca
function showSearch() {

    $("#search").toggle(100);
}

//funzione per la gestione della checkbox "mia posizione" del div per le indicazioni
function myPositionCheckboxHandler(enabled) {

    if (enabled !== undefined) {

        if (enabled === true) {
            document.getElementById('startAddress').disabled = true;
            window.setTimeout(function() {
                getUserPosition();
            }, 150);
        }
        else {
            document.getElementById('startAddress').disabled = false;
            document.getElementById('startAddressGroup').className = 'form-group';
            document.getElementById('startAddress').value = '';
            window.setTimeout(function() {
                userPosition.setMap(null);
            }, 150);
        }
    }
}

//funzione per abilitare il layer dei taxi
function enableTaxiLayer(enable) {

    if (enable)
        taxiLayer.setMap(map);
    else
        taxiLayer.setMap(null);
}

//funzione per abilitare il layer degli autobus
function enableBusLayer(enable) {

    if (enable)
        busLayer.setMap(map);
    else
        busLayer.setMap(null);
}

//funzione per abilitare il layer di Ciro
function enableArtigianiLayer(enable) {

    if (enable) {

        vieMestieriLayer.setMap(map);
        artigianiLayer.setMap(map);
    }
    else {

        artigianiLayer.setMap(null);
        vieMestieriLayer.setMap(null);
    }
}

//funzione per abilitare il layer di Ciro
function enableCiroLayer(enable) {

    if (enable)
        ciroLayer.setMap(map);
    else
        ciroLayer.setMap(null);
}

//funzione per abilitare il layer di NapoliPark
function enableNapoliParkLayer(enable) {

    if (enable)
        napoliparkLayer.setMap(map);
    else
        napoliparkLayer.setMap(null);
}



//funzione per abilitare il layer del traffico
function enableTrafficLayer(enable) {

    if (enable)
        trafficLayer.setMap(map);
    else
        trafficLayer.setMap(null);
}

//funzione per abilitare il layer dei trasporti
function enableTransitLayer(enable) {

    if (enable)
        transitLayer.setMap(map);
    else
        transitLayer.setMap(null);
}

//Funzione per la ricerca dei luoghi di interesse
function searchPOI() {

    infowindow.close();
    $(".ui-autocomplete").hide();
    var divPOI = document.getElementById("poiList");
    var i = 0;
    divPOI.innerHTML = '';
    var words = document.getElementById('searchBar').value.toString().split(' ');
    var j = 0;
    var res;
    var match;
    for (i = 0; i < arrayMarkers.length; i++)
    {
        match = 0;
        for (var k = 0; k < words.length; k++)
        {
            if (arrayMarkers[i].name.toUpperCase().match(words[k].toUpperCase()))
            {
                match++;
            }
            else
            {
                break;
            }
        }

        if (match === words.length)
        {
            arrayMarkers[i].setVisible(true);
            divPOI.innerHTML += '<div class="row" style="margin-top:5px; padding-bottom:5px; padding-top:5px; border-radius:4px 4px 4px 4px;">'
                    + '<div class="col-md-1 col-xs-1"><img  src="'
                    + arrayMarkers[i].getIcon()
                    + '" style="height:25px"/></div><div class="col-md-10 col-xs-10"><a value="'
                    + i
                    + '" class="text-info" href="#">&nbsp;'
                    + arrayMarkers[i].name
                    + '</a></div></div>';
            res = i;
            j++;
        }
        else
        {
            arrayMarkers[i].setVisible(false);
        }
    }
    if (j > 0) {

        showPOI();
    }

    else {

        bootbox.alert('<b>Attenzione:</b> Nessun punto di interesse soddisfa i criteri di ricerca');
        searchBackHandler();
    }
}

//funzione per la visualizzazione del bottone di annullamento della ricerca
function showSearchBackButton() {

    if (document.getElementById('searchBar').value !== '') {

        $("#searchBackButton").show();
    }
    else {

        $("#searchBackButton").hide();
        selectMarkers('all');
    }
}

//funzione per la gestione del bottone di annullamento della ricerca
function searchBackHandler() {

    selectMarkers('all');
    document.getElementById('searchBar').value = '';
    infowindow.close();
    $("#searchBackButton").hide();
}


