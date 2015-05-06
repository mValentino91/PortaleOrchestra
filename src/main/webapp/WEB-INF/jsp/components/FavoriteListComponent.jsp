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
                
                <c:if test="${empty map_cat.map.keySet()}">
                    <div class="no_favorites_result">
                        <spring:message code="label.no_favorites_presents"></spring:message>
                    </div>
                </c:if>
                
                <c:forEach var="cat" items="${map_cat.map.keySet()}">
                    <div class="category-container">
                        <h4>${map_slug.get(cat)}</h4>
                        
                        <div class="poi">
                            
                        
                            <c:forEach var="p" items="${map_cat.map.get(cat)}">
                                    <div class="poi_preview_box">
                                        <i class="fa fa-times poi_preview_delete"  style="cursor:pointer; font-size:16px;" ></i>
                                        <div class="poi_preview_img">
                                            <img src="./dist/page/img/5534a2d944ae96d0ace55886/01_panorama.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/>
                                        </div>
                                        <div class="poi_preview_content">
                                            
                                            <div class="poi_preview_text">
                                                <i class="fa fa-heart"  style="cursor:pointer; font-size:16px; color: #ED5565" ></i>
                                                <i class="fa fa-trash"  style="cursor:pointer; font-size:16px;" ></i>
                                       
                                            </div>
                                            <div class="poi_preview_title">
                                                 ${p.name}
                                                 
                                            </div>
                                                                                      
                                            
                                        </div>
                                            
                                        <div class="poi_preview_rating_container">
                                            
                                            <div class="fav_rating poi_preview_rating" data-idpoi="${p.id}" data-rating="${map_cat.getRate(p.id)}">
                                                <input type="text" class="range" value="" name="range" style="border: 1px solid green"/>
                                                <span class="baloon_subtitle"><spring:message code="label.interested"></spring:message></span>
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
