<%-- 
    Document   : CompositeComponent
    Created on : 11-dic-2014, 11.29.59
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
            <i class="mdi-action-info" style="font-size: 35px;"></i>
        </center>
    </div>
    <div class="panel-body">
        <c:if test="${not empty workingtime && not empty prices}">
            <ul class="nav nav-tabs" style="margin-bottom: 15px;">
            <li class="active"><a href="#orari" data-toggle="tab">Orari</a></li>
            <li><a href="#prezzi" data-toggle="tab">Prezzi</a></li>
            </ul>
            <div class="tab-pane fade active in" id="orari">
                <jsp:include page="WorkingTimeComponent.jsp"/>
            </div>
    <div class="tab-pane fade" id="prezzi">
        <jsp:include page="PricesComponent.jsp"/> 
    </div>
        </c:if>
    </div>
</div>