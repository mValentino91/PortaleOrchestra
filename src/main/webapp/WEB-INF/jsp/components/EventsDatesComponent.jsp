<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
   <div class="component">
    <div class="details">
        <center> 
    <div class="WTL showtab" id="oggitab">
        

                <c:forEach var="edates" items="${eventsdate.dates}">
                    <b>${edates.date}</b><br>
                </c:forEach>


           
        

        <div class="btn btn-teal" style="padding: 1px 1px 1px 1px; color: #FFF; font-size: 85%; margin-top: 4px; margin-bottom: 4px;" id="tutti" onclick="show_hourtab(this.id)">MOSTRA TUTTI GLI ORARI</div>
    </div>
    <div class="WTL hiddentab" id="tuttitab" >
        <c:forEach var="Edates" items="${eventsdate.dates}">

            <div style="font-size: 110%; margin-top: 2px;"><b>${Edates.date}</b></div>
                    <c:forEach var="Etime" items="${Edates.hours}">

                <div style="font-size: 105%;">  ${Etime.start} - ${Etime.end}</div>

            </c:forEach>
        </c:forEach>
         <div class="btn btn-teal" style="padding: 1px 1px 1px 1px; color: #FFF; font-size: 85%; margin-top: 4px; margin-bottom: 4px;" id="oggi" onclick="show_hourtab(this.id)">RIDUCI</div>
    </div>
        </center>
    </div>
   </div>

