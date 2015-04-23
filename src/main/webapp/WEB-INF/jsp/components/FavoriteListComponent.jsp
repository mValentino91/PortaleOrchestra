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

        <!-- implementare il foreach dei preferiti per categorie-->
        <div class="paragrafo">
            <div class="favorite-container" style="min-height: 200px;">
                <c:forEach var="cat" items="${map_cat.map.keySet()}">
                    <div class="category-container">
                        <h4>${map_slug.get(cat)}</h4>
                        
                        <div class="poi">
                            
                        
                            <c:forEach var="p" items="${map_cat.map.get(cat)}">
                                    <div class="poi_preview_box">
                                        <i class="fa fa-trash poi_preview_delete" style="cursor:pointer; font-size:16px;" ></i>
                                        <div class="poi_preview_img" style="background-image: url('dist/poi/img/${p.id}/cover.jpg')"></div>
                                        <div class="poi_preview_content">
                                            <div class="poi_preview_title">
                                                 ${p.name}
                                            </div>
                                            <div class="poi_preview_text">
                                             ${p.address}
                                            </div>
                                            <div class="fav_rating poi_preview_rating" data-idpoi="${p.id}" data-rating="${map_cat.getRate(p.id)}">
                                                <input type="text" class="range" value="" name="range" style="border: 1px solid green"/>
                                                <span class="baloon_subtitle">...quanto sei interessato?</span>
                                            </div>	                                            
                                            
                                        </div>
                                          
                                    </div>
                                    <div class="clear"></div>     
                            
                            </c:forEach>

 
                        </div>
                    </div>
                       
                </c:forEach>
            </div>
        </div>
    </div>
</article>
