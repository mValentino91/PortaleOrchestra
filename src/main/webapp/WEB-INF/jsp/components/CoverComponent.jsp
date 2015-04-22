<%-- 
    Document   : CoverComponent
    Created on : 2-dic-2014, 12.45.06
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
<c:when test="${ empty vartype || vartype != 'DeepeningPage' }">
<div class="cover_img" style="background: url(./dist/poi/img/${poi.id}/${coverimg.link});"></div>
</c:when>
<c:otherwise>
<div class="cover_img" style="background: url(./dist/dpage/img/${poi.id}/${coverimg.link});"></div>
</c:otherwise>
</c:choose>

     
