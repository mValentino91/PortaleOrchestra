<%-- 
    Document   : AddToFavoritesComponent
    Created on : 7-giu-2015, 17.20.13
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<article class="component component-text">
    <div class="details">
        
        <div>
            <c:if test="${fav_rating == 0}">
                <i class="fa fa-heart-o favorite"></i>
                <span class="add_favorite"><spring:message code="label.add_favorite"></spring:message></span>                
            </c:if>  
            <c:if test="${fav_rating > 0}">
                <i class="fa fa-heart favorite"></i>
                <span class="add_favorite"><spring:message code="label.added_favorite"></spring:message></span>                
            </c:if>                   

            <div class="clear"></div>
            <div class="fav_rating">
                <input id="fav_rating_bar" type="text" value="" name="rating" />
                <span class="rating_subtitle"><spring:message code="label.interested"></spring:message></span>
            </div>
        </div>
        
    </div>
    
</article>

<script src="./dist/ion-range/js/ion.rangeSlider.js"></script>
<script>
    enable_favorite_actions('${poi.id}');
</script>
<c:if test="${fav_rating > 0}">
    <script>
        already_fav(${fav_rating});
    </script>             
</c:if> 
