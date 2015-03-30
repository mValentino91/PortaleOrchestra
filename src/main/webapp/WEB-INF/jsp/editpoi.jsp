<%-- 
    Document   : editpoi
    Created on : 18-feb-2015, 17.56.50
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../dist/css/poi_view.css" rel="stylesheet">
        <title>Modifica un poi</title>
    </head>
    <body>
    <center>
        <article class="component component-text" style="margin-top:50px; width: 400px;">
				  <div class="big-header " style="background-color: #f0ad4e;">
					  <span class="caps">Modifica un POI</span>
				  </div> 
				<div class="details" style="text-align: center;">
        <form action="editpoi"  accept-charset="UTF-8">
            Digita il nome del Poi <input type="text" style="width: 300px; margin-bottom: 5px; margin-left: 30px;" class="form-control" name="name"> <input class="btn btn-warning" type="submit" value="MODIFICA">
        </form> 
        <h2 style="margin-top:25px;">Oppure</h2>
        <form action="editpoi">
            Digita l'id del Poi <input type="text" style="width: 300px; margin-bottom: 5px; margin-left: 30px;" class="form-control" name="id"> <input class="btn btn-warning" type="submit" value="MODIFICA">
        </form>  
                                </div>
        </article>
        <a href="../admin" class="btn btn-primary" style="margin-top: 20px;">Torna a Gestione POI</a>
    </center>
    </body>
</html>

