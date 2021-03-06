<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <script src="./dist/js/jquery.js"></script>
        <script src="./dist/js/bootstrap.js"></script>
        <title>Orchestra - Amministrazione </title>
    </head>

    <body>

        <jsp:include page="components/topBar.jsp"/>
        <center>
            <sec:authorize access="hasAnyRole('ROLE_SUPERADMIN','MANAGE_POI')">
                <article class="component component-text"  style="max-width: 450px; margin-top: 50px;">
                    <div class="big-header contact">
                        <span class="caps">gestione poi</span>
                    </div> 

                    <div class="details" style="text-align: center;">

                        <a class="btn btn-primary" style="width: 200px; margin-bottom: 5px;" href="Map?category=all"><h2>Visualizza Mappa</h2></a>
                        <a class="btn btn-success" style="width: 200px; margin-bottom:5px;" href="admin/newpoi"><h2>Aggiungi un poi</h2></a>
                        <a class="btn btn-warning" style="width: 200px; margin-right:0" href="admin/editpoi"><h2>Modifica un poi</h2></a>
                        <a class="btn btn-danger" style="width: 200px; margin-right: 0" href="admin/deletepoi"><h2>Elimina un poi</h2></a>

                    </div>

                </article>
            </sec:authorize>
              <sec:authorize access="hasAnyRole('ROLE_SUPERADMIN','MANAGE_EVENT')">
            <article class="component component-text"  style="max-width: 450px; margin-top: 30px;">
                <div class="big-header contact">
                    <span class="caps">gestione eventi</span>
                </div> 

                <div class="details" style="text-align: center;">

                    <a class="btn btn-success" style="width: 405px; margin-right: 0; margin-top: 5px;" href="admin/newevent"><h2>Nuovo Evento</h2></a>
                    <a class="btn btn-warning" style="width: 405px; margin-right: 0; margin-top: 5px;" href="admin/editevent"><h2>Modifica Evento</h2></a>
                    <a class="btn btn-danger" style="width: 405px; margin-right: 0; margin-top: 5px;" href="admin/deleteevent"><h2>Elimina un evento</h2></a>
                </div>

            </article>
              </sec:authorize>
             <sec:authorize access="hasAnyRole('ROLE_SUPERADMIN','MANAGE_DEEP_PAGE')">
            <article class="component component-text"  style="max-width: 450px; margin-top: 30px;">
                <div class="big-header contact">
                    <span class="caps">gestione pagine d'approfondimento</span>
                </div> 

                <div class="details" style="text-align: center;">
                    <a class="btn btn-primary" style="width: 405px; margin-right: 0; margin-top: 5px;" href="admin/viewallDP"><h2>Visualizza tutte le pagine d'approfondimento</h2></a>
                    <a class="btn btn-success" style="width: 405px; margin-right: 0; margin-top: 5px;" href="admin/newdpage"><h2>Nuova pagina d'approfondimento</h2></a>
                    <a class="btn btn-warning" style="width: 405px; margin-right: 0; margin-top: 5px;" href="admin/editdpage"><h2>Modifica pagina d'approfondimento</h2></a>
                    <a class="btn btn-danger" style="width: 405px; margin-right: 0; margin-top: 5px;" href="admin/deletedpage"><h2>Elimina pagina d'approfondimento</h2></a>


                </div>
        </center>
    </article>
             </sec:authorize>
</body>
</html>
