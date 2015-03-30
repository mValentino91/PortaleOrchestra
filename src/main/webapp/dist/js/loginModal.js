/*Close Modal*/
function closeLoginModal() {
    $('#login-modal').modal('hide')

}


/*Reload Element*/
$.extend({
    reloadElement : function (element_id, source) {
        $('#' + element_id).load(source);

    }
});

//Reload Access Area
function reload_access_area () {
    $.reloadElement("loginArea", "/orchestra/loginArea");

}
