<!doctype html>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="./dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="./dist/css/poi_view.css">
        <link rel="stylesheet" type="text/css" href="./dist/css/font-awesome.min.css">
        <script src="./dist/js/jquery.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>   
    </head>

    <body>
        <div class="col-xs-12">
            <jsp:include page="components/topBar.jsp"/>   
            <div class="container-fixed" style="padding-top: 60px;">
                <div class="col-xs-12">
                    <p>
                        Inserisci il codice segreto(senza spazi) recuperato dalla lettura del QR-Code.
                        Il sistema verificherà che l'utente ha una card attiva e l'offerta non è stata
                        precedentemente utilizzata (oppure scaduta).
                        Un messaggio di conferma permetterà di completare la transazione.
                    </p>

                        <label class="col-xs-12" style="padding-left: 1px; margin-left: 3px;">Codice Itinerario</label>
                        <input type="text" id="idItinerary" value="" />
                        
                        
                </div>   
                <div class="col-xs-4" style="margin-top: 10px;">
                    <input id="validate" type="button" class="btn btn-primary" value="Valida Offerta" >
                </div>

                   
            </div>
        </div>
            <script>
                $("#validate").click(function(){
                   var code = $("#idItinerary").val();
                   //alert(code);
                   $.ajax({
                        type: "GET",
                        url: "./accessOfferCard",
                        data: "code="+code,
                        success: function(data){
                            $("#idItinerary").val('');
                            alert(data);
                        }
                    }); 
                });
            </script>
            
    </body>
</html>
