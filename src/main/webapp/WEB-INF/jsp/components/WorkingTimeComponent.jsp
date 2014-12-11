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

<c:forEach var="Wdays" items="${workingtime.workingdays}">
<div class="row">
    <div class="col-md-6">
        ${Wdays.days}:
    </div>
        <c:forEach var="Wtime" items="${Wdays.workinghours}">
            <div class="col-md-6">
                ${Wtime.start} - ${Wtime.end}
            </div>
        </c:forEach>
</div>
</c:forEach>

