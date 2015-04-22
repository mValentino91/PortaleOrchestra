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

        <h1>Elenco dei preferiti</h1>
        <!-- implementare il foreach dei preferiti per categorie-->
        <div class="paragrafo">
            <div>
                <c:forEach var="cat" items="${map_cat.map.keySet()}">
                    <div class="main_cat">
                        <h3>${cat}</h3>
                        <div class="poi">
                            <c:forEach var="p" items="${map_cat.map.get(cat)}">
                                <p>${p.name}</p>
                                <div class="clear"></div>
                                <div class="fav_rating" data-rating="${map_cat.getRate(p.id)}">
                                        <input type="text" class="range" value="" name="range" style="border: 1px solid green"/>
                                        <span class="baloon_subtitle">...quanto sei interessato?</span>
                                </div>	
                            </c:forEach>    
                        </div>
                    </div>
                    <hr>    
                </c:forEach>
            </div>
        </div>
    </div>
</article>
