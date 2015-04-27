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
    <div class="WTL showtab" id="oggitab">
        <div style="font-size: 110%; margin-top: 2px; " title="${oggi}, ${data}"><b><spring:message code="label.today"></spring:message></b></div>
        <c:choose>           
            <c:when test="${workingtime.weekly_day_of_rest.contains(oggi)}">
                
                <div style="font-size: 105%;">  <spring:message code="label.today"></spring:message></div>
            </c:when>
            <c:when test="${workingtime.days_of_rest.contains(data)}">
                <div style="font-size: 105%;">  <spring:message code="label.today"></spring:message></div>
            </c:when>
            <c:otherwise>

                <c:forEach var="Wdays" items="${workingtime.workingdays}">
                    <c:forEach var="Wtime" items="${Wdays.workinghours}">
                        <c:if test="${Wdays.days.equals(oggi)}">
                            <div style="font-size: 105%;">  ${Wtime.start} - ${Wtime.end}</div>
                        </c:if>
                    </c:forEach>
                </c:forEach>


            </c:otherwise> 
        </c:choose>

        <div class="btn btn-teal" style="padding: 1px 1px 1px 1px; color: #FFF; font-size: 85%; margin-top: 4px; margin-bottom: 4px;" id="tutti" onclick="show_hourtab(this.id)"><spring:message code="label.showsalltimes"></spring:message></div>
    </div>
    <div class="WTL hiddentab" id="tuttitab" >
        <c:forEach var="Wdays" items="${workingtime.workingdays}">

            <div style="font-size: 110%; margin-top: 2px;"><b>${Wdays.days}</b></div>
                    <c:forEach var="Wtime" items="${Wdays.workinghours}">

                <div style="font-size: 105%;">  ${Wtime.start} - ${Wtime.end}</div>

            </c:forEach>
        </c:forEach>
         <div class="btn btn-teal" style="padding: 1px 1px 1px 1px; color: #FFF; font-size: 85%; margin-top: 4px; margin-bottom: 4px;" id="oggi" onclick="show_hourtab(this.id)"><spring:message code="label.minimize"></spring:message></div>
    </div>
    <div class="separator" style="margin-top: 2px;"></div>
     <c:if test="${not empty workingtime.weekly_day_of_rest}">
    <div> <b><spring:message code="label.cdw"></spring:message></b> ${workingtime.weekly_day_of_rest}</div>
     </c:if>
    <c:if test="${not empty workingtime.days_of_rest}">
    <div> <b><spring:message code="label.plannedclosures"></spring:message></b> ${workingtime.days_of_rest}</div>
    </c:if>
</center>