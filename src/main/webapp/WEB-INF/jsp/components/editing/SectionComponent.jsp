<%-- 
    Document   : SectionComponent
    Created on : 4-dic-2014, 9.43.19
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="dist/js/tinymce/tinymce.min.js"></script>
<script>
    tinymce.init({
        selector: ".par",
        force_br_newlines: true,
        force_p_newlines: false,
        forced_root_block: false,
        remove_linebreaks: false,
        convert_newlines_to_brs: true,
        language: 'it',
        plugins: 'wordcount preview paste'


    });
    function delparent2(cont) {
        var txt = $('#' + cont.parentNode.id + ' textarea');
        tinyMCE.triggerSave();
        tinyMCE.EditorManager.execCommand('mceRemoveEditor', false, txt[0].id);
        cont.parentNode.remove();
    }
    function addpar(tasto) {
        var cont = document.getElementById("modding");
        var lastpar = document.getElementsByClassName("titolo");
        var x = lastpar.length;
        x = x + 1;
        var newcont = document.createElement("div");
        newcont.id = "Par" + x;
        newcont.className = "paragrafi";

        var newdel = document.createElement("input");
        newdel.type = "button";
        newdel.value = "Elimina questo paragrafo";
        newdel.setAttribute("onclick", "delparent2(this)");
        newdel.setAttribute("style", "margin-bottom: 5px;");
        newdel.className = "btn btn-danger";

        var newtit = document.createElement("input");
        newtit.type = "text";
        newtit.name = "titolo" + x;
        newtit.className = "form-control titolo";


        var newpar = document.createElement("textarea");
        newpar.name = "par" + x;
        newpar.className = "par obb";
        newpar.id = "par" + x;
        var newbr = document.createElement("br");
        var newbr2 = document.createElement("br");
        var newbr3 = document.createElement("br");
        var newtitdesc = document.createTextNode("Titolo Paragrafo ");
        var newpardesc = document.createTextNode("Paragrafo*");
        newcont.appendChild(newtitdesc);
        newcont.appendChild(newtit);
        newcont.appendChild(newbr);
        newcont.appendChild(newpardesc);
        newcont.appendChild(newpar);
        newcont.appendChild(newbr2);
        newcont.appendChild(newdel);
        cont.insertBefore(newcont, tasto);
        tinymce.EditorManager.execCommand('mceAddEditor', true, newpar.id);
        /* tinymce.init({
         selector: ".par",
         language: 'it'
         }); */

    }
    function editsect(obj) {
        if ($(obj).attr("active") == 'no') {
            $(obj).attr("active","yes");
            $("#modding").fadeIn();
            $("#view").hide();
        }
        else if ($(obj).attr("active") == 'yes') {
            $(obj).attr("active","no");
            $("#view").fadeIn();
            $("#modding").hide();
        }
    }
    function savesect() {
        tinyMCE.triggerSave();
        $("#view").empty();
        var par = $(".par");
        ok = true;
        for (var i = 0; i < par.length; i++) {
            var short = tinyMCE.get(par[i].id).getContent();
            if (short.trim() == '')
                ok = false;
        }
        if (ok == true) {


            var paragrafi = $(".paragrafi");
            for (var i = 0; i < paragrafi.length; i++) {
                var titolo = $("#" + paragrafi[i].id + " .titolo");
                var paragrafo = $("#" + paragrafi[i].id + " .par");
                paragrafi[i].id = "Par" + (i + 1);
                titolo[0].name = "titolo" + (i + 1);
                paragrafo[0].name = "par" + (i + 1);

            }
            var pars = document.getElementsByClassName("paragrafi");
            for (var i = 0; i < pars.length; i++) {
                var newdiv = document.createElement("div");
                newdiv.className = "paragrafo";
                newdiv.innerHTML = "<strong>" + $("#" + pars[i].id + " .titolo").val() + "</strong><br>" + $("#" + pars[i].id + " .par").val();
                $("#view").append(newdiv);
            }
            $("#usect").ajaxForm(function () {


                var inps = $("input");
                for (var i = 0; i < inps.length; i++) {
                    if (inps[i].type != "file") {
                        inps[i].value = inps[i].value.replace(/\'/g, '&#039;');
                        inps[i].value = inps[i].value.replace(/\"/g, '&quot;');
                        inps[i].value = inps[i].value.replace(/\(/g, '&lpar;');
                        inps[i].value = inps[i].value.replace(/\)/g, '&rpar;');
                        inps[i].value = inps[i].value.trim();
                    }

                }
                $("#view").fadeIn();
                $("#modding").hide();
            }).submit();
        }
        else {
            alert("Rispettare i campi obbligatori!")
        }
    }
</script>
<div id="sectcont">
    <article id="sectcomp" class="component component-text">
        <div class="details">
            <div style="font-size: 20px; margin-bottom: 10px;">${poi.name} <i data-toggle="tooltip" data-placement="right" data-original-title="Modifica" active="no" onclick="editsect(this)" class="boh fa fa-pencil"></i> <c:if test="${ empty vartype || vartype != 'DeepeningPage' }"> <c:if test="${not empty poi.start_date }"><div style="float:right; font-size: 15px!important;">${poi.start_date} - ${poi.end_date}</div></c:if></c:if></div>
                    <div class="test" id="view">
                <c:forEach var="sect" items="${description.sectionsList}">
                    <div class="paragrafo">
                        <c:if test="${not empty sect.title}">
                            <strong>${sect.title}</strong>
                            <br>

                        </c:if>
                        ${sect.description}

                    </div>


                </c:forEach>
            </div>
            <form action="updatesect" id="usect" method="POST">
                <div class="test" id="modding" style="display:none">

                    <input type="text" name="id" value="${poi.id}" style="display: none">
                    <c:forEach var="sect" items="${description.sectionsList}" varStatus="tot">
                        <div id="Par${tot.count}" class="paragrafi">
                            Titolo Paragrafo <input type="text" name="titolo${tot.count}" class="form-control titolo" value="${sect.title}"><br>
                            Paragrafo* <textarea name="par${tot.count}" id="par${tot.count}" class="obb par">${sect.description}</textarea><br>
                            <input type="button" class="btn btn-danger" value="Elimina questo paragrafo" onclick="delparent2(this)">
                        </div>
                    </c:forEach>
                    <br>
                    <input type="button" class="btn btn-success" value="Inserisci un nuovo paragrafo" onclick="addpar(this)">
                    <input type="button" class="btn btn-success" value="Salva" onclick="savesect()">


                </div>
            </form>

            <!-- <div class="intents">
              <span class="count">Cultura | Museo</span>
              </div> -->
        </div>
    </article>
</div>