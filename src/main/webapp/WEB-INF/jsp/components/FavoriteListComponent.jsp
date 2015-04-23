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

        <h2>I TUOI PREFERITI</h2>
        <hr>
        <!-- implementare il foreach dei preferiti per categorie-->
        <div class="paragrafo">
            <div class="favorite-container">
                <c:forEach var="cat" items="${map_cat.map.keySet()}">
                    <div class="category-container">
                        <h3>${map_slug.get(cat)}</h3>
                        
                        <div class="poi">
                            <c:forEach var="p" items="${map_cat.map.get(cat)}">
                                <div class="row" style="margin-left:0px; padding-left: 20px; height:120px; margin-top: 20px; margin-bottom: 15px;">
                                    
                                    <div class="col-md-4" style="width:120px; height:inherit; border: 0px solid;"><img class="favorite_img" src="dist/img/06_cristo_velato.jpg"/></div>
                                    <div class="col-md-8" style="height:inherit; border: 0px solid;">
                                        <div class="col-md-1 col-md-offset-11" ></div>
                                        <div class="text">
                                        ${p.name}
                                            <i class="fa fa-trash-o" style="cursor:pointer; font-size:16px;"></i>
                                            
                                        </div>
                                        
                                        <div class="text">
                                            ${p.address}
                                        </div>
                                        <div class="fav_rating" data-rating="${map_cat.getRate(p.id)}">
                                            <input type="text" class="range" value="" name="range" style="border: 1px solid green"/>
                                            <span class="baloon_subtitle">...quanto sei interessato?</span>
                                        </div>	

                                    </div>

                                    <!--
                                    <div class="col-md-12 col-orc box-orc-half" style="background-color:#F69B7C;">
                                            <div class="col-md-4 col-orc">
                                                    <div class="box-orc">
                                                            <div class="tile" style="background-color:#fff;">
                                                                    ddd
                                                            </div>
                                                    </div>
                                            </div>	
                                        
                                            <div class="col-md-8 col-orc">
                                                    <div class="box-orc" style="background-color:#fff;">fff</div>
                                            </div>
                                        
                                        
                                    </div>
                                    -->
                                </div>
                                <!--
                                <div class="col-md-12 col-orc box-orc-half">
                                        <div class="col-md-4 col-orc">
                                                <div class="box-orc">
                                                                <div class="tile" style="background-color:#9FA8DA;"><img src="img/calendar2.png"></div>
                                                        </div>
                                        </div>

                                        <div class="col-md-8 col-orc">
                                                <div class="box-orc">
                                                        <div class="box-elem" style="background-color:#fff">
                                                                <p class="paragrafo" style="padding:5px;">
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus facilisis risus consectetur convallis fermentum. 
                                                    Nam sodales volutpat pharetra. Sed vulputate, quam et fermentum porta, libero enim ultrices mi, quis dignissim purus elit ut nisi. 
                                                    
                                                        </p>
                                                        </div>
                                                </div>
                                        </div>
                                </div>
                                -->



                                <!--
                                <div  class="col-md-12 col-orc box-orc-half">
                                    <div style="border:0px solid; background-color:blue;" class="col-md-4 col-orc">
                                        <div style="border:1px solid;"class="box-orc">
                                            <div style="border:1px solid;">img poi</div>
                                        </div>
                                    </div>
                               

                                    <div style="border:0px solid;" class="col-md-8 col-orc">
                                        <div style="border:0px solid; background-color:blue;" class="box-orc">
                                            <p class="paragrafo" style="background-color:#fff; padding:5px;">dddhhhhh</p>
                                        </div>
                                    </div>
                                </div>
                                -->
                            </c:forEach>

 
                        </div>
                    </div>
                    <hr>    
                </c:forEach>
            </div>
        </div>
    </div>
</article>
