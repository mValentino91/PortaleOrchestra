<%-- 
    Document   : CoverComponent
    Created on : 28-mag-2015, 10.01.45
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<script src="./dist/js/jquery.form.js"></script>


<style type="text/css">

    .boh:hover{
        opacity: 0.5;
        cursor: pointer;

    }
    .edcov {
        background-color: rgba(255, 255, 255, 0.7);
        color: #285E8E;
        z-index: 1000; 
        position: absolute; 
        padding: 3px; 
        padding-bottom: 6px; 
        padding-top:0;
        width: 40px; 
        right: 10px; 
        top:15px;
        text-align: center;
        
    }
</style>

<script>


    function editcover() {
        if ($("#covermenu").attr("status") == 'closed') {
            $("#mod-c").css("opacity", "0.5");
            $("#plus-c").fadeIn(300);
            $("#pos-c").fadeIn(450);
            $("#del-c").fadeIn(600);
            $("#conf-c").fadeIn(750);
            $("#covermenu").attr("status", "open");
        }
        else if ($("#covermenu").attr("status") == 'open') {
            $("#mod-c").css("opacity", "1");
            $("#plus-c").fadeOut(350);
            $("#pos-c").fadeOut(300);
            $("#del-c").fadeOut(250);
            $("#conf-c").fadeOut(200);
            $("#covermenu").attr("status", "closed");
        }
    }

    function Pos() {



        if ($("#pos-c").attr("sel") == 'no') {
            $("#pos-c").attr("sel", "yes");
            $("#pos-c").css("opacity", "0.5");
            $('#coverimg').dragncrop({overlay: true, overflow: true, instruction: true});
            $('#coverimg').dragncrop({
                start: function () {
                    $("#istr").fadeOut(250)
                    $("#covermenu").fadeOut(250)

                },
                stop: function () {
                    $("#istr").fadeIn(250)
                    $("#covermenu").fadeIn(250)
                }
            });
        }
        else if ($("#pos-c").attr("sel") == 'yes') {
            $("#pos-c").attr("sel", "no");
            $("#pos-c").css("opacity", "1");
            $('#coverimg').dragncrop('destroy');
        }
    }

    function savecover() {
        $("#loading").show();
        var top = $("#coverimg").css("top");
        var left = $("#coverimg").css("left");

        $("#to").val(top).blur();
        $("#lef").val(left);

        $("#coverform").ajaxForm(function () {
            setTimeout(function () {
                $("#loading").hide();
            }, 500);

        }).submit();

        if ($("#pos-c").attr("sel") == 'yes') {
            $("#pos-c").attr("sel", "no");
            $("#pos-c").css("opacity", "1");
            $('#coverimg').dragncrop('destroy');
        }
        $("#mod-c").css("opacity", "1");
        $("#plus-c").fadeOut(350);
        $("#pos-c").fadeOut(300);
        $("#del-c").fadeOut(250);
        $("#conf-c").fadeOut(200);
        $("#covermenu").attr("status", "closed");

    }

    function readURL(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                var img = document.getElementById("coverimg");
                img.setAttribute("src", e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
            $("#coverimg").css("top", "0");
            $("#coverimg").css("left", "0");
        }

        photoRatio = $("#coverimg").width() / $("#coverimg").height();
        containerRatio = $("#covercont").width() / $("#covercont").height();

        if (photoRatio > containerRatio) {


            $("#coverimg").attr("class", "dragncrop-horizontal");


        } else if (photoRatio < containerRatio) {

            $("#coverimg").attr("class", "dragncrop-vertical");
        }

        Pos();

    }
      
      function restorecover() {
          $('#coverimg').attr("src","./dist/poi/img/${poi.id}/cover.jpg");
          $('#coverimg').css("top","${coverimg.top}");
          $('#coverimg').css("left","${coverimg.left}");
          Pos();
          editcover();
      }
</script>

<div id="covercont" class="cover_img dragncrop dragncrop-overflow">
    <img style='position: absolute; <c:choose> <c:when test="${not empty coverimg.top && not empty coverimg.left}" >top:${coverimg.top}; left:${coverimg.left};</c:when> <c:otherwise> top:0; left:0;  </c:otherwise> </c:choose> ' src="./dist/poi/img/${poi.id}/cover.jpg" id="coverimg"/>
            <div id="covermenu" class="edcov" status="closed"> 
                <div id="mod-c" style="position: relative; height:30px; margin-left: 3px;  width:30px; font-size:180%;"><center><i data-toggle="tooltip" data-placement="left" data-original-title="Modifica" onclick="editcover()"class="boh fa fa-pencil"></i></center></div>
                <div id="plus-c" style="display:none; position: relative; height:20px; width:20px; font-size:140%; margin-top:4px; margin-left: 7px;"><center><i  class="boh fa fa-picture-o" data-toggle="tooltip" data-placement="left" data-original-title="Nuova" onclick="$('#coverfile').click()"></i></center></div>
                <div id="pos-c" sel="no" style="display:none; position: relative; height:20px; width:20px; font-size:140%; margin-top:4px;margin-left: 7px;"><center><i data-toggle="tooltip" data-placement="left" data-original-title="Posiziona" onclick="Pos()" class="boh fa fa-arrows"></i></center></div>
                <div id="del-c" style="display:none; position: relative; height:20px; width:20px; font-size:140%;  margin-top:4px;margin-left: 7px;"><center><i data-toggle="tooltip" data-placement="left" data-original-title="Annulla" onclick="restorecover()" class="boh fa fa-times"></i></center></div>
                <div id="conf-c" style="display:none; position: relative; height:20px; width:20px; font-size:140%;  margin-top:4px;margin-left: 7px;"><center><i data-toggle="tooltip" data-placement="left" data-original-title="Salva" onclick="savecover()" class="boh fa fa-check"></i></center></div>
            </div>
            <div id="loading" style="position: relative; background: rgba(0,0,0,0.8); display: none; top:0; left: 0; width: 100%; height: 100%">
                <img id="loadingimg" style="position: relative; top:50%; left:50%;" src="./dist/img/loading_dots.gif">
            </div>
        </div>


        <script>
            photoRatio = $("#coverimg").width() / $("#coverimg").height();
            containerRatio = $("#covercont").width() / $("#covercont").height();

            if (photoRatio > containerRatio) {


                $("#coverimg").addClass("dragncrop-horizontal");


            } else if (photoRatio < containerRatio) {

                $("#coverimg").addClass("dragncrop-vertical");
            }

        </script>
        <form action="UpdateCover" id="coverform" method="post" enctype="multipart/form-data">
            <input type="file" accept="image/*" name="cover" id="coverfile" onchange="readURL(this)" style="display: none">
            <input type="text" id="to" name="top" value="0" style="display: none">
            <input type="text" id="lef" name="left" value="0" style="display: none">
                <input type="text"  name="id" value="${poi.id}" style="display: none">
</form>
<script>
    $(function () {
        $('.boh').tooltip();
    });

</script>
