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
        
        <title>ORCHESTRA - NUOVO POI</title>
        <script>
            function addcat (tasto) {
                var cont= document.getElementById("categoria");
               var lastcat= document.getElementsByClassName("cate");
               var x=lastcat.length;
               x=x+1;
               var newcat= document.createElement("input");
               var newbr= document.createElement("br");
               newcat.type="text";
               newcat.className="cate";
               newcat.name="category"+x;
               cont.insertBefore(newcat,tasto);
               cont.insertBefore(newbr,tasto);
            }
            
            function addimg(tasto) {
                var cont= document.getElementById("galleria");
               
                var newimg= document.createElement("input");
                var newbr= document.createElement("br");
                newimg.type="file"
                newimg.name="file"
                cont.insertBefore(newimg,tasto);
               cont.insertBefore(newbr,tasto);
            }
            function addpar(tasto) {
                var cont= document.getElementById("descrizione");
                var lastpar= document.getElementsByClassName("titolo");
                var x = lastpar.length;
                x=x+1;
                var newtit= document.createElement("input");
                newtit.type="text";
                newtit.name="titolo"+x;
                newtit.className="titolo";
                
                var newpar= document.createElement("textarea");
                newpar.name="par"+x;
                newpar.className="par";
                
                var newbr= document.createElement("br");
                var newbr2= document.createElement("br");
                var newbr3= document.createElement("br");
                 var newtitdesc= document.createTextNode("Titolo Paragrafo "+x);
                 var newpardesc= document.createTextNode("Paragrafo "+x);
                 cont.insertBefore(newtitdesc,tasto);
                cont.insertBefore(newtit,tasto);
               cont.insertBefore(newbr,tasto);
               cont.insertBefore(newpardesc,tasto);
               cont.insertBefore(newpar,tasto);
               cont.insertBefore(newbr3,tasto);
            }
            function addwd(tasto) {
                var cont= document.getElementById("orari");
                 var bt= document.getElementById("bt");
                var lastor= document.getElementsByClassName("days");
                var x = lastor.length;
                x=x+1;
                
                 var newgg= document.createElement("h3");
                 newgg.innerHTML="GIORNO "+x;
                var newdalle= document.createTextNode("Dalle");
                 var newalle= document.createTextNode("Alle");
                 var newgiorno= document.createTextNode("Giorno");
                 var newpunti= document.createTextNode(":");
                 var newpunti2= document.createTextNode(":");
                 var newbr= document.createElement("br");
                var newbr2= document.createElement("br");
                var newbr3= document.createElement("br");
                var newbr4= document.createElement("br");
                
                var newstart= document.createElement("input");
                newstart.type="text";
                newstart.name="WD"+x+"start1H";
                newstart.className="orarilav"+x;
                var newstartm= document.createElement("input");
                newstartm.type="text";
                newstartm.name="WD"+x+"start1M";
                var newend= document.createElement("input");
                newend.type="text";
                newend.name="WD"+x+"end1H";
                var newendm= document.createElement("input");
                newendm.type="text";
                newendm.name="WD"+x+"end1M";
                var newday= document.createElement("select");
                newday.name="WD"+x+"day";
                newday.className="days";
                var newopt=document.createElement("option");
   
                newopt.innerHTML="Seleziona un giorno";
                newday.appendChild(newopt);
                var newopt1=document.createElement("option");
                newopt1.value="Lunedì";
                newopt1.innerHTML="Lunedì";
                newday.appendChild(newopt1);
                var newopt2=document.createElement("option");
                newopt2.value="Martedì";
                newopt2.innerHTML="Martedì";
                newday.appendChild(newopt2);
                var newopt3=document.createElement("option");
                newopt3.value="Mercoledì";
                newopt3.innerHTML="Mercoledì";
                newday.appendChild(newopt3);
                var newopt4=document.createElement("option");
                newopt4.value="Giovedì";
                newopt4.innerHTML="Giovedì";
                newday.appendChild(newopt4);
                var newopt5=document.createElement("option");
                newopt5.value="Venerdì";
                newopt5.innerHTML="Venerdì";
                newday.appendChild(newopt5);
                var newopt6=document.createElement("option");
                newopt6.value="Sabato";
                newopt6.innerHTML="Sabato";
                newday.appendChild(newopt6);
                var newopt7=document.createElement("option");
                newopt7.value="Domenica";
                newopt7.innerHTML="Domenica";
                newday.appendChild(newopt7);
                
                cont.insertBefore(newgg,bt);
                cont.insertBefore(newbr,bt);
                cont.insertBefore(newgiorno,bt);
                cont.insertBefore(newday,bt);
                cont.insertBefore(newbr4,bt);
                cont.insertBefore(newdalle,bt);
                cont.insertBefore(newstart,bt);
                cont.insertBefore(newpunti,bt);
                cont.insertBefore(newstartm,bt);
                cont.insertBefore(newbr2,bt);
                cont.insertBefore(newalle,bt);
                cont.insertBefore(newend,bt);
                cont.insertBefore(newpunti2,bt);
                cont.insertBefore(newendm,bt);
                cont.insertBefore(newbr3,bt);
                
                
              }
              function addor(tasto) {
                var cont= document.getElementById("orari");
                var lastor= document.getElementsByClassName("days");
                var x = lastor.length;
                
                var orlav= document.getElementsByClassName("orarilav"+x);
                var k = orlav.length;
                k=k+1;
                var newstart= document.createElement("input");
                newstart.type="text";
                newstart.name="WD"+x+"start"+k+"H";
                newstart.className="orarilav"+x;
                var newstartm= document.createElement("input");
                newstartm.type="text";
                newstartm.name="WD"+x+"start"+k+"M";
                var newend= document.createElement("input");
                newend.type="text";
                newend.name="WD"+x+"end"+k+"H";
                var newendm= document.createElement("input");
                newendm.type="text";
                newendm.name="WD"+x+"end"+k+"M";
                
                var newdalle= document.createTextNode("Dalle");
                 var newalle= document.createTextNode("Alle");
               
                 var newpunti= document.createTextNode(":");
                 var newpunti2= document.createTextNode(":");
                
                var newbr2= document.createElement("br");
                var newbr3= document.createElement("br");
                
                
                 cont.insertBefore(newdalle,tasto);
                cont.insertBefore(newstart,tasto);
                cont.insertBefore(newpunti,tasto);
                cont.insertBefore(newstartm,tasto);
                cont.insertBefore(newbr2,tasto);
                cont.insertBefore(newalle,tasto);
                cont.insertBefore(newend,tasto);
                cont.insertBefore(newpunti2,tasto);
                cont.insertBefore(newendm,tasto);
                cont.insertBefore(newbr3,tasto);
              }
              
            
            function addrestday(tasto) {
                 var cont= document.getElementById("orari");
                var lastor= document.getElementsByClassName("ripsett");
                var x = lastor.length;
                x=x+1;
                var newday= document.createElement("select");
                newday.name="RD"+x;
                newday.className="ripsett";
                var newopt=document.createElement("option");
   
                newopt.innerHTML="Seleziona un giorno";
                newday.appendChild(newopt);
                var newopt1=document.createElement("option");
                newopt1.value="1";
                newopt1.innerHTML="Lunedì";
                newday.appendChild(newopt1);
                var newopt2=document.createElement("option");
                newopt2.value="2";
                newopt2.innerHTML="Martedì";
                newday.appendChild(newopt2);
                var newopt3=document.createElement("option");
                newopt3.value="3";
                newopt3.innerHTML="Mercoledì";
                newday.appendChild(newopt3);
                var newopt4=document.createElement("option");
                newopt4.value="4";
                newopt4.innerHTML="Giovedì";
                newday.appendChild(newopt4);
                var newopt5=document.createElement("option");
                newopt5.value="5";
                newopt5.innerHTML="Venerdì";
                newday.appendChild(newopt5);
                var newopt6=document.createElement("option");
                newopt6.value="2";
                newopt6.innerHTML="Sabato";
                newday.appendChild(newopt6);
                var newopt7=document.createElement("option");
                newopt7.value="7";
                newopt7.innerHTML="Domenica";
                newday.appendChild(newopt7);
                var newrd= document.createTextNode(x+"° giorno di riposo");
                var newbr= document.createElement("br");
                
                
                cont.insertBefore(newrd,tasto);
                cont.insertBefore(newday,tasto);
                cont.insertBefore(newbr,tasto);
            }
            function addripann(tasto) {
                 var cont= document.getElementById("orari");
                var lastor= document.getElementsByClassName("ripann");
                var x = lastor.length;
                x=x+1;
                var newrda= document.createElement("input");
               var newbr= document.createElement("br");
               newrda.type="text";
               newrda.className="ripann";
               newrda.name="RDA"+x;
                var newrd= document.createTextNode(x+"° giorno di riposo (GG/MM)");
                
               cont.insertBefore(newrd,tasto); 
               cont.insertBefore(newrda,tasto);
               cont.insertBefore(newbr,tasto);
               
            }
             function addbiglietto(tasto){
                  var cont= document.getElementById("prezzi");
                var lastor= document.getElementsByClassName("prezzib");
                var x = lastor.length;
                x=x+1;
                var newtype= document.createElement("input");
               var newbr= document.createElement("br");
               newtype.type="text";
               newtype.className="prezzib";
               newtype.name="type"+x;
               var newprice= document.createElement("input");
               var newbr2= document.createElement("br");
               newprice.type="text";
               newprice.name="price"+x;
               var newtypedesc= document.createElement("input");
               var newbr3= document.createElement("br");
               newtypedesc.type="text";
               newtypedesc.name="typedesc"+x;
                var newt= document.createTextNode("Tipo "+x+"° biglietto");
                var newp= document.createTextNode("Prezzo "+x+"° biglietto");
                var newpd= document.createTextNode("Descrizione "+x+"° biglietto");
                
                cont.insertBefore(newt,tasto); 
               cont.insertBefore(newtype,tasto);
               cont.insertBefore(newbr,tasto);
                cont.insertBefore(newp,tasto); 
               cont.insertBefore(newprice,tasto);
               cont.insertBefore(newbr2,tasto);
                cont.insertBefore(newpd,tasto); 
               cont.insertBefore(newtypedesc,tasto);
               cont.insertBefore(newbr3,tasto);
             }
             function addmail(tasto) {
                var cont= document.getElementById("contatti");
                var lastor= document.getElementsByClassName("email");
                var x = lastor.length;
                x=x+1;
                
                var newmail= document.createElement("input");
               var newbr= document.createElement("br");
               newmail.type="text";
               newmail.className="email";
               newmail.name="email"+x;
                var newdescmail= document.createElement("input");
               var newbr2= document.createElement("br");
               newdescmail.type="text";
               newdescmail.name="descemail"+x;
               
               var newde= document.createTextNode("Descrizione "+x+"° Email");
               var newie= document.createTextNode(x+"° Indirizzo Email");
               cont.insertBefore(newde,tasto); 
               cont.insertBefore(newdescmail,tasto);
               cont.insertBefore(newbr,tasto);
                cont.insertBefore(newie,tasto); 
                cont.insertBefore(newmail,tasto); 
                cont.insertBefore(newbr2,tasto);
             }
             function addtel(tasto) {
                var cont= document.getElementById("contatti");
                var lastor= document.getElementsByClassName("tel");
                var x = lastor.length;
                x=x+1;
                
                var newmail= document.createElement("input");
               var newbr= document.createElement("br");
               newmail.type="text";
               newmail.className="tel";
               newmail.name="tel"+x;
                var newdescmail= document.createElement("input");
               var newbr2= document.createElement("br");
               newdescmail.type="text";
               newdescmail.name="desctel"+x;
               
               var newde= document.createTextNode("Descrizione "+x+"° Telefono");
               var newie= document.createTextNode(x+"° Numero Di Telefono");
               cont.insertBefore(newde,tasto); 
               cont.insertBefore(newdescmail,tasto);
               cont.insertBefore(newbr,tasto);
                cont.insertBefore(newie,tasto); 
                cont.insertBefore(newmail,tasto); 
                cont.insertBefore(newbr2,tasto);
             }
             function addfax(tasto) {
                var cont= document.getElementById("contatti");
                var lastor= document.getElementsByClassName("fax");
                var x = lastor.length;
                x=x+1;
                
                var newmail= document.createElement("input");
               var newbr= document.createElement("br");
               newmail.type="text";
               newmail.className="fax";
               newmail.name="fax"+x;
                var newdescmail= document.createElement("input");
               var newbr2= document.createElement("br");
               newdescmail.type="text";
               newdescmail.name="descfax"+x;
               
               var newde= document.createTextNode("Descrizione "+x+"° Fax");
               var newie= document.createTextNode(x+"° Numero Fax");
               cont.insertBefore(newde,tasto); 
               cont.insertBefore(newdescmail,tasto);
               cont.insertBefore(newbr,tasto);
                cont.insertBefore(newie,tasto); 
                cont.insertBefore(newmail,tasto); 
                cont.insertBefore(newbr2,tasto);
             }
             function addsn(tasto){
                var cont= document.getElementById("contatti");
                var lastor= document.getElementsByClassName("sn");
                var x = lastor.length;
                x=x+1;
                
                var newday= document.createElement("select");
                newday.name="SN"+x;
                newday.className="sn";
                var newopt=document.createElement("option");
   
                newopt.innerHTML="Seleziona un social network";
                newday.appendChild(newopt);
                var newopt1=document.createElement("option");
                newopt1.value="facebook";
                newopt1.innerHTML="FaceBook";
                newday.appendChild(newopt1);
                var newopt2=document.createElement("option");
                newopt2.value="twitter";
                newopt2.innerHTML="Twitter";
                newday.appendChild(newopt2);
                var newopt3=document.createElement("option");
                newopt3.value="google";
                newopt3.innerHTML="Google+";
                newday.appendChild(newopt3);
                var newopt4=document.createElement("option");
                newopt4.value="skype";
                newopt4.innerHTML="Skype";
                newday.appendChild(newopt4);
                
                var newde= document.createTextNode(x+"° SocialNetwork (predefinito)");
               var newie= document.createTextNode("Indirizzo:");
               
               var newind= document.createElement("input");
               var newbr= document.createElement("br");
               newind.type="text";
               newind.name="LSN"+x;
               
               cont.insertBefore(newde,tasto); 
               cont.insertBefore(newday,tasto);
               cont.insertBefore(newie,tasto); 
               cont.insertBefore(newind,tasto);
               cont.insertBefore(newbr,tasto); 
               
             }
             
             function addsnp(tasto){
                var cont= document.getElementById("contatti");
                var lastor= document.getElementsByClassName("snp");
                var x = lastor.length;
                x=x+1;
                
              
                
                var newde= document.createTextNode(x+"° SocialNetwork (personalizzato): Nome:");
               var newie= document.createTextNode("Indirizzo:");
               
               var newind= document.createElement("input");
               var newbr= document.createElement("br");
               newind.type="text";
               newind.name="LCSN"+x;
               
               var newlab= document.createElement("input");
               newlab.type="text";
               newlab.name="CSN"+x;
               newlab.className="snp";
               
               cont.insertBefore(newde,tasto); 
               cont.insertBefore(newlab,tasto);
               cont.insertBefore(newie,tasto); 
               cont.insertBefore(newind,tasto);
               cont.insertBefore(newbr,tasto); 
               
             }
            </script>
            
    </head>
    <body>
    <center>
        <h1>Inserimento di un nuovo punto di interesse</h1>
    </center>
    <form action="insertpoi" method="POST" enctype="multipart/form-data" accept-charset="ISO-8859-1">
        Nome <input name="name" type="text"><br>
        Indirizzo <input name="address" type="text"><br>
        Latitudine <input name="latitude" type="text"><br>
        Longitudine <input name="longitude" type="text"><br>
        Descrizione Breve <textarea name="shortd"></textarea><br><br>
        <div id="categoria">
        Categoria <input name="category1" class="cate" type="text"><br>
        
        <input type="button" value="Aggiungi categoria" onclick="addcat(this)"><br>
        </div>
        Immagine di Copertina <input name="cover" type="file"><br>
        <div id="galleria">
            <br><br>
            <h2> GALLERIA </h2>
        Immagine per la Galleria 
        
        <input type="button" value="aggiungi immagine" onclick="addimg(this)"><br><br>
        </div>
        <br><br>
        <div id="descrizione">
            <h2> PARAGRAFI </h2>
            
            <input type="button" value="nuovo paragrafo" onclick="addpar(this)">
        </div>
        <div id="contatti">
            <h2> CONTATTI </h2>
            
            <input type="button" value="aggiungi indirizzo email" onclick="addmail(this)"><br>
            
            <input type="button" value="aggiungi numero di telefono" onclick="addtel(this)"><br>
            
            <input type="button" value="aggiungi numero fax" onclick="addfax(this)"><br>
            
            <input type="button" value="aggiungi social network" onclick="addsn(this)"><br>
           
            <input type="button" value="aggiungi social network personalizzato" onclick="addsnp(this)"><br>
        </div>
        <div id="orari">
            <h2>ORARI - periodo di lavoro</h2>
            
            
            
            <input type="button" value="Inserisci un  giorno" onclick="addwd(this)"><br><br>
            <input type="button" value="Inserisci un  orario" id="bt" onclick="addor(this)"><br><br>
            
            
           
            <input type="button" value="Aggiungi un giorno di riposo settimanale" onclick="addrestday(this)"><br>
           
            <input type="button" value="Aggiungi un giorno di riposo" onclick="addripann(this)"><br>
            </div>
            <div id='prezzi'>
                <h2> PREZZI DI INGRESSO </h2>
               
                <input type="button" value="aggiungi un biglietto" onclick="addbiglietto(this)">
            </div>
        <center>
        <input type="submit" value="SALVA POI">
        </center>
    </form>
    </body>
</html>
