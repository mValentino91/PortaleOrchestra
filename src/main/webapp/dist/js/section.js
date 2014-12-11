/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function espandi(id) {
    
    
    if($("#descr"+id).attr("class") == "nascosto"){
    $("#descr"+id).removeClass( "nascosto" ).addClass( "panel panel-body" );
    }
    else {
        $("#descr"+id).removeClass( "panel panel-body" ).addClass( "nascosto" );
    }
    }