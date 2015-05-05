<%-- 
    Document   : LinkedPoiComponent
    Created on : 30-apr-2015, 15.18.41
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<div class="component">
    <div class="details">
        <c:forEach var="linkedpoi" items="${linkedpoi.linked}">
            
        </c:forEach>
    </div>
</div>