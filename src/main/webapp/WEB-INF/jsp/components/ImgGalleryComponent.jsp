<%-- 
    Document   : ImgGalleryComponent
    Created on : 27-nov-2014, 12.56.32
    Author     : Marco Valentino
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- CAROSELLO
================================================================ -->
<link href="./dist/components/imgGalleryComponent/ImgGalleryComponent.css" rel="stylesheet">
<div class="panel panel-default">
    <div class="panel-thumbnail">
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol id="carouselIndicators" class="carousel-indicators">
            </ol>
            <div id="carouselInner" class="carousel-inner">
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
        </div>
    </div>
</div>
<script>
    //funzione di inizializzazione del carosello  
    function initCarousel() {
        var i = 0;
        if (imgArray.length > 0) {

            document.getElementById('carouselInner').innerHTML +=
                    '<div class="item active">' +
                    '<img src="' + imgArray[i] + '" alt=""/>';

            document.getElementById('carouselIndicators').innerHTML +=
                    '<li data-target="#myCarousel" data-slide-to="' + i + '" class="active"></li>';
            i++;
        }
        while (i < imgArray.length && imgArray[i] !== 'stop') {

            document.getElementById('carouselInner').innerHTML +=
                    '<div class="item">' +
                    '<img class="img-responsive" src="' + imgArray[i] + '" alt=""/>';

            document.getElementById('carouselIndicators').innerHTML +=
                    '<li data-target="Carousel" data-slide-to="' + i + '"></li>';
            i++;
        }

        $('.item').click(function() {
            $('#bigCarousel').html($(this).html());
            $('#modalBigCarousel').modal('show');
        });
    }
    var imgArray = new Array(
    <c:forEach var = "link" items = "${imagegallery.links}" >
    './dist/poi/img/${poi.id}/${link}',
    </c:forEach>
    'stop');
    initCarousel();
</script>

<!--/CAROSELLO
================================================================= -->