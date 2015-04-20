<%-- 
    Document   : FavoriteListComponent
    Created on : 19-apr-2015, 18.50.47
    Author     : andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<article class="component component-text">
    <div class="details">
        <h2>Elenco dei preferiti</h2>
        <!-- implementare il foreach dei preferiti per categorie-->
        <div class="paragrafo">
            <div>
                <strong>${map_cat.main_category}</strong>
            </div>
            <div style="margin-top: 10px;">
                <c:forEach var="poi" items="${map_cat.poi}">
                    ${poi.name}<br>
                </c:forEach>
            </div>
            
            <hr>
            <div>
                <strong>Categoria 2</strong>
            </div>
            
            <div style="margin-top: 10px;">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                Phasellus tempus purus dolor, nec consectetur justo feugiat at. 
                Vestibulum ac ante vitae arcu consectetur iaculis efficitur non libero. 
                Proin ante mi, mollis vel ligula ac, tempus vehicula magna. 
                Donec dapibus, urna eget rutrum sollicitudin, ante nisl malesuada orci, ac dictum elit lacus eu lectus. 
                Aenean feugiat pulvinar nibh, eget euismod lectus ultrices quis. 
                Duis lacus dui, pellentesque in ante lobortis, porta tempor nibh. 
                Maecenas elementum facilisis justo, in rutrum leo mattis ut. 
                Suspendisse luctus ipsum eu ultrices volutpat.<br>
            </div>
            <hr>
            
        </div>
    </div>
</article>