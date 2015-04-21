<%-- 
    Document   : okpageadmin
    Created on : 21-apr-2015, 14.32.25
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../dist/css/poi_view.css" rel="stylesheet">
        <title>Orchestra</title>
    </head
    <jsp:include page="components/topBar.jsp"/>
    <center>
        <div class="container-fixed">
            <div class="col-md-12">
        </div>
        <article class="component component-text">


            <div class="details" style="text-align: center;">
                ${mess}
            </div>
        </article>
        </div>
            <h2><a href="../admin" class="btn btn-primary" style="margin-top: 20px;">Torna a Gestione Orchestra</a></h2>
    </center>
</html>