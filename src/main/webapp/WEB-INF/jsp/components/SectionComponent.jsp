<%-- 
    Document   : SectionComponent
    Created on : 4-dic-2014, 9.43.19
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-default">
    <div style="max-height: 3px; height: 3px; background-color: #9ccc65;"></div>
    <div class="panel-body">
        <h2 style="margin: 0 0 0 0; "><span class="glyphicon glyphicon-map-marker"></span> ${poi.name}</h2><br>
        <c:forEach var="sect" items="${description.sectionsList}">
            <article style="margin: 0 0 0 0;">
            <div class="titolo"> 
                <c:if test="${not empty sect.title}">   
                    <b>${sect.title}</b>
                    </c:if>
            </div>
        <br>
        
        <div class="testo">${sect.description}</div>
        
        
        </article>
            <div class="separator"></div>
            <br>
        </c:forEach>
        
    </div>
</div>
        