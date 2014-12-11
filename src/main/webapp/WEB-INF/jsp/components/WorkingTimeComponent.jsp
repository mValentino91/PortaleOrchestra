<%-- 
    Document   : WorkingTimeComponent
    Created on : 11-dic-2014, 12.12.19
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-default">
    <div class="panel-thumbnail bg-primary" style="padding-bottom: 5px; padding-top: 5px">
        <center>
            <i class="fa fa-clock-o" style="font-size: 35px;"></i>
        </center>
    </div>
    <div class="panel-body">
        <center>
<c:forEach var="Wdays" items="${workingtime.workingdays}">

    <div style="font-size: 110%; margin-top: 2px;"><b>${Wdays.days}</b></div>
     <c:forEach var="Wtime" items="${Wdays.workinghours}">
            
         <div style="font-size: 105%;">  ${Wtime.start} - ${Wtime.end}</div>
         </c:forEach>
 </c:forEach>
        </center>
    </div>
</div>
