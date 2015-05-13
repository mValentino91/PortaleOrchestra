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
            tinymce.init({
                selector: ".enpar",
                force_br_newlines: true,
                force_p_newlines: false,
                forced_root_block: false,
                remove_linebreaks: false,
                convert_newlines_to_brs: true,
                language: 'it',
                plugins: 'wordcount preview paste'


            });
            function enadd_coll(tasto) {
                var cont = document.getElementById("encollegamenti");
                var lastor = document.getElementsByClassName("encolls");
                var x = lastor.length;
                x = x + 1;

                var newcont = document.createElement("div");
                newcont.id = "enCOL" + x;
                newcont.className = "encolls";
                var newmotdesc = document.createTextNode("Motivazione*");
                var newmot = document.createElement("input");
                newmot.type = "text";
                newmot.name = "enmot" + x;
                newmot.className = "form-control obb enmots";
                newmot.setAttribute("maxlength", "50");

                var newsel = document.createElement("input");
                newsel.type = "button";
                newsel.className = "btn btn-primary";
                newsel.value = "Aggiungi un altro Poi o Evento";
                newsel.setAttribute("onclick", "enadd_sel(this.parentNode,this)");
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
                enadd_sel(newcont, newsel);

                newcont.appendChild(newbr5);
                newcont.appendChild(newdel);
                cont.insertBefore(newcont, tasto);
            }
            function enadd_sel(cont, succ) {
                var x = $("#" + cont.id + " .enpoiselect");
                x = x.length;
                x = x + 1;
                var newday = document.createElement("select");
                newday.name = cont.id + "-" + x;
                newday.className = "form-control enpoiselect";
                newday.setAttribute("style", "margin-bottom: 5px; margin-top: 5px; text-align: center")
            <c:forEach var="opt" items="${lista}">
                var newopt1 = document.createElement("option");
                newopt1.value = "${opt.idpoi}";
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
                newsel.value = "Aggiungi un altro Poi,Evento o Pagina d'approfondimento";
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
            function addinglese(check) {
                if (check.checked) {
                    $("#ingcheck").attr("value", "true");
                    $inps = $("#inglese input");
                    $("#inglese textarea").attr("disabled", false);
                    $("#inglese textarea").addClass("obb");
                    for (var i = 0; i < $inps.length; i++) {
                        if ($inps.eq(i).attr("type") == "text") {
                            $inps.eq(i).attr("disabled", false);
                            $inps.eq(i).addClass("obb");
                        }
                        else {

                            $inps.eq(i).attr("disabled", false);

                        }
                    }
                }
                else {
                    $("#ingcheck").attr("value", "false");
                    $inps = $("#inglese input");
                    $("#inglese textarea").attr("disabled", true);
                    $("#inglese textarea").removeClass("obb");
                    for (var i = 0; i < $inps.length; i++) {
                        if ($inps.eq(i).attr("type") == "text") {
                            $inps.eq(i).attr("disabled", true);
                            $inps.eq(i).removeClass("obb");
                        }
                        else {

                            $inps.eq(i).attr("disabled", true);

                        }
                    }

                }
                $("#check").attr("disabled", false);
            }
        </script>

        <link rel="stylesheet" href="../dist/css/jquery-ui.css">
        <link rel="stylesheet" href="../dist/css/jquery.ui.timepicker.css">
        <script src="../dist/js/jquery.js"></script>
        <script src="../dist/js/jquery-ui.js"></script>
        <script src="../dist/js/jquery.ui.timepicker.js"></script>
        <script src="../dist/js/eventform.js"></script>
        <script src="../dist/js/eneventform.js"></script>

        <link href="../dist/css/poi_view.css" rel="stylesheet">

        <title>ORCHESTRA - NUOVO EVENTO</title>



    </head>
    <body>

        <div class="col-md-12" style="background-color: #0081c2; color:#FFFFFF; text-align: center; margin-bottom:20px;">
            <h1><b>Inserimento di un nuovo evento</b></h1><br>
            <h2><b>I Campi contrassegnati dall'asterisco sono obbligatori!</b></h2>
        </div>
        <div class="col-md-12" style="background-color: #0081c2; color:#FFFFFF; text-align: center; margin-bottom:20px;">
            <h2><b>Nota bene: L'immagine di copertina e le immagini della galleria vengono caricate solo nella parte italiana del form ed ereditate automaticamente da quella inglese</b></h2><br>

        </div>
        <div class="container-fixed" style="max-width: 1300px!important;">

            <form class="inserimento" action="insertevent" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
                <div class="col-md-6">
                    <div class="row">
                        <div class="big-header contact" style="margin-bottom: 15px; margin-right: 3px;">
                            <span class="caps">Italiano</span>
                        </div> 
                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Info Generali</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div class="row">
                                        <div class="col-md-6">Nome* <input name="name" class="form-control obb" type="text"> </div>
                                        <div class="col-md-6">Indirizzo* <input name="address" class="form-control obb" type="text"></div>
                                        <div class="col-md-6">Latitudine* <input name="latitude" class="form-control obb" type="text" onblur="replace_virgola(this, this.value)"></div>
                                        <div class="col-md-6">Longitudine* <input name="longitude" class="form-control obb" type="text" onblur="replace_virgola(this, this.value)"></div><br>
                                        <div class="col-md-6">Data Inizio* <input name="datai" class="datepickerinp form-control obb" type="text" onblur="replace_virgola(this, this.value)"></div>
                                        <div class="col-md-6">Data Fine* <input name="dataf" class="datepickerinp form-control obb" type="text" onblur="replace_virgola(this, this.value)"></div><br>
                                        <div class="col-md-12">Descrizione Breve* (Massimo 150 caratteri)<br> <textarea name="shortd" id="short" class="form-control shortd" rows="4" cols="50" maxlength="150"></textarea></div><br><br>
                                    </div>
                                    <div class="row">
                                        <div id="categoria" class="col-md-6">
                                            Categoria* <input name="category1" class="form-control cate obb"  type="text"><br>
                                            <input type="button" class="btn btn-success" style="margin-top: 5px;" value="Aggiungi categoria" onclick="addcat(this)">
                                        </div>
                                        <div class="col-md-6">Immagine di Copertina* <input name="cover" class="form-control obb"  type="file"></div>

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

                        <article class="col-md-12 component component-text">
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

                        <article class="col-md-12 component component-text">
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
                        <article class="col-md-12 component component-text">
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

                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Date evento</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div id="date">
                                        <input type="button" class="btn btn-success" value="Aggiungi giorno evento" onclick="add_date(this)">
                                    </div>
                                </center>
                            </div>
                        </article>

                        <article class="col-md-12 component component-text">
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

                        <article class="col-md-12 component component-text">
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
                    </div>


                </div>

                <div class="col-md-6" id="inglese">
                    <div class="row">
                        <div class="big-header contact" style="margin-bottom: 15px; margin-left: 3px;">

                            <span class="caps"><input type="checkbox" id="check" name="inglese" onclick="addinglese(this)" title="crea evento in lingua inglese">Inglese</span>
                        </div>
                        <input type="hidden" value="false" id="ingcheck" name="inglese">
                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Info Generali</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div class="row">
                                        <div class="col-md-6">Nome* <input name="enname" class="form-control" type="text" disabled="true"> </div>
                                        <div class="col-md-6">Indirizzo* <input name="enaddress" class="form-control" type="text" disabled="true"></div>
                                        <div class="col-md-6">Latitudine* <input name="enlatitude" class="form-control" type="text"  onblur="replace_virgola(this, this.value)" disabled="true"></div>
                                        <div class="col-md-6">Longitudine* <input name="enlongitude" class="form-control" type="text"  onblur="replace_virgola(this, this.value)" disabled="true"></div><br>
                                        <div class="col-md-6">Data Inizio* <input name="endatai" class="datepickerinp form-control" type="text" onblur="replace_virgola(this, this.value)" disabled="true"></div>
                                        <div class="col-md-6">Data Fine* <input name="endataf" class="datepickerinp form-control" type="text" onblur="replace_virgola(this, this.value)" disabled="true"></div><br>
                                        <div class="col-md-12">Descrizione Breve* (Massimo 150 caratteri)<br> <textarea name="enshortd"  id="enshort" class="form-control enshortd" rows="4" cols="50" maxlength="150" disabled="true"></textarea></div><br><br>
                                    </div>
                                    <div class="row">
                                        <div id="encategoria" class="col-md-6">
                                            Categoria* <input name="encategory1" class="form-control encate"  type="text" disabled="true"><br>
                                            <input type="button" class="btn btn-success" style="margin-top: 5px;" value="Aggiungi categoria" onclick="enaddcat(this)" disabled="true">
                                        </div>
                                        <div class="col-md-6"></div>

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
                                    <div id="endescrizione">

                                        <input type="button" class="btn btn-success" value="Nuovo paragrafo" onclick="enaddpar(this)" disabled="true">

                                    </div>
                                </center>
                            </div>
                        </article>
                    </div>
                    <div class="row">

                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Contatti</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div id="encontatti">
                                        <input type="button" class="btn btn-success" style="width: 250px;" value="Aggiungi indirizzo email" onclick="enaddmail(this)" disabled="true"><br>

                                        <input type="button" class="btn btn-success" style="width: 250px; margin-top: 15px;" value="Aggiungi numero di telefono" onclick="enaddtel(this)" disabled="true"><br>

                                        <input type="button" class="btn btn-success" style="width: 250px; margin-top: 15px;" value="Aggiungi numero fax" onclick="enaddfax(this)" disabled="true"><br>

                                        <input type="button" class="btn btn-success" style="width: 250px; margin-top:15px;" value="Aggiungi social network" onclick="enaddsn(this)" disabled="true"><br>

                                        <input type="button" class="btn btn-success" style="width: 250px; margin-top:15px;" value="Aggiungi link" onclick="enaddsnp(this)" disabled="true"><br>
                                    </div>
                                </center>
                            </div>
                        </article>
                    </div>
                    <div class="row">

                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Prezzi</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div id='enprezzi'>
                                        <input type="button" class="btn btn-success" value="Aggiungi un biglietto" onclick="enaddbiglietto(this)" disabled="true">
                                    </div>
                                </center>
                            </div>
                        </article>
                    </div>
                    <div class="row">
                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Servizi</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div id='enservizi'>
                                        <input type="button" class="btn btn-success" value="Aggiungi un servizio" onclick="enaddservizio(this)" disabled="true">
                                    </div>
                                </center>
                            </div>
                        </article>
                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Date evento</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div id="endate">
                                        <input type="button" class="btn btn-success" value="Aggiungi giorno evento" onclick="enadd_date(this)" disabled="true">
                                    </div>
                                </center>
                            </div>
                        </article>
                        <!--
                         <article class="col-md-12 component component-text">
                                      <div class="big-header contact">
                                              <span class="caps">Collega ad altri Poi o Eventi</span>
                                      </div> 
                        
                    <div class="details">
                        <center>
                    <div id="encollegamenti">
                            <input type="button" class="btn btn-success" value="Aggiungi collegamento" onclick="enadd_coll(this)" disabled="true">
                    </div>
                        </center>
                    </div>
                    </article>
                        -->
                    </div>
                    <div class="row">

                    </div>

                </div>

                <div class="col-md-12">  
                    <center>
                        <input type="button" class="btn btn-success" style="width: 150px; height: 50px; margin-top:25px; margin-bottom: 25px;" value="SALVA EVENTO" onclick="pre_submit()">
                    </center>
                </div>
            </form>
        </div>
        <script>
            $('.datepickerinp').datepicker({
                dateFormat: "dd/mm/yy"
            });
        </script>
    </body>
</html>
