<%-- 
    Document   : infopoi
    Created on : 2-dic-2014, 12.10.28
    Author     : Alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <!--<link href="./dist/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>


        <script type="text/javascript" src="./dist/nanoscroller/jquery.nanoscroller.min.js"></script>
        <link rel="stylesheet" href="./dist/nanoscroller/nanoscroller.css" type="text/css" media="screen" />
        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 
        <link href="./dist/css/composite.css" rel="stylesheet">
        <script src="./dist/js/section.js"></script>
        <script src="./dist/js/composite.js"></script>
        <script src="./dist/googlePlusDesign/js/bootstrap.min.js"></script>
        <script src="./dist/js/readmore.js"></script>
        <script src="./dist/js/favorite_ajax.js"></script>
        <script src="./dist/js/favorite.js"></script>
        <link rel="stylesheet" href="./dist/ion-range/css/normalize.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.skinFlat.css" />
        <link rel="stylesheet" href="./dist/css/favorites.css" />

        <title><spring:message code="label.favoritespoi"></spring:message></title>   
        <style>
            .new_it_button{
                user-drag: none; 
                -moz-user-select: none;
                -webkit-user-drag: none;
                background-color:#285E8E;
                color:#fff;
                height: 50px;
                text-align: center;
                vertical-align: middle;
                line-height: 50px; 
                font-weight: bold;
                cursor: pointer;
                
            }
        </style>

        </head>
        <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-md-12">
                <jsp:include page="components/CoverComponentFavorites.jsp"/>
            </div>

            <div class="col-md-8">
                <!-- aggiungere controllo che visualizza una scritta se nn ci sono preferiti -->
                <jsp:include page="components/FavoriteListComponent.jsp"/> 

            </div>

            <div class="col-md-4">
                 <a style="display:block" href="./saveInCart">
                    <div id="nuovo_it" class="new_it_button">Crea il tuo itinerario</div>
                </a>
                <br>
                <jsp:include page="components/FavoriteTopListComponent.jsp"/> 
            </div>


            <div class="col-xs-12" id="footer">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>

        </div>


        <jsp:include page="access/loginModal.jsp" />
        <script src="./dist/ion-range/js/ion.rangeSlider.js"></script>

        <script>

            $(document).ready(function () {
                enableRatingBar();
                $(".poi_preview_box").each(function (index) {
                    updateRatingBar($(this));
                    enableDeleteButton($(this));


                });

            });

        </script>
    </body>
</html>
