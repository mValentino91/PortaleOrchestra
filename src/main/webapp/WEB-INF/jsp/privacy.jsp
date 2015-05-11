<%-- 
    Document   : privacy
    Created on : 10-apr-2015, 0.29.22
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Privacy Policy</title>
        <script src=".dist/js/jquery.js"></script>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700'>
        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600'>
        <link rel='stylesheet' href='./dist/css/bootstrap.min.css'>
        <link href="./dist/css/font-awesome.min.css" rel="stylesheet">
        <link href="./dist/css/poi_view.css" rel="stylesheet">
        <link href="./dist/css/OrchestraIconFont.css" rel="stylesheet"> 
        <script src="./dist/googlePlusDesign/js/bootstrap.min.js"></script>
    </head>
    <body>

        <jsp:include page="components/topBar.jsp"/> 

        <div class="container-fixed">


            <div class="col-xs-12">
                <div class="cover_img" style="background: url('./dist/img/privacy-banner.png');">

                </div>

            </div>



            <div class="col-xs-12 col-sm-6 col-md-6 padding_dx">

                <article class="component component-text">
                    <div class="details">
                        <h2>Facebook Privacy Policy</h2>

                        <p class="paragrafo">
                            I dati che vengono recuperati da Facebook sono esclusivamente quelli pubblici, in paticolare luoghi delle foto caricate, like alle pagine e luoghi in cui sei taggato. Nessuna informazione strettamente personale (es: messaggi, foto, etc) sarà analizzata. Tutti i dati saranno momentaneamente salvati in forma anonima e, subito dopo la loro elaborazione, eliminati. Di seguito un elenco più dettagliato dei dati recuperati. 
                        </p>

                        <p class="paragrafo">
                            <strong>Profilo di Base</strong>
                            <br>
                            Dati anagrafici di base (nome, cognome, età), provenienza geografica, religione, livello di istruzione, tipologia di lavoro.
                        </p>		

                        <p class="paragrafo">
                            <strong>Tagged Places</strong>
                            <br>
                            Luoghi in cui è stato effettuato il check-in. 
                            Di tali luoghi verrà estratta la categoria e sarà utilizzata per definire le preferenze dell'utente.
                        </p>	

                        <p class="paragrafo">
                            <strong>Foto</strong>
                            <br>
                            Foto caricate. In particolare non vengono salvate le foto in se, ma solo i luoghi a cui fanno riferimento.
                            Di tali luoghi verrà estratta la categoria e sarà utilizzata per definire le preferenze dell'utente.
                        </p>	

                        <p class="paragrafo">
                            <strong>Like</strong>
                            <br>
                            Like sulle pagine. In particolare non vengono salvate le categorie delle pagine.
                            Di tali pagine verrà estratta la categoria e sarà utilizzata per definire le preferenze dell'utente.
                        </p>					

                        <div class="intents">
                            <span class="count">ITA</span>
                        </div>
                    </div>
                </article> 




            </div>


            <div class="col-xs-12 col-sm-6 col-md-6 padding_dx">

                <article class="component component-text">
                    <div class="details">
                        <h2>Facebook Privacy Policy</h2>

                        <p class="paragrafo">
                            Tagged places, photos uploaded ad page likes will be analyzed. No strictly private infomation (e.g., private messages, etc) will be analyzed. Each data will be anonymously saved and, after their elaboration, they will be deleted. Details are shown below. 
                        </p>

                        <p class="paragrafo">
                            <strong>Basic Profile</strong>
                            <br>
                            Personal Data (name, surname, age), religion, education, work.
                        </p>		

                        <p class="paragrafo">
                            <strong>Tagged Places</strong>
                            <br>
                            Places where you tagged.  <br>
                            Categories of places will be extracted and used to define user preferences.
                        </p>	

                        <p class="paragrafo">
                            <strong>Foto</strong>
                            <br>
                            Uploaded Photos. <br>
                            Categories of places of photos will be extracted and used to define user preferences.
                        </p>	

                        <p class="paragrafo">
                            <strong>Like</strong>
                            <br>
                            Page Likes. <br> 
                            Categories of pages will be extracted and used to define user preferences.
                        </p>										

                        <div class="intents">
                            <span class="count">ENG</span>
                        </div>
                    </div>
                </article> 

            </div>	          


        </div>
    </body>
</html>
