<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<style>


    .navbar-default{
        border: 0;
    }
    .navbar{
        background-color:#285E8E;
        border-radius: 0;
    }

    .navbar-default .navbar-nav > li > a{
        color: #fff;
        height: 50px !important;
    }

    .navbar-default .navbar-nav > li > a:hover, .navbar-default .navbar-nav > li > a:focus {
        background-color: #285E8E;
        color: #fff;
    }

    .dropdown{
        background-color: #285E8E;
        color: #fff;
    }

    .profile-image{
        height: 25px !important;
        width: 25px !important;   
        margin-top: -3px;
    }
    .profile-image-loading {
        height: 25px !important;
        width: 25px !important;
        margin-right: 15px !important;
        margin-top: 12x !important;
    }

    .navbar-brand{
        padding-top: 0px;
        padding-left: 5px;
    }

    #loginArea{
        margin-right: 0px;
        margin-left: 10px;
    }
    .autocomplete-suggestions { border-radius: 0px 0px 4px 4px; border: 1px solid rgba(0,0,0,0.15); background: #FFF; cursor: default; overflow: auto; -webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); -moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);   box-shadow: 0 6px 12px rgba(0,0,0,0.175); }
    .autocomplete-suggestion { border-top: 1px solid rgba(0,0,0,0.15); padding: 5px 10px; white-space: nowrap; text-overflow: ellipsis; overflow: hidden; }
    .autocomplete-no-suggestion { padding: 2px 5px;}
    .autocomplete-selected { background: whitesmoke; }
    .autocomplete-suggestions strong { font-weight: bold; color: #00689a; }
    .autocomplete-group { padding: 2px 5px; }
    .autocomplete-group strong { font-weight: bold; font-size: 16px; color: #00689a; display: block; }
    
    .translate-icon{
        width:25px;
        height:25px;
    }
    
    .google_translate_container{
        margin-top: 13px;
        margin-right: 10px;
    }
</style>
<script src="./dist/js/jquery.autocomplete.js"></script>
<script src="./dist/fb_js_pack/js_fb.js"></script>

    <jsp:include page="fbComponent.jsp"/>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="./"><img height="50" alt="Home" src="./dist/img/logoOrchestra.png"></a> 
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="./"><spring:message code="label.home"></spring:message></a></li>
                    <li><a href="./Map?category=all"> <spring:message code="label.interactivemap"></spring:message></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="label.categories"></spring:message> <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="./page?sec=culture"><spring:message code="label.culture"></spring:message></a></li>
                            <li><a href="./page?sec=accommodation"><spring:message code="label.accommodation"></spring:message></a></li>
                            <li><a href="./page?sec=food"><spring:message code="label.food"></spring:message></a></li>
                            <li><a href="./page?sec=craft"><spring:message code="label.craft"></spring:message></a></li>
                            <li><a href="./page?sec=mobility"><spring:message code="label.mobility"></spring:message></a></li>
                            <li class="divider"></li>
                            <li><a href="./page?sec=event"><spring:message code="label.event"></spring:message></a></li>
                            <li><a href="#"><spring:message code="label.cultural_association"></spring:message></a></li>
                            <li><a href="./page?sec=expo"><spring:message code="label.expo"></spring:message></a></li>
                        </ul>
                    </li>
                </ul>


                <ul class="nav navbar-nav navbar-right">
                    
                    <li>
                        <div class="google_translate_container">
                            <div id="google_translate_element"></div><script type="text/javascript">
                            function googleTranslateElementInit() {
                              new google.translate.TranslateElement({pageLanguage: 'it', layout: google.translate.TranslateElement.InlineLayout.SIMPLE}, 'google_translate_element');
                            }
                            </script><script type="text/javascript" src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>  
                        </div>
                    </li>
                    
                    <li>
                        
                        <input style="margin-top: 8px" id="autocomplete" type="text" class="form-control" placeholder="<spring:message code='label.topsearch'></spring:message>" />
                    </li>
                    
                    <!--
                    <li>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img class="translate-icon" src="./dist/img/translate.png" />
           
                        </a>
                        <ul class="dropdown-menu">
                            <li>        
                                <a onclick="
                                    if (window.location.href.indexOf('&lang') !== -1) {
                                        window.location = window.location.href.slice(0, window.location.href.indexOf('&lang')) + '&lang=it';
                                    } else
                                        window.location = window.location.href + '&lang=it'; 
                                    ">
                                    Italiano
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>        
                                <a onclick="
                                    if (window.location.href.indexOf('&lang') !== -1) {
                                        window.location = window.location.href.slice(0, window.location.href.indexOf('&lang')) + '&lang=en';
                                    } else
                                        window.location = window.location.href + '&lang=en';
                                    ">
                                    English
                                </a>
                            </li>
                        </ul>
                        
                    </li>
                    -->
                    
                    
                    <li id="loginArea" class="dropdown">
                        <jsp:include page="../access/loginArea.jsp" />
                    </li>
                    
                </ul> 

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <script>
        $('#autocomplete').autocomplete({
            serviceUrl: './Search/Autocomplete',
            onSelect: function (suggestion) {
                window.location = './getPoi?id=' + suggestion.data;
            }
        });
    </script>
