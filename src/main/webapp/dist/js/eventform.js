/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function setallchecked(id) {
    var checks = document.getElementById(id).getElementsByTagName("input");
    var btn = document.getElementById("nuovogiorno");
    if (checks[0].checked) {
        for (var i = 0; i < checks.length; i++) {
            checks[i].checked = true;
        }
        btn.disabled = true;
    }
    else {
        for (var i = 0; i < checks.length; i++) {
            checks[i].checked = false;
        }
        btn.disabled = false;
    }
}
function addripann(tasto) {
    var cont = document.getElementById("orari");
    var lastor = document.getElementsByClassName("ripann");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "RDA" + x;
    newcont.className = "RDAs";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-bottom: 5px;");
    newdel.setAttribute("onclick", "delparent(this)");

    var newrda = document.createElement("input");
    var newbr = document.createElement("br");
    newrda.type = "text";
    newrda.className = "form-control ripann obb";
    newrda.name = "RDA" + x;
    var newrd = document.createTextNode("Giorno di riposo (GG/MM/AAAA) *");

    newcont.appendChild(newrd);
    newcont.appendChild(newrda);
    newcont.appendChild(newbr);
    newcont.appendChild(newdel);

    cont.insertBefore(newcont, tasto);

}
function addripannval(tasto, val) {
    var orari = document.getElementById(tasto);
    var cont = document.getElementById("orari");
    var lastor = document.getElementsByClassName("ripann");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "RDA" + x;
    newcont.className = "RDAs";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("onclick", "delparent(this)");

    var newrda = document.createElement("input");
    var newbr = document.createElement("br");
    newrda.type = "text";
    newrda.className = "form-control ripann obb";
    newrda.name = "RDA" + x;
    newrda.value = val;
    var newrd = document.createTextNode("Giorno di riposo (GG/MM/AAAA) *");

    newcont.appendChild(newrd);
    newcont.appendChild(newrda);
    newcont.appendChild(newbr);
    newcont.appendChild(newdel);

    orari.appendChild(newcont);

}

function delcat(tasto) {
    var name = $(tasto).prev().attr("name");
    var lastcat = document.getElementsByClassName("cate");
    var x = lastcat.length;
    var i = 0;
    var ok = false;
    if (name != "category" + x) {
        while (i <= x && !ok) {
            if (lastcat[i].name == name) {
                ok = true;
                for (var j = x - 1; j > i; j--) {
                    lastcat[j].name = lastcat[j - 1].name;
                }
            }
            i = i + 1;
        }

    }
    $(tasto).prev().prev().remove();
    $(tasto).prev().remove();
    tasto.remove();
}
function delcat2(tasto) {
    $(tasto).prev().remove();
    $(tasto).prev().remove();
    $(tasto).prev().remove();
    tasto.remove();
}
function addcat(tasto) {
    var cont = document.getElementById("categoria");
    var lastcat = document.getElementsByClassName("cate");
    var x = lastcat.length;
    x = x + 1;
    var newcat = document.createElement("input");
    var newbr = document.createElement("br");
    newcat.type = "text";
    newcat.className = "form-control cate obb";
    newcat.name = "category" + x;
    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("onclick", "delcat(this)");
    cont.insertBefore(newcat, tasto);
    cont.insertBefore(newdel, tasto);
    cont.insertBefore(newbr, tasto);
}

function addimg(tasto) {
    var cont = document.getElementById("galleria");


    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-top: 5px; margin-bottom: 5px;")
    newdel.setAttribute("onclick", "$(this).prev().remove();$(this).prev().remove();$(this).prev().remove();$(this).prev().remove();$(this).prev().remove();$(this).next().remove();  this.remove();");
    var newimg = document.createElement("input");
    var newbr = document.createElement("br");
    var newbr2 = document.createElement("br");
    var newbr3 = document.createElement("br");
    var newbr4 = document.createElement("br");
    var newcred = document.createElement("input");
    newcred.type = "text";
    newcred.name = "credit";
    newcred.className = "form-control credits";
    var newcreddesc = document.createElement("span");
    newcreddesc.setAttribute("style", "margin: 0;")
    newcreddesc.innerHTML = "Copyright";

    newimg.type = "file";
    newimg.name = "file";
    newimg.classname = "form-control obb";
    cont.insertBefore(newbr, tasto);
    cont.insertBefore(newimg, tasto);
    cont.insertBefore(newbr3, tasto);
    cont.insertBefore(newcreddesc, tasto);
    cont.insertBefore(newcred, tasto);
    cont.insertBefore(newdel, tasto);
    cont.insertBefore(newbr4, tasto);
}
function delparent2(cont) {
    var txt = $('#' + cont.parentNode.id + ' textarea');
    tinyMCE.triggerSave();
    tinyMCE.EditorManager.execCommand('mceRemoveEditor', false, txt[0].id);
    cont.parentNode.remove();
}
function addpar(tasto) {
    var cont = document.getElementById("descrizione");
    var lastpar = document.getElementsByClassName("titolo");
    var x = lastpar.length;
    x = x + 1;
    var newcont = document.createElement("div");
    newcont.id = "Par" + x;
    newcont.className = "paragrafi";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
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
function setallchecked(id) {
    var checks = document.getElementById(id).getElementsByTagName("input");
    var btn = document.getElementById("nuovogiorno");
    if (checks[0].checked) {
        for (var i = 0; i < checks.length; i++) {
            checks[i].checked = true;
        }
        btn.disabled = true;
    }
    else {
        for (var i = 0; i < checks.length; i++) {
            checks[i].checked = false;
        }
        btn.disabled = false;
    }
}

function add_date(tasto) {
    var cont = document.getElementById("date");
    var lastdate = document.getElementsByClassName("dates");
    var x = lastdate.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "D" + x;
    newcont.className = "dates";

    var newaddorari = document.createElement("input");
    newaddorari.type = "button";
    newaddorari.value = "Aggiungi una fascia oraria";
    newaddorari.className = "btn btn-primary";
    newaddorari.setAttribute("onclick", "addor(this.parentNode.id, this)");

    var newdelgiorno = document.createElement("input");
    newdelgiorno.type = "button";
    newdelgiorno.value = "Elimina giorno evento";
    newdelgiorno.className = "btn btn-danger";
    newdelgiorno.setAttribute("style", "margin-top: 5px; margin-bottom:15px;");
    newdelgiorno.setAttribute("onclick", "delparent(this)");
    var newgiorno = document.createTextNode("Data*");

    var newdate = document.createElement("input");
    newdate.type = "text";
    newdate.name = "date" + x;
    newdate.className = "inpdates datepickerinp obb form-control";

    var newdatetext = document.createElement("input");
    newdatetext.type="text";
    newdatetext.name = "datetext" + x;
    newdatetext.className ="inpdatestext form-control";
    
    var newdesc = document.createTextNode("Descrizione breve");
    var newbr5 = document.createElement("br");
    newcont.appendChild(newgiorno);
    newcont.appendChild(newdate);
    newcont.appendChild(newdesc);
    newcont.appendChild(newdatetext);
    newcont.appendChild(newaddorari);

    newcont.appendChild(newbr5);
    newcont.appendChild(newdelgiorno);

    cont.insertBefore(newcont, tasto);
    addor(newcont.id, newaddorari);
    $('.datepickerinp').datepicker({
        dateFormat: "dd/mm/yy"
    });

}
function addwd(tasto) {
    var cont = document.getElementById("orari");
    var bt = document.getElementById("bt");
    var lastor = document.getElementsByClassName("giornilav");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "D" + x;
    newcont.className = "giornilav";
    var newaddorari = document.createElement("input");
    newaddorari.type = "button";
    newaddorari.value = "Aggiungi una fascia oraria";
    newaddorari.className = "btn btn-primary";
    newaddorari.setAttribute("onclick", "addor(this.parentNode.id, this)");

    var newdelgiorno = document.createElement("input");
    newdelgiorno.type = "button";
    newdelgiorno.value = "Elimina giorno lavorativo";
    newdelgiorno.className = "btn btn-danger";
    newdelgiorno.setAttribute("style", "margin-top: 5px; margin-bottom:15px;");
    newdelgiorno.setAttribute("onclick", "delparent(this)");

    var newgg = document.createElement("h3");
    newgg.innerHTML = "GIORNO "
    var newgiorno = document.createTextNode("Giorno*");
    var newbr4 = document.createElement("br");
    var newbr5 = document.createElement("br");
    var newrow = document.createElement("div");
    newrow.className = "row";
    var newday = document.createElement("div");
    newday.id = "D" + x + "dayChecks";
    newday.className = "form-control container-fluid daysCheck";
    newday.setAttribute("style", "height: auto; margin-bottom: 3px;");
    var newopt = document.createElement("input");
    var newoptdiv = document.createElement("div");
    newoptdiv.className = "col-md-3";
    if (x == 1) {
        newopt.type = "checkbox";
        newopt.name = "Tutti i giorni";
        newopt.setAttribute("onclick", "setallchecked(this.parentNode.parentNode.id)");
        var newoptdesc = document.createTextNode("Tutti i giorni");
        newoptdiv.appendChild(newopt);
        newoptdiv.appendChild(newoptdesc);

        var newopt1 = document.createElement("input");
        var newopt1div = document.createElement("div");
        newopt1div.className = "col-md-3";
        newopt1.type = "checkbox";
        newopt1.name = "Lunedì";
        newopt1.className = "checks"
        var newopt1desc = document.createTextNode("Lunedì");
        newopt1div.appendChild(newopt1);
        newopt1div.appendChild(newopt1desc);
        var newopt2 = document.createElement("input");
        var newopt2div = document.createElement("div");
        newopt2div.className = "col-md-3";
        newopt2.name = "Martedì";
        newopt2.type = "checkbox";
        newopt2.className = "checks"
        var newopt2desc = document.createTextNode("Martedì");
        newopt2div.appendChild(newopt2);
        newopt2div.appendChild(newopt2desc);
        var newopt3 = document.createElement("input");
        var newopt3div = document.createElement("div");
        newopt3div.className = "col-md-3";
        newopt3.name = "Mercoledì";
        newopt3.type = "checkbox";
        newopt3.className = "checks"
        var newopt3desc = document.createTextNode("Mercoledì");
        newopt3div.appendChild(newopt3);
        newopt3div.appendChild(newopt3desc);
        var newopt4 = document.createElement("input");
        var newopt4div = document.createElement("div");
        newopt4div.className = "col-md-3";
        newopt4.name = "Giovedì";
        newopt4.type = "checkbox";
        newopt4.className = "checks"
        var newopt4desc = document.createTextNode("Giovedì");
        newopt4div.appendChild(newopt4);
        newopt4div.appendChild(newopt4desc);
        var newopt5 = document.createElement("input");
        var newopt5div = document.createElement("div");
        newopt5div.className = "col-md-3";
        newopt5.name = "Venerdì";
        newopt5.type = "checkbox";
        newopt5.className = "checks"
        var newopt5desc = document.createTextNode("Venerdì");
        newopt5div.appendChild(newopt5);
        newopt5div.appendChild(newopt5desc);
        var newopt6 = document.createElement("input");
        var newopt6div = document.createElement("div");
        newopt6div.className = "col-md-3";
        newopt6.name = "Sabato";
        newopt6.type = "checkbox";
        newopt6.className = "checks"
        var newopt6desc = document.createTextNode("Sabato");
        newopt6div.appendChild(newopt6);
        newopt6div.appendChild(newopt6desc);

        var newopt7 = document.createElement("input");
        var newopt7div = document.createElement("div");
        newopt7div.className = "col-md-3";
        newopt7.name = "Domenica";
        newopt7.type = "checkbox";
        newopt7.className = "checks"
        var newopt7desc = document.createTextNode("Domenica");
        newopt7div.appendChild(newopt7);
        newopt7div.appendChild(newopt7desc);

        newday.appendChild(newoptdiv);

        newday.appendChild(newopt1div);

        newday.appendChild(newopt2div);

        newday.appendChild(newopt3div);


        newday.appendChild(newopt4div);

        newday.appendChild(newopt5div);

        newday.appendChild(newopt6div);

        newday.appendChild(newopt7div);

    }
    else {

        var check = document.getElementById("D" + (x - 1) + "dayChecks").getElementsByTagName("input");

        for (var i = 0; i < check.length; i++) {
            if (!check[i].checked && check[i].name != "Tutti i giorni") {
                var opt = document.createElement("input");
                var newoptdiv = document.createElement("div");
                newoptdiv.className = "col-md-3";
                opt.type = "checkbox";
                opt.name = check[i].name;
                var newoptdesc = document.createTextNode(check[i].name);
                newoptdiv.appendChild(opt)
                newoptdiv.appendChild(newoptdesc);
                newday.appendChild(newoptdiv);
            }
        }
    }
    newcont.appendChild(newgg);
    newcont.appendChild(newgiorno);
    newrow.appendChild(newday);
    newcont.appendChild(newrow);
    newcont.appendChild(newbr4);
    newcont.appendChild(newaddorari);

    newcont.appendChild(newbr5);
    newcont.appendChild(newdelgiorno);
    cont.insertBefore(newcont, tasto);
    addor(newcont.id, newaddorari);


}


function addor(contid, tasto) {
    var cont = document.getElementById(contid);

    var k = (($("#" + contid + " input.orarilav").length) / 4) + 1;

    var newcont = document.createElement("div");
    newcont.id = contid + "-" + k;
    newcont.className = "fasciaoraria ";

    var newrow = document.createElement("div");
    var newrow2 = document.createElement("div");
    newrow.className = " row";
    newrow2.className = "row";
    var newstart = document.createElement("input");
    newstart.type = "text";
    newstart.name = contid + "start" + k + "H";
    newstart.className = "form-control hourpicker orarilav obb";
    newstart.setAttribute("style", "width: 200px; display: inline !important;");
    newstart.setAttribute("onblur", "addzero(this)");

    var newstartm = document.createElement("input");
    newstartm.type = "text";
    newstartm.name = contid + "start" + k + "M";
    newstartm.className = "form-control minutepicker orarilav obb";
    newstartm.setAttribute("style", "width: 200px; display: inline !important;");
    newstartm.setAttribute("onblur", "addzero(this)");
    var newend = document.createElement("input");
    newend.type = "text";
    newend.name = contid + "end" + k + "H";
    newend.setAttribute("style", "width: 200px; display: inline !important");
    newend.className = "form-control hourpicker orarilav obb";
    newend.setAttribute("onblur", "addzero(this)");
    var newendm = document.createElement("input");
    newendm.type = "text";
    newendm.name = contid + "end" + k + "M";
    newendm.className = "form-control minutepicker orarilav obb";
    newendm.setAttribute("style", "width: 200px; display: inline !important");
    newendm.setAttribute("onblur", "addzero(this)");
    var delel = document.createElement("input");
    delel.type = "button";
    delel.value = "Elimina fascia oraria";
    delel.className = "btn btn-danger";
    delel.setAttribute("style", "margin-top: 5px; margin-bottom: 5px;");
    delel.setAttribute("onclick", "delparent(this)");
    var newdalle = document.createTextNode("Dalle*");
    var newalle = document.createTextNode("Alle*");

    var newpunti = document.createTextNode(":");
    var newpunti2 = document.createTextNode(":");

    var newbr2 = document.createElement("br");
    var newbr3 = document.createElement("br");



    newcont.appendChild(newdalle);
    newrow.appendChild(newstart);
    newrow.appendChild(newpunti);
    newrow.appendChild(newstartm);
    newcont.appendChild(newrow);
    newcont.appendChild(newbr2);
    newcont.appendChild(newalle);
    newrow2.appendChild(newend);
    newrow2.appendChild(newpunti2);
    newrow2.appendChild(newendm);
    newcont.appendChild(newrow2);
    newcont.appendChild(newbr3);
    if (k > 1)
        newcont.appendChild(delel);
    cont.insertBefore(newcont, tasto);

    $('.minutepicker').timepicker({
        showHours: false
    });
    $('.hourpicker').timepicker({
        showMinutes: false,
        showPeriodLabels: false
    });


}

function delparent(tasto) {
    tasto.parentNode.remove();
}


function addrestday(tasto) {
    var cont = document.getElementById("orari");
    var lastor = document.getElementsByClassName("ripsett");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "RD" + x;
    newcont.className = "RDs";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.setAttribute("onclick", "delparent(this)");

    var newday = document.createElement("select");
    newday.name = "RD" + x;
    newday.className = "ripsett obb";
    var newopt = document.createElement("option");

    newopt.innerHTML = "Seleziona un giorno";
    newday.appendChild(newopt);
    var newopt1 = document.createElement("option");
    newopt1.value = "Lunedì";
    newopt1.innerHTML = "Lunedì";
    newday.appendChild(newopt1);
    var newopt2 = document.createElement("option");
    newopt2.value = "Martedì";
    newopt2.innerHTML = "Martedì";
    newday.appendChild(newopt2);
    var newopt3 = document.createElement("option");
    newopt3.value = "Mercoledì";
    newopt3.innerHTML = "Mercoledì";
    newday.appendChild(newopt3);
    var newopt4 = document.createElement("option");
    newopt4.value = "Giovedì";
    newopt4.innerHTML = "Giovedì";
    newday.appendChild(newopt4);
    var newopt5 = document.createElement("option");
    newopt5.value = "Venerdì";
    newopt5.innerHTML = "Venerdì";
    newday.appendChild(newopt5);
    var newopt6 = document.createElement("option");
    newopt6.value = "Sabato";
    newopt6.innerHTML = "Sabato";
    newday.appendChild(newopt6);
    var newopt7 = document.createElement("option");
    newopt7.value = "Domenica";
    newopt7.innerHTML = "Domenica";
    newday.appendChild(newopt7);
    var newrd = document.createTextNode(x + "° giorno di riposo*");
    var newbr = document.createElement("br");


    newcont.appendChild(newrd);
    newcont.appendChild(newday);
    newcont.appendChild(newbr);
    newcont.appendChild(newdel);

    cont.insertBefore(newcont, tasto);
}
function addripann(tasto) {
    var cont = document.getElementById("orari");
    var lastor = document.getElementsByClassName("ripann");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "RDA" + x;
    newcont.className = "RDAs";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-bottom: 5px;");
    newdel.setAttribute("onclick", "delparent(this)");

    var newrda = document.createElement("input");
    var newbr = document.createElement("br");
    newrda.type = "text";
    newrda.className = "form-control ripann obb";
    newrda.name = "RDA" + x;
    var newrd = document.createTextNode("Giorno di riposo (GG/MM/AAAA) *");

    newcont.appendChild(newrd);
    newcont.appendChild(newrda);
    newcont.appendChild(newbr);
    newcont.appendChild(newdel);

    cont.insertBefore(newcont, tasto);

}
function addripannval(tasto, val) {
    var orari = document.getElementById(tasto);
    var cont = document.getElementById("orari");
    var lastor = document.getElementsByClassName("ripann");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "RDA" + x;
    newcont.className = "RDAs";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("onclick", "delparent(this)");

    var newrda = document.createElement("input");
    var newbr = document.createElement("br");
    newrda.type = "text";
    newrda.className = "form-control ripann obb";
    newrda.name = "RDA" + x;
    newrda.value = val;
    var newrd = document.createTextNode("Giorno di riposo (GG/MM/AAAA) *");

    newcont.appendChild(newrd);
    newcont.appendChild(newrda);
    newcont.appendChild(newbr);
    newcont.appendChild(newdel);

    orari.appendChild(newcont);

}
function addbiglietto(tasto) {
    var cont = document.getElementById("prezzi");
    var lastor = document.getElementsByClassName("prezzib");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "Tckt" + x;
    newcont.className = "tickets";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-bottom: 5px;");
    newdel.setAttribute("onclick", "delparent(this)");

    var newtype = document.createElement("input");
    var newbr = document.createElement("br");
    newtype.type = "text";
    newtype.className = "form-control prezzib obb";
    newtype.name = "type" + x;
    var newprice = document.createElement("input");
    var newbr2 = document.createElement("br");
    newprice.type = "text";
    newprice.name = "price" + x;
    newprice.className = "form-control pb obb";
    newprice.setAttribute("onblur", "replace_virgola(this, this.value)");

    var newtypedesc = document.createElement("input");
    var newbr3 = document.createElement("br");
    newtypedesc.type = "text";
    newtypedesc.name = "typedesc" + x;
    newtypedesc.className = "form-control db";
    var newt = document.createTextNode("Tipo biglietto*");
    var newp = document.createTextNode("Prezzo biglietto*");
    var newpd = document.createTextNode("Descrizione biglietto");


    newcont.appendChild(newt);
    newcont.appendChild(newtype);
    newcont.appendChild(newbr);
    newcont.appendChild(newp);
    newcont.appendChild(newprice);
    newcont.appendChild(newbr2);
    newcont.appendChild(newpd);
    newcont.appendChild(newtypedesc);
    newcont.appendChild(newbr3);
    newcont.appendChild(newdel);
    cont.insertBefore(newcont, tasto);

}
function addmail(tasto) {
    var cont = document.getElementById("contatti");
    var lastor = document.getElementsByClassName("email");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "Mail" + x;
    newcont.className = "mails";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-bottom: 5px;");
    newdel.setAttribute("onclick", "delparent(this)");

    var newmail = document.createElement("input");
    var newbr = document.createElement("br");
    newmail.type = "text";
    newmail.className = "form-control email obb";
    newmail.name = "email" + x;
    var newdescmail = document.createElement("input");
    var newbr2 = document.createElement("br");
    newdescmail.type = "text";
    newdescmail.name = "descemail" + x;
    newdescmail.className = "form-control descmail ";
    var newde = document.createTextNode("Descrizione  Email");
    var newie = document.createTextNode("Indirizzo Email *");
    newcont.appendChild(newde);
    newcont.appendChild(newdescmail);
    newcont.appendChild(newbr);
    newcont.appendChild(newie);
    newcont.appendChild(newmail);
    newcont.appendChild(newbr2);
    newcont.appendChild(newdel);
    cont.insertBefore(newcont, tasto);

}
function addtel(tasto) {
    var cont = document.getElementById("contatti");
    var lastor = document.getElementsByClassName("tel");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "Tel" + x;
    newcont.className = "telefoni";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-bottom:5px;")
    newdel.setAttribute("onclick", "delparent(this)");

    var newmail = document.createElement("input");
    var newbr = document.createElement("br");
    newmail.type = "text";
    newmail.className = "form-control tel  obb";
    newmail.name = "tel" + x;
    var newdescmail = document.createElement("input");
    var newbr2 = document.createElement("br");
    newdescmail.type = "text";
    newdescmail.name = "desctel" + x;
    newdescmail.className = "form-control desctel";
    var newde = document.createTextNode("Descrizione Telefono");
    var newie = document.createTextNode("Numero Di Telefono *");
    newcont.appendChild(newde);
    newcont.appendChild(newdescmail);
    newcont.appendChild(newbr);
    newcont.appendChild(newie);
    newcont.appendChild(newmail);
    newcont.appendChild(newbr2);
    newcont.appendChild(newdel);
    cont.insertBefore(newcont, tasto);
}
function addfax(tasto) {
    var cont = document.getElementById("contatti");
    var lastor = document.getElementsByClassName("fax");
    var x = lastor.length;
    x = x + 1;
    var newcont = document.createElement("div");
    newcont.id = "Fax" + x;
    newcont.className = "faxs";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-bottom: 5px;");
    newdel.setAttribute("onclick", "delparent(this)");
    var newmail = document.createElement("input");
    var newbr = document.createElement("br");
    newmail.type = "text";
    newmail.className = "form-control fax obb";
    newmail.name = "fax" + x;
    var newdescmail = document.createElement("input");
    var newbr2 = document.createElement("br");
    newdescmail.type = "text";
    newdescmail.name = " descfax" + x;
    newdescmail.className = "form-control descfax";
    var newde = document.createTextNode("Descrizione Fax");
    var newie = document.createTextNode("Numero Fax *");
    newcont.appendChild(newde);
    newcont.appendChild(newdescmail);
    newcont.appendChild(newbr);
    newcont.appendChild(newie);
    newcont.appendChild(newmail);
    newcont.appendChild(newbr2);
    newcont.appendChild(newdel);
    cont.insertBefore(newcont, tasto);
}
function addsn(tasto) {
    var cont = document.getElementById("contatti");
    var lastor = document.getElementsByClassName("sn");
    var x = lastor.length;
    x = x + 1;
    var newcont = document.createElement("div");
    newcont.id = "SN" + x;
    newcont.className = "predsocials";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute('style', 'margin-bottom: 5px;');
    newdel.setAttribute("onclick", "delparent(this)");
    var newday = document.createElement("select");
    newday.name = "SN" + x;
    newday.className = "form-control sn obb";
    var newopt = document.createElement("option");

    newopt.innerHTML = "Seleziona un social network";
    newday.appendChild(newopt);
    var newopt1 = document.createElement("option");
    newopt1.value = "facebook";
    newopt1.innerHTML = "FaceBook";
    newday.appendChild(newopt1);
    var newopt2 = document.createElement("option");
    newopt2.value = "twitter";
    newopt2.innerHTML = "Twitter";
    newday.appendChild(newopt2);
    var newopt3 = document.createElement("option");
    newopt3.value = "google";
    newopt3.innerHTML = "Google+";
    newday.appendChild(newopt3);
    var newopt4 = document.createElement("option");
    newopt4.value = "skype";
    newopt4.innerHTML = "Skype";
    newday.appendChild(newopt4);

    var newde = document.createTextNode("SocialNetwork (predefinito) * ");
    var newie = document.createTextNode("Indirizzo: *");

    var newind = document.createElement("input");
    var newbr = document.createElement("br");
    newind.type = "text";
    newind.name = "LSN" + x;
    newind.className = "form-control LSN obb";
    newcont.appendChild(newde);
    newcont.appendChild(newday);
    newcont.appendChild(newie);
    newcont.appendChild(newind);
    newcont.appendChild(newbr);
    newcont.appendChild(newdel);
    cont.insertBefore(newcont, tasto);

}

function addsnp(tasto) {
    var cont = document.getElementById("contatti");
    var lastor = document.getElementsByClassName("perssocials");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "CSN" + x;
    newcont.className = "perssocials";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-bottom: 5px;");
    newdel.setAttribute("onclick", "delparent(this)");

    var newde = document.createTextNode("Nome: *");
    var newie = document.createTextNode("Indirizzo: *");

    var newind = document.createElement("input");
    var newbr = document.createElement("br");
    newind.type = "text";
    newind.name = "LCSN" + x;
    newind.className = "form-control lcsn obb";

    var newlab = document.createElement("input");
    newlab.type = "text";
    newlab.name = "CSN" + x;
    newlab.className = "form-control csn obb";
    newcont.appendChild(newde);
    newcont.appendChild(newlab);
    newcont.appendChild(newie);
    newcont.appendChild(newind);
    newcont.appendChild(newbr);
    newcont.appendChild(newdel);
    cont.insertBefore(newcont, tasto);


}
function addservizio(tasto) {
    var cont = document.getElementById("servizi");
    var lastor = document.getElementsByClassName("services");
    var x = lastor.length;
    x = x + 1;

    var newcont = document.createElement("div");
    newcont.id = "SER" + x;
    newcont.className = "services";

    var newdel = document.createElement("input");
    newdel.type = "button";
    newdel.value = "Elimina";
    newdel.className = "btn btn-danger";
    newdel.setAttribute("style", "margin-bottom: 5px;")
    newdel.setAttribute("onclick", "delparent(this)");

    var newlab = document.createElement("input");
    newlab.type = "text";
    newlab.name = "SERV" + x;
    newlab.className = "form-control serv  obb";

    var newde = document.createTextNode("Servizio: *");
    var newbr = document.createElement("br");


    newcont.appendChild(newde);
    newcont.appendChild(newlab);
    newcont.appendChild(newbr);
    newcont.appendChild(newdel);
    cont.insertBefore(newcont, tasto);
}
function replace_virgola(inp, testo) {
    var newtesto = testo.replace(",", ".");
    inp.value = newtesto;
}


function  eliminaimg(img_name, html_param) {

    if (confirm("Vuoi davvero cancellare questa immagine?") == true) {
        var newdel = document.createElement("input");
        newdel.type = "hidden";
        newdel.value = img_name;
        newdel.name = "imgdel";
        var form = document.getElementsByClassName("inserimento")[0];
        form.appendChild(newdel);

        delparent(html_param);
    }
}


function checkobb() {
    ok = true;
    var obb = $("input.obb");
    for (var i = 0; i < obb.length; i++) {
        if (obb[i].value.trim() == "")
            ok = false;
    }

    var short = $('.shortd')[0];
    if (short.value.trim() == '')
        ok = false;
    var par = $(".par");
    for (var i = 0; i < par.length; i++) {
        var short = tinyMCE.get(par[i].id).getContent();
        if (short.trim() == '')
            ok = false;
    }
    return ok;
}
function bonifica() {
    var inps = $("input");
    for (var i = 0; i < inps.length; i++) {
        if (inps[i].type != "file") {
            inps[i].value = inps[i].value.replace(/\'/g, '&#039;');
            inps[i].value = inps[i].value.replace(/\"/g, '&quot;');
            inps[i].value = inps[i].value.trim();
        }

    }
    var short = $('.shortd')[0].value;
    short = short.replace(/\'/g, '&#039;');
    short = short.replace(/\"/g, '&quot;');
    short = short.replace(/\n/ig, "<br>");
    $('.shortd')[0].value = short;
}
function debonifica() {
    var inps = $("input");
    for (var i = 0; i < inps.length; i++) {
        if (inps[i].type != "file") {
            inps[i].value = inps[i].value.replace(/&#039;/g, "'");
            inps[i].value = inps[i].value.replace(/&quot;/g, '"');

        }
    }

    var short = $('.shortd')[0].value;
    short = short.replace(/&#039;/g, "'");
    short = short.replace(/&quot;/g, '"');
    short = short.replace(/<br>/ig, '\n');
    $('.shortd')[0].value = short;
}

function addzero(inp) {
    if (inp.value.length == 1) {
        inp.value = "0" + inp.value;
    }
}


function pre_submit() {
    var paragrafi = $(".paragrafi");
    for (var i = 0; i < paragrafi.length; i++) {
        var titolo = $("#" + paragrafi[i].id + " .titolo");
        var paragrafo = $("#" + paragrafi[i].id + " .par");
        paragrafi[i].id = "Par" + (i + 1);
        titolo[0].name = "titolo" + (i + 1);
        paragrafo[0].name = "par" + (i + 1);

    }
    if (!checkobb()) {
        alert("ERRORE: Rispettare i campi obbligatori!")
    }
    else {
        bonifica();
        var mails = $(".mails");
        for (var i = 0; i < mails.length; i++) {
            var descmail = $("#" + mails[i].id + " .descmail");
            var email = $("#" + mails[i].id + " .email");
            mails[i].id = "Mails" + (i + 1);
            descmail[0].name = "descemail" + (i + 1);
            email[0].name = "email" + (i + 1);
        }
        var mails = $(".telefoni");
        for (var i = 0; i < mails.length; i++) {
            var descmail = $("#" + mails[i].id + " .desctel");
            var email = $("#" + mails[i].id + " .tel");
            mails[i].id = "Tel" + (i + 1);
            descmail[0].name = "desctel" + (i + 1);
            email[0].name = "tel" + (i + 1);
        }
        var mails = $(".faxs");
        for (var i = 0; i < mails.length; i++) {
            var descmail = $("#" + mails[i].id + " .descfax");
            var email = $("#" + mails[i].id + " .fax");
            mails[i].id = "Fax" + (i + 1);
            descmail[0].name = "descfax" + (i + 1);
            email[0].name = "fax" + (i + 1);
        }
        var mails = $(".predsocials");
        for (var i = 0; i < mails.length; i++) {
            var descmail = $("#" + mails[i].id + " .sn");
            var email = $("#" + mails[i].id + " .LSN");
            mails[i].id = "SN" + (i + 1);
            descmail[0].name = "SN" + (i + 1);
            email[0].name = "LSN" + (i + 1);
        }
        var mails = $(".perssocials");
        for (var i = 0; i < mails.length; i++) {
            var descmail = $("#" + mails[i].id + " .csn");
            var email = $("#" + mails[i].id + " .lcsn");
            mails[i].id = "CSN" + (i + 1);
            descmail[0].name = "CSN" + (i + 1);
            email[0].name = "LCSN" + (i + 1);
        }
        var mails = $(".tickets");
        for (var i = 0; i < mails.length; i++) {
            var descmail = $("#" + mails[i].id + " .prezzib");
            var email = $("#" + mails[i].id + " .pb");
            var desctype = $("#" + mails[i].id + " .db");
            mails[i].id = "Tckt" + (i + 1);
            descmail[0].name = "type" + (i + 1);
            email[0].name = "price" + (i + 1);
            desctype[0].name = "typedesc" + (i + 1);
        }
        var mails = $(".services");
        for (var i = 0; i < mails.length; i++) {
            var descmail = $("#" + mails[i].id + " .serv");
            mails[i].id = "SER" + (i + 1);
            descmail[0].name = "SERV" + (i + 1);

        }
        var mails = $(".credits");
        for (var i = 0; i < mails.length; i++) {
            mails[i].name = "newcredit" + (i + 1);
        }
        var mails = $(".oldcredits");
        for (var i = 0; i < mails.length; i++) {
            mails[i].name = "credit" + (i + 1);
        }
        var mails = $(".dates");
        var form = $(".inserimento")[0];
        for (var i = 0; i < mails.length; i++) {
            $("#" + mails[i].id + " .inpdates").attr("name", "WD" + (i + 1));
            $("#" + mails[i].id + " .inpdatestext").attr("name", "WDT" + (i + 1));
            var k = 1;
            var orari = 0;
            var cont = 0;
            while (orari != undefined && orari != null) {
                orari = $("#" + mails[i].id + " .fasciaoraria")[cont];
                if (orari != undefined && orari != null) {
                    orari = orari.getElementsByTagName("input");
                    var newind1 = document.createElement("input");
                    newind1.type = "hidden";
                    newind1.name = "WD" + (i + 1) + "start" + k + "H";
                    newind1.value = orari[0].value;

                    var newind2 = document.createElement("input");
                    newind2.type = "hidden";
                    newind2.name = "WD" + (i + 1) + "start" + k + "M";
                    newind2.value = orari[1].value;

                    var newind3 = document.createElement("input");
                    newind3.type = "hidden";
                    newind3.name = "WD" + (i + 1) + "end" + k + "H";
                    newind3.value = orari[2].value;

                    var newind4 = document.createElement("input");
                    newind4.type = "hidden";
                    newind4.name = "WD" + (i + 1) + "end" + k + "M";
                    newind4.value = orari[3].value;

                    form.appendChild(newind1);
                    form.appendChild(newind2);
                    form.appendChild(newind3);
                    form.appendChild(newind4);

                }
                k = k + 1;
                cont = cont + 1;
            }


        }
        var days = $(".daysCheck");
        var form = $(".inserimento")[0];

        var cont = 0;
        for (var i = 0; i < days.length; i++) {
            var checks = days[i].getElementsByTagName("input");
            for (var j = 0; j < checks.length; j++) {
                if (checks[j].name != "Tutti i giorni" && checks[j].checked) {
                    cont = cont + 1;
                    var newind = document.createElement("input");

                    newind.type = "text";
                    newind.name = "WD" + cont;
                    newind.value = checks[j].name;
                    var k = 1;
                    var orari = 0;
                    while (orari != undefined && orari != null) {
                        orari = document.getElementById("D" + (i + 1) + "-" + k);
                        if (orari != undefined && orari != null) {
                            orari = orari.getElementsByTagName("input");
                            var newind1 = document.createElement("input");
                            newind1.type = "hidden";
                            newind1.name = "WD" + cont + "start" + k + "H";
                            newind1.value = orari[0].value;

                            var newind2 = document.createElement("input");
                            newind2.type = "hidden";
                            newind2.name = "WD" + cont + "start" + k + "M";
                            newind2.value = orari[1].value;

                            var newind3 = document.createElement("input");
                            newind3.type = "hidden";
                            newind3.name = "WD" + cont + "end" + k + "H";
                            newind3.value = orari[2].value;

                            var newind4 = document.createElement("input");
                            newind4.type = "hidden";
                            newind4.name = "WD" + cont + "end" + k + "M";
                            newind4.value = orari[3].value;

                            form.appendChild(newind1);
                            form.appendChild(newind2);
                            form.appendChild(newind3);
                            form.appendChild(newind4);

                        }
                        k = k + 1;
                    }
                    form.appendChild(newind);

                }
            }

        }
        var colls = document.getElementsByClassName("colls");
        for (var i = 0; i < colls.length; i++) {
            colls[i].id = "COL" + (i + 1);
            var mot = $("#" + colls[i].id + " input");
            mot.name = colls[i].id + "mot";
            var pois = $("#" + colls[i].id + " select");
            for (var k = 0; k < pois.length; k++) {
                pois[k].name = colls[i].id + "-" + (k + 1);
            }
        }

        tinyMCE.triggerSave();
        enpre_submit();
        form.submit();
    }
}


