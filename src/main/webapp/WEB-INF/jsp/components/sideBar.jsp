<%-- 
    Document   : sideBar
    Created on : 2-dic-2014, 20.50.49
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    .navbar-nav>li{
        padding-bottom:0px;
        padding-top: 0px;
    }
    .navbar-nav>li>a{
        padding-bottom:12px;
        padding-top: 12px;
    }
    .navbar-nav>li>a:hover{
        background-color: transparent !important;
        color:gray !important;
    }
    .profile-image{
        height: 25px !important;
        width: 25px !important;    
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
        margin-right: -15px;
        margin-left: 10px;
    }
    .autocomplete-suggestions { border-radius: 0px 0px 4px 4px; border: 1px solid rgba(0,0,0,0.15); background: #FFF; cursor: default; overflow: auto; -webkit-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64); -moz-box-shadow: 1px 4px 3px rgba(50, 50, 50, 0.64);   box-shadow: 0 6px 12px rgba(0,0,0,0.175); }
    .autocomplete-suggestion { border-top: 1px solid rgba(0,0,0,0.15); padding: 5px 10px; white-space: nowrap; text-overflow: ellipsis; overflow: hidden; }
    .autocomplete-no-suggestion { padding: 2px 5px;}
    .autocomplete-selected { background: whitesmoke; }
    .autocomplete-suggestions strong { font-weight: bold; color: #00689a; }
    .autocomplete-group { padding: 2px 5px; }
    .autocomplete-group strong { font-weight: bold; font-size: 16px; color: #00689a; display: block; }
</style>
<script src="./dist/js/jquery.autocomplete.js"></script>
<nav class="navbar navbar-fixed-top header" style="
     box-shadow: 0px 0px 3px 0px black;
     background:#00689a; ">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="http://www.orchestra.unina.it/"><img height="50" alt="Home" src="./dist/img/logoOrchestra.jpg"</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar" style="background:#00689a;">
            <ul class="nav navbar-nav">
                <li><a style="color:white" href="./Map?category=all">Interactive Map</a></li>
            </ul>
            <ul id="loginArea" class="nav navbar-nav navbar-right">
                <jsp:include page="../access/loginArea.jsp" />
            </ul>
                <div class="navbar-right">
                    <input style="margin-top: 9px" id="autocomplete" type="text" class="form-control" placeholder="Cerca POI...">
                </div>
        </div>
    </div>
</nav>
<script>
    $('#autocomplete').autocomplete({
        serviceUrl: './Search/Autocomplete',
        onSelect: function(suggestion) {
            window.location='./getPoi?id='+suggestion.data;
        }
    });
</script>

