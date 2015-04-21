<%-- 
    Document   : ImgGalleryComponent2
    Created on : 4-mar-2015, 11.01.11
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>


  <!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
  <style>
    .nano { width: 100%; max-height: 400px;  }
    .nano .nano-pane   { background: #d9d9d9!important; }
    .nano .nano-slider { background: #B6B6B6!important; }
    
    .imagegallery_container {
      display: block;
      max-height: 400px;
    
    }
    .imagegallery_container .image_gallery {
      display: block;
      width: 49%;
      margin-bottom: 2%;
      background: #999;
      float: left;
      clear: left;
    }
    .image_gallery:hover {
      opacity: 0.9;
      cursor: pointer;
    }
    .image_gallery.right {
      float: right;
      clear: right;
    }
    
  </style>
  <!-- Add fancyBox -->
<link rel="stylesheet" href="./dist/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="./dist/fancybox/jquery.fancybox.js"></script>
<!-- Add Scrollator -->

  <script>
   $(document).ready(function() {
       
     $(".fancybox").attr('rel', 'gallery');
    $(".fancybox").fancybox({
        helpers		: {
			title	: { type : 'inside' }
                    }
    });
     
    $(".nano").nanoScroller();
    
    });
     
      function adjustimgHeights() {
         var leftColumnHeight = 0,
          rightColumnHeight = 0,
          $imgs = $('.imagegallery_container .image_gallery');

        for (var i = 0; i < $imgs.length; i++) {
         

          if (leftColumnHeight > rightColumnHeight) {
            rightColumnHeight += $imgs.eq(i).addClass('right').outerHeight(true);
          } else {
            leftColumnHeight += $imgs.eq(i).outerHeight(true);
          }
        }

        return $imgs;
      }
</script>
<div class="component">
    <div class="details">
<div class="nano">

<div class="nano-content imagegallery_container">
    <c:forEach var = "img" items = "${imggallery.links}" >
        <a class="fancybox" title="${img.credit}" href='./dist/poi/img/${poi.id}/${img.link}'>
            <img class="image_gallery"   src='./dist/poi/img/${poi.id}/${img.link}'>
        </a>
    </c:forEach>
</div>
</div>  
    </div>
</div>
