function initPage() {

    var cult = './img/cultura.png';
    var div = './img/divertimento.png';
    var mang = './img/mangiare.png';
    var dorm = './img/pernottamento.png';
    var arts = './img/arts.png';
    var street = './img/street.png';
    var marker_ref_icon = './img/Marker.png';
    var icon = '';

    for (var i = 0; i < arrayMarkers.length; i++)
    {
        if (arrayMarkers[i].category === 'culture')
        {
            icon = cult;
        }
        else if (arrayMarkers[i].category === 'entertainment')
        {
            icon = div;
        }
        else if (arrayMarkers[i].category === 'eating')
        {
            icon = mang;
        }
        else if (arrayMarkers[i].category === 'accomodation')
        {
            icon = dorm;
        }
        else if (arrayMarkers[i].category === 'arts')
        {
            icon = arts;
        }
        else if (arrayMarkers[i].category === 'street')
        {
            icon = street
        }

        if (arrayMarkers[i].id === index_markeref)
        {
            icon = marker_ref_icon;
            map.setCenter(arrayMarkers[i].getPosition());
            arrayMarkers[i].setAnimation(google.maps.Animation.BOUNCE);
            marker_ref = arrayMarkers[i];
        }

        arrayMarkers[i].setIcon(icon);
    }

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
    selectMarkers(marker_ref.category);

    if (imgArray !== null) {
        $('#bigImage').html('<img src="' + imgArray[0] + '"/>');
    }

    initCarousel();
    insertInfo();
    insertRating();

    //inserimento dati per ricerca approfondita
    for (var i = 0; i < arrayMarkers.length; i++) {

        document.getElementById('tableBody').innerHTML +=
                '<tr style="cursor:pointer" onclick="window.location=' + "'./PoiTab?idPoi="
                + arrayMarkers[i].id + "'" + '">' +
                '<td>' + arrayMarkers[i].name + '</td>' +
                '<td>' + arrayMarkers[i].category + '</td>' +
                '</tr>';
    }

    dataTable = $('#poiTable').dataTable({
        info: false,
        scrollY: 250,
        paging: false,
        "language": {
            "lengthMenu": "",
            "zeroRecords": "Nessun risultato",
            "info": "",
            "infoEmpty": "Nessun elemento disponibile",
            "infoFiltered": ""
        }
    });

    $('#searchBar').keyup(function() {
        dataTable.fnFilter($(this).val());
    });
}

function reloadComments() {

    //nuovo oggetto XMLHttpRequest
    var ajax = new XMLHttpRequest();

    var commentsDiv = document.getElementById('UsersComments');

    var refreshButton = document.getElementById('refreshCommentsButton');

    if (ajax)
    {
        refreshButton.className = 'fa fa-refresh fa-spin';
        // impostazione richiesta asincrona in POST
        // del file specificato
        ajax.open('post', './ReloadCommentController', true);

        // imposto gli header per il metodo POST
        ajax.setRequestHeader("content-type", "application/x-www-form-urlencoded");

        // impostazione controllo e stato della richiesta
        ajax.onreadystatechange = function()
        {
            // verifica dello stato
            if (ajax.readyState === 4)
            {
                //risposta ricevuta dal server
                // verifica della risposta da parte del server
                if (ajax.status === 200)
                {
                    // risposta positiva ricevuta
                    // inserisco nel div la risposta ricevuta come codice html
                    commentsDiv.innerHTML = ajax.responseText;
                    refreshButton.className = 'fa fa-refresh';
                }
                else
                {
                    //codice d'errore ricevuto dal server
                    bootbox.alert('<b>Attenzione:</b> Impossibile aggiornare i commenti. Riprovare');
                    //ripristino il codice HTML precedente
                    refreshButton.className = 'fa fa-refresh';
                }
            }
        };

        // invio la richiesta
        ajax.send('idPoi=' + (index_markeref));
    }

}

// funzione per l'invio di un commento
function addComment(comment) {

    var temp = comment.trim();

    //controllo che il parametro commento della funzione non sia vuoto e non sia costituito da soli spazi

    if (comment !== null && comment !== undefined && temp.length !== 0)
    {

        //nuovo oggetto XMLHttpRequest
        var ajax = new XMLHttpRequest();

        var commentsDiv = document.getElementById('UsersComments');

        // se l’oggetto XMLHttpRequest non è nullo
        if (ajax)
        {
            // impostazione richiesta asincrona in GET
            // del file specificato
            ajax.open('post', './InsertNewCommentController', true);

            // imposto gli header per il metodo POST
            ajax.setRequestHeader("content-type", "application/x-www-form-urlencoded");
            // impostazione controllo e stato della richiesta
            ajax.onreadystatechange = function()
            {
                // verifica dello stato
                if (ajax.readyState === 4)
                {
                    //risposta ricevuta dal server
                    // verifica della risposta da parte del server
                    if (ajax.status === 200)
                    {
                        // risposta positiva ricevuta
                        // inserisco nel div la risposta ricevuta come codice html
                        commentsDiv.innerHTML = ajax.responseText;
                        document.getElementById('commentTextArea').value = '';
                        bootbox.alert('Commento inviato con successo!');
                    }
                    else
                    {
                        //codice d'errore ricevuto dal server
                        bootbox.alert('<b>Attenzione:</b> Impossibile inviare il commento. Riprovare');

                    }
                }
            };

            // invio la richiesta
            ajax.send('idPoi=' + (index_markeref) + '&comment=' + comment);
        }
    }
    else
    {
        bootbox.alert('<b>Attenzione:</b> Impossibile iviare un commento vuoto.');
    }
}

//funzione di inizializzazione del carosello
function initCarousel() {
    var i = 0;

    if (imgArray.length > 0) {

        document.getElementById('carouselInner').innerHTML +=
                '<div class="item active">' +
                '<img src="' + imgArray[i] + '" alt=""/>';

        document.getElementById('carouselIndicators').innerHTML +=
                '<li data-target="#myCarousel" data-slide-to="' + i + '" class="active"></li>';
        i++;
    }
    while (i < imgArray.length) {

        document.getElementById('carouselInner').innerHTML +=
                '<div class="item">' +
                '<img src="' + imgArray[i] + '" alt=""/>';

        document.getElementById('carouselIndicators').innerHTML +=
                '<li data-target="Carousel" data-slide-to="' + i + '"></li>';
        i++;
    }

    $('.item').click(function() {
        $('#bigCarousel').html($(this).html());
        $('#modalBigCarousel').modal('show');
    });
}

//Funzione per visualizzare/nascondere i marker in base alla categoria
function selectMarkers(cat) {

    var dim = arrayMarkers.length;
    var divPOI = document.getElementById("poiList");
    var i = 0;
    divPOI.innerHTML = '';
    if (cat === 'all') {
        for (i = 0; i < dim; i++)
        {
            if (arrayMarkers[i].id !== index_markeref) {

                arrayMarkers[i].setVisible(true);
                divPOI.innerHTML += '<div class="row" style="margin-top:5px; margin-right:1px; padding-bottom:5px; padding-top:5px; border-radius:4px 4px 4px 4px;">'
                        + '<div class="col-md-1 col-xs-1"><img  src="'
                        + arrayMarkers[i].getIcon()
                        + '"/></div><div class="col-md-10 col-xs-10"><a value="'
                        + i
                        + '" href="./PoiTab?idPoi=' + arrayMarkers[i].id + '">&nbsp;'
                        + arrayMarkers[i].name
                        + '</a></div></div>';
            }
        }
    }
    else if (cat === 'mustDo')
    {
        for (i = 0; i < dim; i++)
        {
            if (arrayMarkers[i].mustSee)
            {
                if (arrayMarkers[i].id !== index_markeref) {

                    arrayMarkers[i].setVisible(true);
                    divPOI.innerHTML += '<div class="row" style="margin-top:5px; margin-right:1px; padding-bottom:5px; padding-top:5px; border-radius:4px 4px 4px 4px;">'
                            + '<div class="col-md-1 col-xs-1"><img  src="'
                            + arrayMarkers[i].getIcon()
                            + '"/></div><div class="col-md-10 col-xs-10"><a value="'
                            + i
                            + '" href="./PoiTab?idPoi=' + arrayMarkers[i].id + '">&nbsp;'
                            + arrayMarkers[i].name
                            + '</a></div></div>';
                }
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
                if (arrayMarkers[i].id !== index_markeref) {

                    arrayMarkers[i].setVisible(true);
                    divPOI.innerHTML += '<div class="row" style="margin-top:5px; margin-right:1px; padding-bottom:5px; padding-top:5px; border-radius:4px 4px 4px 4px;">'
                            + '<div class="col-md-1 col-xs-1"><img  src="'
                            + arrayMarkers[i].getIcon()
                            + '"/></div><div class="col-md-10 col-xs-10"><a value="'
                            + i
                            + '" href="./PoiTab?idPoi=' + arrayMarkers[i].id + '">&nbsp;'
                            + arrayMarkers[i].name
                            + '</a></div></div>';
                }
            }
            else
            {
                arrayMarkers[i].setVisible(false);
            }
        }
    }
}

//Funzione che associa un InfoWindow a un marker
function attachInfo(index) {

    map.setCenter(arrayMarkers[index].getPosition());
    var contentString = '<div class="container-fluid" style="color:midnightblue; text-transform: capitalize;">'
            + '<div class="row">'
            + '<div class="col-md-12" style="padding:5px">'
            + '<b>' + arrayMarkers[index].name
            + '</b><br>'
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

    contentString += '<br><a href="./PoiTab?idPoi=' + arrayMarkers[index].id + '">Maggiori Informazioni</a>'
            + '</div></div></div>';
    infowindow.setContent(contentString);
    infowindow.open(map, arrayMarkers[index]);
    arrayMarkers[index].setAnimation(google.maps.Animation.BOUNCE);
    window.setTimeout(function() {
        arrayMarkers[index].setAnimation(null);
    }, 1400);
}

//funzione per cancellare i parametri di ricerca
function searchBackHandler() {

    document.getElementById('searchBar').value = '';
    searchPOI();
    infowindow.close();
    $("#searchBackButton").hide();
}

//funzione per controllare la validità dei parametri per le indicazioni stradali
function checkAddress() {

    var start = document.getElementById('startAddress');
    var addr;

    for (var i = 0; i < arrayMarkers.length; i++) {

        if (arrayMarkers[i].id === index_markeref) {

            addr = arrayMarkers[i].address;
            break;
        }
    }


    if (start.value === "")
    {
        bootbox.alert('<b>Attenzione:</b> Indirizzo di partenza non inserito!');
        document.getElementById('startAddressGroup').className = 'form-group has-error';
    }
    else {
        if (addr === null)
        {
            bootbox.alert('<b>Attenzione:</b> inserire un punto di interesse esistente!');
        }
        else
        {
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

// CALCOLA IL PERCORSO PIU BREVE TRA DUE PUNTI NELLA MAPPA
function calcRoute(start, destination) {

    var mode = document.getElementById('mode').value;
    var request = {
        origin: start,
        destination: destination,
        travelMode: google.maps.TravelMode[mode],
        provideRouteAlternatives: true
    };
    infowindow.close();
    // route(request:DirectionsRequest, callback:function(DirectionsResult, DirectionsStatus))
    directionsService.route(request, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK)
        {
            $('#directionDetailsButton').show(150);
            $('#directionBackButton').show(150);
            directionsDisplay.setDirections(response);
            directionsDisplay.setPanel(document.getElementById('directionPanel'));
        }
        else
        {
            bootbox.alert('<b>Attenzione:</b> Impossibile calcolare il percorso tra i due punti selezionati!');
        }
    });
}

//funzione per annullare la ricerca
function directionBackHandler() {

    $('#directionBackButton').hide(150);
    document.getElementById('startAddressGroup').className = 'form-group';
    document.getElementById('directionPanel').innerHTML = '';
    directionsDisplay.setMap(null);
}

//Funzione per la ricerca dei luoghi di interesse
function searchPOI() {

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
            divPOI.innerHTML += '<div class="row" style="margin-top:5px; margin-right:1px; padding-bottom:5px; padding-top:5px; border-radius:4px 4px 4px 4px;">'
                    + '<div class="col-md-1 col-xs-1"><img  src="'
                    + arrayMarkers[i].getIcon()
                    + '"/></div><div class="col-md-10 col-xs-10"><a value="'
                    + i
                    + '" href="./PoiTab?idPoi=' + arrayMarkers[i].id + '">&nbsp;'
                    + arrayMarkers[i].name
                    + '</a></div></div>';
            res = i;
            j++;
        }
    }
    if (j <= 0) {

        bootbox.alert('<b>Attenzione:</b> Nessun punto di interesse soddisfa i criteri di ricerca');
        searchBackHandler();
    }
}

function showSearchBackButton() {

    if (document.getElementById('searchBar').value !== '') {

        $("#searchBackButton").show();
    }
    else {

        $("#searchBackButton").hide();
        searchPOI();
    }
}

function insertInfo() {

    var divs = document.getElementsByClassName('schedaInfo');

    var j = 0;
    for (j = 0; j < divs.length; j++) {

        for (var i = 0; i < arrayMarkers.length; i++) {

            if (arrayMarkers[i].id === index_markeref) {
                divs[j].innerHTML = '<b>' + arrayMarkers[i].name + '</b>'
                        + '<p>'
                        + '<b> Categoria: </b>'
                        + arrayMarkers[i].category + '<br>'
                        + ' <b> Indirizzo: </b>'
                        + arrayMarkers[i].address + '<br>'
                        + '<b> Valutazione: </b>'
                        + arrayMarkers[i].rating
                        + '<br><span class="rating"> </span> </p>';

                break;
            }
        }
    }
}

function insertRating() {

    var divs = document.getElementsByClassName('rating');
    var marker;

    var z = 0;

    for (var z = 0; z < arrayMarkers.length; z++) {

        if (arrayMarkers[z].id === index_markeref) {

            marker = arrayMarkers[z];
        }
    }

    var j = 0;
    for (j = 0; j < divs.length; j++) {

        var i = 0;
        for (i = 0; i < marker.rating; i++) {

            if (marker.rating - i < 1) {

                divs[j].innerHTML += '<i class="fa fa-star-half-o fa-lg star"></i>';
            }
            else {
                divs[j].innerHTML += '<i class="fa fa-star fa-lg star"></i>';
            }
        }

        while (i < 5) {
            divs[j].innerHTML += '<i class="fa fa-star-o fa-lg star"></i>';
            i++;
        }
    }
}

