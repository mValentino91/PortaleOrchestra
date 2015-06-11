<%-- 
    Document   : TileSliderComponent
    Created on : 9-giu-2015, 15.12.58
    Author     : antonio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="box-elem" style="background-color:#fff">
    
        <div id="promoslide">
            <c:forEach var="promotile" items="${tileslider.tileList}" varStatus="cont">
                <div>
                    <a href="${promotile.link}">
                        <img src="./dist/page/img/${pages.id}/${promotile.img}">
                    </a>		     	
                </div>                                                
            </c:forEach>
        </div>

</div>