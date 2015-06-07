/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function enable_favorite_actions(idPoi) {
    
    heart_hover_en($(".favorite"));
    heart_hover_en($(".add_favorite"));
   
    //enable click
    $(".favorite").click(function () {
        heart_click(idPoi);
    });
    $(".add_favorite").click(function () {
        heart_click(idPoi);
    });
    
    
    //enable favorite bar
    enable_fav_bar(idPoi);
    
    
    
}

function heart_hover_en() {
    $(".favorite").on("mouseover",
            function () {
                $(".favorite").addClass("fa-heart");
                $(".favorite").removeClass("fa-heart-o");
            })
    $(".favorite").on("mouseleave",
            function () {
                $(".favorite").addClass("fa-heart-o");
                $(".favorite").removeClass("fa-heart");
            })
    $(".add_favorite").on("mouseover",
            function () {
                $(".favorite").addClass("fa-heart");
                $(".favorite").removeClass("fa-heart-o");
            })
    $(".add_favorite").on("mouseleave",
            function () {
                $(".favorite").addClass("fa-heart-o");
                $(".favorite").removeClass("fa-heart");
            })            
}

function heart_hover_dis() {
    $(".favorite").off("mouseover");
    $(".favorite").off("mouseleave");
    $(".add_favorite").off("mouseover");
    $(".add_favorite").off("mouseleave");    
}

function heart_click(idPoi) {
    if ($(".fav_rating").is(":hidden")) {
        heart_hover_dis();
        $(".fav_rating").slideDown("slow");
        $("#fav_rating_bar").data("ionRangeSlider").update({
            from: 1
        });
        $(".add_favorite").html("Aggiunto ai Preferiti");
        addToFavorite(idPoi);
    } else {
        heart_hover_en();
        $(".fav_rating").slideUp("slow");
        $(".add_favorite").html("Aggiungi ai Preferiti");
        removeFromFavorite(idPoi);
    }
}



function enable_fav_bar(idPoi){
    //enable rating bar
    $("#fav_rating_bar").ionRangeSlider({
        min: 1,
        max: 5,
        from: 1,
        step: 1,
        hide_min_max: true,
        hide_from_to: false,
        grid: false,
        grid_snap: false,
        onFinish: function (data) {
            saveFavoriteRating(idPoi, data.from);
        }
    });
}


function already_fav(rating){
    heart_hover_dis();
    //$(".favorite").removeClass("fa-heart-o");
    //$(".favorite").addClass("fa-heart");
    $(".fav_rating").slideDown("slow");
    $("#fav_rating_bar").data("ionRangeSlider").update({
        from: rating
    });    
    
}