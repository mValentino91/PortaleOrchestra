var map;
var markers = new Array();
var infowindow = new google.maps.InfoWindow();
var panorama;
var streetView;
var bounds;
var rectangle;

function viewPanorama(index) {

    streetView.getPanoramaByLocation(markers[index].getPosition(), 30, function(result, status) {

        if (status === google.maps.StreetViewStatus.OK) {
            
            $("#panoContainer").modal('show');
            window.setTimeout(function() {
                panorama.setPosition(markers[index].getPosition());
                map.setStreetView(panorama);
                map.getStreetView().setVisible(true);
            }, 500);
        }
        else {
            alert("Il panorama del Punto Di Interesse non e' disponibile!");
        }
    }

    );
}

function attachInfo(index) {

    map.setCenter(markers[index].getPosition());
    var contentString =
            '<div class="container-fluid text-center" style="min-height:170px; max-height:300px; max-width:200px;">'
            + '<b>'
            + markers[index].name
            + '</b><br>'
            + markers[index].address
            + '<center><img class="img-rounded" src="./dist/poi/img/'
            + markers[index].id
            + '/cover.jpg" height="60" style="margin: 5px;" alt=""/></center>'
            + '<p style="color:gray">'
            + markers[index].shortDescription + '</p>';

    contentString += '<a target="_blank" href="./getPoi?id='
            + markers[index].id
            + '">Maggiori Informazioni</a>'
            + ' <a style="cursor:pointer" onclick="viewPanorama('
            + index
            + ')"><br>Guarda nei dintorni</a></div>';

    infowindow.setContent(contentString);
    infowindow.open(map, markers[index]);
    markers[index].setAnimation(google.maps.Animation.BOUNCE);
    window.setTimeout(function() {
        markers[index].setAnimation(null);
    }, 1400);
}



