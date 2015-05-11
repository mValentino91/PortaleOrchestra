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
        </script>

        <script src="../dist/js/jquery.js"></script>
        <script src="../dist/js/form.js"></script>
        <link href="../dist/css/poi_view.css" rel="stylesheet">
        <title>ORCHESTRA - NUOVA PAGINA D'APPROFONDIMENTO</title>
        
       

    </head>
    <body>
       
            <div class="col-md-12" style="background-color: #0081c2; color:#FFFFFF; text-align: center; margin-bottom:20px;">
                <h1><b>Inserimento di un nuova pagina d'approfondimento</b></h1><br>
                <h2><b>I Campi contrassegnati dall'asterisco sono obbligatori!</b></h2>
            </div>
        <div class="container-fixed">
            <form class="inserimento" action="savedpage" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
                <div class="row">
                <article class="col-md-12 component component-text">
				  <div class="big-header contact">
					  <span class="caps">Info Generali</span>
				  </div> 
                    
		<div class="details">
                    <center>
                    <div class="row">
                        <center>
                        <div class="col-md-12">Nome* <input name="name" class="form-control obb" type="text"> </div>
                        </center><br>
                       
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
                </div>
                <div class="row">
                <center>
                    <input type="button" class="btn btn-success" style="width: 150px; height: 50px; margin-top:25px; margin-bottom: 25px;" value="SALVA PAGINA" onclick="pre_submit()">
                </center>
                </div>
            </form>
        </div>
    </body>
</html>
