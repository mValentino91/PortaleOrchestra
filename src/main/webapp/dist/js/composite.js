/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function show_comptab(id) {
       $(".active_tab").removeClass("active_tab");
       $("#"+id).addClass("active_tab");
       
       $(".show_comptab").addClass("hidden_comptab");
       $(".show_comptab").removeClass("show_comptab");
       $("#"+id+"-panel").removeClass("hidden_comptab");
       $("#"+id+"-panel").addClass("show_comptab");
       

}
function show_hourtab(id){
       $(".showtab").addClass("hiddentab");
       $(".showtab").removeClass("showtab");
       $("#"+id+"tab").removeClass("hiddentab");
       $("#"+id+"tab").addClass("showtab");
}
function fix_height() {
    max=0;
    $(".ortab-panel").each(function(i) {
        
        if(max < $(this).height()) {
            max=$(this).height();
        }
    });
    $(".ortab-panel").css("min-height",max+"px");
}