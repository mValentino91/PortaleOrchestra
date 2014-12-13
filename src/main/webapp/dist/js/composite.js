/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function show(id) {
       $(".active").removeClass("active");
       $("#"+id).addClass("active");
       
       $(".show").addClass("hidden");
       $(".show").removeClass("show");
       $("#"+id+"-panel").removeClass("hidden");
       $("#"+id+"-panel").addClass("show");
       

}

function fix_height() {
    max=0;
    $(".ortab-panel").each(function(i) {
        
        if(max < $(this).height()) {
            max=$(this).height();
        }
    });
    $(".ortab-panel-container").css("min-height",max+"px");
}