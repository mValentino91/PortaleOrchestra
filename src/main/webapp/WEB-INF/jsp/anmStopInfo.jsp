<%-- 
    Document   : anmStopInfo
    Created on : 3-giu-2015, 10.11.59
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="./dist/js/jquery.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="./dist/css/bootstrap.min.css">
        <title>JSP Page</title>
        <script>
            function getInformation() {
                var code = $('#code').val();
                $('#loading').show();
                $('#result').html('');
                $.ajax({
                    dataType: "json",
                    url: './anmServices/stops/allInfo/' + code
                }).done(function (data) {
                    $('#loading').hide();
                    var json = data;
                    if (json.error === undefined) {
                        $('#result').append(
                                '<br><b>Nome:</b> ' + json.nome
                                + '<br><b>Linee:</b> '
                                )
                        for (var i = 0; i < json.transiti.length; i++) {
                            $('#result').append(json.transiti[i] + ' ');
                        }
                        $('#result').append('<hr><b>Previsioni:</b> ');
                        for (var i = 0; i < json.previsioni.length; i++) {
                            $('#result').append('<br>' + json.previsioni[i].codice + ' - ' + json.previsioni[i].time);
                        }
                    } else {
                        $('#result').append('Informazioni sulla fermata non disponibili!');
                    }
                })
            }
        </script>
    </head>
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container" style="padding-top: 100px;">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <h3>Servizio Anm</h3>
                <center>
                    <input id="code" type="text" placeholder="Inserire Codice Fermata" class="form-control"/>
                    <br><button style="width: 100%;" class="btn btn-default" onclick="getInformation()">Ottieni Informazioni</button>
                    <div id="result"></div>
                    <img id="loading" style="display: none; margin-top:30px;"src="./dist/img/loading.gif"/>
                </center>
            </div>
            <div class="col-md-4"></div>
        </div>
    </body>
</html>
