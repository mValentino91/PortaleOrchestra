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
        padding-bottom:15px;
        padding-top: 10px;
    }
    .navbar-nav>li>a:hover{
        background-color: transparent;
        color:white;
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
</style>
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
            <form class="navbar-form navbar-right" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
            </form>
        </div>
    </div>
</nav>

