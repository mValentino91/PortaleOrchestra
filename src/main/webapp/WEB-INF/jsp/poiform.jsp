<%-- 
    Document   : poiform
    Created on : 14-dic-2014, 18.07.24
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
        <script src="../dist/js/tinymce/tinymce.min.js"></script>
        <script src="../dist/js/section.js"></script>
        <link href="../dist/css/bootstrap.min.css" rel="stylesheet">
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

        <script src=".dist/js/jquery.js"></script>
        <script src="../dist/js/form.js"></script>
        <link href="../dist/css/poi_view.css" rel="stylesheet">
        <title>ORCHESTRA - NUOVO POI</title>



    </head>
    <body>

        <div class="col-md-12" style="background-color: #0081c2; color:#FFFFFF; text-align: center; margin-bottom:20px;">
            <h1><b>Inserimento di un nuovo punto di interesse</b></h1><br>
            <h2><b>I Campi contrassegnati dall'asterisco sono obbligatori!</b></h2>
        </div>
        <div class="container-fixed">
            <form class="inserimento" action="insertpoi" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
                <div class="row">
                    <article class="col-md-12 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Info Generali</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div class="row">
                                    <div class="col-md-6">Nome* <input name="name" class="form-control obb" type="text"> </div>
                                    <div class="col-md-6">Indirizzo* <input name="address" class="form-control obb" type="text"></div>
                                    <div class="col-md-6">Latitudine* <input name="latitude" class="form-control obb" type="text"  onblur="replace_virgola(this, this.value)"></div>
                                    <div class="col-md-6">Longitudine* <input name="longitude" class="form-control obb" type="text"  onblur="replace_virgola(this, this.value)"></div><br>
                                    <div class="col-md-12">Descrizione Breve* (Massimo 150 caratteri)<br> <textarea name="shortd"  id="short" class="form-control shortd" rows="4" cols="50" maxlength="150"></textarea></div><br><br>
                                </div>
                                <div class="row">
                                    <div id="categoria" class="col-md-6">
                                        Categoria* <input name="category1" class="form-control cate obb"  type="text"><br>
                                        <input type="button" class="btn btn-success" style="margin-top: 5px;" value="Aggiungi categoria" onclick="addcat(this)">
                                    </div>
                                    <div class="col-md-6">Immagine di Copertina* <input name="cover" class="form-control obb"  type="file"></div>

                                </div>
                                <div class="row">
                     
                                    
                                    <div class="col-md-6">Visibilità <select name="visibility" class="form-control"> <option value="1" <c:if test="${visibility == '1'}">selected</c:if>>Visibile</option>
                                            <option value="0">Solo preview</option></select>
                                    </div>
                                    
                                </div>
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

                                    <input type="button" class="btn btn-success" value="Nuovo paragrafo" onclick="addpar(this)">

                                </div>
                            </center>
                        </div>
                    </article>
                </div>
                <div class="row">
                    <article class="col-md-6 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Galleria</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div id="galleria">
                                    <input type="button" class="btn btn-success" value="Aggiungi immagine" onclick="addimg(this)">
                                </div>
                            </center>
                        </div>
                    </article>
                    <article class="col-md-6 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Contatti</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div id="contatti">


                                    <input type="button" class="btn btn-success" style="width: 250px;" value="Aggiungi indirizzo email" onclick="addmail(this)"><br>

                                    <input type="button" class="btn btn-success" style="width: 250px; margin-top: 15px;" value="Aggiungi numero di telefono" onclick="addtel(this)"><br>

                                    <input type="button" class="btn btn-success" style="width: 250px; margin-top: 15px;" value="Aggiungi numero fax" onclick="addfax(this)"><br>

                                    <input type="button" class="btn btn-success" style="width: 250px; margin-top:15px;" value="Aggiungi social network" onclick="addsn(this)"><br>

                                    <input type="button" class="btn btn-success" style="width: 250px; margin-top:15px;" value="Aggiungi link" onclick="addsnp(this)"><br>
                                </div>
                            </center>
                        </div>
                    </article>
                </div>
                <div class="row">
                    <article class="col-md-6 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Orari</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div id="orari">
                                    <input type="button" id="nuovogiorno" class="btn btn-success" value="Inserisci un  periodo lavorativo" onclick="addwd(this)"><br><br>
                                    <input type="button" class="btn btn-success" value="Aggiungi un giorno di riposo" onclick="addripann(this)"><br>
                                </div>
                            </center>
                        </div>
                    </article>
                    <article class="col-md-6 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Prezzi</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div id='prezzi'>
                                    <input type="button" class="btn btn-success" value="Aggiungi un biglietto" onclick="addbiglietto(this)">
                                </div>
                            </center>
                        </div>
                    </article>
                </div>
                <div class="row">
                    <article class="col-md-6 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Servizi</span>
                        </div> 

                        <div class="details">
                            <center>
                                <div id='servizi'>
                                    <input type="button" class="btn btn-success" value="Aggiungi un servizio" onclick="addservizio(this)">
                                </div>
                            </center>
                        </div>
                    </article>
                    <article class="col-md-6 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Collega ad altri Poi,Eventi o Pagine D'approfondimento</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div id="collegamenti">
                                        <input type="button" class="btn btn-success" value="Aggiungi collegamento" onclick="add_coll(this)">
                                    </div>
                                </center>
                            </div>
                        </article>
                    <div class="col-md-6"></div>
                </div>
                <div class="row">
                    <center>
                        <input type="button" class="btn btn-success" style="width: 150px; height: 50px; margin-top:25px; margin-bottom: 25px;" value="SALVA POI" onclick="pre_submit()">
                    </center>
                </div>
            </form>
        </div>
    </body>
</html>
