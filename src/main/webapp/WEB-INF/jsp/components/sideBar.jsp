<%-- 
    Document   : sideBar
    Created on : 2-dic-2014, 20.50.49
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                <li><a style="color:white" href="./Map?category=all">Mappa Interattiva</a></li>
            </ul>
            <ul id="loginArea" class="nav navbar-nav navbar-right">
                <jsp:include page="../access/loginArea.jsp" />
            </ul>
        </div>
    </div>
</nav>

