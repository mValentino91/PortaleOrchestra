function showFacebookResults(facebookData) {

    createHeader(facebookData.profilo);
    createFriendsList(facebookData.friends);
    createMarkers(facebookData.loc_friends);
    facebookPoiFilter(facebookData.poi);
}

/*[loc_friends] = > Array
 (
 [0] = > Array
 (
 [id] = > 183464088398010
 [name] = > Skiattat N'cop O Divan!
 [lat] = > 40.8512588331
 [long] = > 14.2517594938
 [friend] = > Array
 (
 [name] = > Giovanna Ercolano
 [pic] = > https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpf1/t1.0-1/p50x50/10250089_10203305407107715_2389646641136458781_t.jpg
 )
 )
 */
function createMarkers(markers) {

    for (var i = 0; i < markers.length; i++) {

        var img = markers[i].friend.pic.replace('\\', '');

        var temp = new google.maps.Marker(
                {
                    position: new google.maps.LatLng(markers[i].lat, markers[i].long),
                    map: map,
                    title: markers[i].name,
                    icon: new google.maps.MarkerImage(
                            img,
                            new google.maps.Size(28, 28))
                });

        temp['infowindow'] = new google.maps.InfoWindow({
            content: '<h4>' + markers[i].name + '</h4>'
                    + '<h5>' + markers[i].friend.name + ' &egrave; stata qui!</h5>'
        });

        google.maps.event.addListener(temp, 'click', function() {

            this['infowindow'].open(map, this);
        });
    }
}

/*[friends] => Array
 (
 [0] => Array
 (
 [id] => 587806719
 [name] => Marco Attanasio
 [pic] => https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpa1/t1.0-1/c8.0.50.50/p50x50/1524923_10151900067691720_948085_n.jpg
 [age] => 
 [education] => 0
 )
 */
function createFriendsList(friends) {

    var friendsDiv = document.getElementById('facebookFriends');

    for (var i = 0; i < friends.length; i++) {

        var img = friends[i].pic.replace('\\', '');
        friendsDiv.innerHTML += '<div class="container-fluid padding-top">' +
                '<img class="box pull-right" src="' + img + '" alt="profile">' +
                '<div class="checkbox"><label><input type="checkbox" name="friend_'
                + i
                + '" value="'
                + friends[i].id + '$$'
                + friends[i].age + '$$'
                + friends[i].education + '"/>'
                + '<b style="padding:5px">'
                + friends[i].name + '</b>' +
                '</label></div>' +
                '</div>';
    }

    document.getElementById('friendsHeader').innerHTML = '<i class="fa fa-users"></i>Amici';
}

/*[profilo] => Array
 (
 [name] => Luigi De Luca
 [pic] => https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xpf1/t1.0-1/p50x50/988362_1386659944912994_1577394287_n.jpg
 [age] => 31
 [gender] => male
 [city] => London
 )*/
function createHeader(profilo) {

    var header = document.getElementById('facebookHeader');
    var img = profilo.pic.replace('\\', '');
    header.innerHTML = '<div class="row">' +
            '<div class="col-md-4"><img class=" pull-right box" src="'
            + img +
            '" alt="profile"></div>' +
            '<div class="col-md-8"><b>' + profilo.name + '</b>' +
            '<br>Eta:&nbsp' + profilo.age +
            '<br>Sesso:&nbsp' + profilo.gender +
            '<br>Citta:&nbsp' + profilo.city +
            '</div></div>';

    document.getElementById('facebookFriends').innerHTML +=
            '<input type="hidden" name="friend_me" value="me$$' + profilo.age + '$$' + profilo.education + '"/>';
}


function facebookPoiFilter(poiList) {

    selectMarkers('null');

    for (var i = 0; i < poiList.length; i++) {

        for (var j = 0; j < arrayMarkers.length; j++) {

            if (arrayMarkers[j].id.toString() === poiList[i].toString()) {

                arrayMarkers[j].setVisible(true);
            }
        }
    }

}

function sendFriendsForm() {

    var formInput = document.getElementById('facebookForm').getElementsByTagName('input');
    var parameters = "";

    for (var i = 0; i < formInput.length; i++) {

        if (formInput[i].getAttribute('name') === 'friend_me') {
            parameters += formInput[i].getAttribute('name') + '=' + formInput[i].getAttribute('value');
            parameters += '&';
        }
        if (formInput[i].checked) {
            parameters += formInput[i].getAttribute('name') + '=' + formInput[i].getAttribute('value');
            parameters += '&';
        }
    }

    handle_gr(parameters);
}