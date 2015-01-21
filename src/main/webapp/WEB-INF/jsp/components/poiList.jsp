<%-- 
    Document   : poiList
    Created on : 20-gen-2015, 13.55.50
    Author     : Marco Valentino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="./dist/components/poiList/poiList.js"></script>
<style>
    #poiListComponent{
        background-color: transparent;
        position: absolute;
        right: 5px;
        top: 100px;
        bottom: 25px;
        width: 260px;
        overflow: auto;
        display: none;
    }
    .poiBox{
        background-color: white;
        border-bottom: 1px solid lightgray;
        padding-top: 10px;
        margin-left: 0;
    }
    .poiBox:hover{
        background-color: whitesmoke;
        cursor:pointer
    }
    .poiBoxShortDescription{
        color: gray;
        display: none;
    }
    .filterOnPoiList{
        z-index: 99999 !important;
        background-color: white;
        position: absolute;
        right: 5px;
        top: 55px;
        height: 40px;
        width: 260px;
        overflow: auto;
        box-shadow: 0px 10px 5px -10px black;
        padding: 5px;
    }
    #poiListComponent::-webkit-scrollbar-track {
        border-radius: 2px;
    }
    #poiListComponent::-webkit-scrollbar {
        width: 5px;
        background-color: #F7F7F7;
    }
    #poiListComponent::-webkit-scrollbar-thumb {
        border-radius: 10px;
        -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
        background-color: #BFBFBF;
    }
</style>
<div class="poiListContainer well">
    <div class="filterOnPoiList text-center">
        <div  class="btn-group" role="group" data-toggle="buttons">
            <label id="searchMapCheckButton" type="button" class="btn btn-default btn-sm" onclick="interactiveMap.searchHandler()">
                <input type="checkbox" autocomplete="off">
                <i class="fa fa-search"></i>
            </label>
            <button type="button" class="btn btn-default btn-sm" onclick="interactiveMap.anmHandler()">
                <i class="fa fa-bus"></i>
            </button>
            <button type="button" class="btn btn-default btn-sm" onclick="$('#poiListComponent').slideToggle();">
                <i class="glyphicon glyphicon-map-marker"></i>
            </button>
        </div>
    </div>
    <div id="poiListComponent">
        <c:forEach var = "poi" items = "${poiList}">
            <div id="${poi.id}" class="container-fluid poiBox" 
                 onmouseover="poiBoxDown('${poi.id}', '${poi.id}', '${poi.id}', true)"
                 onmouseout="poiBoxDown('${poi.id}', '${poi.id}', '${poi.id}', false)"
                 onmouseleave="poiBoxUp('${poi.id}','${poi.id}')">
                <div class="col-md-5"> 
                    <img class="img-responsive text-center"  style="height:40px;"src="./dist/poi/img/${poi.id}/cover.jpg" onError="this.src='./dist/img/notFound.png';" alt=""/>
                    <br>
                </div>
                <div class="col-md-7">
                    <p>
                        ${poi.name}
                    </p>
                </div>   
                <div class="col-md-12">
                    <p class="poiBoxShortDescription ${poi.id}">
                        <span style="color:gray; font-size: 11px;">${poi.address}</span>
                        <br><br>
                        ${poi.shortDescription}
                        <a href="./getPoi?id=${poi.id}">Altro...</a>
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script>initPoiList();</script>