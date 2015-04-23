<%-- 
    Document   : deleteeventform
    Created on : 23-apr-2015, 9.30.01
    Author     : Alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <style>
        .autocomplete-suggestions { border-radius: 0px 0px 4px 4px; border: 1px solid rgba(0,0,0,0.15); background: #FFF; cursor: default; overflow: auto; -webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); -moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);   box-shadow: 0 6px 12px rgba(0,0,0,0.175); }
        .autocomplete-suggestion { border-top: 1px solid rgba(0,0,0,0.15); padding: 5px 10px; white-space: nowrap; text-overflow: ellipsis; overflow: hidden; }
        .autocomplete-no-suggestion { padding: 2px 5px;}
        .autocomplete-selected { background: whitesmoke; }
        .autocomplete-suggestions strong { font-weight: bold; color: #00689a; }
        .autocomplete-group { padding: 2px 5px; }
        .autocomplete-group strong { font-weight: bold; font-size: 16px; color: #00689a; display: block; }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../dist/css/poi_view.css" rel="stylesheet">
    <script src="../dist/js/jquery.js"></script>
    <script src="../dist/js/jquery.autocomplete.js"></script>
</head>
    <body>
    <center>
        <article class="component component-text" style="margin-top:50px; width: 400px;">
				  <div class="big-header" style="background-color: #d9534f;" >
					  <span class="caps">Elimina un evento</span>
				  </div> 
				<div class="details" style="text-align: center;">
        <form action="deletepoi"  accept-charset="UTF-8">
            Digita il nome dell'evento <input type="text" id="autocomplete" style="width: 300px; margin-bottom: 5px; margin-left: 30px;" class="form-control" name="name"> <input class="btn btn-danger" type="submit" value="ELIMINA">
        </form>       
        <h2 style="margin-top: 25px;">Oppure</h2>
        <form action="deletepoi">
            Digita l'id dell'evento <input type="text" style="width: 300px; margin-bottom: 5px; margin-left: 30px;" class="form-control" name="id"> <input class="btn btn-danger" type="submit" value="ELIMINA">
        </form> 
                                </div>
        </article>
        <a href="../admin" class="btn btn-primary" style="margin-top: 20px;">Torna a Gestione Orchestra</a>
    </center>
    </body>
    <script>
    $('#autocomplete').autocomplete({
        serviceUrl: '../Search/Autocompleteevent',
        onSelect: function (suggestion) {
           $('#autocomplete').attr("value",suggestion.data);
        }
    });
</script>
</html>
