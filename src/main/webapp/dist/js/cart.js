
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

$(document).ready(function () {
    $(".category_title").each(function (index) {
        console.log("*************************");
        var category_title = $(this);
        $(this).on("click", function () {
            slideFavoritePoi(category_title);
            var icon = $(this).find(".expand_icon");
            icon.toggleClass("fa-chevron-right fa-chevron-down");
        });
    });
});

function slideFavoritePoi(category_title) {
    //retrive parent linked poi 
    //console.log(ul_poi);

    var poi = category_title.siblings(".poi");

    console.log(poi);
    if (poi.is(":hidden")) {
        poi.slideDown("slow");
    }
    else {
        poi.slideUp("slow");
    }
    //son.slideUp("slow");
}  
