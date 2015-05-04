<%-- 
    Document   : FavoriteListComponent
    Created on : 19-apr-2015, 18.50.47
    Author     : andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
                top: 10px;
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
                <article class="component component-text">
                    <div class="details">

                        <!-- implementare il foreach dei preferiti per categorie-->
                        <div class="paragrafo">
                            <div class="favorite-container" style="min-height: 200px;">

                                <c:if test="${empty map_cat.map.keySet()}">
                                    <div class="no_favorites_result">
                                        <spring:message code="label.no_favorites_presents"></spring:message>
                                        </div>
                                </c:if>

                                <c:forEach var="cat" items="${map_cat.map.keySet()}">
                                    <div class="category-container">
                                        <h4>${map_slug.get(cat)}</h4>

                                        <div class="poi">


                                            <c:forEach var="p" items="${map_cat.map.get(cat)}">
                                                <div class="poi_preview_box">
                                                    <div class="poi_preview_content">
                                                        <div class="poi_preview_title">
                                                            ${p.name}
                                                            <div class="poi_preview_delete">

                                                                <i class="fa fa-trash"  style="cursor:pointer; font-size:16px;" ></i>
                                                                <i class="fa fa-trash " style="cursor:pointer; font-size:16px;" ></i>

                                                            </div>
                                                        </div>
                                                        <div class="poi_preview_text">
                                                            ${p.address}
                                                        </div>
                                                        

                                                    </div>
                                                    <div class="clear"></div>     

                                            </c:forEach>


                                        </div>
                                    </div>

                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </article>

            </div>

            <div class="col-xs-12" id="footer">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>

            <jsp:include page="access/loginModal.jsp" />



    </body>
</html>
