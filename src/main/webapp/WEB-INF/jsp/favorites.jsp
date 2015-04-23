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
        <link rel="stylesheet" href="./dist/ion-range/css/normalize.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.css" />
        <link rel="stylesheet" href="./dist/ion-range/css/ion.rangeSlider.skinFlat.css" />

        <title>I tuoi preferiti</title>   
        
        <style>
            .text{
                font-family: "Roboto",sans-serif;
                width: 100%;
                font-size: 13px;
                border: 0px solid #008000;
                margin: 5px 0px 0px;
                color: #808080;
                overflow: hidden;
                box-sizing: border-box;
                clear: both;
                font-weight: 300;
            }
            
            .favorite_img {
                border: 0px solid green;
                width: 150px;
                height: 120px;
                float: right;
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                overflow: hidden;
                background-size: cover;
                background-position: center center;
                border-radius: 0px;
              }
            
        </style>
    </head>
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-xs-12">
                <jsp:include page="components/CoverComponentFavorites.jsp"/>
            </div>

            <div class="col-xs-12 col-sm-8 col-md-8 padding_dx">
                <!-- aggiungere controllo che visualizza una scritta se nn ci sono preferiti -->
                <jsp:include page="components/FavoriteListComponent.jsp"/> 

            </div>


            <div class="col-xs-12 col-sm-4 col-md-4 padding_sx">

                <!-- div vuoto che visualizza i top preferiti-->
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
            function enableRatingBar(){

                $(".range").ionRangeSlider({
                    min: 1,
                    max: 5,
                    from: 1,
                    step: 1,
                    hide_min_max: true,
                    hide_from_to: false,
                    grid: false,
                    grid_snap: false,
                    onFinish: function (data) {
                        console.log("selected value: " + data.from);
                    }
                });

            }
        </script>        
        
        
        <script>
            $( document ).ready(function() {
                enableRatingBar() ;  
               
                
                $(".fav_rating").each(function(index) {
                    rating = $(this).data('rating');
                    $(this).find(".range").data("ionRangeSlider").update({
                        from: rating
                    });
                });
                
            });
            
        </script>
        
        

        
      
        
    </body>
</html>

