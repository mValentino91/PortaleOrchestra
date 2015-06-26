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

<script src="./dist/js/jquery.form.js"></script>
<!--[if lt IE 9]><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<style>
    .nano { width: 100%; max-height: 400px;  }
    .nano .nano-pane   { background: #d9d9d9!important; }
    .nano .nano-slider { background: #B6B6B6!important; }

    .imagegallery_container {
        display: block;
        max-height: 400px;
        height:400px;

    }
    .imagegallery_container .image_gallery {

        display: block;
        width: 49%;
        margin-bottom: 2%;
        background: #999;
        float: left;
        clear: left;
    }
    .images {
        width:100%;
    }
    .imgop {
        position:absolute;
        display: none;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 20px;
        background-color: rgba(255, 255, 255, 0.7);
        color: #285E8E;
        text-align: left;

    }
    .imgop i {
        padding-left: 2px;
        padding-right: 2px;
        font-weight: bold;
    }
    .imagegallery_container .addimage_gallery {

        display: block;
        width: 100%;
        margin-bottom: 2%;
        background: #999;
        float: left;
        clear: left;
    }
    .addimage_gallery:hover {
        opacity: 0.9;
        cursor: pointer;
    }
    .image_gallery:hover {
        opacity: 0.9;
        cursor: pointer;
    }
    .image_gallery.right {
        float: right;
        clear: right;
    }

    .loading, .loading:hover {
        opacity: 0.5;
        cursor: wait;
    }

</style>
<!-- Add fancyBox -->
<link rel="stylesheet" href="./dist/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="./dist/fancybox/jquery.fancybox.js"></script>
<!-- Add Scrollator -->

<script>
    $(document).ready(function () {

        $(".fancybox").attr('rel', 'gallery');
        $(".fancybox").fancybox({
            helpers: {
                title: {type: 'inside'}
            }});
    });
    function adjustimgHeights() {
        $(".loading_imgs").remove();
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
        if (leftColumnHeight >= rightColumnHeight && leftColumnHeight < 400) {
            $(".imagegallery_container").css("height", (leftColumnHeight) + "px");
        }
        else if (rightColumnHeight > leftColumnHeight && rightColumnHeight < 400) {
            $(".imagegallery_container").css("height", (rightColumnHeight) + "px");
        }
        return $imgs;
    }
    function updateImgs(input) {

        for (var k = 0; k < input.files.length; k++) {

            readURLS(input, k);
        }
        setTimeout(function () {
            adjustimgHeights();
        }, 500);

        setTimeout(function () {
            $(".nano").nanoScroller();
        }, 500);

        setTimeout(function () {
            $(".nano").nanoScroller({scroll: 'bottom'});
        }, 500);
        setTimeout(function () {
            $('.imgopel').tooltip();
        }, 500);

        $("#galleryform").ajaxForm(function () {
            setTimeout(function () {
                $(".loading").removeClass("loading");
            }, 500);

        }).submit();

    }

    function readURLS(input, i) {


        var reader = new FileReader();

        reader.onload = function (e) {
            var cont = document.getElementById("imgscont");
            $imgs = $('.imagegallery_container .image_gallery');

            var alink = document.createElement("a");
            alink.className = "fancybox";
            alink.setAttribute("href", e.target.result);
            alink.setAttribute("rel", "gallery");

            var newdiv = document.createElement("div");
            newdiv.className = "image_gallery loading";
            newdiv.setAttribute("num", $imgs.length + 1);
            newdiv.setAttribute("onmouseover", "$('.imgop', this).show();");
            newdiv.setAttribute("onmouseleave", "$('.imgop', this).hide();");
            newdiv.setAttribute("style", "position: relative");

            var newdeldiv = document.createElement("div");
            newdeldiv.className = "imgop";



            var newi = document.createElement("i");
            newi.className = "imgopel fa fa-times";
            newi.setAttribute("onclick", "delimg(this.parentNode.parentNode)");
            newi.setAttribute("data-toggle", "tooltip");
            newi.setAttribute("data-placement", "right");
            newi.setAttribute("data-original-title", "Cancella");
            newdeldiv.appendChild(newi);

            var newic = document.createElement("i");
            newic.className = "imgopel fa fa-copyright";
            //newic.setAttribute("onclick", "");
            newic.setAttribute("data-toggle", "tooltip");
            newic.setAttribute("data-placement", "right");
            newic.setAttribute("data-original-title", "Imposta Copyright");
            newdeldiv.appendChild(newic);

            var img = document.createElement("img");
            img.className = "images";
            img.setAttribute("src", e.target.result);
            alink.appendChild(img);
            newdiv.appendChild(alink);
            newdiv.appendChild(newdeldiv);
            cont.appendChild(newdiv);

        };

        reader.readAsDataURL(input.files[i]);



    }

    function delimg(img) {
        $(img).addClass("loading");
        $("#dimg").val($(img).attr('num'));

        $("#deleteimgform").ajaxForm(function () {
            setTimeout(function () {
                $(img).fadeOut(250);
                $(img).remove();
            }, 500);
            setTimeout(function () {
                $(".right").removeClass("right");
                adjustimgHeights();
            }, 500);

            setTimeout(function () {
                $(".nano").nanoScroller();
            }, 500);


        }).submit();
    }
    function modcop(img) {

        $("#cimg").val($(img).attr('num'));
        $("#modinp").val($("div[num ='" + $(img).attr('num') + "'] a").attr("title"));
        $("#copymod").modal('show');

    }
    function modcopy() {
        $("div[num ='" + $("#cimg").val() + "'] a").attr("title", $("#modinp").val());
        $("#copyr").val($("#modinp").val());
        $("#modinp").hide();
        $("#modbtn").hide();
        var ok = document.createElement("h4");
        ok.setAttribute("style", "display:none");
        ok.innerHTML = "<center><b>Fatto</b></center>";
        $("#modalcont").append(ok);
        setTimeout(function () {
            $(ok).fadeIn();
        }, 300);
        $("#updateCop").ajaxForm(function () {

            setTimeout(function () {

                $("#closemod").click();
                $("modinp").val("");
                $(ok).hide();
                setTimeout(function () {
                    $("#modinp").show();
                    $("#modbtn").show();

                }, 300);

            }, 500);
        }).submit();


    }

</script>

<div class="component">
    <div class="details">
        <div class="loading_imgs" style="text-align: center;">
            <img class="img" src="./dist/img/loading_dots.gif"  />
        </div>
        <div class="nano">

            <div class="nano-content imagegallery_container" id="imgscont">

                <c:forEach var = "img" items = "${imggallery.links}" varStatus="tot">
                    <c:choose>
                        <c:when test="${ empty vartype || vartype != 'DeepeningPage' }">
                            <div num="${tot.count}" class="image_gallery" style="position: relative" onmouseover="$('.imgop', this).show();" onmouseleave="$('.imgop', this).hide();">
                                <a   class="fancybox"  title="${img.credit}" href='./dist/poi/img/${poi.id}/${img.link}'>
                                    <img class="images" src='./dist/poi/img/${poi.id}/${img.link}'>

                                </a>
                                <div class="imgop">
                                    <i onclick="delimg(this.parentNode.parentNode)" class="imgopel fa fa-times" data-toggle="tooltip" data-placement="right" data-original-title="Cancella"></i>
                                    <i onclick="modcop(this.parentNode.parentNode)" class="imgopel fa fa-copyright" data-toggle="tooltip" data-placement="right" data-original-title="Imposta Copyright"></i>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <a num="${tot.count}" class="fancybox image_gallery" onmouseover="$('.delimg', this).show();" onmouseleave="$('.delimg', this).hide();" style="position: relative" title="${img.credit}" href='./dist/dpage/img/${poi.id}/${img.link}'>
                                <img class="images" src='./dist/dpage/img/${poi.id}/${img.link}'>
                                <div  class="delimg"> 
                                    <i style="color: red; font-size: 120%;" class="fa fa-trash-o"></i>
                                </div>
                            </a>

                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </div>
        </div>
        <div class="addimage_gallery" id="addimgs" onclick="$('#galleryfile').click()" style="margin-top: 10px; padding-top: 2.5%; background-color: #4caf50; height:30px; text-align: center">
            <i style="color: #FFF; font-size: 120%; text-transform: uppercase;" class="fa fa-plus"> Aggiungi immagini</i>
        </div>
    </div>
</div>

<form action="UpdateGallery" id="galleryform" method="post" enctype="multipart/form-data">
    <input type="file" name="files" id="galleryfile" accept="image/*" onchange="updateImgs(this)" style="display: none" multiple="multiple">
    <input type="text"  name="id" value="${poi.id}" style="display: none">
</form>
<form action="DeleteImg" id="deleteimgform" method="post" >
    <input type="text"  name="id" value="${poi.id}" style="display: none">
    <input type="text" id="dimg"  name="del" value="" style="display: none">
</form>
<script>
    $(function () {
        $('.imgopel').tooltip();
    });
</script>
<div id="copymod" class="modal fade bs-example-modal-sm" >
    <div class="modal-dialog modal-sm">

        <div class="modal-content" id="modalcont">
            <div class="modal-header">
                <button type="button" id="closemod" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" >Inserisci copyright</h4>
            </div>
            <center>
                <input class="form-control" id="modinp" style="margin: 5px; width: 250px;" required>
                <input type="button" class="btn btn-success" id="modbtn" style="margin-bottom: 5px;" onclick="modcopy();" value="SALVA">
            </center>
        </div>
    </div>

</div>
<form id="updateCop" action="UpdateCopy" method="post">
    <input type="text" name="id" value="${poi.id}" style="display: none">
    <input type="text" id="cimg"  name="num" value="" style="display: none">
    <input type="text" name="copyright" id="copyr" style="display: none">
</form>