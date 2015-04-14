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
</style>
<nav class="navbar navbar-fixed-top header" style="
     box-shadow: 0px 0px 3px 0px black;
     background:#285e8e; ">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbar" style="background:#285e8e;">
            <ul class="nav navbar-nav">
                <li><a style="color:white" href="http://www.orchestra.unina.it/">
                        Home
                    </a>
                </li>
                <li><a style="color:white" href="./Map?category=all">Interactive Map</a></li>
            </ul>
            <ul id="loginArea" class="nav navbar-nav navbar-right">
                <jsp:include page="../access/loginArea.jsp" />
            </ul>
        </div>
    </div>
</nav>

