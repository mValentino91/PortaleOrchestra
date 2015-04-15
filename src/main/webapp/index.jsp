<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%
    String redirectURL = "http://www.orchestra.unina.it";
    response.sendRedirect(redirectURL);
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!doctype html>
<!--[if lt IE 7 ]><html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]><html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]><html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]><html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="en" class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta name="author" content="ThemeFuse">
        <meta name="keywords" content="">
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <title>Modern Touch | Index</title>

        <!-- main JS libs -->
        <script src="./dist/cardDesign/js/libs/modernizr.min.js"></script>
        <script src="./dist/cardDesign/js/libs/jquery-1.10.0.js"></script>
        <script src="./dist/cardDesign/js/libs/jquery-ui.min.js"></script>
        <script src="./dist/cardDesign/js/libs/bootstrap.min.js"></script>

        <!-- Style CSS -->
        <link href="./dist/cardDesign/css/bootstrap.css" media="screen" rel="stylesheet">
        <link href="./dist/cardDesign/css/style.css" media="screen" rel="stylesheet">

        <!-- scripts -->
        <script src="./dist/cardDesign/js/general.js"></script>

        <!-- custom input -->
        <script src="./dist/cardDesign/js/jquery.customInput.js"></script>
        <script type="text/javascript" src="./dist/cardDesign/js/custom.js"></script>
        <!-- Placeholders -->
        <script type="text/javascript" src="./dist/cardDesign/js/jquery.powerful-placeholder.min.js"></script>
        <!-- Lightbox prettyPhoto -->
        <link href="./dist/cardDesign/css/prettyPhoto.css" rel="stylesheet">
        <script src="./dist/cardDesign/js/jquery.prettyPhoto.js"></script>
        <!-- CarouFredSel  -->
        <script src="./dist/cardDesign/js/jquery.carouFredSel-6.2.1-packed.js"></script>
        <script>
            jQuery(document).ready(function($) {

                $('#carouFredsel-1').carouFredSel({
                    next: "#carousel-next-1",
                    prev: "#carousel-prev-1",
                    auto: false,
                    scroll: {items: 1}
                });

                $(window).resize(function() {

                    $('#carouFredsel-1').carouFredSel({
                        next: "#carousel-next-1",
                        prev: "#carousel-prev-1",
                        auto: false,
                        scroll: {items: 1}
                    });
                })
            });
        </script>
        <!-- Progress Bars -->
        <script src="./dist/cardDesign/js/progressbar.js"></script>
        <!-- Calendar -->
        <script src="./dist/cardDesign/js/jquery-ui.multidatespicker.js"></script>
        <!-- range sliders -->
        <script src="./dist/cardDesign/js/jquery.slider.bundle.js"></script>
        <script src="./dist/cardDesign/js/jquery.slider.js"></script>
        <link rel="stylesheet" href="./dist/cardDesign/css/jslider.css">
        <!-- Video Player -->
        <link href="./dist/cardDesign/css/video-js.css" rel="stylesheet">
        <script src="./dist/cardDesign/js/video.js"></script>

        <!-- Scroll Bars -->
        <script src="./dist/cardDesign/js/jquery.mousewheel.js"></script>
        <script src="./dist/cardDesign/js/jquery.jscrollpane.min.js"></script>
        <script type="text/javascript">
            jQuery(function()
            {
                jQuery('.scrollbar').jScrollPane({
                    verticalDragMaxHeight: 18,
                    verticalDragMinHeight: 18
                });

                jQuery('.scrollbar.style2').jScrollPane({
                    verticalDragMaxHeight: 28,
                    verticalDragMinHeight: 28
                });

                jQuery('.scrollbar.style3').jScrollPane({
                    verticalDragMaxHeight: 38,
                    verticalDragMinHeight: 38
                });

                jQuery('.scrollbar.style4').jScrollPane({
                    verticalDragMaxHeight: 38,
                    verticalDragMinHeight: 38
                });
            });
        </script>
    </head>
    <body>
        <div class="body_wrap">
            <div class="container">
                <!-- content -->
                <div class="content " role="main">
                    <!-- row -->
                    <div class="row">
                        <div class="col-sm-12">
                            <!-- Website Menu -->
                            <div id="topmenu">
                                <ul class="dropdown clearfix boxed boxed-blue">
                                    <li class="link-more"><a href="#"><i></i></a></li>
                                    <li class="menu-level-0"><a href="#"><span>Home</span></a></li>
                                    <li class="menu-level-0"><a href="#"><span>About</span></a></li>
                                    <li class="menu-level-0"><a href="#"><span>Services</span></a>
                                        <ul class="submenu-1">
                                            <li class="menu-level-1"><a href="#">Web design</a></li>
                                            <li class="menu-level-1"><a href="#">User interface</a></li>
                                            <li class="menu-level-1"><a href="#">Social media</a>
                                                <ul class="submenu-2">
                                                    <li class="menu-level-2"><a href="#">Gallery images</a></li>
                                                    <li class="menu-level-2"><a href="#">Fine Slider</a></li>
                                                    <li class="menu-level-2"><a href="#">Video in header</a></li>
                                                    <li class="menu-level-2"><a href="#">Video Slider</a>
                                                        <ul class="submenu-3">
                                                            <li class="menu-level-3"><a href="#">Item 1</a></li>
                                                            <li class="menu-level-3"><a href="#">Item 2</a></li>
                                                            <li class="menu-level-3"><a href="#">Item 3</a></li>
                                                        </ul>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="menu-level-0"><a href="#"><span>Portfolio</span></a>
                                        <ul class="submenu-1">
                                            <li class="menu-level-1"><a href="#">Web design</a></li>
                                            <li class="menu-level-1"><a href="#">User interface</a></li>
                                            <li class="menu-level-1"><a href="#">Social media</a>
                                                <ul class="submenu-2">
                                                    <li class="menu-level-2"><a href="#">Item 1</a></li>
                                                    <li class="menu-level-2"><a href="#">Item 2</a></li>
                                                    <li class="menu-level-2"><a href="#">Item 3</a></li>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="menu-level-0"><a href="#"><span>Blog</span></a></li>
                                    <li class="menu-level-0"><a href="#"><span>Contact</span></a></li>
                                    <li class="menu-search">
                                        <form id="searchForm" class="menu-search-form" method="post">
                                            <input type="text" name="search" value="" class="menu-search-field" placeholder="Search the website" />
                                            <input type="submit" value="" class="btn menu-search-submit" name="search-send" />
                                        </form>
                                    </li>
                                </ul>
                            </div>
                            <!--/ Website Menu -->
                        </div>
                    </div>
                    <!--/ row -->
                    <div class="row">
                        <div class="col-md-12">
                            <!-- Image Slider -->
                            <div class="widget-container widget-gallery boxed">
                                <div class="inner">
                                    <div id="myCarousel" class="carousel slide" data-interval="20000">
                                        <!-- Carousel items -->
                                        <div class="carousel-inner">
                                            <div class="active item"><a href="images/temp/post_img7.jpg" class="prettyPhoto" data-rel="prettyPhoto" title="Post Image"><img src="images/temp/post_img7.jpg" alt="" /></a></div>
                                            <div class="item"><a href="images/temp/post_img1.jpg" class="prettyPhoto" data-rel="prettyPhoto" title="Post Image"><img src="images/temp/post_img1.jpg" alt="" /></a></div>
                                            <div class="item"><a href="images/temp/post_img2.jpg" class="prettyPhoto" data-rel="prettyPhoto" title="Post Image"><img src="images/temp/post_img2.jpg" alt="" /></a></div>
                                            <div class="item"><a href="images/temp/post_img4.jpg" class="prettyPhoto" data-rel="prettyPhoto" title="Post Image"><img src="images/temp/post_img4.jpg" alt="" /></a></div>
                                            <div class="item"><a href="images/temp/post_img6.jpg" class="prettyPhoto" data-rel="prettyPhoto" title="Post Image"><img src="images/temp/post_img6.jpg" alt="" /></a></div>
                                        </div>
                                        <div class="carousel-controls">
                                            <!-- Carousel indicators -->
                                            <ol class="carousel-indicators">
                                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                                <li data-target="#myCarousel" data-slide-to="2"></li>
                                                <li data-target="#myCarousel" data-slide-to="3"></li>
                                                <li data-target="#myCarousel" data-slide-to="4"></li>
                                            </ol>
                                            <!-- Carousel nav -->
                                            <a class="carousel-control left" href="#myCarousel" data-slide="prev"></a>
                                            <a class="carousel-control right" href="#myCarousel" data-slide="next"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--/ Image Slider -->
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-7">
                            <div class="well text-justify" style="font-size: 20px">
                                <h1>Il Portale </h1>
                                Napoli (IPA: ['napoli] ascolta[?·info]; Nàpule in napoletano, pronuncia ['nɑːpələ] oppure ['nɑːpulə]) è un comune italiano di 989 846 abitanti[2], terzo in Italia per popolazione. La città è capoluogo della omonima città metropolitana e della regione Campania ed è situata in posizione pressoché centrale nell'omonimo golfo, tra il Vesuvio e l'area vulcanica dei Campi Flegrei. Con oltre 3 000 000 di abitanti[N 2] la città metropolitana di Napoli è la terza d'Italia dopo quella di Roma e quella di Milano, nonché la più popolosa dell'Italia meridionale e dell'Europa mediterranea.
                                Fondata nell'VIII secolo a.C., fu tra le città egemoni della Magna Graecia,[4] grazie al rapporto privilegiato con Atene,[5] ed esercitò una notevole influenza commerciale, culturale e religiosa sulle popolazioni italiche circostanti[6] tanto da diventare il centro della filosofia epicurea in Italia. Dopo il crollo dell'Impero romano, nell'VIII secolo la città formò un ducato autonomo indipendente dall'Impero bizantino; in seguito, dal XIII secolo e per circa seicento anni, fu capitale del regno di Napoli. Divenuta capitale del Regno delle Due Sicilie sotto i Borbone, conobbe un lungo periodo di sviluppo socioeconomico culminato in una serie di primati civili e tecnologici[7][8] tra cui la costruzione della prima ferrovia in Italia.[9][10] Dopo l'annessione al Regno d'Italia soffrì di un sensibile declino[11] esteso anche a tutto il sud Italia.[12][13] Per motivi storici, artistici, politici ed ambientali è stata, dal IX secolo fino ad oggi, tra i principali centri di riferimento culturale d'Europa.[14][15][16]
                                Sede della Federico II, la più antica università statale d'Europa,[17] ospita altresì l'Orientale, la più antica università di studi sinologici ed orientalistici del continente[18] e la Nunziatella, una delle più antiche accademie militari al mondo, eletta patrimonio storico e culturale dei Paesi del Mediterraneo da parte dell'Assemblea parlamentare del Mediterraneo.[19] Luogo d'origine della lingua napoletana, ha esercitato ed esercita un forte ruolo in numerosi campi del sapere, della cultura e dell'immaginario collettivo a livello nazionale.
                            </div>
                        </div>
                        <div class="col-md-5">
                            <!-- Grid Menu -->
                            <div class="grid-menu clearfix">

                                <div class="grid-box grid-box-large"><a href="./Map?category=all" class="boxed boxed-green"><strong><i class="icon-grid icon-grid-3"></i></strong><span>Mappa Interattiva</span></a></div>
                            </div>
                            <!--/ Grid Menu -->
                        </div>
                        <div class="col-md-5">
                            <!-- Grid Menu -->
                            <div class="grid-menu clearfix">
                                <div class="grid-box grid-box-large"><a href="#" class="boxed boxed-blue"><strong><i class="icon-grid icon-grid-3"></i></strong><span>Cultura</span></a></div>
                                <div class="grid-box"><a href="#" class="boxed boxed-yellow"><strong><i class="icon-grid icon-grid-3"></i></strong><span>Divertimento</span></a></div>
                                <div class="grid-box"><a href="#" class="boxed boxed-red"><strong><i class="icon-grid"></i></strong><span>Enogastronomia</span></a></div>
                                <div class="grid-box"><a href="#" class="boxed boxed-turquoise"><strong><i class="icon-grid icon-grid-2"></i></strong><span>Trasporti</span></a></div>
                                <div class="grid-box"><a href="#" class="boxed boxed-brown"><strong><i class="icon-grid icon-grid-2"></i></strong><span>Artigiani</span></a></div>
                            </div>
                            <!--/ Grid Menu -->
                        </div>
                        <div class="col-md-5">
                            <!-- Grid Menu -->
                            <div class="grid-menu clearfix">
                                <div class="grid-box"><a href="#" class="boxed boxed-red"><strong><i class="icon-grid"></i></strong><span>Chi Siamo</span></a></div>
                                <div class="grid-box"><a href="#" class="boxed boxed-green"><strong><i class="icon-grid icon-grid-2"></i></strong><span>Help Me</span></a></div>
                            </div>
                            <!--/ Grid Menu -->
                        </div>
                    </div>
                    <!--/ row -->
                </div>
                <!--/ content -->
            </div>
            <!--/ container -->
        </div>
    </body>
</html>