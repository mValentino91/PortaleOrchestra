<%-- 
    Document   : WorkingTimeLeaf
    Created on : 13-dic-2014, 10.18.41
    Author     : Alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

  <center>
<c:forEach var="Wdays" items="${workingtime.workingdays}">

    <div style="font-size: 110%; margin-top: 2px;"><b>${Wdays.days}</b></div>
     <c:forEach var="Wtime" items="${Wdays.workinghours}">
            
         <div style="font-size: 105%;">  ${Wtime.start} - ${Wtime.end}</div>
         </c:forEach>
 </c:forEach>
         <div class="separator" style="margin-top: 2px;"></div>
         <div> <b>Giorni di chiusura settimanale:</b> ${workingtime.weekly_day_of_rest}</div>
         <div> <b>Chiusure programmate:</b> 
         <c:forEach var="close" items="${workingtime.days_of_rest}">
             ${close}  
             
         </c:forEach>
         </div>
        </center>