<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
    <head>
        <title>Orchestra</title>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>
        <link rel="stylesheet" href="./dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./dist/css/poi_view.css">
        <link rel="stylesheet" href="./dist/css/struttura.css">
        <link rel="stylesheet" href="./dist/css/OrchestraFontIcon.css">
        <script src="./dist/js/jquery.js"></script>
        <script src="./dist/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="./dist/nanoscroller/jquery.nanoscroller.min.js"></script>
        <link rel="stylesheet" href="./dist/nanoscroller/nanoscroller.css" type="text/css" media="screen" />
        <style>
            .nano {width: 100%;}
            .nano .nano-pane   { background: #d9d9d9!important; }
            .nano .nano-slider { background: #B6B6B6!important; }
        </style>
        <!-- Add fancyBox -->
        <link rel="stylesheet" href="./dist/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
        <script type="text/javascript" src="./dist/fancybox/jquery.fancybox.js"></script>

        <script type="text/javascript" src="./dist/js/weather.js"></script>
        
        <script type="text/javascript" src="./dist/js/struttura.js"></script>
        <script>
            $(document).ready(function () {
                
                //enable tile animation
                $.enableTileAnimation();
                
                //enable fancybox on fancy tile
                $("#tilevid").click(function () {

                    $.fancybox({
                        'padding': 0,
                        'autoScale': false,
                        'transitionIn': 'none',
                        'transitionOut': 'none',
                        'title': this.title,
                        'href': this.href.replace(new RegExp("watch\\?v=", "i"), 'v/'),
                        'type': 'swf',
                        'swf': {
                            'wmode': 'transparent',
                            'allowfullscreen': 'true'
                        }
                    });
                    return false;
                });


            });
        </script>


    </head>

    <body>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed ">

            <input type="hidden" value="${pages.id}" />
            <div class="row" >
                <div class="col-md-6 col-orc">
                    <div class="box-orc">

                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                           
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox" style="height:100%">
                                <c:forEach var="img" varStatus="cont" items="${pages.imgList}">
                                    <div class="item <c:if test="${cont.count == 1}"> active </c:if> >">
                                        <img src="./dist/page/img/${pages.id}/${img.link}"  >
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
                                <span class="sr-only">Precedente</span>
                            </a>

                            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Prossimo</span>
                            </a>
                        </div>

                    </div>
                </div>

                <div class="col-md-6 col-orc">
                    <div class="col-md-12 col-orc box-orc-half">
                        <c:forEach var="tile" items="${pages.tilesList}" varStatus="cont">
                            <c:if test="${cont.count <= 3}">
                                <div class="col-md-4 col-orc">
                                    <div class="box-orc">
                                        
                                            <div class="tile" <c:if test="${not empty tile.color}"> style="background-color: ${tile.color}" </c:if> >
                                                <div class="tile_inner"> 
                                                    <div class="tile_content act tile_animated"> 
                                                        <a href="${tile.link}">
                                                            <c:if test="${not empty tile.icon}">
                                                               <div class="tile_icon tile_icon_act"> 
                                                                  <img src="./dist/page/img/${pages.id}/${tile.icon}"></img>
                                                               </div>
                                                            </c:if>                                                        
                                                            <div class="tile_text tile_icon_dis">
                                                                <div class="tile_text_content">
                                                                    <c:if test="${not empty tile.text}">
                                                                        ${tile.text}
                                                                    </c:if>                                                                
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </div> 				
                                                </div> 
                                            </div>   
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>

                    </div>

                    <div class="col-md-12 col-orc box-orc-half">
                        <c:forEach var="tile" items="${pages.tilesList}" varStatus="cont">
                            <c:if test="${cont.count > 3 && cont.count <= 6}">

                                <div class="col-md-4 col-orc">
                                    <div class="box-orc">
                                        
                                        <div class="tile" <c:if test="${not empty tile.color}"> style="background-color: ${tile.color}" </c:if> >
                                            <div class="tile_inner"> 
                                                <a href="${tile.link}">
                                                    <div class="tile_content act tile_animated"> 
                                                        <c:if test="${not empty tile.icon}">
                                                           <div class="tile_icon tile_icon_act"> 
                                                              <img src="./dist/page/img/${pages.id}/${tile.icon}"></img>
                                                           </div>
                                                        </c:if>                                                     
                                                        <div class="tile_text tile_icon_dis">
                                                            <div class="tile_text_content">
                                                                <c:if test="${not empty tile.text}">
                                                                    ${tile.text}
                                                                </c:if>           
                                                            </div>
                                                        </div>	
                                                    </div>
                                                </a>
                                            </div> 
                                        </div>             
                                                
                                    </div>
                                </div>                                  
                                
                            </c:if>
                        </c:forEach>				
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
                <div class="col-md-6 col-orc">
                    <div class="col-md-12 col-orc box-orc-half">
                        <c:forEach var="tile" items="${pages.tilesList}" varStatus="cont">
                            <c:if test="${cont.count > 6 and cont.count <=9}">
                                <div class="col-md-4 col-orc">
                                    <div class="box-orc">
                                        <div <c:if test="${cont.count == 8}">id="weather"</c:if>  class="tile" <c:if test="${not empty tile.color}"> style="background-color: ${tile.color}" </c:if> >
                                            <div class="tile_inner"> 
                                                <a href="${tile.link}">
                                                    <div class="tile_content act tile_animated"> 
                                                        <c:if test="${not empty tile.icon}">
                                                           <div class="tile_icon tile_icon_act"> 
                                                              <img src="./dist/page/img/${pages.id}/${tile.icon}"></img>
                                                           </div>
                                                        </c:if>                                                            
                                                        <div class="tile_text tile_icon_dis">
                                                            <div class="tile_text_content">
                                                                <c:if test="${not empty tile.text}">
                                                                    ${tile.text}
                                                                </c:if>   
                                                            </div>
                                                        </div>	
                                                    </div> 	
                                                </a>
                                            </div> 
                                        </div>                                            
                                        

                                    </div>
                                </div>
                                <c:if test="${cont.count == 8}">
                                    <script>
                                        getWeather();
                                        
                                    </script>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </div>

                    <div class="col-md-12 col-orc box-orc-half">
                        <c:forEach var="tile" items="${pages.tilesList}" varStatus="cont">
                            <c:if test="${cont.count > 9}">
                                <div class="col-md-4 col-orc">
                                    <div class="box-orc">
                                        
                                        <div class="tile" <c:if test="${not empty tile.color}"> style="background-color: ${tile.color}" </c:if> >
                                            <div class="tile_inner"> 
                                                <a href="${tile.link}">
                                                    <div class="tile_content act tile_animated"> 
                                                        <c:if test="${not empty tile.icon}">
                                                            <c:if test="${not empty tile.icon}">
                                                               <div class="tile_icon tile_icon_act"> 
                                                                  <img src="./dist/page/img/${pages.id}/${tile.icon}"></img>
                                                               </div>
                                                            </c:if>                         
                                                        </c:if>                                                        
                                                        <div class="tile_text tile_icon_dis">
                                                            <div class="tile_text_content">
                                                                <c:if test="${not empty tile.text}">
                                                                    ${tile.text}
                                                                </c:if>   
                                                            </div>
                                                        </div>
                                                    </div> 
                                                </a>
                                            </div> 
                                        </div>                                            
                                        
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>

                        <div class="col-md-8 col-orc">
                            <div class="box-orc">
                                <div class="box-elem" style="background-color:#fff">
                                    <p class="paragrafo" style="padding:5px;">
                                        ${pages.description}

                                    </p>
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
            
            <div class="row" style="height: 170px; margin-top: 20px;">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>



        </div>
   
	
                    
    </body>
    <jsp:include page="access/loginModal.jsp" />
</html>
