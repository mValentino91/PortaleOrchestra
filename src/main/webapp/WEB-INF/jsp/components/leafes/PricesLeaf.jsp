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
     <c:forEach var="Tprice" varStatus="status" items="${prices.prices}">

    <div style="margin-top: 5px; font-size: 120%;"><b><i class="fa fa-ticket"></i> ${Tprice.type.toUpperCase()}</b> <p style="font-size: 90%; display: inline;">€ ${Tprice.price}</p> <div style="color: #64b5f6; font-size: 110%;" data-toggle="tooltip" data-original-title="${Tprice.type_description}" class="prinf fa fa-info-circle"></div></div>
    <c:if test="${ ! status.last}" >
    <div class="separator"></div>
    </c:if>
</c:forEach>
        </center>
<script type="text/javascript">
    $(function(){
       $('.prinf').tooltip();
    });
</script>