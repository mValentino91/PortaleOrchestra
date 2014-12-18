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
    <div class="panel-thumbnail" style= " background-color: #fbc02d; padding-bottom: 1px; padding-top: 1px">
        <center>
            <i class="fa fa-eur" style="color :#FFF; font-size: 39px;"></i>
        </center>
    </div>
    <div class="panel-body" style= "padding-top: 1px; padding-bottom: 4px;">
        <center>
<c:forEach var="Tprice" items="${prices.prices}">

    <div style="margin-top: 2px; font-size: 120%; font: Roboto"><b>${Tprice.type}</b> <div style="font-size: 100%"> ${Tprice.price}€</div> <i title="${Tprice.type_description}" style="color: #64b5f6; font-size: 130%; position: relative;" class="fa fa-info-circle"></i></div>

</c:forEach>
        </center>
    </div>
</div>