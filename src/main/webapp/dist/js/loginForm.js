$.extend({
    formSubmitEnable : function (button_id, form_id) {
        $( "#"+button_id ).click(function() {
            $( "#"+form_id ).submit();

        });

    }
});



/*Close modal*/
function close_modal() {
   window.parent.closeLoginModal();
}


/*Fb Login*/
function fb_login() {
    close_modal();
    FbLogin();
}

/*Login Done*/
function loginDone() {
    close_modal();
    window.parent.reload_access_area();
}