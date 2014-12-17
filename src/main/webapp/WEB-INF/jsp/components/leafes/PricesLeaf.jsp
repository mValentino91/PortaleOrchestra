<%-- 
    Document   : PricesLeaf
    Created on : 13-dic-2014, 10.19.43
    Author     : Alex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <center>
<c:forEach var="Tprice" items="${prices.prices}">

    <div style="margin-top: 2px; font-size: 120%;"><b><i class="fa fa-ticket"></i> ${Tprice.type.toUpperCase()}</b> <p style="font-size: 90%; display: inline;">€ ${Tprice.price}</p> <i title="${Tprice.type_description}" style="color: #64b5f6; font-size: 110%" class="fa fa-info-circle"></i></div>
    <div class="separator"></div>
</c:forEach>
        </center>
