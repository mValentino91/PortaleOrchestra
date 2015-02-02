<%-- 
    Document   : cartComponent
    Created on : 2-feb-2015, 15.34.40
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<div class="col-md-12">
    <c:if test="${empty cart}">
        <h2 class="text-center">Nessun Poi Presente Nel Carrello!</h2>
    </c:if>
    <c:forEach var = "poi" items = "${cart}">
        <div id="${poi.id}" class="container-fluid poiBox">
            <div class="col-md-3"> 
                <img class="img-responsive center-block" style="height: 120px"
                     src="./dist/poi/img/${poi.id}/cover.jpg" 
                     onError="this.src='./dist/img/notFound.png';" alt=""/>
            </div>
            <div class="col-md-5">
                <span style="color: #242424">
                    <h4>${poi.name}</h4>
                    <p style="padding-top: 5px;" class="poiBoxShortDescription ${poi.id}">
                        ${poi.shortDescription}
                        <a href="./getPoi?id=${poi.id}">Altro...</a>
                    </p>
                </span>
            </div>
        </div>
        <hr>
    </c:forEach>
</div>
