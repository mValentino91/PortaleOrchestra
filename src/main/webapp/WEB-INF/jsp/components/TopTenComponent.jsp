<%-- 
    Document   : TopTenComponent
    Created on : 4-giu-2015, 9.13.21
    Author     : Alex
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="top10-row" class="row" style="height:100px; position: relative;">
    <div class="col-md-12 col-orc">

        <div class="top10-container">
            <div id="poi-prev" class="top_arrow_box top_arrow_left_box">
                <div class="top_arrow top_arrow_left">
                    <i class="fa fa-chevron-left"></i>
                </div>
            </div>
            <div id="poi-next" class="top_arrow_box top_arrow_right_box">
                <div class="top_arrow top_arrow_right">
                    <i class="fa fa-chevron-right"></i>
                </div>
            </div>                        
            <div id="top10-slider" class="owl-carousel owl-theme">
                <c:forEach var="toppoi" items="${toppois}">
                    <div style="width: 329px; float: left;">
                        <div class="box-orc">
                            <div class="box-elem component component-text" style="overflow: hidden; border: 0px solid green;">
                                <div class="top_poi_box">
                                    <div class="top_flag"></div>
                                    <a href="getPoi?id=${toppoi.id}"><div class="top_poi_img" style="background-image: url('dist/poi/img/${toppoi.id}/cover.jpg')"></div></a>
                                    <div class="top_poi_content">
                                        <div class="top_poi_title">
                                            <a href="getPoi?id=${toppoi.id}"> ${toppoi.name}</a>
                                        </div>
                                        <div class="top_poi_text">
                                            ${toppoi.shortDescription}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach> 

            </div>

        </div>
    </div>
</div>
