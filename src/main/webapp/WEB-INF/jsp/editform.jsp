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

        <title>ORCHESTRA - MODIFICA POI</title>



    </head>
    <body>

        <div class="col-md-12" style="background-color: #0081c2; color:#FFFFFF; text-align: center; margin-bottom:20px;">
            <h1><b>Modifica di un poi</b></h1><br>
            <h2><b>I Campi contrassegnati dall'asterisco sono obbligatori!</b></h2>
        </div>
        <div class="col-md-12" style="background-color: #0081c2; color:#FFFFFF; text-align: center; margin-bottom:20px;">
            <h2><b>Nota bene: L'immagine di copertina e le immagini della galleria vengono caricate solo nella parte italiana del form ed ereditate automaticamente da quella inglese</b></h2><br>

        </div>
        <div class="container-fixed" style="max-width: 1300px!important;">

            <form class="inserimento" action="updatepoi" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
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
                                        <input type="hidden" name="id" value="${id}">
                                        <div class="col-md-6">Nome* <input name="name" class="form-control obb" type="text" value="${nome}"> </div>
                                        <div class="col-md-6">Indirizzo* <input name="address" class="form-control obb" type="text" value="${addr}"></div>
                                        <div class="col-md-6">Latitudine* <input name="latitude" class="form-control obb" type="text" onblur="replace_virgola(this, this.value)" value="${loc[0]}"></div>
                                        <div class="col-md-6">Longitudine* <input name="longitude" class="form-control obb" type="text" onblur="replace_virgola(this, this.value)" value="${loc[1]}"></div><br>
                                        
                                        <div class="col-md-12">Descrizione Breve* (Massimo 150 caratteri)<br> <textarea name="shortd" id="short" class="form-control shortd" rows="4" cols="50" maxlength="150">${shortD}</textarea></div><br><br>
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
                                                    <img style= " width : 100% " src="../dist/poi/img/${id}/${coverimg.link}">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <b>Modifica l'immagine di copertina</b>
                                                <input name="cover" class="form-control" type="file">
                                            </div>
                                        </div>

                                    </div>
                                      <div class="row">


                                        <div class="col-md-6">Visibilità <select name="visibility" class="form-control"> <option value="1" <c:if test="${visibility == '1'}">selected</c:if>>Visibile</option>
                                                <option value="0" <c:if test="${visibility == '0'}">selected</c:if>>Solo preview</option></select>
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
                                <span class="caps">Contatti</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div id="contatti">

                                        <c:if test="${not empty contacts.emailsList}"> 
                                            <h3>Indirizzi Email presenti</h3>
                                            <c:forEach var="mail" items="${contacts.emailsList}" varStatus="tot">
                                                <div id="mail"${tot.count} class="mails">
                                                    Descrizione Email<input type="text" name="descmail${tot.count}" class="form-control descmail" value="${mail.label}"><br>
                                                    Indirizzo Email*<input type="text" name="email${tot.count}" class="form-control email obb" value="${mail.email}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)"><br>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <h3>Aggiungi un nuovo indirizzo email</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi indirizzo email" onclick="addmail(this)">
                                        <c:if test="${not empty contacts.phoneList}">
                                            <h3>Numeri di telefono presenti</h3>
                                            <c:forEach var="cont" items="${contacts.phoneList}" varStatus="tot">
                                                <div id="Tel"${tot.count} class="telefoni">
                                                    Descrizione Telefono<input type="text" name="desctel${tot.count}" class="form-control desctel" value="${cont.label}"><br>
                                                    Numero di Telefono*<input type="text" name="tel${tot.count}" class="form-control tel obb" value="${cont.number}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)"><br>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <h3>Aggiungi un nuovo contatto telefonico</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi numero di telefono" onclick="addtel(this)">
                                        <c:if test="${not empty contacts.faxList}">
                                            <h3>Numeri di fax presenti</h3>
                                            <c:forEach var="cont" items="${contacts.faxList}" varStatus="tot">
                                                <div id="Fax"${tot.count} class="faxs">
                                                    Descrizione Fax<input type="text" name="descfax${tot.count}" class="form-control descfax" value="${cont.label}"><br>
                                                    Numero di Fax*<input type="text" name="fax${tot.count}" class="form-control fax obb" value="${cont.fax}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)"><br>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <h3>Aggiungi un nuovo contatto fax</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi numero fax" onclick="addfax(this)">
                                        <c:if test="${not empty contacts.facebook || not empty contacts.twitter || not empty contacts.google || not empty contacts.skype}">
                                            <h3>SocialNetwork presenti</h3>
                                            <c:if test="${not empty contacts.facebook}"> 
                                                <div id="SN" class="predsocials">
                                                    SocialNetwork* <select name="SN" class="form-control sn obb">
                                                        <option>Seleziona un social network</option>
                                                        <option value="facebook" selected>FaceBook</option>
                                                        <option value="twitter">Twitter</option>
                                                        <option value="google">Google+</option>
                                                        <option value="skype">Skype</option>
                                                    </select>
                                                    Indirizzo*<input type="text" name="LSN" class="form-control LSN obb" value="${contacts.facebook}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)"><br>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty contacts.twitter}"> 
                                                <div id="SN" class="predsocials">
                                                    SocialNetwork* <select name="SN" class="form-control sn obb">
                                                        <option>Seleziona un social network</option>
                                                        <option value="facebook">FaceBook</option>
                                                        <option value="twitter" selected>Twitter</option>
                                                        <option value="google">Google+</option>
                                                        <option value="skype">Skype</option>
                                                    </select>
                                                    Indirizzo*<input type="text" name="LSN" class="form-control LSN obb" value="${contacts.twitter}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)"><br>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty contacts.google}"> 
                                                <div id="SN" class="predsocials">
                                                    SocialNetwork* <select name="SN" class="form-control sn obb">
                                                        <option>Seleziona un social network</option>
                                                        <option value="facebook">FaceBook</option>
                                                        <option value="twitter">Twitter</option>
                                                        <option value="google" selected>Google+</option>
                                                        <option value="skype">Skype</option>
                                                    </select>
                                                    Indirizzo*<input type="text" name="LSN" class="form-control LSN obb" value="${contacts.google}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)"><br>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty contacts.skype}"> 
                                                <div id="SN" class="predsocials">
                                                    SocialNetwork* <select name="SN" class="form-control sn obb">
                                                        <option>Seleziona un social network</option>
                                                        <option value="facebook">FaceBook</option>
                                                        <option value="twitter">Twitter</option>
                                                        <option value="google">Google+</option>
                                                        <option value="skype" selected>Skype</option>
                                                    </select>
                                                    Indirizzo*<input type="text" name="LSN" class="form-control LSN obb" value="${contacts.skype}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)"><br>
                                                </div>
                                            </c:if>
                                        </c:if>
                                        <h3>Aggiungi un nuovo SocialNetwork</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi social network" onclick="addsn(this)">
                                        <c:if test="${not empty contacts.socialList}">
                                            <h3>Links Presenti</h3>
                                            <c:forEach var="cont" items="${contacts.socialList}" varStatus="tot">
                                                <div id="CSN${tot.count}" class="perssocials">
                                                    Nome: *<input type="text" value="${cont.label}" name="CSN${tot.count}" class="form-control csn  obb">Indirizzo: *<input type="text" value="${cont.social}" name="LCSN${tot.count}" class="form-control lcsn obb"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)"><br>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <h3>Aggiungi un nuovo link</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi link" onclick="addsnp(this)">
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
                                    <div id="prezzi">
                                        <c:if test="${not empty prices}">
                                            <h3>Prezzi di ingresso inseriti</h3>
                                            <c:forEach var="Tprice" items="${prices.prices}" varStatus="tot">
                                                <div id="Tckt${tot.count}" class="tickets">
                                                    Tipo biglietto*<input type="text" value="${Tprice.type}" class="form-control prezzib obb" name="type${tot.count}"><br>
                                                    Prezzo biglietto*<input type="text" value="${Tprice.price}" name="price${tot.count}" class="form-control pb obb" onblur="replace_virgola(this, this.value)"><br>
                                                    Descrizione biglietto<input type="text" value="${Tprice.type_description}" name="typedesc${tot.count}" class="form-control db"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)">
                                                </div>
                                            </c:forEach>
                                        </c:if>
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
                                        <c:forEach var="service" items="${services.servicesList}">

                                            <div id="SER${tot.count}" class="services">
                                                Servizio: *<input type="text" name="SERV${tot.count}" value="${service}" class="form-control serv  obb"><br>
                                                <input type="button" class="btn btn-danger" value="Elimina" onclick="delparent(this)">
                                            </div> 
                                        </c:forEach>
                                        <input type="button" class="btn btn-success" value="Aggiungi un servizio" onclick="addservizio(this)">
                                    </div>
                                </center>
                            </div>
                        </article>

                       <article class="col-md-12 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Orari</span>
                        </div> 

                        <div class="details">
                            <center> 
                                <div id="orari">

                                    <c:if test="${not empty workingtime}">
                                        <h3>Periodi di lavoro presenti</h3>
                                        <c:forEach var="Wdays" items="${workingtime.workingdays}" varStatus="tot">
                                            <div id="D${tot.count}" class="giornilav">
                                                <h4>GIORNO</h4>
                                                Giorno*
                                                <div class="row">
                                                    <div id="D${tot.count}dayChecks" style="height: auto; margin-bottom: 3px; " class="form-control container-fluid daysCheck">

                                                        <div class="col-md-3"><input type="checkbox" name="Lunedì"  class=" checks" <c:if test="${Wdays.days.equals('Lunedì')}"> checked </c:if>>Lunedì</div>
                                                        <div class="col-md-3"><input name="Martedì" type="checkbox" class=" checks" <c:if test="${Wdays.days.equals('Martedì')}"> checked </c:if>>Martedì</div>
                                                        <div class="col-md-3"><input name="Mercoledì" type="checkbox" class=" checks" <c:if test="${Wdays.days.equals('Mercoledì')}"> checked </c:if>>Mercoledì</div>
                                                        <div class="col-md-3"><input name="Giovedì" type="checkbox"  class=" checks" <c:if test="${Wdays.days.equals('Giovedì')}"> checked </c:if>>Giovedì</div>
                                                        <div class="col-md-3"><input name="Venerdì" type="checkbox"  class=" checks" <c:if test="${Wdays.days.equals('Venerdì')}"> checked </c:if>>Venerdì</div>
                                                        <div class="col-md-3"><input name="Sabato" type="checkbox"  class=" checks" <c:if test="${Wdays.days.equals('Sabato')}"> checked </c:if>>Sabato</div>
                                                        <div class="col-md-3"><input name="Domenica" type="checkbox" class=" checks" <c:if test="${Wdays.days.equals('Domenica')}"> checked </c:if>>Domenica</div>
                                                        </div>
                                                    </div>
                                                <c:forEach var="Wtime" items="${Wdays.workinghours}" varStatus="tot2">
                                                    <div id="D${tot.count}-${tot2.count}" class="fasciaoraria ">
                                                        Dalle*
                                                        <div class="row">
                                                            <input type="text" id="${tot.count}start${tot2.count}H" name="D${tot.count}start${tot2.count}H" class="form-control orarilav obb" onblur="addzero(this)" style="width: 200px; display: inline !important;">
                                                            :
                                                            <input type="text" id="${tot.count}start${tot2.count}M" name="D${tot.count}start${tot2.count}M" class="form-control orarilav obb" onblur="addzero(this)" style="width: 200px; display: inline !important;">
                                                        </div>
                                                        Alle*
                                                        <div class="row">
                                                            <input type="text" id="${tot.count}end${tot2.count}H" name="D${tot.count}end${tot2.count}H" class="form-control orarilav obb" onblur="addzero(this)" style="width: 200px; display: inline !important;">
                                                            :
                                                            <input type="text" id="${tot.count}end${tot2.count}M" name="D${tot.count}end${tot2.count}M" class="form-control orarilav obb" onblur="addzero(this)" style="width: 200px; display: inline !important;"><br><br>
                                                        </div>
                                                        <script>
                                                            var id = "#" +${tot.count} + "start" +${tot2.count} + "H";
                                                            var Start = "${Wtime.start}";
                                                            var End = "${Wtime.end}";
                                                            var starth = Start.substr(0, 2);
                                                            var startm = Start.substr(3, 5);
                                                            $(id).val(starth);
                                                            $("#${tot.count}start${tot2.count}M").val(startm);
                                                            var endh = End.substr(0, 2);
                                                            var endm = End.substr(3, 5);
                                                            $("#${tot.count}end${tot2.count}H").val(endh);
                                                            $("#${tot.count}end${tot2.count}M").val(endm);
                                                        </script>
                                                    </div>
                                                </c:forEach>
                                                <input type="button" class="btn btn-primary" value="Aggiungi una fascia oraria" onclick="addor(this.parentNode.id, this)"><br>
                                                <input type="button" style="margin-top: 3px;"class="btn btn-danger" value="Elimina giorno lavorativo" onclick="delparent(this)"><br>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <h3>Aggiungi un giorno lavorativo</h3>
                                    <input type="button" class="btn btn-success" id="nuovogiorno" value="Inserisci un  periodo lavorativo" onclick="addwd(this)">

                                    <c:if test="${not empty workingtime.days_of_rest}">
                                        <h3>Giorni di chiusura annuale inseriti</h3>
                                        <script>

                                            var giorni = "${workingtime.days_of_rest}";
                                            giorni = giorni.trim();
                                            giorni = giorni + " ";
                                            var numgiorni = giorni.match(/ /g).length;

                                            for (var i = 1; i <= numgiorni; i++) {
                                                giorno = giorni.substring(0, giorni.indexOf(' '));
                                                giorni = giorni.substring(giorni.indexOf(' '), giorni.length);
                                                giorni = giorni.trim();
                                                giorni = giorni + " ";
                                                addripannval("orari", giorno);
                                            }
                                        </script>
                                    </c:if>
                                    <h3>Inserisci un nuovo giorno di chiusura annuale</h3>
                                    <input type="button" class="btn btn-success" value="Aggiungi un giorno di riposo annuale" onclick="addripann(this)">
                                </div>
                            </center>
                        </div>
                    </article>

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

                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">galleria</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div id="galleria">
                                        <c:if test="${not empty imggallery}">
                                            <div class="row" >
                                                <c:forEach var="img" items="${imggallery.links}">

                                                    <div class="col-md-6" >
                                                        
                                                        <div class="item">
                                                            <img style= "width : 250px; height: 250px;" src="../dist/poi/img/${id}/${img.link}">
                                                        </div>
                                                        <input type="hidden" value="${img.link}" name="fileprec">
                                                        Copyright <input type="text" name="credit" class="form-control oldcredits" value="${img.credit}" style="width: 100%;"><br> 
                                                        <input type="button" style="width: 100%;" class="btn btn-danger col-md-12" value="Elimina" onclick='eliminaimg("${img.link}", this)'> 
                                                        
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


                </div>

                <div class="col-md-6" id="inglese">
                    <div class="row">
                        <div class="big-header contact" style="margin-bottom: 15px; margin-left: 3px;">
                            <span class="caps">Inglese</span>
                        </div>
                        <input type="hidden" value="true" id="ingcheck" name="inglese">
                        <article class="col-md-12 component component-text">
                            <div class="big-header contact">
                                <span class="caps">Info Generali</span>
                            </div> 

                            <div class="details">
                                <center>
                                    <div class="row">

                                        <div class="col-md-6">Nome* <input name="enname" class="form-control obb" type="text" value="${ennome}"> </div>
                                        <div class="col-md-6">Indirizzo* <input name="enaddress" class="form-control obb" type="text" value="${enaddr}"></div>
                                        <div class="col-md-6">Latitudine* <input name="enlatitude" class="form-control obb" type="text" onblur="replace_virgola(this, this.value)" value="${enloc[0]}"></div>
                                        <div class="col-md-6">Longitudine* <input name="enlongitude" class="form-control obb" type="text" onblur="replace_virgola(this, this.value)" value="${enloc[1]}"></div><br>
                                        
                                        <div class="col-md-12">Descrizione Breve* (Massimo 150 caratteri)<br> <textarea name="enshortd" id="short" class="form-control enshortd" rows="4" cols="50" maxlength="150">${enshortD}</textarea></div><br><br>
                                    </div>
                                    <div class="row">
                                        <div id="encategoria" class="col-md-6">

                                            <c:forEach var="encats" items="${encat}" varStatus="tot" >
                                                <span>Categoria*</span><input type="text" name="encategory${tot.count}" class="form-control encate obb" value="${encats}"><br>
                                                <c:if test="${tot.count > 1}">
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelcat2(this)"><br>
                                                </c:if>
                                            </c:forEach>
                                            <input type="button" class="btn btn-success" value="Aggiungi categoria" onclick="enaddcat(this)">
                                        </div>
                                        <div class="col-md-6">

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
                                    <div id="endescrizione">
                                        <c:if test="${not empty endescription}">
                                            <c:forEach var="ensect" items="${endescription.sectionsList}" varStatus="tot">
                                                <div id="Par${tot.count}" class="enparagrafi">
                                                    Titolo Paragrafo <input type="text" name="entitolo${tot.count}" class="form-control entitolo" value="${ensect.title}"><br>
                                                    Paragrafo* <textarea name="enpar${tot.count}" id="par${tot.count}" class="enpar obb">${ensect.description}</textarea><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent2(this)">
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <br>
                                        <input type="button" class="btn btn-success" value="Nuovo paragrafo" onclick="enaddpar(this)">
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

                                        <c:if test="${not empty encontacts.emailsList}"> 
                                            <h3>Indirizzi Email presenti</h3>
                                            <c:forEach var="enmail" items="${encontacts.emailsList}" varStatus="tot">
                                                <div id="mail"${tot.count} class="enmails">
                                                    Descrizione Email<input type="text" name="endescmail${tot.count}" class="form-control endescmail" value="${enmail.label}"><br>
                                                    Indirizzo Email*<input type="text" name="enemail${tot.count}" class="form-control enemail obb" value="${enmail.email}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)"><br>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <h3>Aggiungi un nuovo indirizzo email</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi indirizzo email" onclick="enaddmail(this)">
                                        <c:if test="${not empty encontacts.phoneList}">
                                            <h3>Numeri di telefono presenti</h3>
                                            <c:forEach var="encont" items="${encontacts.phoneList}" varStatus="tot">
                                                <div id="Tel"${tot.count} class="entelefoni">
                                                    Descrizione Telefono<input type="text" name="endesctel${tot.count}" class="form-control endesctel" value="${encont.label}"><br>
                                                    Numero di Telefono*<input type="text" name="entel${tot.count}" class="form-control entel obb" value="${encont.number}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)"><br>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <h3>Aggiungi un nuovo contatto telefonico</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi numero di telefono" onclick="enaddtel(this)">
                                        <c:if test="${not empty encontacts.faxList}">
                                            <h3>Numeri di fax presenti</h3>
                                            <c:forEach var="encont" items="${encontacts.faxList}" varStatus="tot">
                                                <div id="Fax"${tot.count} class="enfaxs">
                                                    Descrizione Fax<input type="text" name="endescfax${tot.count}" class="form-control endescfax" value="${encont.label}"><br>
                                                    Numero di Fax*<input type="text" name="enfax${tot.count}" class="form-control enfax obb" value="${encont.fax}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)"><br>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <h3>Aggiungi un nuovo contatto fax</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi numero fax" onclick="enaddfax(this)">
                                        <c:if test="${not empty encontacts.facebook || not empty encontacts.twitter || not empty encontacts.google || not empty encontacts.skype}">
                                            <h3>SocialNetwork presenti</h3>
                                            <c:if test="${not empty encontacts.facebook}"> 
                                                <div id="SN" class="enpredsocials">
                                                    SocialNetwork* <select name="enSN" class="form-control ensn obb">
                                                        <option>Seleziona un social network</option>
                                                        <option value="facebook" selected>FaceBook</option>
                                                        <option value="twitter">Twitter</option>
                                                        <option value="google">Google+</option>
                                                        <option value="skype">Skype</option>
                                                    </select>
                                                    Indirizzo*<input type="text" name="enLSN" class="form-control enLSN obb" value="${encontacts.facebook}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)"><br>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty encontacts.twitter}"> 
                                                <div id="SN" class="enpredsocials">
                                                    SocialNetwork* <select name="enSN" class="form-control ensn obb">
                                                        <option>Seleziona un social network</option>
                                                        <option value="facebook">FaceBook</option>
                                                        <option value="twitter" selected>Twitter</option>
                                                        <option value="google">Google+</option>
                                                        <option value="skype">Skype</option>
                                                    </select>
                                                    Indirizzo*<input type="text" name="enLSN" class="form-control enLSN obb" value="${encontacts.twitter}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)"><br>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty encontacts.google}"> 
                                                <div id="SN" class="enpredsocials">
                                                    SocialNetwork* <select name="enSN" class="form-control ensn obb">
                                                        <option>Seleziona un social network</option>
                                                        <option value="facebook">FaceBook</option>
                                                        <option value="twitter">Twitter</option>
                                                        <option value="google" selected>Google+</option>
                                                        <option value="skype">Skype</option>
                                                    </select>
                                                    Indirizzo*<input type="text" name="enLSN" class="form-control enLSN obb" value="${encontacts.google}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)"><br>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty encontacts.skype}"> 
                                                <div id="SN" class="enpredsocials">
                                                    SocialNetwork* <select name="enSN" class="form-control ensn obb">
                                                        <option>Seleziona un social network</option>
                                                        <option value="facebook">FaceBook</option>
                                                        <option value="twitter">Twitter</option>
                                                        <option value="google">Google+</option>
                                                        <option value="skype" selected>Skype</option>
                                                    </select>
                                                    Indirizzo*<input type="text" name="enLSN" class="form-control enLSN obb" value="${encontacts.skype}"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)"><br>
                                                </div>
                                            </c:if>
                                        </c:if>
                                        <h3>Aggiungi un nuovo SocialNetwork</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi social network" onclick="enaddsn(this)">
                                        <c:if test="${not empty encontacts.socialList}">
                                            <h3>Links Presenti</h3>
                                            <c:forEach var="encont" items="${encontacts.socialList}" varStatus="tot">
                                                <div id="CSN${tot.count}" class="enperssocials">
                                                    Nome: *<input type="text" value="${encont.label}" name="enCSN${tot.count}" class="form-control encsn  obb">Indirizzo: *<input type="text" value="${encont.social}" name="enLCSN${tot.count}" class="form-control enlcsn obb"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)"><br>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <h3>Aggiungi un nuovo link</h3>
                                        <input type="button" class="btn btn-success" value="Aggiungi link" onclick="enaddsnp(this)">
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
                                    <div id="enprezzi">
                                        <c:if test="${not empty enprices}">
                                            <h3>Prezzi di ingresso inseriti</h3>
                                            <c:forEach var="enTprice" items="${enprices.prices}" varStatus="tot">
                                                <div id="Tckt${tot.count}" class="entickets">
                                                    Tipo biglietto*<input type="text" value="${enTprice.type}" class="form-control enprezzib obb" name="entype${tot.count}"><br>
                                                    Prezzo biglietto*<input type="text" value="${enTprice.price}" name="enprice${tot.count}" class="form-control enpb obb" onblur="replace_virgola(this, this.value)"><br>
                                                    Descrizione biglietto<input type="text" value="${enTprice.type_description}" name="entypedesc${tot.count}" class="form-control endb"><br>
                                                    <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)">
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                        <input type="button" class="btn btn-success" value="Aggiungi un biglietto" onclick="enaddbiglietto(this)">
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
                                        <c:forEach var="service" items="${enservices.servicesList}">

                                            <div id="enSER${tot.count}" class="enservices">
                                                Servizio: *<input type="text" name="SERV${tot.count}" value="${service}" class="form-control enserv obb"><br>
                                                <input type="button" class="btn btn-danger" value="Elimina" onclick="endelparent(this)">
                                            </div> 
                                        </c:forEach>
                                        <input type="button" class="btn btn-success" value="Aggiungi un servizio" onclick="enaddservizio(this)">
                                    </div>
                                </center>
                            </div>
                        </article>

                        
                        <article class="col-md-12 component component-text">
                        <div class="big-header contact">
                            <span class="caps">Orari</span>
                        </div> 

                        <div class="details">
                            <center> 
                                <div id="enorari">

                                    <c:if test="${not empty enworkingtime}">
                                        <h3>Periodi di lavoro presenti</h3>
                                        <c:forEach var="Wdays" items="${enworkingtime.workingdays}" varStatus="tot">
                                            <div id="enD${tot.count}" class="engiornilav">
                                                <h4>GIORNO</h4>
                                                Giorno*
                                                <div class="row">
                                                    <div id="enD${tot.count}dayChecks" style="height: auto; margin-bottom: 3px; " class="form-control container-fluid endaysCheck">

                                                        <div class="col-md-3"><input type="checkbox" name="Lunedì"  class=" enchecks" <c:if test="${Wdays.days.equals('Lunedì')}"> checked </c:if>>Lunedì</div>
                                                        <div class="col-md-3"><input name="Martedì" type="checkbox" class=" enchecks" <c:if test="${Wdays.days.equals('Martedì')}"> checked </c:if>>Martedì</div>
                                                        <div class="col-md-3"><input name="Mercoledì" type="checkbox" class=" enchecks" <c:if test="${Wdays.days.equals('Mercoledì')}"> checked </c:if>>Mercoledì</div>
                                                        <div class="col-md-3"><input name="Giovedì" type="checkbox"  class=" enchecks" <c:if test="${Wdays.days.equals('Giovedì')}"> checked </c:if>>Giovedì</div>
                                                        <div class="col-md-3"><input name="Venerdì" type="checkbox"  class=" enchecks" <c:if test="${Wdays.days.equals('Venerdì')}"> checked </c:if>>Venerdì</div>
                                                        <div class="col-md-3"><input name="Sabato" type="checkbox"  class=" enchecks" <c:if test="${Wdays.days.equals('Sabato')}"> checked </c:if>>Sabato</div>
                                                        <div class="col-md-3"><input name="Domenica" type="checkbox" class=" enchecks" <c:if test="${Wdays.days.equals('Domenica')}"> checked </c:if>>Domenica</div>
                                                        </div>
                                                    </div>
                                                <c:forEach var="Wtime" items="${Wdays.workinghours}" varStatus="tot2">
                                                    <div id="enD${tot.count}-${tot2.count}" class="enfasciaoraria ">
                                                        Dalle*
                                                        <div class="row">
                                                            <input type="text" id="en${tot.count}start${tot2.count}H" name="enD${tot.count}start${tot2.count}H" class="form-control enorarilav obb" onblur="addzero(this)" style="width: 200px; display: inline !important;">
                                                            :
                                                            <input type="text" id="en${tot.count}start${tot2.count}M" name="enD${tot.count}start${tot2.count}M" class="form-control enorarilav obb" onblur="addzero(this)" style="width: 200px; display: inline !important;">
                                                        </div>
                                                        Alle*
                                                        <div class="row">
                                                            <input type="text" id="en${tot.count}end${tot2.count}H" name="enD${tot.count}end${tot2.count}H" class="form-control enorarilav obb" onblur="addzero(this)" style="width: 200px; display: inline !important;">
                                                            :
                                                            <input type="text" id="en${tot.count}end${tot2.count}M" name="enD${tot.count}end${tot2.count}M" class="form-control enorarilav obb" onblur="addzero(this)" style="width: 200px; display: inline !important;"><br><br>
                                                        </div>
                                                        <script>
                                                            var id = "#en" +${tot.count} + "start" +${tot2.count} + "H";
                                                            var Start = "${Wtime.start}";
                                                            var End = "${Wtime.end}";
                                                            var starth = Start.substr(0, 2);
                                                            var startm = Start.substr(3, 5);
                                                            $(id).val(starth);
                                                            $("#en${tot.count}start${tot2.count}M").val(startm);
                                                            var endh = End.substr(0, 2);
                                                            var endm = End.substr(3, 5);
                                                            $("#en${tot.count}end${tot2.count}H").val(endh);
                                                            $("#en${tot.count}end${tot2.count}M").val(endm);
                                                        </script>
                                                    </div>
                                                </c:forEach>
                                                <input type="button" class="btn btn-primary" value="Aggiungi una fascia oraria" onclick="enaddor(this.parentNode.id, this)"><br>
                                                <input type="button" style="margin-top: 3px;"class="btn btn-danger" value="Elimina giorno lavorativo" onclick="delparent(this)"><br>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <h3>Aggiungi un giorno lavorativo</h3>
                                    <input type="button" class="btn btn-success" id="nuovogiorno" value="Inserisci un  periodo lavorativo" onclick="enaddwd(this)">

                                    <c:if test="${not empty enworkingtime.days_of_rest}">
                                        <h3>Giorni di chiusura annuale inseriti</h3>
                                        <script>

                                            var giorni = "${enworkingtime.days_of_rest}";
                                            giorni = giorni.trim();
                                            giorni = giorni + " ";
                                            var numgiorni = giorni.match(/ /g).length;

                                            for (var i = 1; i <= numgiorni; i++) {
                                                giorno = giorni.substring(0, giorni.indexOf(' '));
                                                giorni = giorni.substring(giorni.indexOf(' '), giorni.length);
                                                giorni = giorni.trim();
                                                giorni = giorni + " ";
                                                enaddripannval("enorari", giorno);
                                            }
                                        </script>
                                    </c:if>
                                    <h3>Inserisci un nuovo giorno di chiusura annuale</h3>
                                    <input type="button" class="btn btn-success" value="Aggiungi un giorno di riposo annuale" onclick="enaddripann(this)">
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
                                                            <div id="collegamenti">
                                                                <input type="button" class="btn btn-success" value="Aggiungi collegamento" onclick="enadd_coll(this)">
                                                            </div>
                                                        </center>
                                                    </div>
                                                </article>
                        -->

                    </div>


                </div>

                <div class="col-md-12">  
                    <center>
                        <input type="button" class="btn btn-success" style="width: 150px; height: 50px; margin-top:25px; margin-bottom: 25px;" value="SALVA POI" onclick="pre_submit()">
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

