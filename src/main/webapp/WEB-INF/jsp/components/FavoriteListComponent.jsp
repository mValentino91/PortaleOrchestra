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
        <script src="../../../dist/js/eneventform.js" type="text/javascript"></script>

        <!-- implementare il foreach dei preferiti per categorie-->
        <div class="paragrafo">
            <div class="favorite_container" style="min-height: 200px;">

                <c:if test="${empty map_cat.map.keySet()}">
                    <div class="no_favorites_result">
                        <spring:message code="label.no_favorites_presents"></spring:message>
                        </div>
                </c:if>
                
                <div class="sel-all-btn" style="float:right;">
                    <!--<input type="checkbox" id="sel-all" name="checkbox-all" class="css-checkbox" style="font-size: 11px;"/><label for="checkbox-all" class="css-label">Seleziona tutti</label>-->
                    <!--<input type="checkbox" id="sel-all"/> -->
                </div>
                
                <c:forEach var="cat" items="${map_cat.map.keySet()}">
                    
                    <div class="category_container">

                        <span class="category_label category_title" >${map_slug.get(cat)} <i class="expand_icon fa fa-chevron-down" style="font-size:12px;"></i></span>
                        
                        <div class="poi">

                            <c:forEach var="p" items="${map_cat.map.get(cat)}">
                                <div class="poi_preview_box">
                                    <i class="fa fa-times poi_preview_delete"  style="cursor:pointer; font-size:16px;" ></i>
                                    <div class="poi_preview_img">
                                        <img src="./dist/poi/img/${p.id}/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/>
                                        <!--<img src="./dist/poi/img/550885d0edc9635d04573597/cover.jpg" style="width:56px; height:56px; border-radius: 50%; margin-top:5px;"/>-->
                                    </div>
                                    <div class="poi_preview_content">

                                        <div class="poi_preview_text">
                                            <c:set var="pid" scope="session" value="${p.id}"/>

                                            <c:set var="noff" scope="session" value="${poi_offc.get(pid)}"/>
                                            <a href="./getPoi?id=${p.id}" target="_blank"><i class="fa fa-info-circle info" style="cursor:pointer; color: #2980B9; font-size:16px;" data-toggle="tooltip" data-original-title="Maggiori informazioni"></i></a>
                                            <c:if test="${noff > 0}">
                                                <i class="fa fa-credit-card" style="font-size:16px;" data-toggle="tooltip" data-original-title="Orchestra Card"></i>
                                            </c:if>
                                                <!--<input type="checkbox" name="checkboxG4" id="checkbox" class="sel css-checkbox" style="font-size: 11px;"/><label for="checkboxG4" class="css-label">Seleziona</label>-->
                                            <!--<input type="checkbox" class="sel"/>--->
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
                                        <hr/>
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
<script>
    $(function () {
        $('.fa-heart').tooltip();
        $('.fa-info-circle').tooltip();
        $('.fa-credit-card').tooltip();
    });
    
    
</script>
