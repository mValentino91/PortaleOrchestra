<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <title></title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>


        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <link rel="stylesheet" href="./dist/css/poi_view.css">
        <link rel="stylesheet" href="./dist/css/struttura.css">
        <link rel="stylesheet" href="./dist/css/OrchestraFontIcon.css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="./dist/nanoscroller/jquery.nanoscroller.min.js"></script>
        <link rel="stylesheet" href="./dist/nanoscroller/nanoscroller.css" type="text/css" media="screen" />
        
        <script type="text/javascript" src="./dist/js/struttura.js"></script>
        
        <!-- Owl Carousel -->
        <link href="./dist/owl-carousel/owl.carousel.css" rel="stylesheet">        
        <script src="./dist/owl-carousel/owl.carousel.js"></script>
        
        <style>
            .nano {width: 100%;}
            .nano .nano-pane   { background: #d9d9d9!important; }
            .nano .nano-slider { background: #B6B6B6!important; }
        </style>
        
       
        
        <script>
            $(document).ready(function () {
                
                //enable tile animation
                $.enableTileAnimation(); 
                
                $.enableTileButtons();
                
                $(".nano").nanoScroller();
                
                $.enableTop10Slider();
                
            });
        </script>

        
    </head>

    <body>
<jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">
            
            <input type="hidden" value="${pages.id}">
            <div class="row">
                <div class="col-md-6 col-orc">
                    <div class="box-orc">

                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                           
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox" style="height:100%">
                                <c:forEach var="img" varStatus="cont" items="${pages.imgList}">
                                    <div class="item <c:if test="${cont.count == 1}"> active </c:if> >">
                                        <div class="carousel_content" style="background-image: url('./dist/page/img/${pages.id}/${img.link}');"></div>
                                            
                                            
                                        <c:if test="${not empty img.titolo || not empty img.testo}">
                                        <div class="carousel-caption">
                                            <h5>${img.titolo}</h5>
                                            <p>${img.testo}</p>
                                        </div>
                                        </c:if>
                                    </div>
                                </c:forEach>


                            </div>

                            <!-- Left and right controls -->
                            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only"><spring:message code="label.sliderprev"></spring:message></span>
                            </a>

                            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only"><spring:message code="label.slidernext"></spring:message></span>
                            </a>
                        </div>

                    </div>
                </div>
                <c:set var="tot_tiles" value="${fn:length(pages.tilesList)}"/>
                <c:set var="n_tiles" value="${((tot_tiles>6 )?5:6)}"/>
                <c:set var="subtiles" value="${(tot_tiles/n_tiles) - ((tot_tiles/n_tiles)%1) + ((tot_tiles%n_tiles>0 )?1:0)}"/>
                <c:set var="tot_tiles_round" value="${tot_tiles + ((tot_tiles%n_tiles>0 )?(n_tiles-tot_tiles%n_tiles):0)}"/>
                <c:set var="color_schema" value="${pages.colorSchemaList[0]}"/>
                
                <div class="col-md-6 col-orc">
                    <div class="col-md-12 col-orc box-orc-half">
 
                        <!-- Tiles -->
                        <c:forEach var="i" begin="0" end="2">
                            <div class="col-md-4 col-orc">
                                <div class="box-orc">

                                    <div class="tile" <c:if test="${not empty color_schema.tileColors[i]}"> style="background-color: ${color_schema.tileColors[i]}" </c:if> >
                                        <div class="tile_inner"> 
                                            <c:forEach var="j" varStatus="contTile" begin="0" end="${tot_tiles_round-1}" step="${n_tiles}">
                                                <!-- Sub Tiles -->
                                                <c:choose>
                                                    <c:when test="${i+j < tot_tiles}">
                                                        <c:set var="tile" value="${pages.tilesList.get(i+j)}"/>
                                                        <div class="tile_content <c:choose><c:when test="${contTile.count == 1}">act</c:when> <c:otherwise>dis</c:otherwise></c:choose> <c:if test="${tile.animated == true}">tile_animated</c:if>">
                                                            <a href="${tile.link}">
                                                            <c:if test="${not empty tile.icon}">
                                                                
                                                                <object class="tile_icon <c:choose><c:when test="${tile.start == 'icon'}">tile_icon_act</c:when><c:otherwise>tile_icon_dis</c:otherwise></c:choose>" type="image/svg+xml" data="./dist/page/img/${pages.id}/${tile.icon}"></object>
                                                            </c:if>   
                                                            <c:if test="${not empty tile.text}">
                                                            <div class="tile_text <c:choose><c:when test="${tile.start == 'text'}">tile_icon_act</c:when><c:otherwise>tile_icon_dis</c:otherwise></c:choose>">
                                                                <div class="tile_text_content">
                                                                    ${tile.text}                                                 
                                                                </div>
                                                            </div>
                                                            </c:if> 
                                                             </a>
                                                        </div>                                                         
                                                        
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="tile_content dis"> 
                                                            <div class="tile_text tile_icon_act">
                                                                <div class="tile_text_content">

                                                                </div>
                                                            </div>
                                                        </div> 
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </div> 
                                    </div>                                                  
                                </div> 
                            </div>
                        </c:forEach>                        
                        
                    </div>

                    <div class="col-md-12 col-orc box-orc-half">
                         <!-- Tiles -->
                        <c:forEach var="i" begin="3" end="${n_tiles-1}">
                            <div class="col-md-4 col-orc">
                                <div class="box-orc">

                                    <div class="tile" <c:if test="${not empty color_schema.tileColors[i]}"> style="background-color: ${color_schema.tileColors[i]}" </c:if> >
                                        <div class="tile_inner"> 
                                            <c:forEach var="j" varStatus="contTile" begin="0" end="${tot_tiles_round-1}" step="${n_tiles}">
                                                <!-- Sub Tiles -->
                                                <c:choose>
                                                    <c:when test="${i+j < tot_tiles}">
                                                        <c:set var="tile" value="${pages.tilesList.get(i+j)}"/>
                                                        <div class="tile_content <c:choose><c:when test="${contTile.count == 1}">act</c:when> <c:otherwise>dis</c:otherwise></c:choose> <c:if test="${tile.animated == true}">tile_animated</c:if>">
                                                            <a href="${tile.link}">
                                                            <c:if test="${not empty tile.icon}">
                                                                 <object class="tile_icon <c:choose><c:when test="${tile.start == 'icon'}">tile_icon_act</c:when><c:otherwise>tile_icon_dis</c:otherwise></c:choose>" type="image/svg+xml" data="./dist/page/img/${pages.id}/${tile.icon}"></object>
                                                            </c:if>   
                                                            <c:if test="${not empty tile.text}">
                                                            <div class="tile_text <c:choose><c:when test="${tile.start == 'text'}">tile_icon_act</c:when><c:otherwise>tile_icon_dis</c:otherwise></c:choose>">
                                                                <div class="tile_text_content">
                                                                    ${tile.text}  
                                                                </div>
                                                            </div>
                                                            </c:if> 
                                                             </a>
                                                        </div>                                                         
                                                        
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="tile_content dis"> 
                                                            <div class="tile_text tile_icon_act">
                                                                <div class="tile_text_content">

                                                                </div>
                                                            </div>
                                                        </div> 
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </div> 
                                    </div>                                                  
                                </div> 
                            </div>
                        </c:forEach>  
                        
                        <c:if test="${tot_tiles>6}"> 
                        <div class="col-md-4 col-orc">
                            <div class="box-orc">

                                <div class="tile"  <c:if test="${not empty color_schema.tileColors[5]}"> style="background-color: ${color_schema.tileColors[5]}" </c:if>>
                                    <div class="tile_inner"> 
                                        <a href="#">
                                            <div class="tile_content act"> 
                                                <div class="tile_text tile_icon_act">
                                                    <div class="tile_text_content">
                                                        
                                                            <div class="tile_more">
                                                                <div class="tile_ellipsis" style="display: none;">
                                                                    <i class="fa fa-ellipsis-h"></i>
                                                                </div>
                                                                <div class="tile_arrows">
                                                                    <i id="button_p" class="fa fa-arrow-left disabled" style="margin-right:10px;"></i>
                                                                    <i id="button_n" class="fa fa-arrow-right" style="margin-left:10px;"></i> 
                                                                </div>
                                                            </div>
                                                       
                                                    </div>
                                                </div>
                                            </div> 
                                        </a>             
                                    </div> 
                                </div>                                
                                

                            </div>
                        </div>                        
                        </c:if>
                         
                    </div>

                </div>

            </div>

            <div class="row" style="height:37px; margin-top:5px; margin-bottom:5px;">
                <div class="col-md-12 col-orc">
                    <div class="box-orc">

                        <div id="menu" style="background-color:<c:choose><c:when test="${not empty pages.submenu.color}">${pages.submenu.color}</c:when><c:otherwise>#E74C3C</c:otherwise></c:choose>; margin-top:-5px;">
                                    <ul>
                                        <li></li>
                                    <c:forEach var="cat" items="${pages.submenu.categories}">
                                    <li><a href="${cat.link}">${cat.text}</a></li>
                                    </c:forEach>
                            </ul>  
                        </div>

                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6 col-orc" >
                    <div class="box-orc">

                        <div class="box-elem component component-text" style="overflow: hidden;">
                            <div class="nano">
                                <div class="nano-content">
                                    <div class="details" >
                                        <p class="paragrafo">
                                            ${pages.description}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div> 

                    </div>
                </div>


                <div class="col-md-6 col-orc">
                    <div class="box-orc">

                        <jsp:include page="components/mapPageComponent.jsp"/>

                    </div>			
                </div>

            </div>
                        
           
           <!--             
            <div id="top10-row" class="row" style="height:100px; position: relative;">
                 <div class="col-md-12 col-orc">
                     
                    <div class="top10-container">
                        <div id="poi-prev" class="top_arrow_box top_arrow_left_box">
                            <div class="top_arrow top_arrow_left">
                                <i class="fa fa-chevron-left"></i>
                            </div>
                        </div>
                        <div id="poi-next" class="top_arrow_box top_arrow_right_box">
                            <div class="top_arrow top_arrow_right">
                                <i class="fa fa-chevron-right"></i>
                            </div>
                        </div>                        
                            <div id="top10-slider" class="owl-carousel owl-theme">

                                <div style="width: 329px; float: left;">
                                   <div class="box-orc">
                                       <div class="box-elem component component-text" style="overflow: hidden; border: 0px solid green;">
                                           <div class="top_poi_box">
                                               <div class="top_flag"></div>
                                               <div class="top_poi_img" style="background-image: url('dist/poi/img/5496cfecdf6ef624f2d63de7/cover.jpg')"></div>
                                               <div class="top_poi_content">
                                                   <div class="top_poi_title">
                                                       Pio monte della misericordia
                                                   </div>
                                                   <div class="top_poi_text">
                                                       Questo è il pio monte della misericordia, questo è il pio monte della misericordia.
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>

                                <div style="width: 329px; float: left;">
                                   <div class="box-orc">
                                       <div class="box-elem component component-text" style="overflow: hidden; border: 0px solid green;">
                                           <div class="top_poi_box">
                                               <div class="top_flag"></div>
                                               <div class="top_poi_img" style="background-image: url('dist/poi/img/5496cfecdf6ef624f2d63de7/cover.jpg')"></div>
                                               <div class="top_poi_content">
                                                   <div class="top_poi_title">
                                                       Pio monte della misericordia
                                                   </div>
                                                   <div class="top_poi_text">
                                                       Questo è il pio monte della misericordia, questo è il pio monte della misericordia.
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>

                                <div style="width: 329px; float: left;">
                                   <div class="box-orc">
                                       <div class="box-elem component component-text" style="overflow: hidden; border: 0px solid green;">
                                           <div class="top_poi_box">
                                               <div class="top_flag"></div>
                                               <div class="top_poi_img" style="background-image: url('dist/poi/img/5496cfecdf6ef624f2d63de7/cover.jpg')"></div>
                                               <div class="top_poi_content">
                                                   <div class="top_poi_title">
                                                       Pio monte della misericordia
                                                   </div>
                                                   <div class="top_poi_text">
                                                       Questo è il pio monte della misericordia, questo è il pio monte della misericordia.
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>                        

                                <div style="width: 329px; float: left;">
                                   <div class="box-orc">
                                       <div class="box-elem component component-text" style="overflow: hidden; border: 0px solid green;">
                                           <div class="top_poi_box">
                                               <div class="top_flag"></div>
                                               <div class="top_poi_img" style="background-image: url('dist/poi/img/5496cfecdf6ef624f2d63de7/cover.jpg')"></div>
                                               <div class="top_poi_content">
                                                   <div class="top_poi_title">
                                                       Pio monte della misericordia
                                                   </div>
                                                   <div class="top_poi_text">
                                                       Questo è il pio monte della misericordia, questo è il pio monte della misericordia.
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>

                                <div style="width: 329px; float: left;">
                                   <div class="box-orc">
                                       <div class="box-elem component component-text" style="overflow: hidden; border: 0px solid green;">
                                           <div class="top_poi_box">
                                               <div class="top_flag"></div>
                                               <div class="top_poi_img" style="background-image: url('dist/poi/img/5496cfecdf6ef624f2d63de7/cover.jpg')"></div>
                                               <div class="top_poi_content">
                                                   <div class="top_poi_title">
                                                       Pio monte della misericordia
                                                   </div>
                                                   <div class="top_poi_text">
                                                       Questo è il pio monte della misericordia, questo è il pio monte della misericordia.
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>

                                <div style="width: 329px; float: left;">
                                   <div class="box-orc">
                                       <div class="box-elem component component-text" style="overflow: hidden; border: 0px solid green;">
                                           <div class="top_poi_box">
                                               <div class="top_flag"></div>
                                               <div class="top_poi_img" style="background-image: url('dist/poi/img/5496cfecdf6ef624f2d63de7/cover.jpg')"></div>
                                               <div class="top_poi_content">
                                                   <div class="top_poi_title">
                                                       Pio monte della misericordia
                                                   </div>
                                                   <div class="top_poi_text">
                                                       Questo è il pio monte della misericordia, questo è il pio monte della misericordia.
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>                                 
                            </div>

                    </div>
                </div>
            </div>
            
           -->
            
            <div class="row" style="height: 170px; margin-top: 20px;">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>            


        </div>
        
        <jsp:include page="access/loginModal.jsp" />
    </body>
</html>
