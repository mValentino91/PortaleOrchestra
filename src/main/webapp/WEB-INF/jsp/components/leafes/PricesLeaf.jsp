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

    <div style="margin-top: 2px; font-size: 120%; font: Roboto"><b>${Tprice.type}</b> <div style="font-size: 100%"> ${Tprice.price}€</div> <i title="${Tprice.type_description}" style="color: #64b5f6; font-size: 130%" class="fa fa-info-circle"></i></div>

</c:forEach>
        </center>
