/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function enableRatingBar(){

    $(".range").ionRangeSlider({
        min: 1,
        max: 5,
        from: 1,
        step: 1,
        hide_min_max: true,
        hide_from_to: false,
        grid: false,
        grid_snap: false,
        onFinish: function (data) {
            console.log("selected value: " + data.from);
        }
    });

}



function enableDeleteButton(poi_prev_box){
    var box = poi_prev_box;
    var fav_rating = box.find(".fav_rating");
    var idpoi = fav_rating.data('idpoi');
    var delete_poi = box.find(".poi_preview_delete");
    delete_poi.click(function() {
        removeFromFavorite(idpoi);
        box.fadeOut(300, function() { $(this).remove(); });
        //obtain category div
        deleteCategoryContainer(box);
    });    
    
}

function updateRatingBar(poi_prev_box){
    var box = poi_prev_box;
    var fav_rating = box.find(".fav_rating");
    var rating = fav_rating.data('rating');
    var idpoi = fav_rating.data('idpoi');
    box.find(".range").data("ionRangeSlider").update({
        from: rating,
        onFinish: function (data) {
            saveFavoriteRating(fav_rating.data('idpoi'), data.from);
        }
    });
   

}

function deleteCategoryContainer(poiPreviewBox){
    //retrieve parent category box
    var cat_box = poiPreviewBox.parents(".category-container");
    //obtain number of poi of category
    var n_poi=cat_box.find(".poi_preview_box").size();
    //if the poi inside category box is the last, delete the box
    if(n_poi==1){
        cat_box.fadeOut(300, function() { $(this).remove(); });
    }


}