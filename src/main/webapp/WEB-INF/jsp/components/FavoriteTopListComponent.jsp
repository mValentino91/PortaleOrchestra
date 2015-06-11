<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
    .poiR {
        width: 100%;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;  
        clear: both;
    }

    .poiRimg {
        width: 60px;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;  
        float:left;
    
    }

    .poiRnameContainer{
        display: table;
        border:0px solid green;
        width: 180px;
        height: 60px;
        float: left;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;				
        border: 0px solid red;
    }    
    
    .poiRname {
        display: table-cell;
        overflow: hidden;
        vertical-align: middle;
        border: 0px solid red;
        
    }    
    
</style>
<article class="component component-text">
    <div class="details">

        <!-- implementare il foreach dei preferiti per categorie-->
        <div class="paragrafo">
            <b><spring:message code="label.${listRTitle}"></spring:message></b>
            <c:set var="count" scope="page" value="${0}"/>
            <c:forEach var="poiR" items="${listR}">
                <c:if test="${count < 10 }">
                    <div class="poiR">
                        <div class="poiRimg">
                           <img src="./dist/poi/img/${poiR.id}/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/> 
                        </div>
                        <div class="poiRnameContainer">
                            <div class="poiRname">
                                <a href="getPoi?id=${poiR.id}">${poiR.name}</a>
                            </div>
                        </div>
                    </div>
                </c:if>
                
                <c:set var="count" value="${count + 1}" scope="page"/>         
                </c:forEach>
            <div class="clear"></div>
        </div>
    </div>
</article>
