<%-- 
    Document   : ContactsComponent
    Created on : 1-dic-2014, 14.31.40
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="panel panel-default">
    <div class="panel-thumbnail bg-primary" style="padding-bottom: 10px; padding-top: 5px">
        <center>
            <i class="fa fa-envelope-o fa-lg" style="font-size: 35px;"></i>
        </center>
    </div>
    <div class="panel-body">
        <center>
            <c:if test="${not empty contacts.emailsList}">   
                <b>Contatti Email:</b><br>
                <c:forEach var="contm" items="${contacts.emailsList}">
                    <b>${contm.label}:</b>
                    <a href="mailto:${contm.email}" target="_top"> ${contm.email}</a><br>
                </c:forEach>
                <br>
            </c:if>
            <c:if test="${not empty contacts.phoneList}">
                <b>Contatti Telefonici:</b><br>
                <c:forEach var="cont" items="${contacts.phoneList}">
                    <b>${cont.label}:</b>
                    ${cont.number}<br>
                </c:forEach>
                <br>
            </c:if>
            <c:if test="${not empty contacts.faxList}">
                <b>Fax:</b><br>
                <c:forEach var="cont" items="${contacts.faxList}">
                    <b>${cont.label}:</b>
                    ${cont.fax}<br>
                </c:forEach>
                <br>
            </c:if>
            <c:if test="${not empty contacts.facebook}">    
                <a href="#" class="matbtn matbtn-material-blue matbtn-fab matbtn-fab-mini fa fa-facebook"></a>
            </c:if>
            <c:if test="${not empty contacts.twitter}">    
                <a href="${contacts.twitter}" class="matbtn matbtn-material-lightblue matbtn-fab matbtn-fab-mini fa fa-twitter"></a>
            </c:if>
            <c:if test="${not empty contacts.google}">    
                <a href="${contacts.google}" class="matbtn matbtn-material-red matbtn-fab matbtn-fab-mini fa fa-google-plus"></a>
            </c:if>
            <c:if test="${not empty contacts.skype}">    
                <a href="${contacts.skype}" class="matbtn matbtn-material-lightblue matbtn-fab matbtn-fab-mini fa fa-skype"></a>
            </c:if>
            <c:if test="${not empty contacts.socialList}">
                <c:forEach var="cont" items="${contacts.socialList}">
                    <a href="${cont.social}" class="matbtn matbtn-material-teal matbtn-fab  fa fa-star"></a>
                </c:forEach>
            </c:if>
        </center>
    </div>  
</div>







