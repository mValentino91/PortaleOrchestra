function ifFavorite(poiId) {
        var rating=0;
        if(ifAuth() === true){
            //alert("UserId:" + getUserId() + " - PoiId:" + poiId);
            userId = getUserId();
            $.ajax({
                type: "GET",
                url: "./ifFavorite",
                data: "id_user="+userId+"&id_poi="+poiId,	
		async: false,
                success: function(result, stato){
                   rating=result;
                },
                error: function(richiesta,stato,errori){
                    alert("Error. State: "+stato);
                }                 
            });            
        }
        else{
            //alert("Utente non autenticato");
        }
        
        return rating;
    }

    
    function removeFromFavorite(poiId) {
        if(ifAuth() === true){
            //alert("UserId:" + getUserId() + " - PoiId:" + poiId);
            userId = getUserId();
            $.ajax({
                type: "GET",
                url: "./deleteFavorite",
                data: "id_user="+userId+"&id_poi="+poiId,
                success: function(){
                    //alert("OK");
                },
                error: function(richiesta,stato,errori){
                    alert("Error. State: "+stato);
                }                 
            });            
        }
        else{
            //alert("Utente non autenticato");
        }        
    }    
    
    function addToFavorite(poiId){
        if(ifAuth() === true){
            //alert("UserId:" + getUserId() + " - PoiId:" + poiId);
            userId = getUserId();
            $.ajax({
                type: "GET",
                url: "./saveFavorite",
                data: "id_user="+userId+"&id_poi="+poiId,
                success: function(){
                    //alert("OK");
                },
                error: function(richiesta,stato,errori){
                    alert("Error. State: "+stato);
                }                 
            });            
        }
        else{
            //alert("Utente non autenticato");
        }
    }    
    
    function saveFavoriteRating(poiId, rating){
        if(ifAuth() === true){
            //alert("UserId:" + getUserId() + " - PoiId:" + poiId + " - Rating:" + rating);
            userId = getUserId();
            $.ajax({
                type: "GET",
                url: "./saveFavoriteRating",
                data: "id_user="+userId+"&id_poi="+poiId+"&rating="+rating,
                success: function(){
                    //alert("OK");
                },
                error: function(richiesta,stato,errori){
                    alert("Error. State: "+stato);
                }                 
            });            
        }
        else{
            //alert("Utente non autenticato");
        }
    }          


