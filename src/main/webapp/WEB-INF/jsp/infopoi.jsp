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
        <script>
            function loaded() {
                adjustimgHeights();
                $(".nano").nanoScroller();

            }
        </script>
        <title>Orchestra - ${poi.name}</title>   
    </head>
    <body <c:if test="${not empty imggallery}"> onload="loaded()"</c:if> >
        <jsp:include page="components/topBar.jsp"/>
        <div class="container-fixed">

            <c:if test="${not empty coverimg}">
                <div class="col-xs-12">
                    <jsp:include page="components/CoverComponent.jsp"/>
                </div>
            </c:if>

            <div class="col-xs-12 col-sm-8 col-md-8 padding_dx">
                <c:if test="${not empty description}">
                    <jsp:include page="components/SectionComponent.jsp"/> 
                </c:if>
                <c:if test="${ empty vartype || vartype != 'DeepeningPage' }">
                    <jsp:include page="components/mapComponent.jsp"/>
                </c:if>
            </div>


            <div class="col-xs-12 col-sm-4 col-md-4 padding_sx">

                <c:if test="${not empty imggallery}">
                    <jsp:include page="components/ImgGalleryComponent2.jsp"/> 
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
                     <jsp:include page="components/LinkedPoiComponent.jsp"/>

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

