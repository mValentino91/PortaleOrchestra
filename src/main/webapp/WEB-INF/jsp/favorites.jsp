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

        <title><spring:message code="label.favoritespoi"></spring:message></title>   
        
        <style>
                     
            .poi_preview_box{
              height: 120px; 
              width: 100%; 
              border: 0px solid red; 
              -moz-box-sizing: border-box;
              -webkit-box-sizing: border-box;
              box-sizing: border-box;
              clear: both;
              magin-top: 10px;
              margin-bottom: 10px;
              position: relative;
              border-bottom: 1px solid #E9EAED;
            }  
              
            .poi_preview_img{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 25%; 
                border: 0px solid green;
                float: left;
                background-size: cover;
                background-position: center center;
                border-radius: 0px;                
            }
            
            .poi_preview_content{
                -moz-box-sizing: border-box;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                height: 100%; 
                width: 75%; 
                border: 0px solid yellow; 
                float: left;
                padding: 10px;
            }
            
            .poi_preview_title{
                font-weight: bold;
            }
            
            .poi_preview_delete{
                position: absolute;
                top: 5px;
                right: 5px;
            }
            
            .poi_preview_rating{
                border: 0px solid pink;
            }
            
            
            .clear{
                clear:both;
            }
        </style>
    </head>
    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <div class="col-xs-12">
                <jsp:include page="components/CoverComponentFavorites.jsp"/>
            </div>

            <div class="col-xs-12">
                <!-- aggiungere controllo che visualizza una scritta se nn ci sono preferiti -->
                <jsp:include page="components/FavoriteListComponent.jsp"/> 

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
       
            $( document ).ready(function() {
                enableRatingBar() ;  
                $(".poi_preview_box").each(function(index) {
                    var box = $(this);
                    var fav_rating = $(this).find(".fav_rating");
                    var rating = fav_rating.data('rating');
                    var idpoi = fav_rating.data('idpoi');
                    $(this).find(".range").data("ionRangeSlider").update({
                        from: rating,
                        onFinish: function (data) {
                            saveFavoriteRating(fav_rating.data('idpoi'), data.from);
                        }
                    });
                    var delete_poi = $(this).find(".poi_preview_delete");
                    delete_poi.click(function() {
                        removeFromFavorite(idpoi);
                        box.fadeOut(300, function() { $(this).remove(); });
                        //obtain category div
                        deleteCategoryContainer(box);
                    });
                });
                
            });    
    
        </script>
        
        

        
      
        
    </body>
</html>

