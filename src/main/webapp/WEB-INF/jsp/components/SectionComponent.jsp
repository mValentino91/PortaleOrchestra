<%-- 
    Document   : SectionComponent
    Created on : 4-dic-2014, 9.43.19
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<article class="component component-text">
    <div class="details">
        <div style="font-size: 20px; margin-bottom: 10px;">${poi.name} <c:if test="${not empty poi.start_date }"><div style="float:right; font-size: 15px!important;">${poi.start_date} - ${poi.end_date}</div></c:if></div>
        <c:forEach var="sect" items="${description.sectionsList}">
            <div class="paragrafo">
            <c:if test="${not empty sect.title}">   
                    <strong>${sect.title}</strong>
                    <br>
            </c:if>
            ${sect.description}
            </div>
          
            
        </c:forEach>
      <!-- <div class="intents">
	<span class="count">Cultura | Museo</span>
	</div> -->
    </div>
</article>
        