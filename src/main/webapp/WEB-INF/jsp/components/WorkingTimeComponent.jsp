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
    <div class="panel-thumbnail " style="background-color: #009688; padding-bottom: 1px; padding-top: 1px">
        <center>
            <i class="fa fa-clock-o" style="font-size: 39px; color: #FFF"></i>
        </center>
    </div>
    <div class="panel-body" style="padding-top: 1px; padding-bottom: 4px;">
        <jsp:include page="leafes/WorkingTimeLeaf.jsp"/>
    </div>
</div>
