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

//trigger event
function login_event(){
    var loginEvent=$.Event("loginDone");
    $(document).trigger(loginEvent);
}


function user_img_loading(){
    $('#userImg').attr("src", "./dist/img/loading.gif");
}