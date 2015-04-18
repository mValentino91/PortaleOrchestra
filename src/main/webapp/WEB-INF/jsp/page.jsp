<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
        <style>
            .nano {width: 100%;}
            .nano .nano-pane   { background: #d9d9d9!important; }
            .nano .nano-slider { background: #B6B6B6!important; }
        </style>
        <script>
            $(document).ready(function () {
                $(".nano").nanoScroller();
            });
        </script>

    </head>

    <body>

        <div class="container-fixed">
            <jsp:include page="components/sideBar.jsp"/>

            <div class="row" style="margin-top: 60px !important;">
                <div class="col-md-6 col-orc">
                    <div class="box-orc">

                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                            <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <c:forEach var="img" varStatus="cont" items="${pages.imgList}">
                                    <li data-target="#myCarousel" data-slide-to="cont.count" <c:if test="${cont.count == 1}"> class="active" </c:if> > </li>
                                    </c:forEach>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox" style="height:100%">
                                <c:forEach var="img" varStatus="cont" items="${pages.imgList}">
                                    <div class="item <c:if test="${cont.count == 1}"> active </c:if> >">
                                        <img src="${img.link}"  >
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

                                        <a href="${tile.link}"><div class="tile" <c:if test="${not empty tile.color}"> style="background-color: ${tile.color}" </c:if> >
                                                <c:if test="${not empty tile.icon}">
                                                    <img src="${tile.icon}" style="margin-left:-5px; margin-top:-10px;">
                                                </c:if>
                                                <c:if test="${not empty tile.text}">
                                                    ${tile.text}
                                                </c:if>
                                            </div></a>

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
                                        <a href="${tile.link}"><div class="tile" <c:if test="${not empty tile.color}"> style="background-color: ${tile.color}" </c:if> >
                                                <c:if test="${not empty tile.icon}">
                                                    <img src="${tile.icon}" style="margin-left:-5px; margin-top:-10px;">
                                                </c:if>
                                                <c:if test="${not empty tile.text}">
                                                    ${tile.text}
                                                </c:if>
                                            </div></a>

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
                <div class="col-md-4 col-orc" >
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

                <div class="col-md-2 col-orc">
                    <c:forEach var="tile" items="${pages.tilesList}" varStatus="cont">
                        <c:if test="${cont.count > 6}">
                            <div class="col-md-12 col-orc box-orc-half">
                                <div class="box-orc">
                                    <a href="${tile.link}"><div class="tile" <c:if test="${not empty tile.color}"> style="background-color: ${tile.color}" </c:if> >
                                            <c:if test="${not empty tile.icon}">
                                                <img src="${tile.icon}" style="margin-left:-5px; margin-top:-10px;">
                                            </c:if>
                                            <c:if test="${not empty tile.text}">
                                                ${tile.text}
                                            </c:if>
                                        </div></a>

                                </div>
                            </div>
                        </c:if>
                    </c:forEach>

                </div>

                <div class="col-md-6 col-orc">
                    <div class="box-orc">

                        <jsp:include page="components/mapComponent.jsp"/>

                    </div>			
                </div>

            </div>

            <div class="row">
                <c:if test="${not empty contacts}">
                    <div class="col-md-4 col-orc">
                        <div class="box-orc">
                            <jsp:include page="components/ContactsComponent.jsp"/>
                        </div>			
                    </div>
                </c:if>
                <div class="col-md-4 col-orc">
                    <div class="box-orc">

                        <div class="box-elem component  component-text" style="height:100%">
                            <div class="big-header contact">
                                <span class="caps">Servizi speciali</span>
                            </div> 

                            <div class="details">
                                <p class="paragrafo">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus facilisis risus consectetur convallis fermentum. 
                                    Nam sodales volutpat pharetra. Sed vulputate, quam et fermentum porta, libero enim ultrices mi, quis dignissim purus elit ut nisi. 
                                    Donec gravida porta lacus viverra dictum. Curabitur mollis consectetur est id efficitur. Phasellus venenatis mi ipsum, 
                                    id blandit erat tincidunt vitae. Nam eget dolor lectus. Etiam aliquam diam in tortor ullamcorper cursus. 
                                    Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin malesuada enim at turpis vulputate 
                                    varius. Nulla sed tempor quam, vel vulputate neque. 
                                </p>
                            </div>

                            <div class="clear"></div>
                        </div>

                    </div>			
                </div>

                <div class="col-md-4 col-orc">
                    <div class="box-orc">

                        <div class="box-elem component  component-text" style="height:100%">
                            <div class="big-header contact">
                                <span class="caps">Prodotti speciali</span>
                            </div> 

                            <div class="details">
                                <p class="paragrafo">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus facilisis risus consectetur convallis fermentum. 
                                    Nam sodales volutpat pharetra. Sed vulputate, quam et fermentum porta, libero enim ultrices mi, quis dignissim purus elit ut nisi. 
                                    Donec gravida porta lacus viverra dictum. Curabitur mollis consectetur est id efficitur. Phasellus venenatis mi ipsum, 
                                    id blandit erat tincidunt vitae. Nam eget dolor lectus. Etiam aliquam diam in tortor ullamcorper cursus. 
                                    Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Proin malesuada enim at turpis vulputate 
                                    varius. Nulla sed tempor quam, vel vulputate neque. 
                                </p>
                            </div>

                            <div class="clear"></div>
                        </div>

                    </div>			
                </div>

            </div>


        </div>
    </body>
</html>