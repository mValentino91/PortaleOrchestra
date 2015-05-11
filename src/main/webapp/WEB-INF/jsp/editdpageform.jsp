<%-- 
    Document   : editdpageform
    Created on : 21-apr-2015, 10.04.48
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
        <title>MODIFICA DI UNA PAGINA INTERMEDIA</title>
        <script src="../dist/js/tinymce/tinymce.min.js"></script>
        <script src="../dist/js/section.js"></script>
        <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="../dist/css/poi_view.css" rel="stylesheet">
        <script type="text/javascript">
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
            function add_coll(tasto) {
                var cont = document.getElementById("collegamenti");
                var lastor = document.getElementsByClassName("colls");
                var x = lastor.length;
                x = x + 1;

                var newcont = document.createElement("div");
                newcont.id = "COL" + x;
                newcont.className = "colls";
                var newmotdesc = document.createTextNode("Motivazione*");
                var newmot = document.createElement("input");
                newmot.type = "text";
                newmot.name = "mot" + x;
                newmot.className = "form-control obb mots";
                newmot.setAttribute("maxlength", "50");

                var newsel = document.createElement("input");
                newsel.type = "button";
                newsel.className = "btn btn-primary";
                newsel.value = "Aggiungi un altro Poi o Evento";
                newsel.setAttribute("onclick", "add_sel(this.parentNode,this)");
                newsel.setAttribute("style", "margin-top:5px; margin-bottom:5px;)");
                var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina Collegamento";
                newdel.className = "btn btn-danger";
                newdel.setAttribute("style", "margin-bottom: 10px;")
                newdel.setAttribute("onclick", "delparent(this)");
                var newbr5 = document.createElement("br");

                newcont.appendChild(newmotdesc);
                newcont.appendChild(newmot);
                newcont.appendChild(newsel);
                add_sel(newcont, newsel);

                newcont.appendChild(newbr5);
                newcont.appendChild(newdel);
                cont.insertBefore(newcont, tasto);
            }
            function add_sel(cont, succ) {
                var x = $("#" + cont.id + " .poiselect");
                x = x.length;
                x = x + 1;
                var newday = document.createElement("select");
                newday.name = cont.id + "-" + x;
                newday.className = "form-control poiselect";
                newday.setAttribute("style", "margin-bottom: 5px; margin-top: 5px; text-align: center");
                var newopt1 = document.createElement("option");
                newopt1.setAttribute("disabled", "disabled");
                newopt1.innerHTML = "<center>-- Punti di interesse e eventi</center>";
                newday.appendChild(newopt1);
            <c:forEach var="opt" items="${lista}">
                var newopt1 = document.createElement("option");
                newopt1.value = "${opt.idpoi}|${opt.type}|";
                newopt1.innerHTML = "<center>${opt.nome}</center>";
                newday.appendChild(newopt1);
            </c:forEach>
                var newopt1 = document.createElement("option");
                newopt1.setAttribute("disabled", "disabled");
                newopt1.innerHTML = "";
                newday.appendChild(newopt1);
                var newopt1 = document.createElement("option");
                newopt1.setAttribute("disabled", "disabled");
                newopt1.innerHTML = "<center><b>-- Pagine d'approfondimento</b></center>";
                newday.appendChild(newopt1);
                var newopt1 = document.createElement("option");
                newopt1.setAttribute("disabled", "disabled");
                newopt1.innerHTML = "";
                newday.appendChild(newopt1);
            <c:forEach var="opt" items="${lista2}">
                var newopt1 = document.createElement("option");
                newopt1.value = "${opt.idpoi}|${opt.type}|";
                newopt1.innerHTML = "<center>${opt.nome}</center>";
                newday.appendChild(newopt1);
            </c:forEach>

                var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina Poi o Evento";
                newdel.className = "btn btn-danger";
                newdel.setAttribute("style", "margin-bottom: 5px; margin-top: 5px;");
                newdel.setAttribute("onclick", "$(this).prev().remove(); $(this).next().remove(); $(this).remove(); ");
                var newbr = document.createElement("br");
                cont.insertBefore(newday, succ);

                if (x > 1) {
                    cont.insertBefore(newdel, succ);
                    cont.insertBefore(newbr, succ);
                }
            }

        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="../dist/js/form.js"></script>

    </head>
    <body onload="debonifica()">

        <div class="col-md-12" style="background-color: #0081c2; color:#FFFFFF; text-align: center; margin-bottom:20px;">
            <h1><b>Modifica di una pagina d'approfondimento</b></h1><br>
            <h2><b>I Campi contrassegnati dall'asterisco sono obbligatori!</b></h2>
        </div>
        <div class="container-fixed">
            <form class="inserimento" action="updatedpage" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
                <input name="id" type="hidden" value="${id}">
                <div class="row">
                    <article class="col-md-12 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Info Generali</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div class="row">
                                    <div class="col-md-12">Nome* <input name="name" class="form-control obb" type="text" value="${nome}"></div><br>


                                </div>
                                <div class="row">
                                    <div id="categoria" class="col-md-6">

                                        <c:forEach var="cats" items="${cat}" varStatus="tot" >
                                            <span>Categoria*</span><input type="text" name="category${tot.count}" class="form-control cate obb" value="${cats}"><br>
                                            <c:if test="${tot.count > 1}">
                                                <input type="button" class="btn btn-danger" value="Elimina" onclick="delcat2(this)"><br>
                                            </c:if>
                                        </c:forEach>
                                        <input type="button" class="btn btn-success" value="Aggiungi categoria" onclick="addcat(this)">
                                    </div>
                                    <div class="col-md-6">
                                        <h3>Immagine di copertina attuale</h3>
                                        <div class="row">
                                            <div class="item">
                                                <img style= " width : 100% " src="../dist/dpage/img/${id}/${coverimg.link}">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <b>Modifica l'immagine di copertina</b>
                                            <input name="cover" class="form-control" type="file">
                                        </div>
                                    </div>

                                </div><br>

                            </center>
                        </div>
                    </article>
                </div>
                <div class="row">
                    <article class="col-md-12 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Paragrafi</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div id="descrizione">
                                    <c:if test="${not empty description}">
                                        <c:forEach var="sect" items="${description.sectionsList}" varStatus="tot">
                                            <div id="Par${tot.count}" class="paragrafi">
                                                Titolo Paragrafo <input type="text" name="titolo${tot.count}" class="form-control titolo" value="${sect.title}"><br>
                                                Paragrafo* <textarea name="par${tot.count}" id="par${tot.count}" class="par obb">${sect.description}</textarea><br>
                                                <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent2(this)">
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <br>
                                    <input type="button" class="btn btn-success" value="Nuovo paragrafo" onclick="addpar(this)">
                                </div> 
                            </center>
                        </div>
                    </article>
                </div>


                <div class="row">
                    <article class="col-md-12 component component-text">
                        <div class="big-header contact">
                            <span class="caps">galleria</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div id="galleria">
                                    <c:if test="${not empty imggallery}">
                                        <div class="row" style="padding-left: 60px;">
                                            <c:forEach var="img" items="${imggallery.links}">

                                                <div class="col-md-3" style="margin-left: 5px; margin-right: 45px; margin-top: 5px;">
                                                    <div class="item">
                                                        <img style= "width : 250px; height: 250px;" src="../dist/dpage/img/${id}/${img.link}">
                                                    </div>
                                                    <input type="hidden" value="${img.link}" name="fileprec">
                                                    Copyright <input type="text" name="credit" class="form-control oldcredits" value="${img.credit}" style="width: 100%;"><br> 
                                                    <input type="button" style="width: 250px;" class="btn btn-danger col-md-12" value="Elimina" onclick='eliminaimg("${img.link}", this)'> 
                                                </div>

                                            </c:forEach>
                                        </div>
                                    </c:if>

                                    <h3>Aggiungi Immagine</h3>
                                    <input type="button" class="btn btn-success" value="Aggiungi immagine" onclick="addimg(this)">

                                </div>
                            </center>
                        </div>
                    </article>


                </div>
                <div class="row">
                    <article class="col-md-12 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Collega ad altri Poi o Eventi</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div id="collegamenti">
                                    <input type="button" id="tastocol" class="btn btn-success" value="Aggiungi collegamento" onclick="add_coll(this)">
                                    <br> <br>
                                    <c:if test="${not empty linkedpoi}">
                                        <c:forEach var="links" items="${linkedpoi.linked}">

                                            <script>
                                                var cont = document.getElementById("collegamenti");
                                                var lastor = document.getElementsByClassName("colls");
                                                var x = lastor.length;
                                                x = x + 1;

                                                var newcont = document.createElement("div");
                                                newcont.id = "COL" + x;
                                                newcont.className = "colls";

                                                var newmotdesc = document.createTextNode("Motivazione*");
                                                var newmot = document.createElement("input");
                                                newmot.type = "text";
                                                newmot.name = "mot" + x;
                                                newmot.className = "form-control obb mots";
                                                newmot.setAttribute("maxlength", "50");
                                                newmot.value = "${links.description}";
                                                newcont.appendChild(newmotdesc);
                                                newcont.appendChild(newmot);

                                                var succ = document.createElement("input");
                                                succ.type = "button";
                                                succ.className = "btn btn-primary";
                                                succ.value = "Aggiungi un altro Poi o Evento";
                                                succ.setAttribute("onclick", "add_sel(this.parentNode,this)");
                                                succ.setAttribute("style", "margin-top:5px; margin-bottom:5px;)");
                                                newcont.appendChild(succ);
                                                var newmot = document.createElement("input");
                                                newmot.type = "text";
                                                newmot.className = "form-control obb";
                                                newmot.value = "${links.description}";
                                                var newdel = document.createElement("input");
                                                newdel.type = "button";
                                                newdel.value = "Elimina Collegamento";
                                                newdel.className = "btn btn-danger";
                                                newdel.setAttribute("style", "margin-bottom: 10px;")
                                                newdel.setAttribute("onclick", "delparent(this)");
                                                var newbr = document.createElement("br");
                                                newcont.appendChild(newbr);
                                                newcont.appendChild(newdel);
                                                var tasto = document.getElementById("tastocol");
                                                cont.insertBefore(newcont, tasto);
                                                <c:forEach var="link" items="${links.poilist}">

                                                var y = $("#" + newcont.id + " .poiselect");
                                                y = y.length;
                                                y = y + 1;
                                                var newday = document.createElement("select");
                                                newday.name = newcont.id + "-" + y;
                                                newday.className = "form-control poiselect";
                                                newday.setAttribute("style", "margin-bottom: 5px; margin-top: 5px; text-align: center")
                                                    <c:forEach var="opt" items="${lista}">
                                                var newopt1 = document.createElement("option");
                                                newopt1.value = "${opt.idpoi}|${opt.type}|";
                                                if ("${opt.idpoi}" == "${link.idpoi}") {
                                                    newopt1.setAttribute("selected", "selected");
                                                }
                                                newopt1.innerHTML = "<center>${opt.nome}</center>";
                                                newday.appendChild(newopt1);
                                                    </c:forEach>
                                                var newopt1 = document.createElement("option");
                                                newopt1.setAttribute("disabled", "disabled");
                                                newopt1.innerHTML = "";
                                                newday.appendChild(newopt1);
                                                var newopt1 = document.createElement("option");
                                                newopt1.setAttribute("disabled", "disabled");
                                                newopt1.innerHTML = "<center><b>-- Pagine d'approfondimento</b></center>";
                                                newday.appendChild(newopt1);
                                                var newopt1 = document.createElement("option");
                                                newopt1.setAttribute("disabled", "disabled");
                                                newopt1.innerHTML = "";
                                                newday.appendChild(newopt1);
                                                    <c:forEach var="opt" items="${lista2}">
                                                var newopt1 = document.createElement("option");
                                                newopt1.value = "${opt.idpoi}|${opt.type}|";
                                                if ("${opt.idpoi}" == "${link.idpoi}") {
                                                    newopt1.setAttribute("selected", "selected");
                                                }
                                                newopt1.innerHTML = "<center>${opt.nome}</center>";
                                                newday.appendChild(newopt1);
                                                    </c:forEach>
                                                var newdel = document.createElement("input");
                                                newdel.type = "button";
                                                newdel.value = "Elimina Poi o Evento";
                                                newdel.className = "btn btn-danger";
                                                newdel.setAttribute("style", "margin-bottom: 5px; margin-top: 5px;");
                                                newdel.setAttribute("onclick", "$(this).prev().remove(); $(this).next().remove(); $(this).remove(); ");
                                                var newbr = document.createElement("br");
                                                newcont.insertBefore(newday, succ);
                                                if (y > 1) {
                                                    newcont.insertBefore(newdel, succ);
                                                    newcont.insertBefore(newbr, succ);
                                                }


                                                </c:forEach>

                                            </script>
                                        </c:forEach>


                                    </c:if>

                                </div>
                            </center>
                        </div>
                    </article>
                </div>              
                <div class="row">
                    <center><input type="button" style="width: 150px; height: 50px; margin-top:25px; margin-bottom: 25px;" class="btn btn-success" value="SALVA PAGINA" onclick="pre_submit()"></center>
                </div>                         