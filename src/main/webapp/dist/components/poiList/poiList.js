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
                        + '<p class="text-center" style="color: #242424">'
                        + markers[i].name
                        + '</p>'
                        + '</div>'
                        + '<div class="col-md-1 text-right"><span onmouseover="this.className = ' + "'" + 'fa fa-star fa-2x' + "'" + ';"'
                        + 'onmouseleave="this.className=' + "'" + 'fa fa-star-o fa-2x' + "'" + ';"'
                        + 'onclick="alert(' + "'" + 'aggiungo a preferiti!' + "'" + ')"'
                        + 'data-toggle="tooltip" title="Aggiungi Ai Preferiti!"'
                        + 'class="fa fa-star-o fa-2x"></span></div>'
                        + '<div class="col-md-12">'
                        + '<p style="padding-top: 5px;" class="poiBoxShortDescription ' + markers[i].id + '">'
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
    return {
        poiBoxUp: poiBoxUp,
        poiBoxDown: poiBoxDown,
        initPoiList: initPoiList,
        poiClicked: poiClicked
    };
})();

