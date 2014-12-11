<%-- 
    Document   : PricesComponent
    Created on : 11-dic-2014, 12.47.48
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
            <i class="fa fa-eur" style="font-size: 35px;"></i>
        </center>
    </div>
    <div class="panel-body">
        <center>
<c:forEach var="Tprice" items="${prices.prices}">

    <div style="margin-top: 2px; font-size: 110%; font: Roboto"><b>${Tprice.type}:</b> ${Tprice.price}€ <i title="${Tprice.type_description}" style="color: blue;" class="fa fa-info-circle"></i></div>

</c:forEach>
        </center>
    </div>
</div>