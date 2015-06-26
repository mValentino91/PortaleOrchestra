<%-- 
    Document   : updatepoi
    Created on : 28-mag-2015, 10.12.28
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
        <script src="./dist/js/jquery.js"></script>

        <!--<link href="./dist/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>
        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>


        <script type="text/javascript" src="./dist/nanoscroller/jquery.nanoscroller.min.js"></script>
        <link rel="stylesheet" href="./dist/nanoscroller/nanoscroller.css" type="text/css" media="screen" />

        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraFontIcon.css" rel="stylesheet"> 
        <link href="./dist/css/composite.css" rel="stylesheet">
        <script src="./dist/js/section.js"></script>
        <script src="./dist/js/composite.js"></script>

        <script src="./dist/js/readmore.js"></script>
        <script src="./dist/js/jquery-ui.js"></script>
        <script src="./dist/js/imagesloaded.js"></script>
        <script src="./dist/js/scale.fix.js"></script>
        <script src="./dist/js/jquery.drag-n-crop.js"></script>
        <link href="./dist/css/jquery.drag-n-crop.css" rel="stylesheet" type="text/css">

        <script src="./dist/js/bootstrap.js"></script>
        <!--[if lt IE 9]>
            <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
            <![endif]-->

        <script>
            function loaded() {
                adjustimgHeights();
                $(".nano").nanoScroller();

            }
            $(".compbox").mouseenter(function () {
                alert("entrato");
                $(".compcontent").mouseover();
            });
        </script>
        <style>
            .compcontent {
                height: 20px;
                transition: height 1.5s;
                box-shadow: 0px 2px 2px #888888;
            }
            .compcontent:hover, .compcontent.activecontent {
                height:100%;
                transition: height 1.5s;
            }
            .added {
                height:100%;
                opacity: 0.4;
            }
        </style>

        <title>Orchestra - ${poi.name}</title>   
    </head>
    <body <c:if test="${not empty imggallery}"> onload="loaded()"</c:if> >
            <div class="modal fade bs-example-modal-lg" id="modalcomp">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">Aggiungi Componenti</h4>
                        </div>
                        <div class="modal-body">
                            <div class="compcontainer" style="margin-left: 5px; width: -moz-fit-content; width: webkit-fit-content;  width: fit-content;">
                            <c:forEach var="comp" items="${complist}">
                            <div class="compbox" onmouseover="$('.compcontent', this).addClass('activecontent')" onmouseleave="$('.compcontent',this).removeClass('activecontent')" style=" background-image: url('./dist/img/notFound.png'); text-align: center; border: 1px solid black; float:left; margin: 15px; width: 250px; height: 230px;" >
                                <div class="compcontent" id='${comp[0]}' style="overflow: hidden; border-bottom: 1px solid black; background-color: rgba(254, 255, 255, 0.9);">
                                    <div class="comptitolo">
                                        ${comp[0]}Component
                                    </div>
                                    <div class="compdescr">
                                            ${comp[1]}
                                    </div>
                                    <div class="compbtn" style='margin-bottom:0;'>
                                        <input type="button" class="btn btn-success" style='float: bottom' value="AGGIUNGI">

                                    </div>
                                </div>
                            </div>
                            </c:forEach>

                            <div class="clear"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <c:if test="${not empty coverimg}">
                <div class="col-xs-12">
                    <jsp:include page="components/editing/CoverComponent.jsp"/>
                </div>
            </c:if>

            <div class="col-xs-12 col-sm-8 col-md-8 padding_dx">
                <c:if test="${not empty description}">
                    <script>
                        $("#description").removeClass('compcontent').addClass('added');

                    </script>
                    <jsp:include page="components/editing/SectionComponent.jsp"/> 

                </c:if>
                <c:if test="${ empty vartype || vartype != 'DeepeningPage' }">
                    <jsp:include page="components/mapComponent.jsp"/>
                </c:if>
            </div>


            <div class="col-xs-12 col-sm-4 col-md-4 padding_sx">
                <div class="component">
                    <div class="details">
                        <input type="button" class="btn btn-success" value="GESTISCI COMPONENTI" onclick="$('#modalcomp').modal('show')">
                    </div>
                </div>
                <c:if test="${not empty imggallery}">
                    <script>
                        $("#imggallery").removeClass('compcontent').addClass('added');

                    </script>
                    <jsp:include page="components/editing/ImgGalleryComponent2.jsp"/> 
                    <script>
                        $(".loading_imgs .img").attr('src', './dist/img/loading_dots.gif');
                    </script>
                </c:if>


                <c:if test="${not empty contacts}">
                    <jsp:include page="components/ContactsComponent.jsp"/>
                </c:if>
                <c:choose>
                    <c:when test="${not empty workingtime && not empty prices && not empty services || not empty workingtime && not empty prices || not empty workingtime && not empty services || not empty prices && not empty services}">
                        <jsp:include page="components/CompositeComponent.jsp"/>
                    </c:when> 
                    <c:when test="${not empty workingtime}">
                        <jsp:include page="components/WorkingTimeComponent.jsp"/>
                    </c:when>
                    <c:when test="${not empty prices}">
                        <jsp:include page="components/PricesComponent.jsp"/>
                    </c:when>
                    <c:when test="${not empty services}">
                        <jsp:include page="components/ServicesComponent.jsp"/>
                    </c:when>
                </c:choose>
                <c:if test="${not empty eventsdate}">
                    <jsp:include page="components/EventsDatesComponent.jsp"/>
                </c:if>
                <c:if test="${not empty linkedpoi}">
                    <jsp:include page="components/LinkedPoiComponent.jsp"/>
                </c:if>
            </div>  

            <div class="col-xs-12" id="footer">
                <center>
                    <img class="img-responsive" src="./dist/img/footerPON.png" alt="footer"/>
                </center>
            </div>

            <div style="clear: both"></div>

        </div>


        <script>
            $('.paragrafo').readmore({speed: 5, maxHeight: 150, moreLink: '<a href="#" style="text-align: right; margin-top: -20px!important; font-size:100%; text-decoration: none; margin: 0 0 0 0;"><i class="fa fa-chevron-down"></i></a>', lessLink: '<a href="#" style="text-align: right; font-size:100%; margin-top: -20px!important;  text-decoration: none;"><i class="fa fa-chevron-up"></i></a>'});



        </script>






        <jsp:include page="access/loginModal.jsp" />
    </body>


</html>

