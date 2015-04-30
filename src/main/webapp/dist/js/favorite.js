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