var timeoutId;

function initPoiList() {
    $(document).bind("markers_changed", function(event) {
        var markers = event.target;
        document.getElementById('poiListComponent').innerHTML = '';
        var content = '';
        for (var i = 0; i < markers.length; i++) {
            content += ' <div id="' + markers[i].id + '" class="container-fluid poiBox" '
                    + 'onmouseover="poiBoxDown(' + "'" + markers[i].id + "'" + ', ' + "'" + markers[i].id + "'" + ', ' + "'" + markers[i].id + "'" + ', true)"'
                    + 'onmouseout="poiBoxDown(' + "'" + markers[i].id + "'" + ', ' + "'" + markers[i].id + "'" + ', ' + "'" + markers[i].id + "'" + ', false)"'
                    + 'onmouseleave="poiBoxUp(' + "'" + markers[i].id + "'" + ',' + "'" + markers[i].id + "'" + ')">'
                    + '<div class="col-md-5"> '
                    + '<img class="img-responsive text-center"  style="height:40px;"src="./dist/poi/img/' + markers[i].id + '/cover.jpg" onError="this.src=' + "'" + './dist/img/notFound.png' + "'" + ';" alt=""/>'
                    + '<br>'
                    + '</div>'
                    + '<div class="col-md-7">'
                    + '<p>'
                    + markers[i].name
                    + '</p>'
                    + '</div>'
                    + '<div class="col-md-12">'
                    + '<p class="poiBoxShortDescription ' + markers[i].id + '">'
                    + '<span style="color:gray; font-size: 11px;">' + markers[i].address + '</span>'
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
function  poiBoxDown(idBox, nameClass, id, enable) {
    if (enable) {
        timeoutId = setTimeout(function() {
            $('.' + nameClass).slideDown();
            interactiveMap.poiHoverHandler(id, true);
        }, 500);
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


