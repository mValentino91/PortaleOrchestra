/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function enadd_date(tasto) {
                 var cont = document.getElementById("endate");
                 var lastdate = document.getElementsByClassName("endates");
                 var x = lastdate.length;
                x = x + 1;
                
                var newcont = document.createElement("div");
                newcont.id = "enD" + x;
                newcont.className = "endates";
                
                var newaddorari = document.createElement("input");
                newaddorari.type = "button";
                newaddorari.value = "Aggiungi una fascia oraria";
                newaddorari.className ="btn btn-primary";
                newaddorari.setAttribute("onclick", "enaddor(this.parentNode.id, this)");
                
                var newdelgiorno = document.createElement("input");
                newdelgiorno.type = "button";
                newdelgiorno.value = "Elimina giorno evento";
                newdelgiorno.className="btn btn-danger";
                newdelgiorno.setAttribute("style", "margin-top: 5px; margin-bottom:15px;");
                newdelgiorno.setAttribute("onclick", "endelparent(this)");
                var newgiorno = document.createTextNode("Data*");
                
                var newdate = document.createElement("input");
                newdate.type = "text";
                newdate.name = "endate"+x;
                newdate.className="eninpdates datepickerinp obb form-control";
                
                var newbr5 = document.createElement("br");
                newcont.appendChild(newgiorno);
                newcont.appendChild(newdate);
                newcont.appendChild(newaddorari);
                
                newcont.appendChild(newbr5);
                newcont.appendChild(newdelgiorno);
               
                cont.insertBefore(newcont,tasto);
                enaddor(newcont.id, newaddorari);
                $('.datepickerinp').datepicker({
                    dateFormat: "dd/mm/yy"
    });
              
            }
            function enaddor(contid, tasto) {
                var cont = document.getElementById(contid);

                var k = (($("#" + contid + " input.enorarilav").length) / 4) + 1;

                var newcont = document.createElement("div");
                newcont.id = contid + "-" + k;
                newcont.className = "enfasciaoraria ";

                var newrow = document.createElement("div");
                var newrow2 = document.createElement("div");
                newrow.className =" row";
                newrow2.className="row";
                var newstart = document.createElement("input");
                newstart.type = "text";
                newstart.name ="en"+ contid + "start" + k + "H";
                newstart.className = "form-control hourpicker enorarilav obb";
                newstart.setAttribute("style", "width: 200px; display: inline !important;");
                newstart.setAttribute("onblur", "addzero(this)");
                
                var newstartm = document.createElement("input");
                newstartm.type = "text";
                newstartm.name = "en"+contid + "start" + k + "M";
                newstartm.className = "form-control minutepicker enorarilav obb";
                newstartm.setAttribute("style", "width: 200px; display: inline !important;");
                newstartm.setAttribute("onblur", "addzero(this)");
                var newend = document.createElement("input");
                newend.type = "text";
                newend.name = "en"+contid + "end" + k + "H";
                newend.setAttribute("style", "width: 200px; display: inline !important");
                newend.className = "form-control hourpicker enorarilav obb";
                newend.setAttribute("onblur", "addzero(this)");
                var newendm = document.createElement("input");
                newendm.type = "text";
                newendm.name = "en"+contid + "end" + k + "M";
                newendm.className = "form-control minutepicker enorarilav obb";
                newendm.setAttribute("style", "width: 200px; display: inline !important");
                newendm.setAttribute("onblur", "addzero(this)");
                var delel = document.createElement("input");
                delel.type = "button";
                delel.value = "Elimina fascia oraria";
                delel.className="btn btn-danger";
                delel.setAttribute("style", "margin-top: 5px; margin-bottom: 5px;");
                delel.setAttribute("onclick", "endelparent(this)");
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
            
function endelcat(tasto) {
                var name = $(tasto).prev().attr("name");
                var lastcat = document.getElementsByClassName("encate");
                var x = lastcat.length;
                var i = 0;
                var ok = false;
                if (name != "encategory" + x) {
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
            function endelcat2(tasto) {
                $(tasto).prev().remove();
                $(tasto).prev().remove();
                $(tasto).prev().remove();
                tasto.remove();
            }
            function enaddcat(tasto) {
                var cont = document.getElementById("encategoria");
                var lastcat = document.getElementsByClassName("encate");
                var x = lastcat.length;
                x = x + 1;
                var newcat = document.createElement("input");
                var newbr = document.createElement("br");
                newcat.type = "text";
                newcat.className = "form-control encate obb";
                newcat.name = "encategory" + x;
                var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.className = "btn btn-danger";
                newdel.setAttribute("onclick", "endelcat(this)");
                cont.insertBefore(newcat, tasto);
                cont.insertBefore(newdel, tasto);
                cont.insertBefore(newbr, tasto);
            }

            
            function endelparent2(cont) {
                var txt= $('#'+cont.parentNode.id+' textarea');
               tinyMCE.triggerSave();
               tinyMCE.EditorManager.execCommand('mceRemoveEditor', false, txt[0].id);
                cont.parentNode.remove();
            }
            function enaddpar(tasto) {
                var cont = document.getElementById("endescrizione");
                var lastpar = document.getElementsByClassName("entitolo");
                var x = lastpar.length;
                x = x + 1;
                var newcont = document.createElement("div");
                newcont.id="enPar"+x;
                newcont.className="enparagrafi";
                
               var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.setAttribute("onclick", "endelparent2(this)");
                newdel.setAttribute("style", "margin-bottom: 5px;");
                newdel.className = "btn btn-danger";
        
                var newtit = document.createElement("input");
                newtit.type = "text";
                newtit.name = "entitolo" + x;
                newtit.className = "form-control entitolo";
                

                var newpar = document.createElement("textarea");
                newpar.name = "enpar" + x;
                newpar.className = "enpar obb";
                newpar.id = "enpar"+x;
                var newbr = document.createElement("br");
                var newbr2 = document.createElement("br");
                var newbr3 = document.createElement("br");
                var newtitdesc = document.createTextNode("Titolo Paragrafo " );
                var newpardesc = document.createTextNode("Paragrafo*");
                newcont.appendChild(newtitdesc);
                newcont.appendChild(newtit);
                newcont.appendChild(newbr);
                newcont.appendChild(newpardesc);
                newcont.appendChild(newpar);
                newcont.appendChild(newbr2);
                newcont.appendChild(newdel);
                cont.insertBefore(newcont, tasto);
                tinymce.EditorManager.execCommand('mceAddEditor',true, newpar.id);
              /* tinymce.init({
                selector: ".par",
                language: 'it'
                    }); */
               
            }

            function endelparent(tasto) {
                tasto.parentNode.remove();
            }

            
            
            
            function enaddbiglietto(tasto) {
                var cont = document.getElementById("enprezzi");
                var lastor = document.getElementsByClassName("enprezzib");
                var x = lastor.length;
                x = x + 1;
                
                 var newcont = document.createElement("div");
                newcont.id="enTckt"+x;
                newcont.className="entickets";
                
                var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.className ="btn btn-danger";
                newdel.setAttribute("style", "margin-bottom: 5px;");
                newdel.setAttribute("onclick", "delparent(this)");
                
                var newtype = document.createElement("input");
                var newbr = document.createElement("br");
                newtype.type = "text";
                newtype.className = "form-control enprezzib obb";
                newtype.name = "type" + x;
                var newprice = document.createElement("input");
                var newbr2 = document.createElement("br");
                newprice.type = "text";
                newprice.name = "enprice" + x;
                newprice.className ="form-control enpb obb";
                newprice.setAttribute("onblur", "enreplace_virgola(this, this.value)");

                var newtypedesc = document.createElement("input");
                var newbr3 = document.createElement("br");
                newtypedesc.type = "text";
                newtypedesc.name = "typedesc" + x;
                newtypedesc.className ="form-control endb";
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
            function enaddmail(tasto) {
                var cont = document.getElementById("encontatti");
                var lastor = document.getElementsByClassName("enemail");
                var x = lastor.length;
                x = x + 1;
                
                var newcont = document.createElement("div");
                newcont.id="enMail"+x;
                newcont.className="enmails";
                
               var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.className = "btn btn-danger";
                newdel.setAttribute("style","margin-bottom: 5px;");
                newdel.setAttribute("onclick", "endelparent(this)");
                
                var newmail = document.createElement("input");
                var newbr = document.createElement("br");
                newmail.type = "text";
                newmail.className = "form-control enemail obb";
                newmail.name = "enemail" + x;
                var newdescmail = document.createElement("input");
                var newbr2 = document.createElement("br");
                newdescmail.type = "text";
                newdescmail.name = "endescemail" + x;
                newdescmail.className = "form-control endescmail ";
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
            function enaddtel(tasto) {
                var cont = document.getElementById("encontatti");
                var lastor = document.getElementsByClassName("entel");
                var x = lastor.length;
                x = x + 1;

                var newcont = document.createElement("div");
                newcont.id="enTel"+x;
                newcont.className="entelefoni";
                
               var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.className = "btn btn-danger";
                newdel.setAttribute("style", "margin-bottom:5px;")
                newdel.setAttribute("onclick", "endelparent(this)");
                
                var newmail = document.createElement("input");
                var newbr = document.createElement("br");
                newmail.type = "text";
                newmail.className = "form-control entel  obb";
                newmail.name = "entel" + x;
                var newdescmail = document.createElement("input");
                var newbr2 = document.createElement("br");
                newdescmail.type = "text";
                newdescmail.name = "endesctel" + x;
                newdescmail.className = "form-control endesctel";
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
            function enaddfax(tasto) {
                var cont = document.getElementById("encontatti");
                var lastor = document.getElementsByClassName("enfax");
                var x = lastor.length;
                x = x + 1;
                var newcont = document.createElement("div");
                newcont.id="enFax"+x;
                newcont.className="enfaxs";
                
               var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.className="btn btn-danger";
                newdel.setAttribute("style","margin-bottom: 5px;");
                newdel.setAttribute("onclick", "endelparent(this)");
                var newmail = document.createElement("input");
                var newbr = document.createElement("br");
                newmail.type = "text";
                newmail.className = "form-control enfax obb";
                newmail.name = "enfax" + x;
                var newdescmail = document.createElement("input");
                var newbr2 = document.createElement("br");
                newdescmail.type = "text";
                newdescmail.name = "endescfax" + x;
                newdescmail.className = "form-control endescfax";
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
            function enaddsn(tasto) {
                var cont = document.getElementById("encontatti");
                var lastor = document.getElementsByClassName("ensn");
                var x = lastor.length;
                x = x + 1;
                 var newcont = document.createElement("div");
                newcont.id="enSN"+x;
                newcont.className="enpredsocials";
                
               var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.className = "btn btn-danger";
                newdel.setAttribute('style', 'margin-bottom: 5px;');
                newdel.setAttribute("onclick", "endelparent(this)");
                var newday = document.createElement("select");
                newday.name = "enSN" + x;
                newday.className = "form-control ensn obb";
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
                newind.name = "enLSN" + x;
                newind.className="form-control enLSN obb";
                newcont.appendChild(newde);
                newcont.appendChild(newday);
                newcont.appendChild(newie);
                newcont.appendChild(newind);
                newcont.appendChild(newbr);
                newcont.appendChild(newdel);
               cont.insertBefore(newcont,tasto);

            }

            function enaddsnp(tasto) {
                var cont = document.getElementById("encontatti");
                var lastor = document.getElementsByClassName("enperssocials");
                var x = lastor.length;
                x = x + 1;

                var newcont = document.createElement("div");
                newcont.id="enCSN"+x;
                newcont.className="enperssocials";
                
               var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.className= "btn btn-danger";
                newdel.setAttribute("style","margin-bottom: 5px;");
                newdel.setAttribute("onclick", "endelparent(this)");

                var newde = document.createTextNode("Nome: *");
                var newie = document.createTextNode("Indirizzo: *");

                var newind = document.createElement("input");
                var newbr = document.createElement("br");
                newind.type = "text";
                newind.name = "enLCSN" + x;
                newind.className ="form-control enlcsn obb";

                var newlab = document.createElement("input");
                newlab.type = "text";
                newlab.name = "enCSN" + x;
                newlab.className = "form-control encsn obb";
                newcont.appendChild(newde);
                newcont.appendChild(newlab);
                newcont.appendChild(newie);
                newcont.appendChild(newind);
                newcont.appendChild(newbr);
                newcont.appendChild(newdel);
               cont.insertBefore(newcont,tasto);
                

            }
            function enaddservizio(tasto) {
                var cont = document.getElementById("enservizi");
                var lastor = document.getElementsByClassName("enservices");
                var x = lastor.length;
                x = x + 1;
                
                var newcont = document.createElement("div");
                newcont.id="enSER"+x;
                newcont.className="enservices";
                
               var newdel = document.createElement("input");
                newdel.type = "button";
                newdel.value = "Elimina";
                newdel.className = "btn btn-danger";
                newdel.setAttribute("style","margin-bottom: 5px;")
                newdel.setAttribute("onclick", "endelparent(this)");
                
                var newlab = document.createElement("input");
                newlab.type = "text";
                newlab.name = "enSERV" + x;
                newlab.className = "form-control enserv  obb";

                var newde = document.createTextNode("Servizio: *");
                var newbr = document.createElement("br");

                
                newcont.appendChild(newde);
                newcont.appendChild(newlab);
                newcont.appendChild(newbr);
                newcont.appendChild(newdel);
                cont.insertBefore(newcont,tasto);
            }
            function enreplace_virgola(inp, testo) {
                var newtesto = testo.replace(",", ".");
                inp.value = newtesto;
            }
            
           
            function encheckobb() {
                ok=true;
                var obb=$("input.obb");
                for (var i=0; i<obb.length; i++){
                    if(obb[i].value.trim() == "")
                        ok=false;
                }
                
                 var short=$('.enshortd')[0];
                 if (short.value.trim() =='')
                     ok=false;
                var par=$(".enpar");
                 for(var i=0; i<par.length; i++){
                     var short=tinyMCE.get(par[i].id).getContent();
                     if (short.trim()=='')
                     ok=false;
                 }
                return ok;
            }
            function enbonifica () {
                var inps = $("input");
                for (var i=0; i<inps.length; i++){
                   if(inps[i].type!="file"){
                   inps[i].value = inps[i].value.replace(/\'/g, '&#039;');
                   inps[i].value = inps[i].value.replace(/\"/g, '&quot;');
                   inps[i].value= inps[i].value.trim();
               }
                    
                }
                var short=$('.enshortd')[0].value;
                 short = short.replace(/\'/g, '&#039;');
                 short = short.replace(/\"/g, '&quot;');
                 short = short.replace(/\n/ig,"<br>");
                $('.enshortd')[0].value=short;
            }
            function endebonifica () {
                var inps = $("input");
                for (var i=0; i<inps.length; i++){
                   if(inps[i].type!="file"){
                   inps[i].value = inps[i].value.replace(/&#039;/g,"'");
                   inps[i].value = inps[i].value.replace(/&quot;/g,'"');
                  
               }
           }
           
               var short=$('.enshortd')[0].value;
               short= short.replace(/&#039;/g,"'");
               short= short.replace(/&quot;/g,'"');
               short = short.replace(/<br>/ig,'\n');
               $('.enshortd')[0].value=short;
            }
           
             
           
            function enpre_submit() {
                 var paragrafi = $(".enparagrafi");
                for(var i=0; i< paragrafi.length; i++){
                   var titolo= $("#"+paragrafi[i].id+" .entitolo");
                    var paragrafo = $("#"+paragrafi[i].id+" .enpar");
                        paragrafi[i].id="enPar"+(i+1);
                        titolo[0].name="entitolo"+(i+1);
                        paragrafo[0].name="enpar"+(i+1);
                       
                }
                if(!checkobb()){
                    alert("ERRORE: Rispettare i campi obbligatori!")
                }
                else {
                
                enbonifica();
                
                var mails = $(".enmails");
                for (var i=0; i<mails.length; i++){
                    var descmail= $("#"+mails[i].id+ " .endescmail");
                    var email = $("#"+mails[i].id+ " .enemail");
                    mails[i].id="enMails"+(i+1);
                    descmail[0].name="endescemail"+(i+1);
                    email[0].name="enemail"+(i+1);
                }
                var mails = $(".entelefoni");
                for (var i=0; i<mails.length; i++){
                    var descmail= $("#"+mails[i].id+ " .endesctel");
                    var email = $("#"+mails[i].id+ " .entel");
                    mails[i].id="enTel"+(i+1);
                    descmail[0].name="endesctel"+(i+1);
                    email[0].name="entel"+(i+1);
                }
                var mails = $(".enfaxs");
                for (var i=0; i<mails.length; i++){
                    var descmail= $("#"+mails[i].id+ " .endescfax");
                    var email = $("#"+mails[i].id+ " .enfax");
                    mails[i].id="enFax"+(i+1);
                    descmail[0].name="endescfax"+(i+1);
                    email[0].name="enfax"+(i+1);
                }
                var mails = $(".enpredsocials");
                for (var i=0; i<mails.length; i++){
                    var descmail= $("#"+mails[i].id+ " .ensn");
                    var email = $("#"+mails[i].id+ " .enLSN");
                    mails[i].id="enSN"+(i+1);
                    descmail[0].name="enSN"+(i+1);
                    email[0].name="enLSN"+(i+1);
                }
                var mails = $(".enperssocials");
                for (var i=0; i<mails.length; i++){
                    var descmail= $("#"+mails[i].id+ " .encsn");
                    var email = $("#"+mails[i].id+ " .enlcsn");
                    mails[i].id="enCSN"+(i+1);
                    descmail[0].name="enCSN"+(i+1);
                    email[0].name="enLCSN"+(i+1);
                }
                 var mails = $(".entickets");
                for (var i=0; i<mails.length; i++){
                    var descmail= $("#"+mails[i].id+ " .enprezzib");
                    var email = $("#"+mails[i].id+ " .enpb");
                    var desctype = $("#"+mails[i].id+ " .endb");
                    mails[i].id="enTckt"+(i+1);
                    descmail[0].name="entype"+(i+1);
                    email[0].name="enprice"+(i+1);
                    desctype[0].name="entypedesc"+(i+1);
                }
                 var mails = $(".enservices");
                for (var i=0; i<mails.length; i++){
                    var descmail= $("#"+mails[i].id+ " .enserv");
                    mails[i].id="enSER"+(i+1);
                    descmail[0].name="enSERV"+(i+1);
                   
                }
                var mails = $(".endates");
                var form = $(".inserimento")[0];
                    for (var i = 0; i < mails.length; i++) {
                        $("#"+mails[i].id+" .eninpdates").attr("name","enWD"+(i+1));
                        var k=1;
                            var orari=0;
                            var cont=0;
                            while(orari!= undefined && orari!= null){
                                orari=$("#"+mails[i].id+" .enfasciaoraria")[cont];
                                
                                if(orari != undefined && orari != null){
                                    orari=orari.getElementsByTagName("input");
                                    var newind1 = document.createElement("input");
                                    newind1.type = "hidden";
                                    newind1.name = "enWD"+(i+1)+"start"+k+"H";
                                    newind1.value= orari[0].value;
                                    
                                    var newind2 = document.createElement("input");
                                    newind2.type = "hidden";
                                    newind2.name = "enWD"+(i+1)+"start"+k+"M";
                                    newind2.value= orari[1].value;
                                   
                                    var newind3 = document.createElement("input");
                                    newind3.type = "hidden";
                                    newind3.name = "enWD"+(i+1)+"end"+k+"H";
                                    newind3.value= orari[2].value;
                                    
                                    var newind4 = document.createElement("input");
                                    newind4.type = "hidden";
                                    newind4.name = "enWD"+(i+1)+"end"+k+"M";
                                    newind4.value= orari[3].value;
                                    
                                    form.appendChild(newind1);
                                    form.appendChild(newind2);
                                    form.appendChild(newind3);
                                    form.appendChild(newind4);
                                    
                                }
                                k=k+1;
                                cont=cont+1;
                            }
                            
                        
                    }
                    var colls = document.getElementsByClassName("encolls");
                    for(var i=0; i< colls.length; i++) {
                        colls[i].id="enCOL"+(i+1);
                        var mot= $("#"+colls[i].id+" input");
                        mot.name=colls[i].id+"enmot";
                        var pois=$("#"+colls[i].id+" select");
                        for (var k=0; k < pois.length; k++) {
                            pois[k].name=colls[i].id+"-"+(k+1);
                        }
                    }
                tinyMCE.triggerSave();
                
            }
            }
          

