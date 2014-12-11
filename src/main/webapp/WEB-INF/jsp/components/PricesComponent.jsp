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

<c:forEach var="Tprice" items="${prices.prices}">

    <div title="${Tprice.type_description}">${Tprice.type}:</div> ${Tprice.price}

</c:forEach>
